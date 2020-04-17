package mtgcogwork.deckgenerator.metric.synergy;

import mtgcogwork.magic.quality.MagicCardQuality;

public abstract class Synergy {

    protected Synergy() {
    }

    public final double getScore(MagicCardQuality quality) {
        if (this.test(quality))
            return 1.0;
        else
            return 0.0;
    }

    public abstract boolean test(MagicCardQuality quality);

}
