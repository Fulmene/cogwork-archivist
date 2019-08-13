package alchemagis.magic;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Deck {

    private List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getList() {
        return List.copyOf(cards);
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

    public void sort() {
        // TODO sort by card type
        /*
        Collections.sort(
            cards,
            (Card c1, Card c2) -> {
                int cardTypeCompareResult = cardTypeCompare(
            });
        */
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Card card : cards) {
            str.append(card.getName());
            str.append('\n');
        }
        return str.toString();
    }

}
