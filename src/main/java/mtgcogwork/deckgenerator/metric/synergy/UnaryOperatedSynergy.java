package mtgcogwork.deckgenerator.metric.synergy;

import java.util.List;

import mtgcogwork.magic.quality.MagicCardQuality;

public final class UnaryOperatedSynergy extends Synergy {

    private Synergy baseSynergy;
    private String unaryOperator;

    public UnaryOperatedSynergy(Synergy baseSynergy, String op) {
        this.baseSynergy = baseSynergy;
        this.unaryOperator = op;
    }

    @Override
    public boolean test(List<MagicCardQuality> quality) {
        switch (this.unaryOperator) {
            case "~": return !baseSynergy.test(quality);
            default : throw new SynergyFormatException("operator " + this.unaryOperator);
        }
    }
}
