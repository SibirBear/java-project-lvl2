package hexlet.code.formatters;

import java.util.Arrays;
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

public class Plain extends Format {

    private static final int TAB_CHAR_CONSTANT = 10;
    public static final String COMPLEX_VALUE = "[complex value]";
    private static final String DELETED_STRING = " was removed";

    /**
     * Returns string after analyse data and formatting to specified output in Plain.
     * @param mapToFormat data from two files which need to analyse and formatting.
     * @return Returns string after analyse data and formatting to specified output in Plain.
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
                        .thenComparing(key -> "Property ".indexOf(key.charAt(2))))
                .map(value -> value + analysedMap.get(value))
                .collect(Collectors.joining("\n"));

    }

    private Map<String, Object> analyserMap(final Map<String, Object> diffNode) {

        Map<String, Object> result = new LinkedHashMap<>();
        Object status = diffNode.get(STATUS);
        Object key = diffNode.get(KEY);
        Object oldValue = diffNode.get(OLD_VALUE);
        Object newValue = diffNode.get(NEW_VALUE);

        if (Objects.equals(status, CHANGED_VALUE)) {
            result.put(String.format("Property '%s'", key),
                    String.format(" was updated. From %s to %s", checkForCompositeValue(oldValue),
                    checkForCompositeValue(newValue)));
        }

        if (Objects.equals(status, ADDED_VALUE)) {
            result.put(String.format("Property '%s'", key),
                    String.format(" was added with value: %s", checkForCompositeValue(newValue)));
        }

        if (Objects.equals(status, DELETED_VALUE)) {
            result.put(String.format("Property '%s'", key), DELETED_STRING);
        }

        return result;

    }

    private Object checkForCompositeValue(Object value) {
        if (value instanceof Map || value instanceof Arrays || value instanceof List) {
            return COMPLEX_VALUE;
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        return value;

    }

}
