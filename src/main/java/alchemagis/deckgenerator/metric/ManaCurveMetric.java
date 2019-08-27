package alchemagis.deckgenerator.metric;

import java.util.ArrayList;
import java.util.List;

import alchemagis.magic.Card;
import alchemagis.magic.Deck;
import alchemagis.util.NumberUtil;

public final class ManaCurveMetric extends Metric {

    private List<Integer> manaCurve;
    private List<Integer> deckCurve;
    private List<Double> score;
    private Double landScore;

    public ManaCurveMetric(List<Integer> manaCurve) {
        this.manaCurve = manaCurve;
    }

    @Override
    public void preprocessDeck(Deck deck) {
        this.deckCurve = new ArrayList<>();
        deck.stream().forEach(c -> incrementCurve(this.deckCurve, c));
        this.score = new ArrayList<>();
        this.landScore = -NumberUtil.positiveEuclideanDistance(this.deckCurve, this.manaCurve);
    }

    @Override
    protected double getRawMetricScore(Deck deck, Card card) {
        if (card.getTypes().contains("land"))
            return this.landScore;
        else {
            List<Integer> curve = new ArrayList<>(this.deckCurve);
            incrementCurve(curve, card);
            int cmc = card.getConvertedManaCost();
            while (this.score.size() <= cmc)
                this.score.add(null);
            if (this.score.get(cmc) == null)
                this.score.set(cmc, -NumberUtil.positiveEuclideanDistance(curve, this.manaCurve));
            return this.score.get(cmc);
        }
    }

    private static void incrementCurve(List<Integer> curve, Card card) {
        if (!card.getTypes().contains("land")) {
            int cmc = card.getConvertedManaCost();
            while (curve.size() <= cmc)
                curve.add(0);
            curve.set(cmc, curve.get(cmc)+1);
        }
    }

}
