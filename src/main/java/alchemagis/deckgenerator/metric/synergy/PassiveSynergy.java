package alchemagis.deckgenerator.metric.synergy;

import java.util.List;

import alchemagis.magic.MagicCardQualityPredicate;

public abstract class PassiveSynergy extends Synergy {

    protected MagicCardQualityPredicate qualityPredicate;

    protected PassiveSynergy() {
        this.qualityPredicate = MagicCardQualityPredicate.ANY;
    }

    protected PassiveSynergy(double synergyWeight) {
        super(synergyWeight);
        this.qualityPredicate = MagicCardQualityPredicate.ANY;
    }

    protected PassiveSynergy(List<String> qualities) {
        super();
        this.qualityPredicate = new MagicCardQualityPredicate(qualities);
    }

    protected PassiveSynergy(double synergyWeight, List<String> qualities) {
        super(synergyWeight);
        this.qualityPredicate = new MagicCardQualityPredicate(qualities);
    }

}
