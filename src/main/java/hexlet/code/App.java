package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import static picocli.CommandLine.*;


@Command(mixinStandardHelpOptions = true, version = "1.0",
        name = "gendiff",
        description = "Compares two configuration files and shows a difference.")

public class App implements Runnable {

    @Option(names = {"-f", "--format"},description = "output format [default: stylish]")
    private String format;

    @Parameters(description = "path to first file")
    private String filepath1;

    @Parameters(description = "path to second file")
    private String filepath2;

    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine(new App());
        commandLine.parseArgs(args);
        if (commandLine.isUsageHelpRequested()) {
            commandLine.usage(System.out);
        } else if (commandLine.isVersionHelpRequested()) {
            commandLine.printVersionHelp(System.out);
        }
    }

    @Override
    public void run() {

    }
}

