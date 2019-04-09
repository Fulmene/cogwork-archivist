package alchemagis.deckgenerator.metric.synergy;

import alchemagis.magic.Card;

public abstract class Synergy {
    public abstract double getSynergyScore(Card card);
}
