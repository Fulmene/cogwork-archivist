package mtgcogwork.magic.quality;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mtgcogwork.magic.Card;

public abstract class MagicCardQuality {

    private static final Map<String, MagicCardQuality> INSTANCES = new HashMap<>();

    public static final MagicCardQuality getInstance(String qualityString) {
        return INSTANCES.computeIfAbsent(qualityString, QualityParser::parseQuality);
    }

    public static final List<MagicCardQuality> getCardQualityList(Card card, String additionalQualities) {
        List<MagicCardQuality> qualities = new ArrayList<>();

        qualities.add(new NameQuality(card.getName()));

        qualities.add(new ColorQuality(card.getColors()));

        qualities.add(new ManaCostQuality(card.getManaCost()));

        TypeQuality typeQuality = new TypeQuality(card.getSupertypes(), card.getTypes(), card.getSubtypes());
        qualities.add(typeQuality);

        if (typeQuality.isType("creature") || typeQuality.isType("vehicle"))
            qualities.add(new StatQuality(card.getPower(), card.getToughness()));

        return qualities;

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
