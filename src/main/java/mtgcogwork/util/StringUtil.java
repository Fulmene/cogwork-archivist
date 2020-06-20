package mtgcogwork.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Splitter;

public final class StringUtil {

    public static List<String> split(String input, String separator) {
        if (input.isBlank())
            return List.of();
        else
            return Splitter.onPattern(separator).trimResults().splitToList(input);
    }

    public static List<String> splitArgs(String input, String argsBegin, String separator, String argsEnd) {
        int argsBeginIndex = input.indexOf(argsBegin);

        if (argsBeginIndex < 0)
            return List.of(input);

        int argsEndIndex = input.indexOf(argsEnd, argsBeginIndex);
        String head = input.substring(0, argsBeginIndex);
        List<String> result = new ArrayList<>();
        result.add(head);
        result.addAll(split(input.substring(argsBeginIndex + 1, argsEndIndex), separator));
        return Collections.unmodifiableList(result);
    }

    public static List<String> splitExpression(String expression, String operators) {
        if (expression.isBlank())
            return List.of();
        else
            return Splitter.on(lookAround(operators)).trimResults().splitToList(expression);
    }

    public static String lookAround(String separator) {
        return "(?<=[" + separator + "])|(?=[" + separator + "])";
    }

    private StringUtil() {
        // private constructor to prevent instantiation
    }

}
