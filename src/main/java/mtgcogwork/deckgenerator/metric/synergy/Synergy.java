package mtgcogwork.deckgenerator.metric.synergy;

import java.util.List;

import mtgcogwork.magic.quality.MagicCardQuality;

public abstract class Synergy {

    protected Synergy() {
    }

    public final double getScore(List<MagicCardQuality> quality) {
        if (this.test(quality))
            return 1.0;
        else
            return 0.0;
    }

    public abstract boolean test(List<MagicCardQuality> quality);

}
