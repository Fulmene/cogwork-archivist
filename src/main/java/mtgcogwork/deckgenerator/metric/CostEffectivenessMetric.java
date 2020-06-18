package mtgcogwork.deckgenerator.metric;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Map;
import java.util.stream.Collectors;

import mtgcogwork.magic.Card;
import mtgcogwork.magic.CardPool;
import mtgcogwork.magic.Deck;
import mtgcogwork.util.FileUtil;

public final class CostEffectivenessMetric extends Metric {

    private final Map<String, Double> costEffectivenessTable;

    public CostEffectivenessMetric(CardPool cardPool) {
        try {
            this.costEffectivenessTable = FileUtil.readCsvToList(CostEffectivenessMetric.class.getResource("costeffectiveness.csv")).readAll().stream().
                filter(l -> cardPool.contains(l.get(0))).
                collect(Collectors.toMap(l -> l.get(0), l -> Double.parseDouble(l.get(1))));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    protected double getRawMetricScore(Deck deck, Card card) {
        return this.getCostEffectiveness(card);
    }

    private double getCostEffectiveness(Card card) {
        return this.costEffectivenessTable.getOrDefault(card.getName(), 0.0);
    }

}
