package alchemagis.deckgenerator.metric.synergy;

import java.util.List;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;

public class JumpStartSynergy extends Synergy {

    public static final JumpStartSynergy INSTANCE = new JumpStartSynergy();
    private static List<Class<? extends Synergy>> matchingSynergies = List.of(DiscardSynergy.class, PassiveGraveyardSynergy.class);

    @Override
    protected double getRawScore(SynergyMetric metric, Card card1, Card card2) {
        List<Synergy> otherSynergies = metric.getSynergyList(card2);
        if (otherSynergies.stream().anyMatch(s -> matchingSynergies.stream().anyMatch(c -> c.isInstance(s))))
            return 1.0;
        else
            return 0.0;
    }

}
