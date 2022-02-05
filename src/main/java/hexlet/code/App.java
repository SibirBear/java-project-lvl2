package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;


@Command(mixinStandardHelpOptions = true, version = "auto help demo - picocli 3.0",
        name = "gendiff",
        description = "Compares two configuration files and shows a difference.")

public class App implements Runnable {

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

