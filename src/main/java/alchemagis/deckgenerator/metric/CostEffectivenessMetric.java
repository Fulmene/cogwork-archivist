package alchemagis.deckgenerator.metric;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.MappingIterator;

import alchemagis.magic.Card;
import alchemagis.magic.Deck;
import alchemagis.util.FileUtil;

public final class CostEffectivenessMetric extends Metric {

    private Map<String, Double> costEffectivenessTable;

    public CostEffectivenessMetric(Iterable<String> setNames) {
        this.metricWeight = 0.2;
        this.costEffectivenessTable = new HashMap<>();
        for (String name : setNames) {
            MappingIterator<Map<String, String>> it = FileUtil.readCsv(Metric.class.getResource(name + ".csv"));
            while (it.hasNext()) {
                Map<String, String> values = it.next();
                this.costEffectivenessTable.put(values.get("Card Name"), Double.parseDouble(values.get("Cost Effectiveness")));
            }
        }
    }

    @Override
    protected double getRawMetricScore(Deck deck, Card card) {
        return this.getCostEffectiveness(card);
    }

    private double getCostEffectiveness(Card card) {
        Double costEffectiveness = this.costEffectivenessTable.get(card.getName());
        if (costEffectiveness == null)
            return 0.0;
        else
            return costEffectiveness;
    }

}
