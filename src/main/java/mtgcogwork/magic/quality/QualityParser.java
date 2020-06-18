package mtgcogwork.magic.quality;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import mtgcogwork.magic.Action;

public final class QualityParser {

    public static List<MagicCardQuality> parseQualities(String qualitiesString) {
        return Arrays.stream(qualitiesString.split("/")).
            map(MagicCardQuality::getInstance).
            collect(Collectors.toList());
    }

    public static MagicCardQuality parseQuality(String qualityString) {
        List<String> qualityArgs = List.of(qualityString.split("[(),]"));
        switch (qualityArgs.get(0)) {
            case "ability":
                return new AbilityQuality(
                        AbilityQuality.AbilityType.valueOf(qualityArgs.get(1).toUpperCase()),
                        Arrays.stream(qualityArgs.get(2).split(";")).map(Action::getInstance).collect(Collectors.toList()),
                        Arrays.stream(qualityArgs.get(3).split(";")).map(Action::getInstance).collect(Collectors.toList()),
                        List.of(qualityArgs.get(4).split(";")));
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
