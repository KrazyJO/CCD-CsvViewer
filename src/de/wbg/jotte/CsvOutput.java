package de.wbg.jotte;

import java.util.Arrays;

public class CsvOutput {

  private CSVData data;

  public CsvOutput(CSVData data) {
    this.data = data;
  }

  public void outputCsv() {
    printHeadline();
    printSeparator();
    printEntries();
  }

  private void printHeadline() {
    String formattedHeadline = formatLine(Arrays.toString(data.getHeadline()));
    print(formattedHeadline);
  }

  private void printSeparator() {
    print("-------------------------------");
  }

  private void printEntries() {
    for(String entrie: data.getEntries()) {
      print(entrie);
    }
  }

  private String formatLine(String line) {
    return line.replace(";", " | ");
  }

  private void print(String content) {
    System.out.println(content);
  }
}
