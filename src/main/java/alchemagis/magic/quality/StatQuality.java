package alchemagis.magic.quality;

public final class StatQuality extends MagicCardQualityType {

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
    public <T> T accept(MagicCardQualityType.Visitor<T> visitor) {
        return visitor.visitStat(this);
    }

}
