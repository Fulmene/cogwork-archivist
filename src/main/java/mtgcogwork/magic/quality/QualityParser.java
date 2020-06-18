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
        String[] qualityArgs = qualityString.split("[(),]");
        switch (qualityArgs[0]) {
            case "ability":
                return new AbilityQuality(
                        AbilityQuality.AbilityType.valueOf(qualityArgs[1].toUpperCase()),
                        Arrays.stream(qualityArgs[2].split(";")).map(Action::getInstance).collect(Collectors.toList()),
                        Arrays.stream(qualityArgs[3].split(";")).map(Action::getInstance).collect(Collectors.toList()),
                        List.of(qualityArgs[4].split(";")));
            case "keyword":
                return new KeywordQuality(qualityArgs[1]);
            default: throw new QualityFormatException(qualityString);
        }
    }

}
