package alchemagis.deckgenerator.metric.synergy;

import java.util.List;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;

public class DamageSynergy extends Synergy {

    private final int amount;
    private final List<String> targets;

    public DamageSynergy(int amount, List<String> targets) {
        this.amount = amount;
        this.targets = targets;
    }

    @Override
    protected double getRawScore(SynergyMetric metric, Card card) {
        return 0.0;
    }

    public int getAmount() {
        return this.amount;
    }

    public List<String> getTargets() {
        return this.targets;
    }

}
