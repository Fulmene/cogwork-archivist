package alchemagis.deckgenerator.metric.synergy;

import java.util.List;

public class DamageSynergy extends ActiveSynergy {

    private final int amount;
    private final List<String> targets;

    public DamageSynergy(int amount, List<String> targets) {
        this.amount = amount;
        this.targets = targets;
    }

    public int getAmount() {
        return this.amount;
    }

    public List<String> getTargets() {
        return this.targets;
    }

}
