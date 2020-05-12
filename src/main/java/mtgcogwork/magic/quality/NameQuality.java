package mtgcogwork.magic.quality;

public final class NameQuality extends MagicCardQuality {

    private final String name;

    public NameQuality(String name) {
        this.name = name;
    }

    public boolean isNamed(String other) {
        return this.name.equalsIgnoreCase(other);
    }

    @Override
    public <T> T accept(MagicCardQuality.Visitor<T> visitor) {
        return visitor.visitName(this);
    }

}
