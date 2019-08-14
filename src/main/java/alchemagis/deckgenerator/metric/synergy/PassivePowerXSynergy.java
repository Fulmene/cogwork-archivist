package alchemagis.deckgenerator.metric.synergy;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;

public class PassivePowerXSynergy extends Synergy {

    public static final PassivePowerXSynergy INSTANCE = new PassivePowerXSynergy();

    @Override
    protected double getRawScore(SynergyMetric metric, Card card) {
        return card.getPower();
    }

}
