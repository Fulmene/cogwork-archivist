package mtgcogwork.magic.quality;

import java.util.List;
import java.util.stream.Stream;

import mtgcogwork.magic.Action;

public class AbilityQuality extends MagicCardQuality {

    public static enum AbilityType { STATIC, ACTIVATED, TRIGGERED, RESOLVE };

    private final AbilityType abilityType;
    private final List<Action> cost;
    private final List<Action> actions;
    private final List<String> keywords;

    public AbilityQuality(AbilityType abilityType, List<Action> cost, List<Action> actions, List<String> keywords) {
        this.abilityType = abilityType;
        this.cost = cost;
        this.actions = actions;
        this.keywords = keywords;
    }

    public boolean isAbilityType(AbilityType type) {
        return this.abilityType == type;
    }

    public boolean hasActionType(String actionType) {
        return Stream.concat(this.cost.stream(), this.actions.stream()).anyMatch(a -> a.getType().equals(actionType));
    }

    public boolean hasKeyword(String keyword) {
        return this.keywords.contains(keyword);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitAbility(this);
    }

}
