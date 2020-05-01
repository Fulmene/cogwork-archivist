package mtgcogwork.magic.quality;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import mtgcogwork.magic.MagicConstants;

public final class TypeQuality extends MagicCardQuality {

    private final List<String> types;

    public TypeQuality(String... types) {
        this.types = List.of(types);
    }

    public TypeQuality(List<String> types) {
        this.types = Collections.unmodifiableList(types);
    }

    @SafeVarargs
    public TypeQuality(List<String>... types) {
        this.types = Stream.of(types).flatMap(List::stream).collect(Collectors.toList());
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

    public <T> T accept(MagicCardQuality.Visitor<T> visitor) {
        return visitor.visitType(this);
    }

}
