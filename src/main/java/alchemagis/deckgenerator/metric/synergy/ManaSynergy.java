package alchemagis.deckgenerator.metric.synergy;

import java.util.List;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;
import alchemagis.magic.MagicCardQualityPredicate;

public class ManaSynergy extends Synergy {

    private MagicCardQualityPredicate qualityPredicate;

    public ManaSynergy(List<String> qualities) {
        this.qualityPredicate = new MagicCardQualityPredicate(qualities);
    }

    public MagicCardQualityPredicate getQualityPredicate() {
        return this.qualityPredicate;
    }

    @Override
    protected double getRawScore(SynergyMetric metric, Card selfCard, Card otherCard) {
        if (this.qualityPredicate.test(otherCard))
            return 1.0;
        else
            return 0.0;
    }

}
