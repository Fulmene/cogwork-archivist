package alchemagis.deckgenerator.metric.synergy;

import alchemagis.magic.quality.MagicCardQuality;
import alchemagis.magic.quality.MagicCardQualityType;
import alchemagis.magic.quality.ColorQuality;
import alchemagis.magic.quality.KeywordQuality;
import alchemagis.magic.quality.ManaCostQuality;
import alchemagis.magic.quality.StatQuality;
import alchemagis.magic.quality.TypeQuality;

public abstract class BaseSynergy extends Synergy implements MagicCardQualityType.Visitor<Boolean> {

    public static final Synergy NONE = new BaseSynergy() {};

    protected BaseSynergy() {
    }

    @Override
    public boolean test(MagicCardQuality quality) {
        return quality.getQualities().stream().anyMatch(q -> q.accept(this));
    }

    // Visitor default methods
    @Override
    public Boolean visitColor(ColorQuality color) {
        return false;
    }

    @Override
    public Boolean visitKeyword(KeywordQuality keyword) {
        return false;
    }

    @Override
    public Boolean visitManaCost(ManaCostQuality manaCost) {
        return false;
    }

    @Override
    public Boolean visitStat(StatQuality stat) {
        return false;
    }

    @Override
    public Boolean visitType(TypeQuality type) {
        return false;
    }

}
