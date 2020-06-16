package mtgcogwork.magic;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;

import mtgcogwork.magic.quality.MagicCardQualityList;

public class CardPool {

    protected final Map<String, Card> cardNameMap;
    protected final Multiset<Card> cards;
    protected final Map<Card, MagicCardQualityList> cardQualityTable;

    public CardPool(Multiset<Card> cards) {
        this.cardNameMap = cards.elementSet().stream().collect(Collectors.toMap(Card::getName, Function.identity()));
        this.cards = Multisets.unmodifiableMultiset(cards);
        this.cardQualityTable = cards.elementSet().stream().collect(Collectors.toUnmodifiableMap(Function.identity(), MagicCardQualityList::new));
    }

    public Set<Card> getCards() {
        return this.cards.elementSet();
    }

    public Card getCard(String name) {
        return this.cardNameMap.get(name);
    }

    public boolean contains(String name) {
        return this.cardNameMap.containsKey(name);
    }

    public boolean contains(Card card) {
        return this.cards.contains(card);
    }

    public int count(String name) {
        return this.cards.count(this.cardNameMap.get(name));
    }

    public int count(Card card) {
        return this.cards.count(card);
    }

    public MagicCardQualityList getCardQuality(String name) {
        return this.cardQualityTable.get(this.cardNameMap.get(name));
    }

    public MagicCardQualityList getCardQuality(Card card) {
        return this.cardQualityTable.getOrDefault(card, MagicCardQualityList.NONE);
    }

}
