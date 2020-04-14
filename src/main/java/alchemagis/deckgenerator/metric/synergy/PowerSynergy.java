package alchemagis.deckgenerator.metric.synergy;

import java.util.Map;
import java.util.HashMap;

import alchemagis.magic.quality.StatQuality;
import alchemagis.util.NumberUtil;
import alchemagis.util.NumberUtil.ComparisonOperation;

public final class PowerSynergy extends BaseSynergy {

    private static Map<String, PowerSynergy> INSTANCES = new HashMap<>();

    public static PowerSynergy getInstance(String comp, String threshold) {
        String key = comp + threshold;
        PowerSynergy target = INSTANCES.get(key);
        if (target == null) {
            target = new PowerSynergy(NumberUtil.getComparisonOperation(comp), Integer.parseInt(threshold));
            INSTANCES.put(key, target);
        }
        return target;
    }

    private ComparisonOperation comp;
    private int threshold;

    private PowerSynergy(ComparisonOperation comp, int threshold) {
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
