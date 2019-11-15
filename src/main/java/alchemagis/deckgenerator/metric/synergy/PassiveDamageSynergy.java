package alchemagis.deckgenerator.metric.synergy;

import java.util.List;
import java.util.stream.Stream;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;
import alchemagis.magic.MagicCardQualityPredicate;

public class PassiveDamageSynergy extends Synergy {

    private final MagicCardQualityPredicate qualityPredicate;
    private final List<String> targets;

    public PassiveDamageSynergy(List<String> qualities, List<String> targets) {
        this.qualityPredicate = new MagicCardQualityPredicate(qualities);
        this.targets = targets;
    }

    @Override
    protected double getRawScore(SynergyMetric metric, Card selfCard, Card otherCard) {
        if (this.qualityPredicate.test(otherCard)) {
            if (otherCard.getTypes().contains("creature"))
                return 1.0;
            else {
                List<Synergy> otherSynergies = metric.getSynergyList(otherCard);

                if (otherSynergies.stream().anyMatch(BecomeCreatureSynergy.class::isInstance))
                    return 1.0;
                else {
                    Stream<DamageSynergy> otherDamageSynergies = otherSynergies.stream().
                        filter(DamageSynergy.class::isInstance).
                        map(DamageSynergy.class::cast);

                    if (otherDamageSynergies.anyMatch(d -> d.getTargets().stream().anyMatch(this.targets::contains)))
                        return 1.0;
                    else
                        return 0.0;
                }
            }
        }
        else
            return 0.0;
    }

}
