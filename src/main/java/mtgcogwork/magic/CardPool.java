package mtgcogwork.magic;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;

import mtgcogwork.magic.quality.MagicCardQuality;
import mtgcogwork.util.FileUtil;

public final class CardPool {

    private final Map<String, Card> cardNameMap;
    private final Multiset<Card> cards;
    private final Map<Card, MagicCardQuality> cardQualityTable;

    public static CardPool loadCardPool(Collection<String> setNames) {
        return createConstructedCardPool(setNames.stream().
            map(name -> {
                try {
                    return new URL("file:///home/adelaide/Downloads/AllSetFiles/" + name + ".json");
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e); }
            }).
            map(FileUtil::readMtgJsonSet).
            collect(Collectors.toList()));
    }

    public static CardPool createConstructedCardPool(Collection<CardSet> sets) {
        Multiset<Card> cards = sets.stream().
            map(s -> s.getCards().stream()).
            flatMap(Function.identity()).
            distinct().
            collect(Multisets.toMultiset(Function.identity(), MagicConstants::getMaxCopies, HashMultiset::create));
        return new CardPool(cards);
    }

    public CardPool(Multiset<Card> cards) {
        this.cardNameMap = cards.elementSet().stream().collect(Collectors.toMap(Card::getName, Function.identity()));
        this.cards = Multisets.unmodifiableMultiset(cards);
        this.cardQualityTable = cards.elementSet().stream().collect(Collectors.toUnmodifiableMap(Function.identity(), MagicCardQuality::new));
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

    public MagicCardQuality getCardQuality(String name) {
        return this.cardQualityTable.get(this.cardNameMap.get(name));
    }

    public MagicCardQuality getCardQuality(Card card) {
        MagicCardQuality quality = this.cardQualityTable.get(card);
        if (quality == null)
            return MagicCardQuality.NONE;
        else
            return this.cardQualityTable.get(card);
    }

}
