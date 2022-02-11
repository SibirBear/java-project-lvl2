package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Differ {

    public static String generate(final String filePath1, final String filePath2) throws JsonProcessingException {
        String contentFileFirst = getData(filePath1);
        String contentFileSecond = getData(filePath2);
//        System.out.println("file1:\n" + contentFileFirst);
//        System.out.println("file2:\n" + contentFileSecond);
        Map<String, Object> firstMap = convertStringToMap(contentFileFirst);
        Map<String, Object> secondMap = convertStringToMap(contentFileSecond);

        List<Map<String, Object>> differentsList = analyserMaps(firstMap, secondMap);

        return format(mapToFormat(differentsList));
    }

    private static String getData(final String filePath) {
        Path path = Paths.get(filePath);
        StringBuilder sb = new StringBuilder();

        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(s -> sb.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    private static Map<String, Object> convertStringToMap(final String str) throws JsonProcessingException {
        ObjectMapper jsonObjMapper = new ObjectMapper();
        return jsonObjMapper.readValue(str, new TypeReference<>() {}); //<Map<String, Object>>()

    }

    private static List<Map<String, Object>> analyserMaps(final Map<String, Object> first,
                                                          final Map<String, Object> second) {
        List<Map<String, Object>> resultAnalyse = new ArrayList<>();
        Set<String> keys = allKeys(first, second);

        for (String key : keys) {
            Map<String, Object> node = new LinkedHashMap<>();

            if (!first.containsKey(key)) {
                node.putAll(differentNode(key, "added", second.get(key), second.get(key)));
            }

            if (!second.containsKey(key)) {
                node.putAll(differentNode(key, "deleted", first.get(key), first.get(key)));
            }

            if (first.containsKey(key)
                    && second.containsKey(key)
                    && !Objects.equals(first.get(key), second.get(key))) {
                node.putAll(differentNode(key, "changed", first.get(key), second.get(key)));
            }

            if (Objects.equals(first.get(key), second.get(key))) {
                node.putAll(differentNode(key, "unchanged", first.get(key), second.get(key)));
            }

            resultAnalyse.add(node);

        }

        return resultAnalyse;

    }

    private static Set<String> allKeys (final Map<String, Object> firstMap, final Map<String, Object> secondMap) {
        Set<String> keys = new HashSet<>(firstMap.keySet());
        keys.addAll(secondMap.keySet());
        return keys;
    }

    private static Map<String, Object> differentNode(String key, String status, Object oldValue, Object newValue) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("key", key);
        result.put("status", status);
        result.put("old", oldValue);
        result.put("new", newValue);

        return result;

    }

    private static String format(final Map<String, Object> differentNodes) {
        return differentNodes.keySet().stream()
                .sorted(Comparator.comparing((String key) -> key.substring(4))
                        .thenComparing(key -> " -+".indexOf(key.charAt(2))))
                .map(value -> value + differentNodes.get(value))
                .collect(Collectors.joining("\n", "{\n", "\n}"));

    }

    private static Map<String, Object> mapToFormat(final List<Map<String, Object>> listDifferentNodes) {

        Map<String, Object> analysedMap = new LinkedHashMap<>();
        for (Map<String, Object> diffNode : listDifferentNodes) {
            analysedMap.putAll(analyser(diffNode));
        }

        return analysedMap;

    }

    private static Map<String, Object> analyser(final Map<String, Object> map) {

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

