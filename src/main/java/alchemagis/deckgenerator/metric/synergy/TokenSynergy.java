package alchemagis.deckgenerator.metric.synergy;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;

public class TokenSynergy extends Synergy {

    private final String tokenType;

    public TokenSynergy(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    @Override
    protected double getRawScore(SynergyMetric metric, Card card) {
        return 0.0;
    }

}

