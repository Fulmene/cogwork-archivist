package alchemagis.deckgenerator.metric.synergy;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;

public class ExploreSynergy extends Synergy {

    public static final ExploreSynergy INSTANCE = new ExploreSynergy();

    @Override
    protected double getRawScore(SynergyMetric metric, Card card) {
        return 0.0;
    }

}
