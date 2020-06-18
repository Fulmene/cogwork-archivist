package mtgcogwork.magic;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;

import mtgcogwork.magic.quality.MagicCardQuality;
import mtgcogwork.util.FileUtil;

public class CardPool {

    protected final Map<String, Card> cardNameMap;
    protected final Multiset<Card> cards;
    protected final Map<Card, List<MagicCardQuality>> cardQualityTable;

    public CardPool(Multiset<Card> cards) {
        this.cardNameMap = cards.elementSet().stream().collect(Collectors.toMap(Card::getName, Function.identity()));
        this.cards = Multisets.unmodifiableMultiset(cards);
        this.cardQualityTable = generateQualityTable(this.cardNameMap);
    }

    private static Map<Card, List<MagicCardQuality>> generateQualityTable(Map<String, Card> cardNameMap) {
        try {
            return FileUtil.readCsvToList(MagicCardQuality.class.getResource("quality.csv")).readAll().stream().
                filter(l -> cardNameMap.containsKey(l.get(0))).
                collect(Collectors.toMap(l -> cardNameMap.get(l.get(0)), l -> MagicCardQuality.getCardQualityList(cardNameMap.get(l.get(0)), l.get(1))));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
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

    public List<MagicCardQuality> getCardQuality(String name) {
        return this.cardQualityTable.get(this.cardNameMap.get(name));
    }

    public List<MagicCardQuality> getCardQuality(Card card) {
        return this.cardQualityTable.getOrDefault(card, List.of());
    }

}
