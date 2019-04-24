package alchemagis.deckgenerator.metric.synergy;

import java.util.ArrayList;
import java.util.List;

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
        // TODO stub
        return new ArrayList<>();
    }

    public final double getScore(Card card) {
        return this.synergyWeight * this.getRawScore(card);
    }

    public abstract double getRawScore(Card card);

}
