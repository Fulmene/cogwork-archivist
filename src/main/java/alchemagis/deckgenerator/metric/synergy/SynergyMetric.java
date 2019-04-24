package alchemagis.deckgenerator.metric.synergy;

import java.util.List;
import java.util.Map;
import java.util.stream.DoubleStream;

import alchemagis.deckgenerator.metric.Metric;
import alchemagis.magic.Card;
import alchemagis.magic.Deck;

public final class SynergyMetric extends Metric {

    private Map<String, List<Synergy>> synergyTable;

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
