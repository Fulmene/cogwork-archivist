package mtgcogwork.magic.quality;

import java.util.List;

import mtgcogwork.magic.Action;

public class KeywordQuality extends AbilityQuality {

    public static enum Keyword {

        FLYING(AbilityType.STATIC, List.of(), List.of(Action.getInstance("evasion")), List.of("flying")),
        TRAMPLE(AbilityType.STATIC, List.of(), List.of(Action.getInstance("evasion")), List.of("trample")),
        MENACE(AbilityType.STATIC, List.of(), List.of(Action.getInstance("evasion")), List.of("menace")),
        HASTE(AbilityType.STATIC, List.of(), List.of(), List.of("haste")),
        DEFENDER(AbilityType.STATIC, List.of(), List.of(Action.getInstance("cantattack")), List.of("defender")),
        FIRSTSTRIKE(AbilityType.STATIC, List.of(), List.of(Action.getInstance("firststrike")), List.of("firststrike")),
        DOUBLESTRIKE(AbilityType.STATIC, List.of(), List.of(Action.getInstance("firststrike")), List.of("doublestrike")),
        MENTOR(AbilityType.TRIGGERED, List.of(), List.of(Action.getInstance("put target creature 1 +1")), List.of("mentor")),
        JUMPSTART(AbilityType.ACTIVATED, List.of(Action.getInstance("discard 1")), List.of(Action.getInstance("cast self")), List.of("jumpstart")),
        ASCEND(AbilityType.STATIC, List.of(), List.of(Action.getInstance("gain you cityblessing")), List.of("ascend")),
        ;

        private final AbilityType abilityType;
        private final List<Action> cost;
        private final List<Action> actions;
        private final List<String> keywords;

        private Keyword(AbilityType abilityType, List<Action> cost, List<Action> actions, List<String> keywords) {
            this.abilityType = abilityType;
            this.cost = cost;
            this.actions = actions;
            this.keywords = keywords;
        }
    };

    public KeywordQuality(String keyword) {
        this(Keyword.valueOf(keyword.toUpperCase()));
    }

    public KeywordQuality(Keyword keyword) {
        super(keyword.abilityType, keyword.cost, keyword.actions, keyword.keywords);
    }

}
