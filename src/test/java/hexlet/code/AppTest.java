package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static hexlet.code.Differ.generate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    private final String PLAIN = "plain";
    private final String pathToResources = "src/test/resources/";

    private final String expectedNested = getDataFile(Path.of(pathToResources + "expected_nested"));
    private final String expectedPlain = getDataFile(Path.of(pathToResources + "expected_plain"));

    private final String firstTestFileJson = pathToResources + "test1.json";
    private final String secondTestFileJson = pathToResources + "test2.json";
    private final String firstTestFileYml = pathToResources + "test1.yaml";
    private final String secondTestFileYml = pathToResources + "test2.yml";


    @Test
    void nestedJsonTest() throws JsonProcessingException {
        assertEquals(expectedNested, generate(firstTestFileJson, secondTestFileJson));
    }

    @Test
    void nestedYamlTest() throws JsonProcessingException {
        assertEquals(expectedNested, generate(firstTestFileYml, secondTestFileYml));
    }

    @Test
    void plainJsonTest() throws JsonProcessingException {
        assertEquals(expectedPlain, generate(firstTestFileJson, secondTestFileJson, PLAIN));
    }

    @Test
    void plainYamlTest() throws JsonProcessingException {
        assertEquals(expectedPlain, generate(firstTestFileYml, secondTestFileYml, PLAIN));
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
