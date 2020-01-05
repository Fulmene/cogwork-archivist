package alchemagis.magic.quality;

public abstract class MagicCardQualityType {

    public abstract <T> T accept(Visitor<T> visitor);

    public static interface Visitor<T> {
        T visitColor(ColorQuality color);
        T visitStat(StatQuality stat);
        T visitType(TypeQuality type);
    }

}
