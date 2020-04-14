package alchemagis.deckgenerator.metric;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.DoubleStream;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.MappingIterator;

import alchemagis.deckgenerator.metric.synergy.Synergy;
import alchemagis.deckgenerator.metric.synergy.SynergyParser;
import alchemagis.magic.Card;
import alchemagis.magic.CardPool;
import alchemagis.magic.Deck;
import alchemagis.util.FileUtil;

public final class SynergyMetric extends Metric {

    private CardPool cardPool;
    private Map<String, List<Synergy>> synergyTable;

    public SynergyMetric(CardPool cardPool) {
        try {
            this.cardPool = cardPool;
            this.synergyTable = FileUtil.readCsv(Metric.class.getResource("synergy.csv")).readAll().stream().
                filter(l -> cardPool.contains(l.get(0))).
                collect(Collectors.toMap(l -> l.get(0), l -> SynergyParser.parseSynergies(l.get(1))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected double getRawMetricScore(Deck deck, Card card) {
        return deck.stream().
            flatMapToDouble(c ->
                DoubleStream.concat(
                    this.getSynergyList(card).stream().
                        mapToDouble(s -> s.getScore(cardPool.getCardQuality(c))),
                    this.getSynergyList(c).stream().
                        mapToDouble(s -> s.getScore(cardPool.getCardQuality(card))))).
            sum(); // / deck.size() / Math.max(1, this.getSynergyList(card).size());
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
