package mtgcogwork.deckgenerator.metric.synergy;

import mtgcogwork.magic.quality.MagicCardQualityList;

public abstract class Synergy {

    protected Synergy() {
    }

    public final double getScore(MagicCardQualityList quality) {
        if (this.test(quality))
            return 1.0;
        else
            return 0.0;
    }

    public abstract boolean test(MagicCardQualityList quality);

}
