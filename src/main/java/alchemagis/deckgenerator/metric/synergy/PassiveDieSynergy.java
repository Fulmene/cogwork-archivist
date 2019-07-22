package alchemagis.deckgenerator.metric.synergy;

import java.util.List;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;

public class PassiveDieSynergy extends Synergy {

    public static final PassiveDieSynergy INSTANCE = new PassiveDieSynergy();
    private static List<Class<? extends Synergy>> matchingSynergies = List.of(SacrificeSynergy.class);

    @Override
    protected double getRawScore(SynergyMetric metric, Card card) {
        List<Synergy> otherSynergies = metric.getSynergyList(card);
        if (otherSynergies.stream().anyMatch(s -> matchingSynergies.stream().anyMatch(c -> c.isInstance(s))))
            return 1.0;
        else
            return 0.0;
    }

}
