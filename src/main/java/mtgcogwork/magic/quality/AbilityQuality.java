package mtgcogwork.magic.quality;

import java.util.List;

public class AbilityQuality extends MagicCardQuality {

    public static enum AbilityType { STATIC, ACTIVATED, TRIGGERED, RESOLVE };

    private final AbilityType abilityType;
    private final String cost;
    private final List<String> actions;
    private final List<String> keywords;

    public static AbilityQuality getKeywordAbility(String keyword) {
        // This constructor is for simple keyword abilities like Trample, Lifelink et al.
        switch (keyword) {
            case "flying": return new AbilityQuality(AbilityType.STATIC, "", List.of("evasion"), List.of("flying"));
            case "trample": return new AbilityQuality(AbilityType.STATIC, "", List.of("evasion"), List.of("trample"));
            case "menace": return new AbilityQuality(AbilityType.STATIC, "", List.of("evasion"), List.of("menace"));
            default: throw new QualityFormatException("ability(" + keyword + ")");
        }
    }

    public AbilityQuality(AbilityType abilityType, String cost, List<String> actions, List<String> keywords) {
        this.abilityType = abilityType;
        this.cost = cost;
        this.actions = actions;
        this.keywords = keywords;
    }

    public boolean isAbilityType(AbilityType type) {
        return this.abilityType == type;
    }

    public boolean hasAction(String action) {
        return this.actions.contains(action);
    }

    public boolean hasKeyword(String keyword) {
        return this.keywords.contains(keyword);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitAbility(this);
    }

}
