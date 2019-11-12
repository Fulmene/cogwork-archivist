package alchemagis.deckgenerator.metric.synergy;

import java.util.List;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;

public abstract class ActiveSynergy extends Synergy {

    @Override
    protected final double getRawScore(SynergyMetric metric, Card selfCard, Card otherCard) {
        //List<Synergy> otherSynergies = metric.getSynergyList(otherCard);
        //return otherSynergies.stream().mapToDouble(s -> s.getScore(metric, otherCard, selfCard)).sum();
        return 0.0;
    }

}
