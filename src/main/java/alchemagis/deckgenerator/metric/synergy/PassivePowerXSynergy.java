package alchemagis.deckgenerator.metric.synergy;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;
import alchemagis.magic.MagicConstants;

public class PassivePowerXSynergy extends Synergy {

    public static final PassivePowerXSynergy INSTANCE = new PassivePowerXSynergy();

    @Override
    protected double getRawScore(SynergyMetric metric, Card card) {
        return Math.tanh(2.0 * card.getPower() / MagicConstants.MAX_MEANINGFUL_POWER);
    }

}
