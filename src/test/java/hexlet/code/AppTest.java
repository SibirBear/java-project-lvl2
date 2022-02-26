package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static hexlet.code.Differ.generate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    private final String plainFormat = "plain";
    private final String jsonFormat = "json";
    private final String pathToResources = "src/test/resources/";

    private final String expectedNested = getDataFile(Path.of(pathToResources + "expected_nested"));
    private final String expectedPlain = getDataFile(Path.of(pathToResources + "expected_plain"));
    private final String expectedJson = getDataFile(Path.of(pathToResources + "expected_json"));

    private final String firstTestFileJson = pathToResources + "test1.json";
    private final String secondTestFileJson = pathToResources + "test2.json";
    private final String firstTestFileYml = pathToResources + "test1.yaml";
    private final String secondTestFileYml = pathToResources + "test2.yml";


    @Test
    void nestedJsonTest() throws IOException {
        assertEquals(expectedNested, generate(firstTestFileJson, secondTestFileJson));
    }

    @Test
    void nestedYamlTest() throws IOException {
        assertEquals(expectedNested, generate(firstTestFileYml, secondTestFileYml));
    }

    @Test
    void plainJsonTest() throws IOException {
        assertEquals(expectedPlain, generate(firstTestFileJson, secondTestFileJson, plainFormat));
    }

    @Test
    void plainYamlTest() throws IOException {
        assertEquals(expectedPlain, generate(firstTestFileYml, secondTestFileYml, plainFormat));
    }

    @Test
    void jsonFormatJsonTest() throws IOException {
        assertEquals(expectedJson, generate(firstTestFileJson, secondTestFileJson, jsonFormat));
    }

    @Test
    void jsonFormatYamlTest() throws IOException {
        assertEquals(expectedJson, generate(firstTestFileYml, secondTestFileYml, jsonFormat));
    }


    private String getDataFile(final Path path) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            System.out.println("Test Error! Reading file " + path + ". " + e.getMessage());
        }

        return null;
    }

}
