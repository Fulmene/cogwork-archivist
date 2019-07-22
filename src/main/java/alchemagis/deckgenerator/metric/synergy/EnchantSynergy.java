package alchemagis.deckgenerator.metric.synergy;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;
import alchemagis.magic.MagicCardQuality;

public class EnchantSynergy extends Synergy {

    private final MagicCardQuality quality;

    public EnchantSynergy(String... qualities) throws MagicCardQuality.IllegalQualityException {
        this.quality = new MagicCardQuality(qualities);
    }

    @Override
    protected double getRawScore(SynergyMetric metric, Card card) {
        if (this.quality.isSatisfied(card))
            return 1.0;
        else
            return 0.0;
    }

}
