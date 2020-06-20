package mtgcogwork.util;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public final class NumberUtil {

    public static enum ComparisonOperator {
        EQUAL("=", x -> x == 0),
        NOT_EQUAL("!=", x -> x != 0),
        LESS_THAN("<", x -> x < 0),
        GREATER_THAN(">", x -> x > 0),
        LESS_THAN_OR_EQUAL("<=", x -> x <= 0),
        GREATER_THAN_OR_EQUAL(">=", x -> x >= 0),
        ;

        private final String sign;
        private final Predicate<Integer> comparePredicate;

        private ComparisonOperator(String sign, Predicate<Integer> comparePredicate) {
            this.sign = sign;
            this.comparePredicate = comparePredicate;
        }

        public <T extends Comparable<U>, U> boolean test(T lhs, U rhs) {
            int compareResult = lhs.compareTo(rhs);
            return this.comparePredicate.test(compareResult);
        }

        public static ComparisonOperator fromSign(String s) {
            for (var c : ComparisonOperator.values())
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
        int resultSquared = 0;
        int size = Integer.max(v1.size(), v2.size());
        for (int i = 0; i < size; i++) {
            int x1, x2;
            x1 = i < v1.size() ? v1.get(i) : 0;
            x2 = i < v2.size() ? v2.get(i) : 0;
            resultSquared += (x1-x2)*(x1-x2);
        }
        return Math.sqrt(resultSquared);
    }

    private static final double EPSILON = 1e-15;
    public static boolean isNearZero(double d) {
        return Math.abs(d) < EPSILON;
    }

    private NumberUtil() {
        // private constructor to prevent instantiation
    }

}
