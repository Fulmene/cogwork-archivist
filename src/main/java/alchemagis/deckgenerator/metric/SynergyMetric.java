package alchemagis.deckgenerator.metric;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.DoubleStream;

import com.fasterxml.jackson.databind.MappingIterator;

import alchemagis.deckgenerator.metric.synergy.Synergy;
import alchemagis.magic.Card;
import alchemagis.magic.Deck;
import alchemagis.util.FileUtil;

public final class SynergyMetric extends Metric {

    private Map<String, List<Synergy>> synergyTable;

    public SynergyMetric(File tableFile) {
        this.synergyTable = new HashMap<>();
        MappingIterator<Map<String, String>> it = FileUtil.readCsvFile(tableFile);
        while (it.hasNext()) {
            Map<String, String> values = it.next();
            this.synergyTable.put(values.get("Card Name"), Synergy.parseSynergies(values.get("Synergies")));
        }
    }

    @Override
    protected double getRawMetricScore(Deck deck, Card card) {
        return deck.stream().
            flatMapToDouble(c ->
                DoubleStream.concat(
                    this.getSynergyList(card).stream().
                        mapToDouble(s -> s.getScore(c)),
                    this.getSynergyList(c).stream().
                        mapToDouble(s -> s.getScore(card)))).
            sum();
    }

    private List<Synergy> getSynergyList(Card card) {
        return this.synergyTable.get(card.getName());
    }

}
