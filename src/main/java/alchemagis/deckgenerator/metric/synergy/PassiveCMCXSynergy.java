package alchemagis.deckgenerator.metric.synergy;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;

public class PassiveCMCXSynergy extends Synergy {

    public static final PassiveCMCXSynergy INSTANCE = new PassiveCMCXSynergy();

    @Override
    protected double getRawScore(SynergyMetric metric, Card card) {
        return card.getConvertedManaCost();
    }

}
