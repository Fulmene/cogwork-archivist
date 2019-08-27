package alchemagis.deckgenerator.metric;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.MappingIterator;

import alchemagis.deckgenerator.metric.synergy.Synergy;
import alchemagis.magic.Card;
import alchemagis.magic.Deck;
import alchemagis.util.FileUtil;

public final class SynergyMetric extends Metric {

    private Map<String, List<Synergy>> synergyTable;

    public SynergyMetric(Iterable<String> setNames) {
        this.synergyTable = new HashMap<>();
        for (String name : setNames) {
            MappingIterator<Map<String, String>> it = FileUtil.readCsv(Metric.class.getResource(name + ".csv"));
            while (it.hasNext()) {
                Map<String, String> values = it.next();
                this.synergyTable.put(values.get("Card Name"), Synergy.parseSynergies(values.get("Synergies")));
            }
        }
    }

    @Override
    protected double getRawMetricScore(Deck deck, Card card) {
        return deck.stream().
            flatMapToDouble(c ->
                DoubleStream.concat(
                    this.getSynergyList(card).stream().
                        mapToDouble(s -> s.getScore(this, c)),
                    this.getSynergyList(c).stream().
                        mapToDouble(s -> s.getScore(this, card)))).
            sum();
    }

    public List<Synergy> getSynergyList(Card card) {
        List<Synergy> synergyList = this.synergyTable.get(card.getName());
        if (synergyList == null)
            return Collections.emptyList();
        else
            return synergyList;
    }

    public<T extends Synergy> Stream<T> classFilterStream(Class<T> clazz, Card card) {
        return this.getSynergyList(card).stream().
            filter(clazz::isInstance).
            map(clazz::cast);
    }

}
