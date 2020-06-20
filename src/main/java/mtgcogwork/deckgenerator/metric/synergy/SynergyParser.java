package mtgcogwork.deckgenerator.metric.synergy;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import mtgcogwork.util.NumberUtil.ComparisonOperation;
import mtgcogwork.util.StringUtil;

public final class SynergyParser {

    public static List<Synergy> parseSynergies(String synergiesString) {
        return StringUtil.split(synergiesString, "/").stream().
            map(SynergyParser::parseSynergy).
            collect(Collectors.toList());
    }

    private static int precedence(String operator) {
        switch (operator) {
            case "~": return 3;
            case "&": return 2;
            case "|": return 1;
            case "[": return 0;
            default : throw new SynergyFormatException("operator " + operator);
        }
    }

    private static String OPERATORS = "\\[\\]&|~";

    public static Synergy parseSynergy(String synergyString) {
        List<String> tokens = StringUtil.splitExpression("[" + synergyString + "]", OPERATORS);
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
            case "die":
                return new DieSynergy();
            case "discard":
                return new DiscardSynergy();
            case "kicker":
                return new KickerSynergy();
            case "powerx":
                return new PowerXSynergy();
            case "sacrifice":
                return new SacrificeSynergy();
            case "power":
                return new PowerSynergy(ComparisonOperation.fromSign(synergyArgs[1]), Integer.parseInt(synergyArgs[2]));
            case "color":
                return ColorSynergy.get(synergyArgs[1]);
            case "type":
                return TypeSynergy.get(synergyArgs[1]);
            default:
                throw new SynergyFormatException(baseSynergyString);
        }
    }

}
