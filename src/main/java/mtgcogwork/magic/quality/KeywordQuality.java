package mtgcogwork.magic.quality;

import java.util.List;

import mtgcogwork.magic.Action;

public class KeywordQuality extends AbilityQuality {

    public static enum Keyword {

        FLYING(AbilityType.STATIC, List.of(), List.of(Action.getInstance("evasion")), List.of("flying")),
        TRAMPLE(AbilityType.STATIC, List.of(), List.of(Action.getInstance("evasion")), List.of("trample")),
        MENACE(AbilityType.STATIC, List.of(), List.of(Action.getInstance("evasion")), List.of("menace")),
        HASTE(AbilityType.STATIC, List.of(), List.of(), List.of("haste")),
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
