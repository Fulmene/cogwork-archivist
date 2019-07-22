package alchemagis.deckgenerator.metric.synergy;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;

public class SacrificeSynergy extends Synergy {

    public static final SacrificeSynergy INSTANCE = new SacrificeSynergy();

    @Override
    protected double getRawScore(SynergyMetric metric, Card card) {
        return 0.0;
    }

}
