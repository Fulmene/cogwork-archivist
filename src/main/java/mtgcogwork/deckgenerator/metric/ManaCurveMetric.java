package mtgcogwork.deckgenerator.metric;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mtgcogwork.magic.Card;
import mtgcogwork.magic.Deck;
import mtgcogwork.util.NumberUtil;

public final class ManaCurveMetric extends Metric {

    private static final int MAX_MEANINGFUL_CMC = 6;

    private final List<Integer> manaCurve;
    private final double maxDistance;
    private List<Integer> deckCurve;
    private List<Double> score;
    private Double landScore;

    public ManaCurveMetric(List<Integer> manaCurve) {
        this(manaCurve, 1.0);
    }

    public ManaCurveMetric(List<Integer> manaCurve, double metricWeight) {
        super(metricWeight);
        this.manaCurve = manaCurve;
        this.maxDistance = NumberUtil.euclideanDistance(manaCurve, List.of());
    }

    @Override
    public void preprocessDeck(Deck deck) {
        List<Integer> deckCurve = new ArrayList<>();
        for (int i = 0; i <= MAX_MEANINGFUL_CMC; i++)
            deckCurve.add(0);
        deck.forEach(c -> incrementCurve(deckCurve, c));
        this.deckCurve = Collections.unmodifiableList(deckCurve);

        List<Double> score = new ArrayList<>();
        for (int cmc = 0; cmc <= MAX_MEANINGFUL_CMC; cmc++) {
            List<Integer> newCurve = new ArrayList<>(this.deckCurve);
            incrementCurve(newCurve, cmc);
            score.add((this.maxDistance - NumberUtil.euclideanDistance(newCurve, this.manaCurve)) / this.maxDistance);
        }
        this.score = Collections.unmodifiableList(score);

        this.landScore = (this.maxDistance - NumberUtil.euclideanDistance(this.deckCurve, this.manaCurve)) / this.maxDistance;
    }

    @Override
    protected double getRawMetricScore(Deck deck, Card card) {
        if (card.getTypes().contains("land"))
            return this.landScore;
        else
            return this.score.get(Math.min(MAX_MEANINGFUL_CMC, card.getConvertedManaCost()));
    }

    private static void incrementCurve(List<Integer> curve, Card card) {
        if (!card.getTypes().contains("land"))
            incrementCurve(curve, Math.min(MAX_MEANINGFUL_CMC, card.getConvertedManaCost()));
    }

    private static void incrementCurve(List<Integer> curve, int cmc) {
        while (curve.size() <= cmc)
            curve.add(0);
        curve.set(cmc, curve.get(cmc)+1);
    }

}
