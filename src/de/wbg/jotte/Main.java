package de.wbg.jotte;

public class Main {

    public static void main(String[] args) {

        CsvReader reader = new CsvReader();
        CSVData data = reader.readFile("./persons.csv");
        CsvOutput outputter = new CsvOutput(data);
        outputter.outputCsv();
        //console input, menu

    }
}
