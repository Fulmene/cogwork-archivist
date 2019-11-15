package alchemagis.deckgenerator.metric.synergy;

import java.util.List;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;
import alchemagis.magic.MagicCardQualityPredicate;

public class EnchantSynergy extends Synergy {

    private final MagicCardQualityPredicate qualityPredicate;

    public EnchantSynergy(List<String> qualities) {
        this.qualityPredicate = new MagicCardQualityPredicate(qualities);
    }

    @Override
    protected double getRawScore(SynergyMetric metric, Card selfCard, Card otherCard) {
        if (this.qualityPredicate.test(otherCard))
            return 1.0;
        else
            return 0.0;
    }

}
