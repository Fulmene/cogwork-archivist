package mtgcogwork.magic;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.HashMultiset;

public class Deck implements Iterable<Card> {

    private Multiset<Card> cards;

    public Deck() {
        this.cards = HashMultiset.create();
    }

    public Deck(Iterable<? extends Card> cards) {
        this.cards = HashMultiset.create(cards);
    }

    public int size() {
        return this.cards.size();
    }

    public void add(final Card card) {
        this.cards.add(card);
    }

    public int count(final Card card) {
        return this.cards.count(card);
    }

    public Stream<Card> stream() {
        return this.cards.stream();
    }

    @Override
    public Iterator<Card> iterator() {
        return this.cards.iterator();
    }

    public Multiset<Card> getCards() {
        return Multisets.unmodifiableMultiset(this.cards);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        List<Multiset.Entry<Card>> sortedEntry = new ArrayList<>(this.cards.entrySet());
        Collections.sort(sortedEntry, Deck::compareType);
        for (Multiset.Entry<Card> cardEntry : sortedEntry) {
            str.append(cardEntry.getCount());
            str.append(' ');
            str.append(cardEntry.getElement().getName());
            str.append('\n');
        }
        return str.toString();
    }

    private static final List<String> typeOrder = List.of("creature", "planeswalker", "instant", "sorcery", "artifact", "enchantment", "land");
    private static final int compareType(Multiset.Entry<Card> entry1, Multiset.Entry<Card> entry2) {
        Card card1 = entry1.getElement();
        Card card2 = entry2.getElement();
        for (String type : typeOrder) {
            boolean contain1 = card1.getTypes().contains(type);
            boolean contain2 = card2.getTypes().contains(type);
            if (contain1 && !contain2)
                return -1;
            else if (contain2 && !contain1)
                return 1;
        }
        int compareCMC = Integer.compare(card1.getConvertedManaCost(), card2.getConvertedManaCost());
        if (compareCMC != 0)
            return compareCMC;
        int compareCount = Integer.compare(entry2.getCount(), entry1.getCount());
        if (compareCount != 0)
            return compareCount;
        return card1.getName().compareToIgnoreCase(card2.getName());
    }
}
