package alchemagis.deckgenerator.metric;

import alchemagis.magic.Card;
import alchemagis.magic.Deck;

public class CostEffectivenessMetric extends Metric {

    @Override
    protected double getRawMetricScore(Deck deck, Card card) {
        return card.getCostEffectiveness();
    }

}
