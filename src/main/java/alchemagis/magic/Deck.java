package alchemagis.magic;

import java.util.List;
import java.util.stream.Stream;

public class Deck {

    private List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public int size() {
        return this.cards.size();
    }

    public void add(final Card card) {
        this.cards.add(card);
    }

    public Stream<Card> stream() {
        return this.cards.stream();
    }

}
