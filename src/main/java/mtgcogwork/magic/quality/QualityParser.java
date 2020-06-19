package mtgcogwork.magic.quality;

import java.util.List;
import java.util.stream.Collectors;

import mtgcogwork.magic.Action;
import mtgcogwork.util.StringUtil;

public final class QualityParser {

    public static List<MagicCardQuality> parseQualities(String qualitiesString) {
        if (qualitiesString.isBlank())
            return List.of();
        else
            return StringUtil.split(qualitiesString, "/").stream().
                map(MagicCardQuality::getInstance).
                collect(Collectors.toList());
    }

    public static MagicCardQuality parseQuality(String qualityString) {
        List<String> qualityArgs = StringUtil.splitArgs(qualityString, "(", ",", ")");
        switch (qualityArgs.get(0)) {
            case "ability":
                return new AbilityQuality(
                        AbilityQuality.AbilityType.valueOf(qualityArgs.get(1).toUpperCase()),
                        StringUtil.split(qualityArgs.get(2), ";").stream().map(Action::getInstance).collect(Collectors.toList()),
                        StringUtil.split(qualityArgs.get(3), ";").stream().map(Action::getInstance).collect(Collectors.toList()),
                        StringUtil.split(qualityArgs.get(4), ";"));
            case "keyword":
                return new KeywordQuality(qualityArgs.get(1));
            case "name":
                return new NameQuality(qualityArgs.get(1));
            case "color":
                return new ColorQuality(qualityArgs.subList(1, qualityArgs.size()));
            case "manacost":
                return new ManaCostQuality(qualityArgs.get(1));
            case "type":
                return new TypeQuality(qualityArgs.subList(1, qualityArgs.size()));
            case "stat":
                return new StatQuality(Integer.parseInt(qualityArgs.get(1)), Integer.parseInt(qualityArgs.get(2)));
            default: throw new QualityFormatException(qualityString);
        }
    }

}
