package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

import static hexlet.code.Differ.generate;
import static picocli.CommandLine.*;


@Command(mixinStandardHelpOptions = true, version = "Version App is 1.0",
        name = "gendiff",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {

    @Option(names = {"-f", "--format"},description = "output format [default: stylish]")
    private String format;

    @Parameters(description = "path to first file")
    private String filepath1;

    @Parameters(description = "path to second file")
    private String filepath2;

    public static void main(String[] args) {
        int code = new CommandLine(new App()).execute(args);
        System.exit(code);

    }

    @Override
    public Integer call() throws JsonProcessingException {
        System.out.println(generate(filepath1, filepath2));
        return 0;
    }
}

