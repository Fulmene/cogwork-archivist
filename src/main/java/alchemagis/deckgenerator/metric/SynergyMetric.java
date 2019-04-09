package alchemagis.deckgenerator.metric;

import java.util.List;
import java.util.function.Function;

import alchemagis.deckgenerator.Deck;
import alchemagis.deckgenerator.metric.synergy.Synergy;

public final class SynergyMetric extends Metric {

    private List<Synergy> synergies;

    @Override
    public double getMetricScore(Deck deck) {
        return synergies.stream().
            map(s -> deck.getCards().stream().
                mapToDouble(c -> s.getSynergyScore(c))).
            flatMapToDouble(Function.identity()).
            sum();
    }

}
