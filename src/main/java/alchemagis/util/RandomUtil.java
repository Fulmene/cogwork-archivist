package alchemagis.util;

import java.util.Random;

public final class RandomUtil {

    private static final Random random = new Random();

    public static int getRandomInt(int upper) {
        return random.nextInt(upper);
    }

}
