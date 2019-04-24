package alchemagis.magic;

import java.util.List;
import java.util.stream.Stream;

import alchemagis.util.RandomUtil;

public final class CardPool {

    private List<Card> cards;

    // TODO Add card pool loading

    private CardPool(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public Stream<Card> stream() {
        return this.cards.stream();
    }

    public Card getRandomCard() {
        return cards.get(RandomUtil.getRandomInt(cards.size()));
    }

}
