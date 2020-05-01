package mtgcogwork.deckgenerator.metric.synergy;

import mtgcogwork.magic.quality.MagicCardQualityList;

public final class UnaryOperatedSynergy extends Synergy {

    private Synergy baseSynergy;
    private String unaryOperator;

    public UnaryOperatedSynergy(Synergy baseSynergy, String op) {
        this.baseSynergy = baseSynergy;
        this.unaryOperator = op;
    }

    @Override
    public boolean test(MagicCardQualityList quality) {
        switch (this.unaryOperator) {
            case "~": return !baseSynergy.test(quality);
            default : throw new SynergyFormatException("operator " + this.unaryOperator);
        }
    }
}
