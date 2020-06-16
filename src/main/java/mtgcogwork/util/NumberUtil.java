package mtgcogwork.util;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public final class NumberUtil {

    public static enum ComparisonOperation {
        EQ("=", x -> x == 0),
        NE("!=", x -> x != 0),
        LT("<", x -> x < 0),
        MT(">", x -> x > 0),
        LE("<=", x -> x <= 0),
        ME(">=", x -> x >= 0),
        ;

        private final String sign;
        private final Predicate<Integer> comparePredicate;

        private ComparisonOperation(String sign, Predicate<Integer> comparePredicate) {
            this.sign = sign;
            this.comparePredicate = comparePredicate;
        }

        public <T extends Comparable<U>, U> boolean testComparison(T lhs, U rhs) {
            int compareResult = lhs.compareTo(rhs);
            return this.comparePredicate.test(compareResult);
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

    private NumberUtil() {
        // private constructor to prevent instantiation
    }

}
