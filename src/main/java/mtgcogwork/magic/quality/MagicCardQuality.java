package mtgcogwork.magic.quality;

import java.util.HashMap;
import java.util.Map;

public abstract class MagicCardQuality {

    private static final Map<String, MagicCardQuality> INSTANCES = new HashMap<>();

    public static final MagicCardQuality getInstance(String qualityString) {
        return INSTANCES.computeIfAbsent(qualityString, QualityParser::parseQuality);
    }

    public abstract <T> T accept(Visitor<T> visitor);

    public static interface Visitor<T> {
        T visitAbility(AbilityQuality ability);
        T visitColor(ColorQuality color);
        T visitManaCost(ManaCostQuality manaCost);
        T visitName(NameQuality name);
        T visitStat(StatQuality stat);
        T visitType(TypeQuality type);
    }

}
