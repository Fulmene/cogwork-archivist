package mtgcogwork.magic.quality;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class TypeQuality extends MagicCardQuality {

    public static final List<String> PERMANENT_TYPES = List.of("artifact", "creature", "enchantment", "land", "planeswalker");

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

    public boolean isType(String type) {
        return this.types.contains(type);
    }

    public <T> T accept(MagicCardQuality.Visitor<T> visitor) {
        return visitor.visitType(this);
    }

    @Override
    public String toString() {
        return "type(" + String.join(",", this.types) + ")";
    }

}
