package alchemagis.magic.quality;

import java.util.stream.Collectors;
import java.util.List;

public final class MagicCardQuality {

    private final ColorQuality colorQuality;
    private final TypeQuality typeQuality;
    private final ManaCostQuality manaCostQuality;
    private final StatQuality statQuality;
    private final List<MagicCardQualityType> otherQualities;

    public MagicCardQuality(String qualities) {
        // TODO parse quality string
        this.qualities = List.of();
    }

    public final List<MagicCardQualityType> getQualities() {
        return this.qualities;
    }

    public final List<MagicCardQualityType> getQualitiesOfType(Class qualityType) {
        return this.qualities.stream().filter(qualityType::isInstance).collect(Collectors.toList());
    }

}
