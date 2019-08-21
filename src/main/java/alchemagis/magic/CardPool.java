package alchemagis.magic;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import alchemagis.util.FileUtil;
import alchemagis.util.NumberUtil;

public final class CardPool {

    private List<Card> cards;

    public static CardPool loadCardPool(URL ...setURLs) {
        return new CardPool(Arrays.stream(setURLs).map(FileUtil::readMtgJsonSet).toArray(CardSet[]::new));
    }

    public CardPool(CardSet ...sets) {
        Set<Card> cardPoolSet = Arrays.stream(sets).
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
