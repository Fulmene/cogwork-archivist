package mtgcogwork.deckgenerator.metric.synergy;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import mtgcogwork.util.NumberUtil.ComparisonOperation;

public final class SynergyParser {

    public static List<Synergy> parseSynergies(String synergiesString) {
        return Arrays.stream(synergiesString.split("/")).
            map(SynergyParser::parseSynergy).
            collect(Collectors.toList());
    }

    private static String lookAround(String delimiters) {
        return "(?<=[" + delimiters + "])|(?=[" + delimiters + "])";
    }

    private static String DELIMITERS = "\\[\\]&|~";

    private static int precedence(String operator) {
        switch (operator) {
            case "~": return 3;
            case "&": return 2;
            case "|": return 1;
            case "[": return 0;
            default : throw new SynergyFormatException("operator " + operator);
        }
    }

    public static Synergy parseSynergy(String synergyString) {
        if (synergyString.matches("[" + DELIMITERS + "]*"))
            return BaseSynergy.getInstance("none");

        String[] tokens = ("[" + synergyString + "]").split(lookAround(DELIMITERS));
        Stack<Synergy> operandStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();
        for (String t : tokens) {
            switch (t) {
                case "~":
                case "&":
                case "|":
                    while (precedence(operatorStack.peek()) >= precedence(t)) {
                        popOperatorStack(operandStack, operatorStack);
                    }
                    operatorStack.push(t);
                    break;
                case "[":
                    operatorStack.push(t);
                    break;
                case "]": 
                    while (!operatorStack.peek().equals("["))
                        popOperatorStack(operandStack, operatorStack);
                    operatorStack.pop();
                    break;
                default: operandStack.push(BaseSynergy.getInstance(t)); 
            }
        }
        return operandStack.pop();
    }

    private static void popOperatorStack(Stack<Synergy> operandStack, Stack<String> operatorStack) {
        String op = operatorStack.pop();
        switch (op) {
            case "~":
                Synergy base = operandStack.pop();
                operandStack.push(new UnaryOperatedSynergy(base, op));
                break;
            case "&":
            case "|":
                Synergy rhs = operandStack.pop();
                Synergy lhs = operandStack.pop();
                operandStack.push(new BinaryOperatedSynergy(lhs, op, rhs));
                break;
            default :
                throw new SynergyFormatException("operator " + op);
        }
    }

    public static BaseSynergy parseBaseSynergy(String baseSynergyString) {
        String[] synergyArgs = baseSynergyString.split("[(),]");
        switch (synergyArgs[0]) {
            case "cmcx":
                return new CmcXSynergy();
            case "damage":
                return new DamageSynergy();
            case "thisdies":
                return new ThisDiesSynergy();
            case "graveyard":
                return new GraveyardSynergy();
            case "kicker":
                return new KickerSynergy();
            case "powerx":
                return new PowerXSynergy();
            case "sacrifice":
                return new SacrificeSynergy();
            case "power":
                return new PowerSynergy(ComparisonOperation.fromString(synergyArgs[1]), Integer.parseInt(synergyArgs[2]));
            case "color":
                return ColorSynergy.get(synergyArgs[1]);
            case "type":
                return TypeSynergy.get(synergyArgs[1]);
            case "none":
                return new BaseSynergy() {};
            default:
                throw new SynergyFormatException(baseSynergyString);
        }
    }

}
