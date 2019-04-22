package alchemagis.deckgenerator.synergy;

import alchemagis.magic.Card;

public abstract class Synergy {

    private final double synergyWeight;

    protected Synergy() {
        this.synergyWeight = 1.0;
    }

    protected Synergy(double synergyWeight) {
        this.synergyWeight = synergyWeight;
    }

    public static final double getSynergyScore(Card card1, Card card2) {
        return card1.getSynergies().stream().
            mapToDouble(s -> s.getScore(card2)).
            sum();
    }

    public final double getScore(Card card) {
        return this.synergyWeight * this.getRawScore(card);
    }

    public abstract double getRawScore(Card card);

}
