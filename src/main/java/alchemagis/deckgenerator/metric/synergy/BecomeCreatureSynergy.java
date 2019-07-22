package alchemagis.deckgenerator.metric.synergy;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;

public class BecomeCreatureSynergy extends Synergy {

    public static final BecomeCreatureSynergy INSTANCE = new BecomeCreatureSynergy();

    @Override
    protected double getRawScore(SynergyMetric metric, Card card) {
        return 0.0;
    }

}
