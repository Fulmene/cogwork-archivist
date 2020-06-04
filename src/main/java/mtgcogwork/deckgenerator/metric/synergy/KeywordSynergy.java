package mtgcogwork.deckgenerator.metric.synergy;

import mtgcogwork.magic.quality.AbilityQuality;

public class KeywordSynergy extends BaseSynergy {

    private String keyword;

    public KeywordSynergy(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public Boolean visitAbility(AbilityQuality ability) {
        return ability.hasKeyword(this.keyword);
    }

}
