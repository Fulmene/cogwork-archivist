package alchemagis.deckgenerator.metric.synergy;

public class TokenSynergy extends ActiveSynergy {

    private final String tokenType;

    public TokenSynergy(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getTokenType() {
        return this.tokenType;
    }

}

