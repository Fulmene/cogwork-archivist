package mtgcogwork.deckgenerator.metric;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mtgcogwork.magic.Card;
import mtgcogwork.magic.Deck;
import mtgcogwork.util.NumberUtil;

public final class CardTypeMetric extends Metric {

    private static enum Category {
        CREATURE(0), LAND(1), OTHER(2);

        private int index;

        private Category(int index) {
            this.index = index;
        }
    };

    private static Category getCategory(Card card) {
        if (card.getTypes().contains("creature"))
            return Category.CREATURE;
        else if (card.getTypes().contains("land"))
            return Category.LAND;
        else
            return Category.OTHER;
    }

    private final List<Integer> cardTypeCount;
    private final double maxDistance;
    private List<Integer> deckTypeCount;
    private Map<Category, Double> score;

    public CardTypeMetric(List<Integer> cardTypeCount) {
        this.cardTypeCount = cardTypeCount;
        this.maxDistance = NumberUtil.euclideanDistance(cardTypeCount, List.of());
    }

    @Override
    public void preprocessDeck(Deck deck) {
        List<Integer> deckTypeCount = new ArrayList<>();
        deckTypeCount.add(0);
        deckTypeCount.add(0);
        deckTypeCount.add(0);
        deck.forEach(c -> {
            int index = getCategory(c).index;
            deckTypeCount.set(index, deckTypeCount.get(index)+1);
        });
        this.deckTypeCount = Collections.unmodifiableList(deckTypeCount);

        Map<Category, Double> score = new HashMap<>();
        for (Category c : Category.values()) {
            List<Integer> newCount = new ArrayList<>(this.deckTypeCount);
            newCount.set(c.index, newCount.get(c.index)+1);
            score.put(c, (this.maxDistance - NumberUtil.euclideanDistance(newCount, this.cardTypeCount)) / this.maxDistance);
        }
        this.score = Collections.unmodifiableMap(score);
    }

    @Override
    protected double getRawMetricScore(Deck deck, Card card) {
        return this.score.get(getCategory(card));
    }

}
