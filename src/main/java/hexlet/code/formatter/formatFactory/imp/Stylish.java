package hexlet.code.formatter.formatFactory.imp;

import hexlet.code.formatter.formatFactory.Format;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Stylish extends Format {

    private static final int TAB_CHAR_CONSTANT = 4;

    /**
     * Returns string after analyse data and formatting to specified input.
     * @param mapToFormat data from two files which need to analyse and formatting.
     * @return Returns string after analyse data and formatting to specified input.
     */
    @Override
    public String format(List<Map<String, Object>> mapToFormat) {

        Map<String, Object> analysedMap = new LinkedHashMap<>();
        for (Map<String, Object> diffNode : mapToFormat) {
            analysedMap.putAll(analyserMap(diffNode));
        }

        return formatMapToString(analysedMap);
    }

    private static String formatMapToString(final Map<String, Object> differentNodes) {
        return differentNodes.keySet().stream()
                .sorted(Comparator.comparing((String key) -> key.substring(TAB_CHAR_CONSTANT))
                        .thenComparing(key -> " -+".indexOf(key.charAt(2))))
                .map(value -> value + differentNodes.get(value))
                .collect(Collectors.joining("\n", "{\n", "\n}"));

    }

    private Map<String, Object> analyserMap(final Map<String, Object> map) {

        Map<String, Object> result = new LinkedHashMap<>();

        if (Objects.equals(map.get("status"), "changed")) {
            result.put("  - " + map.get("key") + ": ", map.get("old"));
            result.put("  + " + map.get("key") + ": ", map.get("new"));
        }

        if (Objects.equals(map.get("status"), "deleted")) {
            result.put("  - " + map.get("key") + ": ", map.get("old"));
        }

        if (Objects.equals(map.get("status"), "added")) {
            result.put("  + " + map.get("key") + ": ", map.get("new"));
        }

        if (Objects.equals(map.get("status"), "unchanged")) {
            result.put("    " + map.get("key") + ": ", map.get("old"));
        }

        return result;

    }

}
