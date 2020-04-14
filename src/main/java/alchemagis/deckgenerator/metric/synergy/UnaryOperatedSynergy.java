package alchemagis.deckgenerator.metric.synergy;

import alchemagis.magic.quality.MagicCardQuality;

public final class UnaryOperatedSynergy extends Synergy {

    private Synergy baseSynergy;
    private String unaryOperator;

    public UnaryOperatedSynergy(Synergy baseSynergy, String op) {
        this.baseSynergy = baseSynergy;
        this.unaryOperator = op;
    }

    @Override
    public boolean test(MagicCardQuality quality) {
        switch (this.unaryOperator) {
            case "~": return !baseSynergy.test(quality);
            default : throw new SynergyFormatException("operator " + this.unaryOperator);
        }
    }
}
