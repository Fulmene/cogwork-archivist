package alchemagis.magic;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import alchemagis.util.RandomUtil;

public final class CardPool {

    private Set<Card> cards;

    // TODO Add card pool loading

    public CardPool(CardSet ...sets) {
        this.cards = Arrays.stream(sets).
            map(s -> s.getCards().stream()).
            flatMap(Function.identity()).
            collect(Collectors.toSet());
    }

    public Set<Card> getCards() {
        return this.cards;
    }

    public Stream<Card> stream() {
        return this.cards.stream();
    }

    public Card getRandomCard() {
        return null;
    }

}
