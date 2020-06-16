package mtgcogwork.deckgenerator.metric.synergy;

import java.util.List;

import mtgcogwork.magic.quality.AbilityQuality;

public class ActionSynergy extends BaseSynergy {

    private List<String> actionTypes;

    public ActionSynergy(String... actionTypes) {
        this.actionTypes = List.of(actionTypes);
    }

    @Override
    public Boolean visitAbility(AbilityQuality ability) {
        return this.actionTypes.stream().anyMatch(ability::hasActionType);
    }

}
