package alchemagis.magic;

import java.util.List;

import alchemagis.deckgenerator.synergy.Synergy;

public class Card {

    private String name;

    private double costEffectiveness;
    private List<Synergy> synergies;

    public String getName() {
        return this.name;
    }

    public double getCostEffectiveness() {
        return this.costEffectiveness;
    }

    public List<Synergy> getSynergies() {
        return this.synergies;
    }

}
