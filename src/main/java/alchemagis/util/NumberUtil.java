package alchemagis.util;

import java.util.Random;

public final class NumberUtil {

    public static enum ComparisonOperation { EQ, NE, LT, MT, LE, ME };

    public static ComparisonOperation getComparisonOperation(String operationString) {
        switch (operationString) {
            case "=":
                return ComparisonOperation.EQ;
            case "!=":
                return ComparisonOperation.NE;
            case "<":
                return ComparisonOperation.LT;
            case ">":
                return ComparisonOperation.MT;
            case "<=":
                return ComparisonOperation.LE;
            case ">=":
                return ComparisonOperation.ME;
            default:
                throw new IllegalArgumentException("Unknown operation " + operationString);
        }
    }
    public static <T extends Comparable<T>> boolean testComparison(T lhs, ComparisonOperation operation, T rhs) {
        int compareResult = lhs.compareTo(rhs);
        switch (operation) {
            case EQ:
                return compareResult == 0;
            case NE:
                return compareResult != 0;
            case LT:
                return compareResult < 0;
            case MT:
                return compareResult > 0;
            case LE:
                return compareResult <= 0;
            case ME:
                return compareResult >= 0;
            default:
                throw new NullPointerException("Operation must not be null");
        }
    }

    private static final Random random = new Random();
    public static int getRandomInt(int upper) {
        return random.nextInt(upper);
    }

}
