package mtgcogwork.deckgenerator.metric;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import mtgcogwork.magic.Card;
import mtgcogwork.magic.CardPool;
import mtgcogwork.magic.Deck;
import mtgcogwork.util.FileUtil;

public final class CostEffectivenessMetric extends Metric {

    private Map<String, Double> costEffectivenessTable;

    public CostEffectivenessMetric(CardPool cardPool) {
        try {
            this.costEffectivenessTable = FileUtil.readCsv(Metric.class.getResource("costeffectiveness.csv")).readAll().stream().
                filter(l -> cardPool.contains(l.get(0))).
                collect(Collectors.toMap(l -> l.get(0), l -> Double.parseDouble(l.get(1))));
        } catch (IOException e) {
            throw new RuntimeException(e);
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
