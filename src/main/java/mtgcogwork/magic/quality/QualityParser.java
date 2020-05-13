package mtgcogwork.magic.quality;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class QualityParser {

    public static List<MagicCardQuality> parseQualities(String qualitiesString) {
        return Arrays.stream(qualitiesString.split("/")).
            map(MagicCardQuality::getInstance).
            collect(Collectors.toList());
    }

    public static MagicCardQuality parseQuality(String qualityString) {
        String[] qualityArgs = qualityString.split("[(),]");
        switch (qualityArgs[0]) {
            default: throw new QualityFormatException(qualityString);
        }
    }

}
