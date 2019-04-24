package alchemagis.deckgenerator.metric;

import java.util.Map;

import alchemagis.magic.Card;
import alchemagis.magic.Deck;

public class CostEffectivenessMetric extends Metric {

    private Map<String, Double> costEffectivenessTable;

    @Override
    protected double getRawMetricScore(Deck deck, Card card) {
        return this.getCostEffectiveness(card);
    }

    private double getCostEffectiveness(Card card) {
        return this.costEffectivenessTable.get(card.getName());
    }

}
