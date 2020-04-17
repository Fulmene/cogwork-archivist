package alchemagis.deckgenerator.metric.synergy;

import alchemagis.magic.quality.StatQuality;
import alchemagis.util.NumberUtil;
import alchemagis.util.NumberUtil.ComparisonOperation;

public final class PowerSynergy extends BaseSynergy {

    private ComparisonOperation comp;
    private int threshold;

    public PowerSynergy(ComparisonOperation comp, int threshold) {
        this.comp = comp;
        this.threshold = threshold;
    }

    @Override
    public Boolean visitStat(StatQuality stat) {
        if (NumberUtil.testComparison(stat.getPower(), this.comp, this.threshold))
            return true;
        else
            return false;
    }
}
