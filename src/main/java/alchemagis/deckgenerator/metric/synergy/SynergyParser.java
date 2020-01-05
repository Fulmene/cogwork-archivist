package alchemagis.deckgenerator.metric.synergy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import alchemagis.magic.MagicConstants;

public class SynergyParser {

    public static List<Synergy> parseSynergies(String synergiesString) {
        if (synergiesString.isEmpty())
            return Collections.emptyList();
        else {
            return Arrays.stream(synergiesString.split("/")).
                map(SynergyParser::parseSingleSynergy).
                collect(Collectors.toUnmodifiableList());
        }
    }

    private static Synergy parseSingleSynergy(String synergy) {
        List<String> synergySplit = Arrays.asList(synergy.split("[()]"));
        String synergyType = synergySplit.get(0);
        List<String> synergyParameter = null;
        if (synergySplit.size() > 1)
            synergyParameter = Arrays.asList(synergySplit.get(1).split(","));
        switch (synergyType) {
            case "ascend":
                return AscendSynergy.INSTANCE;
            case "becomecreature":
                return BecomeCreatureSynergy.INSTANCE;
            case "damage":
                int amount;
                if ("x".equals(synergyParameter.get(0)))
                    amount = -1;
                else
                    amount = Integer.parseInt(synergyParameter.get(0));
                List<String> targets = synergyParameter.subList(1, synergyParameter.size());
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
                if (synergyParameter.size() >= 1 && MagicConstants.colors.contains(synergyParameter.get(0))) {
                    return new ManaSynergy(synergyParameter);
                } else {
                    throw new SynergyFormatException(synergy);
                }
            case "mentor":
                return MentorSynergy.INSTANCE;
            case "sacrifice":
                return SacrificeSynergy.INSTANCE;
            case "token":
                return new TokenSynergy(synergyParameter.get(0));
            case "xspell":
                return XSpellSynergy.INSTANCE;
            case "pcmcx":
                return PassiveCMCXSynergy.INSTANCE;
            case "pdamage":
                int i = 0;
                for (i = 0; i < synergyParameter.size(); i++)
                    if (MagicConstants.targets.contains(synergyParameter.get(i)))
                        break;
                if (i == synergyParameter.size()) {
                    i--;
                    if (!(synergyParameter.get(i).equals("creature") || synergyParameter.get(i).equals("any")))
                        throw new SynergyFormatException(synergy);
                }
                return new PassiveDamageSynergy(
                        synergyParameter.subList(0, i),
                        synergyParameter.subList(i, synergyParameter.size()));
            case "pdie":
                return PassiveDieSynergy.INSTANCE;
            case "penrage":
                return PassiveEnrageSynergy.INSTANCE;
            case "pgrave":
                return PassiveGraveyardSynergy.INSTANCE;
            case "pkicker":
                return PassiveKickerSynergy.INSTANCE;
            case "ppower":
                return new PassivePowerSynergy(synergyParameter.get(0), Integer.parseInt(synergyParameter.get(1)));
            case "ppowerx":
                return PassivePowerXSynergy.INSTANCE;
            case "pquality":
                return new PassiveQualitySynergy(synergyParameter);
            case "psacrifice":
                return PassiveSacrificeSynergy.INSTANCE;
        }
        throw new SynergyFormatException(synergy);
    }

    @SuppressWarnings("serial")
    public static class SynergyFormatException extends RuntimeException {
        private SynergyFormatException(String synergy) {
            super("Unknown synergy " + synergy);
        }
    }

}
