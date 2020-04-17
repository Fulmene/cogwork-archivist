package mtgcogwork.deckgenerator.metric;

import java.util.ArrayList;
import java.util.List;

import mtgcogwork.magic.Card;
import mtgcogwork.magic.Deck;
import mtgcogwork.magic.MagicConstants;
import mtgcogwork.util.NumberUtil;

public final class ManaCurveMetric extends Metric {

    private List<Integer> manaCurve;
    private double maxDistance;
    private List<Integer> deckCurve;
    private List<Double> score;
    private Double landScore;

    public ManaCurveMetric(List<Integer> manaCurve) {
        this.manaCurve = manaCurve;
        this.maxDistance = NumberUtil.euclideanDistance(manaCurve, List.of());
    }

    @Override
    public void preprocessDeck(Deck deck) {
        this.deckCurve = new ArrayList<>();
        deck.forEach(c -> incrementCurve(this.deckCurve, c));
        this.score = new ArrayList<>();
        this.landScore = (this.maxDistance - NumberUtil.euclideanDistance(this.deckCurve, this.manaCurve)) / this.maxDistance;
    }

    @Override
    protected double getRawMetricScore(Deck deck, Card card) {
        if (card.getTypes().contains("land"))
            return this.landScore;
        else {
            List<Integer> curve = new ArrayList<>(this.deckCurve);
            incrementCurve(curve, card);
            int cmc = Math.min(MagicConstants.MAX_MEANINGFUL_CMC, card.getConvertedManaCost());
            while (this.score.size() <= cmc)
                this.score.add(null);
            if (this.score.get(cmc) == null)
                this.score.set(cmc, (this.maxDistance - NumberUtil.euclideanDistance(curve, this.manaCurve)) / this.maxDistance);
            return this.score.get(cmc);
        }
    }

    private static void incrementCurve(List<Integer> curve, Card card) {
        if (!card.getTypes().contains("land")) {
            int cmc = Math.min(MagicConstants.MAX_MEANINGFUL_CMC, card.getConvertedManaCost());
            while (curve.size() <= cmc)
                curve.add(0);
            curve.set(cmc, curve.get(cmc)+1);
        }
    }

}
