package de.wbg.jotte;

import org.jetbrains.annotations.NotNull;

public class Main {

    private static CommandLineTool commandLineTool;

    public static void main(String @NotNull [] args) {
        commandLineTool = new CommandLineTool();
        CsvReader reader = new CsvReader();
        CSVData data = reader.readFile("src/de/wbg/jotte/persons.csv");
        CsvOutput outputter = new CsvOutput(data);

        if (args.length > 0) {
            // we assume that the first arg is the page size and the user will never ever do a failure
            var pageSize = Integer.parseInt(args[0]);
            outputter.setPageSize(pageSize);
        }

        outputter.outputPage(0);
        commandLineTool.setOutputter(outputter);
        commandLineTool.printMenu();
        commandLineTool.expectInput();
    }
}
