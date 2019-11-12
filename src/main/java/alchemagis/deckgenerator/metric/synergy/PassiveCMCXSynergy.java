package alchemagis.deckgenerator.metric.synergy;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;
import alchemagis.magic.MagicConstants;

public class PassiveCMCXSynergy extends Synergy {

    public static final PassiveCMCXSynergy INSTANCE = new PassiveCMCXSynergy();

    @Override
    protected double getRawScore(SynergyMetric metric, Card card) {
        return Math.tanh(2.0 * card.getConvertedManaCost() / MagicConstants.MAX_MEANINGFUL_CMC);
    }

}
