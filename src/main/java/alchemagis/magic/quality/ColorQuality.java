package alchemagis.magic.quality;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ColorQuality extends MagicCardQualityType {

    private final List<String> colors;

    public ColorQuality(String... colors) {
        this.colors = List.of(colors);
    }

    public ColorQuality(List<String> colors) {
        this.colors = Collections.unmodifiableList(colors);
    }

    public List<String> getColors() {
        return this.colors;
    }

    public boolean isColor(String... colors) {
        return Arrays.stream(colors).allMatch(c -> this.colors.contains(c));
    }

    public boolean isNoncolor(String... colors) {
        return Arrays.stream(colors).noneMatch(c -> this.colors.contains(c));
    }

    public boolean isMonocolored() {
        return this.colors.size() == 1;
    }

    public boolean isMulticolored() {
        return this.colors.size() > 1;
    }

    public boolean isColorless() {
        return this.colors.size() == 0;
    }

    @Override
    public <T> T accept(MagicCardQualityType.Visitor<T> visitor) {
        return visitor.visitColor(this);
    }

}
