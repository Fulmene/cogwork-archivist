package alchemagis.deckgenerator.metric;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.MappingIterator;

import alchemagis.magic.Card;
import alchemagis.magic.Deck;
import alchemagis.util.FileUtil;

public final class CostEffectivenessMetric extends Metric {

    private Map<String, Double> costEffectivenessTable;

    public CostEffectivenessMetric(URL ...tableURLs) {
        this.costEffectivenessTable = new HashMap<>();
        for (URL tableURL : tableURLs) {
            MappingIterator<Map<String, String>> it = FileUtil.readCsv(tableURL);
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
        return this.costEffectivenessTable.get(card.getName());
    }

}
