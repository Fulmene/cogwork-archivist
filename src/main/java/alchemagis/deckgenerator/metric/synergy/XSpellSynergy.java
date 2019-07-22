package alchemagis.deckgenerator.metric.synergy;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;

public class XSpellSynergy extends Synergy {

    public static final XSpellSynergy INSTANCE = new XSpellSynergy();

    @Override
    protected double getRawScore(SynergyMetric metric, Card card) {
        return 0.0;
    }

}
