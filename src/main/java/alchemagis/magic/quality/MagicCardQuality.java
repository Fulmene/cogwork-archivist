package alchemagis.magic.quality;

import java.util.stream.Collectors;
import java.util.List;

public final class MagicCardQuality {

    private final List<MagicCardQualityType> qualities;

    public MagicCardQuality(String qualities) {
        // TODO parse quality string
        this.qualities = List.of();
    }

    public final List<MagicCardQualityType> getQualities() {
        return this.qualities;
    }

    public final List<MagicCardQualityType> getQualitiesOfType(Class<? extends MagicCardQualityType> qualityType) {
        return this.qualities.stream().filter(qualityType::isInstance).collect(Collectors.toList());
    }

}
