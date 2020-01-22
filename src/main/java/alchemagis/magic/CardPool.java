package alchemagis.magic;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import alchemagis.magic.quality.MagicCardQuality;
import alchemagis.util.FileUtil;
import alchemagis.util.NumberUtil;

public final class CardPool {

    private List<Card> cards;

    public static CardPool loadCardPool(Collection<String> setNames) {
        return new CardPool(setNames.stream().
            map(name -> {
                try {
                    return new URL("file:///home/adelaide/Downloads/AllSetFiles/" + name + ".json");
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e); }
            }).
            map(FileUtil::readMtgJsonSet).
            collect(Collectors.toList()));
    }

    public CardPool(Collection<CardSet> sets) {
        // Create a TreeSet to sort and uniq the cards from the sets
        Set<Card> cardPoolSet = sets.stream().
            map(s -> s.getCards().stream()).
            flatMap(Function.identity()).
            collect(Collectors.toCollection(TreeSet::new));
        this.cards = List.copyOf(cardPoolSet);
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public Card getCard(String name) {
        // Binary search since the cards are sorted by name in the constructor
        int first = 0;
        int last = this.cards.size();
        while (first < last) {
            int mid = first + (last-first)/2;
            Card target = cards.get(mid);
            int comparison = name.compareToIgnoreCase(target.getName());
            if (comparison < 0)
                last = mid;
            else if (comparison > 0)
                first = mid+1;
            else
                return target;
        }
        throw new NoSuchElementException("No cards named " + name + "in the card pool");
    }

    public Stream<Card> stream() {
        return this.cards.stream();
    }

    public Card getRandomCard() {
        return this.cards.get(NumberUtil.getRandomInt(cards.size()));
    }

}
