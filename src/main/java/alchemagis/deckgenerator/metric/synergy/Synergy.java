package alchemagis.deckgenerator.metric.synergy;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;
import alchemagis.magic.MagicCardQuality.IllegalQualityException;

public abstract class Synergy {

    private final double synergyWeight;

    protected Synergy() {
        this.synergyWeight = 1.0;
    }

    protected Synergy(double synergyWeight) {
        this.synergyWeight = synergyWeight;
    }

    public static List<Synergy> parseSynergies(String synergiesString) {
        return Arrays.stream(synergiesString.split("/")).
            map(Synergy::parseSingleSynergy).
            collect(Collectors.toUnmodifiableList());
    }

    private static Synergy parseSingleSynergy(String synergy) {
        String[] synergySplit = synergy.split("[()]");
        String synergyType = synergySplit[0];
        String[] synergyParameter = null;
        if (synergySplit.length > 1)
            synergyParameter = synergySplit[1].split("/");
        try {
            switch (synergyType) {
                case "ascend":
                    return AscendSynergy.INSTANCE;
                case "becomecreature":
                    return BecomeCreatureSynergy.INSTANCE;
                case "damage":
                    int amount;
                    if ("x".equals(synergyParameter[0]))
                        amount = -1;
                    else
                        amount = Integer.parseInt(synergyParameter[0]);
                    String[] targets = Arrays.copyOfRange(synergyParameter, 1, synergyParameter.length);
                    return new DamageSynergy(amount, targets);
                case "discard":
                    return DiscardSynergy.INSTANCE;
                case "draw":
                    return DrawSynergy.INSTANCE;
                case "enchant":
                    return new EnchantSynergy(synergyParameter);
                case "explore":
                    return ExploreSynergy.INSTANCE;
                case "flying":
                    return FlyingSynergy.INSTANCE;
                case "jumpstart":
                    return JumpStartSynergy.INSTANCE;
                case "kicker":
                    return KickerSynergy.INSTANCE;
                case "legendarysorcery":
                    return LegendarySorcerySynergy.INSTANCE;
                case "mana":
                    if (synergyParameter.length == 1)
                        return new ManaSynergy(synergyParameter[0]);
            }
        } catch (IllegalQualityException e) {
            // TODO do something for illegal quality
        }
        return null;
    }

    public final double getScore(SynergyMetric metric, Card card) {
        return this.synergyWeight * this.getRawScore(metric, card);
    }

    protected abstract double getRawScore(SynergyMetric metric, Card card);

    @SuppressWarnings("serial")
    public static class SynergyFormatException extends IOException {}

}
