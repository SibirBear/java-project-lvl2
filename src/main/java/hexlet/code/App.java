package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

import static hexlet.code.Differ.generate;
import static picocli.CommandLine.Option;
import static picocli.CommandLine.Parameters;
import static picocli.CommandLine.defaultExceptionHandler;


@Command(mixinStandardHelpOptions = true, version = "App version is @|fg(yellow) 1.0|@",
        name = "gendiff",
        description = "@|bold Compares two configuration files and shows a difference.|@")

public class App implements Callable<Integer> {

    @Option(names = {"-f", "--format"}, defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String format;

    @Parameters(description = "path to first file")
    private String filepath1;

    @Parameters(description = "path to second file")
    private String filepath2;

    public static void main(String[] args) {
        int code = new CommandLine(new App())
                .setColorScheme(defaultExceptionHandler().colorScheme()).execute(args);
        System.exit(code);

    }

    /**
     * The method for determining the differences of the specified files.
     *
     * @return status code = 0
     */
    @Override
    public Integer call() {
        try {
            System.out.println(generate(filepath1, filepath2, format));
            return 0;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

