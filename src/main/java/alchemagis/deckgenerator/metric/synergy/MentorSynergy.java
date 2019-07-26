package alchemagis.deckgenerator.metric.synergy;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;

public class MentorSynergy extends Synergy {

    public static final MentorSynergy INSTANCE = new MentorSynergy();

    @Override
    protected double getRawScore(SynergyMetric metric, Card card) {
        // TODO add self to parameter
        //if (card.getTypes().contains("creature") && card.getPower() <
        return 0.0;
    }

}
