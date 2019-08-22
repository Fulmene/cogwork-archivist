package alchemagis.magic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import com.google.common.collect.Multiset;
import com.google.common.collect.HashMultiset;

public class Deck {

    private Multiset<Card> cards;
    private List<Card> deckList;
    private List<Integer> manaCurve;

    public Deck() {
        this.cards = HashMultiset.create();
        this.deckList = new ArrayList<>();
        this.manaCurve = new ArrayList<>();
    }

    public Deck(Iterable<? extends Card> cards) {
        this.cards = HashMultiset.create(cards);
        this.deckList = new ArrayList<>(this.cards);
        this.manaCurve = new ArrayList<>();
        for (Card card : cards)
            this.incrementCurve(card.getConvertedManaCost());
    }

    public int size() {
        return this.cards.size();
    }

    public void add(final Card card) {
        this.cards.add(card);
        this.deckList.add(card);
        if (!card.getTypes().contains("land"))
            this.incrementCurve(card.getConvertedManaCost());
    }

    public int count(final Card card) {
        return this.cards.count(card);
    }

    public Stream<Card> stream() {
        return this.cards.stream();
    }

    public List<Card> getDeckList() {
        return this.deckList;
    }

    public List<Integer> getManaCurve() {
        return this.manaCurve;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Multiset.Entry<Card> cardEntry : this.cards.entrySet()) {
            str.append(cardEntry.getCount());
            str.append(' ');
            str.append(cardEntry.getElement().getName());
            str.append('\n');
        }
        return str.toString();
    }

    private void incrementCurve(int cmc) {
        while (this.manaCurve.size() <= cmc)
            this.manaCurve.add(0);
        this.manaCurve.set(cmc, this.manaCurve.get(cmc)+1);
    }

}
