package alchemagis.deckgenerator.metric;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import alchemagis.magic.Card;
import alchemagis.magic.Deck;
import alchemagis.util.NumberUtil;

public final class ManaCurveMetric extends Metric {

    private List<Integer> manaCurve;

    public ManaCurveMetric(List<Integer> manaCurve) {
        this.manaCurve = manaCurve;
    }

    @Override
    protected double getRawMetricScore(Deck deck, Card card) {
        List<Integer> deckCurve = new ArrayList<>(deck.getManaCurve());
        if (!card.getTypes().contains("land")) {
            int cmc = card.getConvertedManaCost();
            while (deckCurve.size() <= cmc)
                deckCurve.add(0);
            deckCurve.set(cmc, deckCurve.get(cmc)+1);
        }
        return -NumberUtil.positiveEuclideanDistance(deckCurve, this.manaCurve);
    }

}
