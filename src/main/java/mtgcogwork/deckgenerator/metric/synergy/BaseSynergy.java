package mtgcogwork.deckgenerator.metric.synergy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mtgcogwork.magic.quality.AbilityQuality;
import mtgcogwork.magic.quality.ColorQuality;
import mtgcogwork.magic.quality.MagicCardQuality;
import mtgcogwork.magic.quality.ManaCostQuality;
import mtgcogwork.magic.quality.NameQuality;
import mtgcogwork.magic.quality.StatQuality;
import mtgcogwork.magic.quality.TypeQuality;

public abstract class BaseSynergy extends Synergy implements MagicCardQuality.Visitor<Boolean> {

    private static final Map<String, BaseSynergy> INSTANCES = new HashMap<>();

    public static final BaseSynergy getInstance(String synergyString) {
        return INSTANCES.computeIfAbsent(synergyString, SynergyParser::parseBaseSynergy);
    }

    protected BaseSynergy() {
    }

    @Override
    public boolean test(List<MagicCardQuality> quality) {
        return quality.stream().anyMatch(q -> q.accept(this));
    }

    // Visitor default methods
    @Override
    public Boolean visitAbility(AbilityQuality ability) {
        return false;
    }

    @Override
    public Boolean visitColor(ColorQuality color) {
        return false;
    }

    @Override
    public Boolean visitManaCost(ManaCostQuality manaCost) {
        return false;
    }

    @Override
    public Boolean visitName(NameQuality name) {
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
