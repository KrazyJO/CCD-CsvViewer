package de.wbg.jotte;

import java.util.Arrays;

public class CsvOutput {

  CSVData data;

  public CsvOutput(CSVData data) {
    this.data = data;
  }

  void outputCsv() {
    printHeadline();
    printSeparator();
    printEntries();
  }

  void printHeadline() {
    String formattedHeadline = formatLine(Arrays.toString(data.getHeadline()));
    print(formattedHeadline);
  }

  void printSeparator() {
    print("-------------------------------");
  }

  void printEntries() {
    for(String entrie: data.getEntries()) {
      print(entrie);
    }
  }

  String formatLine(String line) {
    return line.replace(";", " | ");
  }

  void print(String content) {
    System.out.println(content);
  }
}
