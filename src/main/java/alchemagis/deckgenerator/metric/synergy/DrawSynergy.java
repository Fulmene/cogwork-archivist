package alchemagis.deckgenerator.metric.synergy;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;

public class DrawSynergy extends Synergy {

    public static final DrawSynergy INSTANCE = new DrawSynergy();

    @Override
    protected double getRawScore(SynergyMetric metric, Card card) {
        return 0.0;
    }

}
