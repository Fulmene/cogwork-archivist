package alchemagis.deckgenerator.metric.synergy;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;

public class LegendarySorcerySynergy extends Synergy {

    public static final LegendarySorcerySynergy INSTANCE = new LegendarySorcerySynergy();

    @Override
    protected double getRawScore(SynergyMetric metric, Card selfCard, Card otherCard) {
        if (otherCard.getSupertypes().contains("legendary") &&
            (otherCard.getTypes().contains("creature") || otherCard.getTypes().contains("planeswalker")))
            return 1.0;
        else
            return 0.0;
    }

}
