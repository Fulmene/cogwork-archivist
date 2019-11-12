package alchemagis.deckgenerator.metric.synergy;

import java.util.stream.Stream;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;

public class PassiveEnrageSynergy extends Synergy {

    public static final PassiveEnrageSynergy INSTANCE = new PassiveEnrageSynergy();

    @Override
    protected double getRawScore(SynergyMetric metric, Card selfCard, Card otherCard) {
        Stream<DamageSynergy> otherDamageSynergies = metric.classFilterStream(DamageSynergy.class, otherCard);

        if (otherDamageSynergies.anyMatch(d -> d.getTargets().contains("creature")))
            return 1.0;
        else
            return 0.0;
    }

}
