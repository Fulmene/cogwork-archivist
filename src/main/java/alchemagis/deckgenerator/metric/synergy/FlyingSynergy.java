package alchemagis.deckgenerator.metric.synergy;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;

public class FlyingSynergy extends Synergy {

    public static final FlyingSynergy INSTANCE = new FlyingSynergy();

    @Override
    protected double getRawScore(SynergyMetric metric, Card card) {
        return 0.0;
    }

}
