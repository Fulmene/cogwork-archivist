package alchemagis.magic;

import java.util.List;

public final class CardPool {

    private List<Card> cards;

    // TODO Add card pool loading

    private CardPool(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return this.cards;
    }

}
