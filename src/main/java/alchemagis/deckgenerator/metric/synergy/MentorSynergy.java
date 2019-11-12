package alchemagis.deckgenerator.metric.synergy;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;

public class MentorSynergy extends Synergy {

    public static final MentorSynergy INSTANCE = new MentorSynergy();

    @Override
    protected double getRawScore(SynergyMetric metric, Card selfCard, Card otherCard) {
        if (otherCard.getTypes().contains("creature") && otherCard.getPower() < selfCard.getPower())
            return 1.0;
        else
            return 0.0;
    }

}
