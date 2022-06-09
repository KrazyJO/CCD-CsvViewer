package de.wbg.jotte;

import org.jetbrains.annotations.NotNull;

public class Main {

    private static CommandLineTool commandLineTool;

    public static void main(String @NotNull [] args) {
        commandLineTool = new CommandLineTool();
        CsvReader reader = new CsvReader();
        CSVData data = reader.readFile("src/main/resources/persons.csv");
        CsvOutput outputter = new CsvOutput(data);

        PageArgResult result = ReadArgs.readPageSize(args);

        if (result.isHasError()) {
            commandLineTool.printErrorArgNotParsed(result.getErrorReason());
            outputter.setPageSize(3);
        } else if (!result.isArgFound()) {
            outputter.setPageSize(3);
        } else {
            outputter.setPageSize(result.getPageSizes());
        }
        outputter.outputPage(0);
        commandLineTool.setOutputter(outputter);
        commandLineTool.printMenu();
        commandLineTool.expectInput();


    }
}
