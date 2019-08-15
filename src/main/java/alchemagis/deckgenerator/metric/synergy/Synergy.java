package alchemagis.deckgenerator.metric.synergy;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import alchemagis.deckgenerator.metric.SynergyMetric;
import alchemagis.magic.Card;
import alchemagis.magic.MagicCardQuality.IllegalQualityException;
import alchemagis.magic.MagicConstants;

public abstract class Synergy {

    private final double synergyWeight;

    protected Synergy() {
        this.synergyWeight = 1.0;
    }

    protected Synergy(double synergyWeight) {
        this.synergyWeight = synergyWeight;
    }

    public static List<Synergy> parseSynergies(String synergiesString) {
        if (synergiesString.isEmpty())
            return Collections.emptyList();
        else {
            return Arrays.stream(synergiesString.split("/")).
                map(Synergy::parseSingleSynergy).
                collect(Collectors.toUnmodifiableList());
        }
    }

    private static Synergy parseSingleSynergy(String synergy) {
        String[] synergySplit = synergy.split("[()]");
        String synergyType = synergySplit[0];
        String[] synergyParameter = null;
        if (synergySplit.length > 1)
            synergyParameter = synergySplit[1].split(",");
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
                    if (synergyParameter.length == 1) {
                        return new ManaSynergy(synergyParameter[0]);
                    } else if (synergyParameter.length > 1) {
                        String[] qualities = Arrays.copyOfRange(synergyParameter, 1, synergyParameter.length);
                        return new ManaSynergy(synergyParameter[0], qualities);
                    } else {
                        throw new SynergyFormatException(synergy);
                    }
                case "mentor":
                    return MentorSynergy.INSTANCE;
                case "sacrifice":
                    return SacrificeSynergy.INSTANCE;
                case "token":
                    return new TokenSynergy(synergyParameter[0]);
                case "xspell":
                    return XSpellSynergy.INSTANCE;
                case "pcmcx":
                    return PassiveCMCXSynergy.INSTANCE;
                case "pdamage":
                    int i = 0;
                    for (i = 0; i < synergyParameter.length; i++)
                        if (MagicConstants.targets.contains(synergyParameter[i]))
                            break;
                    if (i == synergyParameter.length) {
                        i--;
                        if (!(synergyParameter[i].equals("creature") || synergyParameter[i].equals("any")))
                            throw new SynergyFormatException(synergy);
                    }
                    return new PassiveDamageSynergy(
                        Arrays.copyOfRange(synergyParameter, 0, i),
                        Arrays.copyOfRange(synergyParameter, i, synergyParameter.length));
                case "pdie":
                    return PassiveDieSynergy.INSTANCE;
                case "penrage":
                    return PassiveEnrageSynergy.INSTANCE;
                case "pgrave":
                    return PassiveGraveyardSynergy.INSTANCE;
                case "pkicker":
                    return PassiveKickerSynergy.INSTANCE;
                case "ppower":
                    return new PassivePowerSynergy(synergyParameter[0], Integer.parseInt(synergyParameter[1]));
                case "ppowerx":
                    return PassivePowerXSynergy.INSTANCE;
                case "pquality":
                    return new PassiveQualitySynergy(synergyParameter);
                case "psacrifice":
                    return PassiveSacrificeSynergy.INSTANCE;
            }
        } catch (IllegalQualityException e) {
            // TODO do something for illegal quality
            throw new RuntimeException(e);
        } catch (SynergyFormatException e) {
            // TODO do something for illegal synergy
            throw new RuntimeException(e);
        }
        throw new RuntimeException(new SynergyFormatException(synergy));
    }

    public final double getScore(SynergyMetric metric, Card card) {
        return this.synergyWeight * this.getRawScore(metric, card);
    }

    protected abstract double getRawScore(SynergyMetric metric, Card card);

    @SuppressWarnings("serial")
    public static class SynergyFormatException extends IOException {
        public SynergyFormatException(String synergy) {
            super("Unknown synergy " + synergy);
        }
    }

}
