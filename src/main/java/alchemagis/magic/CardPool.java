package alchemagis.magic;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Set<Card> cardPoolSet = sets.stream().
            map(s -> s.getCards().stream()).
            flatMap(Function.identity()).
            collect(Collectors.toSet());
        this.cards = List.copyOf(cardPoolSet);
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public Card getCard(String name) {
        return this.cards.stream().filter(c -> c.getName().equalsIgnoreCase(name)).findAny().get();
    }

    public Stream<Card> stream() {
        return this.cards.stream();
    }

    public Card getRandomCard() {
        return this.cards.get(NumberUtil.getRandomInt(cards.size()));
    }

}
