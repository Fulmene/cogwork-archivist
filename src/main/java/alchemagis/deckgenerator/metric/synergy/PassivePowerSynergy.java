package alchemagis.deckgenerator.metric.synergy;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;
import alchemagis.util.NumberUtil;
import alchemagis.util.NumberUtil.ComparisonOperation;

public class PassivePowerSynergy extends Synergy {

    private ComparisonOperation operation;
    private int targetPower;

    public PassivePowerSynergy(String operation, int targetPower) {
        this.operation = NumberUtil.getComparisonOperation(operation);
        this.targetPower = targetPower;
    }

    @Override
    protected double getRawScore(SynergyMetric metric, Card selfCard, Card otherCard) {
        if (NumberUtil.testComparison(otherCard.getPower(), operation, targetPower))
            return 1.0;
        else
            return 0.0;
    }

}
