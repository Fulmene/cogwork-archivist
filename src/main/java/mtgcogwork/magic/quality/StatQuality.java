package mtgcogwork.magic.quality;

public final class StatQuality extends MagicCardQuality {

    private final int power;
    private final int toughness;

    public StatQuality(int power, int toughness) {
        this.power = power;
        this.toughness = toughness;
    }

    public int getPower() {
        return this.power;
    }

    public int getToughness() {
        return this.toughness;
    }

    @Override
    public <T> T accept(MagicCardQuality.Visitor<T> visitor) {
        return visitor.visitStat(this);
    }

    @Override
    public String toString() {
        return String.format("stat(%d,%d)", this.power, this.toughness);
    }

}
