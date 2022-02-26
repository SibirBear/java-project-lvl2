package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static hexlet.code.Analyser.analyserMaps;
import static hexlet.code.Parser.parser;
import static hexlet.code.Formatter.useFormat;
import static hexlet.code.formatFactory.FormatConstant.STYLISH;

public class Differ {

    public static String generate(final String filePath1, final String filePath2)
            throws IOException {
        return generate(filePath1, filePath2, STYLISH);
    }

    public static String generate(final String filePath1, final String filePath2,
                                  final String format) throws IOException {
        String contentFileFirst = getData(filePath1);
        String contentFileSecond = getData(filePath2);
        Map<String, Object> firstMap = parser(contentFileFirst, filePath1);
        Map<String, Object> secondMap = parser(contentFileSecond, filePath2);

        List<Map<String, Object>> differenceList = analyserMaps(firstMap, secondMap);

        return useFormat(differenceList, format);
    }

    private static String getData(final String filePath) {
        Path path = Paths.get(filePath);
        StringBuilder sb = new StringBuilder();

        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(s -> sb.append(s).append("\n"));
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return sb.toString();
    }

}
