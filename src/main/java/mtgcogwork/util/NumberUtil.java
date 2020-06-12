package mtgcogwork.util;

import java.util.List;
import java.util.Random;

public final class NumberUtil {

    public static enum ComparisonOperation { EQ("="), NE("!="), LT("<"), MT(">"), LE("<="), ME(">=");

        private String sign;

        private ComparisonOperation(String sign) {
            this.sign = sign;
        }

        public <T extends Comparable<U>, U> boolean testComparison(T lhs, U rhs) {
            int compareResult = lhs.compareTo(rhs);
            switch (this) {
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
                    throw new Error("This shouldn't happen");
            }
        }

        public static ComparisonOperation fromString(String s) {
            for (var c : ComparisonOperation.values())
                if (c.sign.equals(s))
                    return c;
            throw new IllegalArgumentException("Unknown operation " + s);
        }

    };

    private static final Random random = new Random();
    public static int getRandomInt(int upper) {
        return random.nextInt(upper);
    }

    public static double euclideanDistance(List<Integer> v1, List<Integer> v2) {
        int result = 0;
        int size = Integer.max(v1.size(), v2.size());
        for (int i = 0; i < size; i++) {
            int x1, x2;
            if (i < v1.size())
                x1 = v1.get(i);
            else
                x1 = 0;
            if (i < v2.size())
                x2 = v2.get(i);
            else
                x2 = 0;
            result += (x1-x2)*(x1-x2);
        }
        return Math.sqrt(result);
    }

    private static final double EPSILON = 1e-15;
    public static boolean isNearZero(double d) {
        return Math.abs(d) < EPSILON;
    }

}
