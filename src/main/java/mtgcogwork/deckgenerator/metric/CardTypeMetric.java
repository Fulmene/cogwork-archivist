package mtgcogwork.deckgenerator.metric;

import java.util.ArrayList;
import java.util.List;

import mtgcogwork.magic.Card;
import mtgcogwork.magic.Deck;
import mtgcogwork.util.NumberUtil;

public final class CardTypeMetric extends Metric {

    private List<Integer> cardTypeCount;
    private double maxDistance;
    private List<Integer> deckTypeCount;
    private List<Double> score;

    public CardTypeMetric(List<Integer> cardTypeCount) {
        this.cardTypeCount = cardTypeCount;
        this.maxDistance = NumberUtil.euclideanDistance(cardTypeCount, List.of());
    }

    @Override
    public void preprocessDeck(Deck deck) {
        this.deckTypeCount = new ArrayList<>();
        this.deckTypeCount.add(0);
        this.deckTypeCount.add(0);
        this.deckTypeCount.add(0);
        deck.forEach(c -> {
            int category = getCategory(c);
            this.deckTypeCount.set(category, this.deckTypeCount.get(category)+1);
        });

        this.score = new ArrayList<>();
        this.score.add(null);
        this.score.add(null);
        this.score.add(null);
    }

    @Override
    protected double getRawMetricScore(Deck deck, Card card) {
        int category = getCategory(card);
        if (this.score.get(category) == null) {
            List<Integer> count = new ArrayList<>(this.deckTypeCount);
            count.set(category, count.get(category)+1);
            this.score.set(category, (this.maxDistance - NumberUtil.euclideanDistance(count, this.cardTypeCount)) / this.maxDistance);
        }
        return this.score.get(category);
    }

    private static int getCategory(Card card) {
        if (card.getTypes().contains("creature"))
            return 0;
        else if (card.getTypes().contains("land"))
            return 1;
        else
            return 2;
    }

}
