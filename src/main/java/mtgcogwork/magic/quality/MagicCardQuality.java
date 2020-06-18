package mtgcogwork.magic.quality;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import mtgcogwork.magic.Card;

public abstract class MagicCardQuality {

    private static final Map<String, MagicCardQuality> INSTANCES = new HashMap<>();

    public static final MagicCardQuality getInstance(String qualityString) {
        return INSTANCES.computeIfAbsent(qualityString, QualityParser::parseQuality);
    }

    public static final List<MagicCardQuality> getCardQualityList(Card card, String additionalQualities) {
        List<MagicCardQuality> qualities = new ArrayList<>();

        qualities.add(getInstance("name(" + card.getName() + ")"));

        qualities.add(getInstance("color(" + card.getColors() + ")"));

        qualities.add(getInstance("manacost(" + card.getManaCost() + ")"));

        String typeString = Stream.concat(Stream.concat(card.getSupertypes().stream(), card.getTypes().stream()), card.getSubtypes().stream()).
            collect(Collectors.joining(","));
        TypeQuality typeQuality = (TypeQuality)getInstance("type(" + typeString + ")");
        qualities.add(typeQuality);

        if (typeQuality.isType("creature") || typeQuality.isType("vehicle"))
            qualities.add(getInstance("stat(" + card.getPower() + "," + card.getToughness() + ")"));

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
