package alchemagis.deckgenerator.metric.synergy;

import java.util.List;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;
import alchemagis.magic.MagicCardQuality;

public class ManaSynergy extends Synergy {

    private MagicCardQuality quality;

    public ManaSynergy(List<String> qualities) {
        this.quality = new MagicCardQuality(qualities);
    }

    public MagicCardQuality getQuality() {
        return this.quality;
    }

    @Override
    protected double getRawScore(SynergyMetric metric, Card selfCard, Card otherCard) {
        if (this.quality.isSatisfied(otherCard))
            return 1.0;
        else
            return 0.0;
    }

}
