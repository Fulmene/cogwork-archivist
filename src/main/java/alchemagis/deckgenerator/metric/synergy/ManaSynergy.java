package alchemagis.deckgenerator.metric.synergy;

import java.util.List;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;
import alchemagis.magic.MagicCardQuality;

public class ManaSynergy extends Synergy {

    private String color;
    private MagicCardQuality quality;

    public ManaSynergy(String color) {
        this.color = color;
        this.quality = MagicCardQuality.ANY;
    }

    public ManaSynergy(String color, List<String> qualities) {
        this.color = color;
        this.quality = new MagicCardQuality(qualities);
    }

    public String getColor() {
        return this.color;
    }

    public MagicCardQuality getQuality() {
        return this.quality;
    }

    @Override
    protected double getRawScore(SynergyMetric metric, Card card) {
        // TODO card requires this mana
        if (this.quality.isSatisfied(card))
            return 1.0;
        else
            return 0.0;
    }

}
