package alchemagis.deckgenerator.metric.synergy;

import java.util.List;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;
import alchemagis.magic.MagicCardQuality;

public class EnchantSynergy extends Synergy {

    private final MagicCardQuality quality;

    public EnchantSynergy(List<String> qualities) {
        this.quality = new MagicCardQuality(qualities);
    }

    @Override
    protected double getRawScore(SynergyMetric metric, Card selfCard, Card otherCard) {
        if (this.quality.isSatisfied(otherCard))
            return 1.0;
        else
            return 0.0;
    }

}
