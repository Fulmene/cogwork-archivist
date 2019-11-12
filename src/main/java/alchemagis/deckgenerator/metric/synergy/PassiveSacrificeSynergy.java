package alchemagis.deckgenerator.metric.synergy;

import java.util.List;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;

public class PassiveSacrificeSynergy extends Synergy {

    public static final PassiveSacrificeSynergy INSTANCE = new PassiveSacrificeSynergy();
    private static List<Class<? extends Synergy>> matchingSynergies = List.of(SacrificeSynergy.class);

    @Override
    protected double getRawScore(SynergyMetric metric, Card selfCard, Card otherCard) {
        List<Synergy> otherSynergies = metric.getSynergyList(otherCard);
        if (otherSynergies.stream().anyMatch(s -> matchingSynergies.stream().anyMatch(c -> c.isInstance(s))))
            return 1.0;
        else
            return 0.0;
    }

}
