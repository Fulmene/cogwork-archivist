package alchemagis.deckgenerator.metric;

import java.util.List;
import java.util.function.Function;

import alchemagis.deckgenerator.metric.synergy.Synergy;
import alchemagis.magic.Card;
import alchemagis.magic.Deck;

public final class SynergyMetric extends Metric {

    @Override
    public double getRawMetricScore(Deck deck, Card card) {
        return deck.stream().
            mapToDouble(c -> Synergy.getSynergyScore(c, card)).
            sum();
    }

}
