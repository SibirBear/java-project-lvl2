package hexlet.code.formatters;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static hexlet.code.formatters.FormattersConstants.ADDED_VALUE;
import static hexlet.code.formatters.FormattersConstants.CHANGED_VALUE;
import static hexlet.code.formatters.FormattersConstants.DELETED_VALUE;
import static hexlet.code.formatters.FormattersConstants.KEY;
import static hexlet.code.formatters.FormattersConstants.NEW_VALUE;
import static hexlet.code.formatters.FormattersConstants.OLD_VALUE;
import static hexlet.code.formatters.FormattersConstants.STATUS;
import static hexlet.code.formatters.FormattersConstants.UNCHANGED_VALUE;

public class Stylish extends Format {

    private static final int TAB_CHAR_CONSTANT = 4;
    public static final String PLUS = "  + ";
    public static final String MINUS = "  - ";
    public static final String SPACE = "    ";
    public static final String COLON = ": ";

    /**
     * Returns string after analyse data and formatting to specified input.
     * @param mapToFormat data from two files which need to analyse and formatting.
     * @return Returns string after analyse data and formatting to specified input.
     */
    @Override
    public String format(final List<Map<String, Object>> mapToFormat) {

        Map<String, Object> analysedMap = new LinkedHashMap<>();
        for (Map<String, Object> diffNode : mapToFormat) {
            analysedMap.putAll(analyserMap(diffNode));
        }

        return formatMapToString(analysedMap);
    }

    private static String formatMapToString(final Map<String, Object> analysedMap) {
        return analysedMap.keySet().stream()
                .sorted(Comparator.comparing((String key) -> key.substring(TAB_CHAR_CONSTANT))
                        .thenComparing(key -> " -+".indexOf(key.charAt(2))))
                .map(value -> value + analysedMap.get(value))
                .collect(Collectors.joining("\n", "{\n", "\n}"));

    }

    private Map<String, Object> analyserMap(final Map<String, Object> diffNode) {

        Map<String, Object> result = new LinkedHashMap<>();
        Object status = diffNode.get(STATUS);
        Object key = diffNode.get(KEY);
        Object oldValue = diffNode.get(OLD_VALUE);
        Object newValue = diffNode.get(NEW_VALUE);

        if (Objects.equals(status, CHANGED_VALUE) || Objects.equals(status, DELETED_VALUE)) {
            result.put(MINUS + key + COLON, oldValue);
        }

        if (Objects.equals(status, CHANGED_VALUE) || Objects.equals(status, ADDED_VALUE)) {
            result.put(PLUS + key + COLON, newValue);
        }

        if (Objects.equals(status, UNCHANGED_VALUE)) {
            result.put(SPACE + key + COLON, oldValue);
        }

        return result;

    }

}
