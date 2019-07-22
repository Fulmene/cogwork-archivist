package alchemagis.deckgenerator.metric.synergy;

import java.util.List;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;

public class PassiveGraveyardSynergy extends Synergy {

    public static final PassiveGraveyardSynergy INSTANCE = new PassiveGraveyardSynergy();
    private static List<Class<? extends Synergy>> matchingSynergies = List.of(DiscardSynergy.class, SacrificeSynergy.class);

    @Override
    protected double getRawScore(SynergyMetric metric, Card card) {
        List<Synergy> otherSynergies = metric.getSynergyList(card);
        if (otherSynergies.stream().anyMatch(s -> matchingSynergies.stream().anyMatch(c -> c.isInstance(s))))
            return 1.0;
        else
            return 0.0;
    }

}
