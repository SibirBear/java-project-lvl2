package hexlet.code;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static hexlet.code.formatters.FormattersConstants.ADDED_VALUE;
import static hexlet.code.formatters.FormattersConstants.CHANGED_VALUE;
import static hexlet.code.formatters.FormattersConstants.DELETED_VALUE;
import static hexlet.code.formatters.FormattersConstants.KEY;
import static hexlet.code.formatters.FormattersConstants.NEW_VALUE;
import static hexlet.code.formatters.FormattersConstants.OLD_VALUE;
import static hexlet.code.formatters.FormattersConstants.STATUS;
import static hexlet.code.formatters.FormattersConstants.UNCHANGED_VALUE;

public class Analyser {

    public static List<Map<String, Object>> analyserMaps(final Map<String, Object> first,
                                                          final Map<String, Object> second) {
        List<Map<String, Object>> resultAnalyse = new ArrayList<>();
        Set<String> keys = allKeys(first, second);

        for (String key : keys) {
            Map<String, Object> node = new LinkedHashMap<>();

            if (!first.containsKey(key)) {
                node.putAll(differentNode(key, ADDED_VALUE, second.get(key), second.get(key)));
            }

            if (!second.containsKey(key)) {
                node.putAll(differentNode(key, DELETED_VALUE, first.get(key), first.get(key)));
            }

            if (first.containsKey(key)
                    && second.containsKey(key)
                    && !Objects.equals(first.get(key), second.get(key))) {
                node.putAll(differentNode(key, CHANGED_VALUE, first.get(key), second.get(key)));
            }

            if (Objects.equals(first.get(key), second.get(key))) {
                node.putAll(differentNode(key, UNCHANGED_VALUE, first.get(key), second.get(key)));
            }

            resultAnalyse.add(node);

        }

        return resultAnalyse;

    }

    private static Set<String> allKeys(final Map<String, Object> firstMap, final Map<String, Object> secondMap) {
        Set<String> keys = new HashSet<>(firstMap.keySet());
        keys.addAll(secondMap.keySet());
        return keys;
    }

    private static Map<String, Object> differentNode(String key, String status, Object oldValue, Object newValue) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put(KEY, key);
        result.put(STATUS, status);
        result.put(OLD_VALUE, oldValue);
        result.put(NEW_VALUE, newValue);

        return result;

    }

}
