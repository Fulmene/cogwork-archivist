package alchemagis.deckgenerator.metric.synergy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;

public abstract class Synergy {

    private final double synergyWeight;

    protected Synergy() {
        this.synergyWeight = 1.0;
    }

    protected Synergy(double synergyWeight) {
        this.synergyWeight = synergyWeight;
    }

    public static List<Synergy> parseSynergies(String synergiesString) {
        return Arrays.stream(synergiesString.split("/")).
            map(Synergy::parseSingleSynergy).
            collect(Collectors.toUnmodifiableList());
    }

    private static Synergy parseSingleSynergy(String synergy) {
        // TODO stub
        return null;
    }

    public final double getScore(SynergyMetric metric, Card card) {
        return this.synergyWeight * this.getRawScore(metric, card);
    }

    protected abstract double getRawScore(SynergyMetric metric, Card card);

}
