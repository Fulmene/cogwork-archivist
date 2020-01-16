package alchemagis.magic.quality;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import alchemagis.magic.MagicConstants;

public final class TypeQuality extends MagicCardQualityType {

    private final List<String> types;

    public TypeQuality(String... types) {
        this.types = List.of(types);
    }

    public TypeQuality(List<String> types) {
        this.types = Collections.unmodifiableList(types);
    }

    public List<String> getTypes() {
        return this.types;
    }

    public boolean isType(String... types) {
        return Arrays.stream(types).allMatch(this.types::contains);
    }

    public boolean isNontype(String... types) {
        return Arrays.stream(types).noneMatch(this.types::contains);
    }

    public boolean isPermanent() {
        return MagicConstants.permanentTypes.stream().anyMatch(this.types::contains);
    }

    public <T> T accept(MagicCardQualityType.Visitor<T> visitor) {
        return visitor.visitType(this);
    }

}
