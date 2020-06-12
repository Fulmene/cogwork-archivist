package mtgcogwork.deckgenerator.metric.synergy;

import mtgcogwork.magic.quality.StatQuality;
import mtgcogwork.util.NumberUtil.ComparisonOperation;

public final class PowerSynergy extends BaseSynergy {

    private ComparisonOperation comp;
    private int threshold;

    public PowerSynergy(ComparisonOperation comp, int threshold) {
        this.comp = comp;
        this.threshold = threshold;
    }

    @Override
    public Boolean visitStat(StatQuality stat) {
        return this.comp.testComparison(stat.getPower(), this.threshold);
    }

}
