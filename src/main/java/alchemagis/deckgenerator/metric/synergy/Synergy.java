package alchemagis.deckgenerator.metric.synergy;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;
import alchemagis.util.NumberUtil;

public abstract class Synergy {

    private final Logger log = LogManager.getLogger(this.getClass());

    private final double synergyWeight;

    protected Synergy() {
        this.synergyWeight = 1.0;
    }

    protected Synergy(double synergyWeight) {
        this.synergyWeight = synergyWeight;
    }

    public final double getScore(SynergyMetric metric, Card selfCard, Card otherCard) {
        double score = this.synergyWeight * this.getRawScore(metric, selfCard, otherCard);
        if (!NumberUtil.isNearZero(score)) {
            log.debug("            Score for {} -> {}: {}", selfCard, otherCard, score);
        }
        return score;
    }

    protected abstract double getRawScore(SynergyMetric metric, Card selfCard, Card otherCard);

}
