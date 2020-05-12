package mtgcogwork.magic.quality;

public abstract class MagicCardQuality {

    public abstract <T> T accept(Visitor<T> visitor);

    public static interface Visitor<T> {
        T visitAbility(AbilityQuality ability);
        T visitColor(ColorQuality color);
        T visitManaCost(ManaCostQuality manaCost);
        T visitName(NameQuality name);
        T visitStat(StatQuality stat);
        T visitType(TypeQuality type);
        T visitKeyword(KeywordQuality keyword);
    }

}
