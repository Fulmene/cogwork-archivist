package mtgcogwork.magic.quality;

public abstract class MagicCardQualityType {

    public abstract <T> T accept(Visitor<T> visitor);

    public static interface Visitor<T> {
        T visitColor(ColorQuality color);
        T visitManaCost(ManaCostQuality manaCost);
        T visitStat(StatQuality stat);
        T visitType(TypeQuality type);
        T visitKeyword(KeywordQuality keyword);
    }

}
