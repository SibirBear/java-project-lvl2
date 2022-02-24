package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static hexlet.code.Differ.generate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    private final String pathToResources = "src/test/resources/";

    private final String expectedFlat = getDataFile(Path.of(pathToResources + "expected_flat"));
    private final String expectedNested = getDataFile(Path.of(pathToResources + "expected_nested"));

    @Test
    void flatJsonTest() throws JsonProcessingException {
        final String firstTestFile = pathToResources + "test_file1.json";
        final String secondTestFile = pathToResources + "test_file2.json";

        assertEquals(expectedFlat, generate(firstTestFile, secondTestFile));
    }

    @Test
    void flatYamlTest() throws JsonProcessingException {
        final String firstTestFile = pathToResources + "test_file1.yaml";
        final String secondTestFile = pathToResources + "test_file2.yml";

        assertEquals(expectedFlat, generate(firstTestFile, secondTestFile));
    }

    @Test
    void nestedJsonTest() throws JsonProcessingException {
        final String firstTestFile = pathToResources + "test_nested1.json";
        final String secondTestFile = pathToResources + "test_nested2.json";

        assertEquals(expectedNested, generate(firstTestFile, secondTestFile));
    }

    @Test
    void nestedYamlTest() throws JsonProcessingException {
        final String firstTestFile = pathToResources + "test_nested1.yaml";
        final String secondTestFile = pathToResources + "test_nested2.yml";

        assertEquals(expectedNested, generate(firstTestFile, secondTestFile));
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
