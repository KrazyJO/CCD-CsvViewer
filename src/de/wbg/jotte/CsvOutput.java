package de.wbg.jotte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvOutput {

  CSVData data;
  int pageSize = 3;

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  int currentPage;

  public CsvOutput(CSVData data) {
    this.data = data;
    this.currentPage = 0;
  }

  void outputPage(int page) {
    this.currentPage = page;
    printHeadline();
    printSeparator();
    printEntries();
  }

  void printHeadline() {
    String formattedHeadline = formatLine(Arrays.toString(data.getHeadline()));
    simplePrint(formattedHeadline);
  }

  void printSeparator() {
    simplePrint("-------------------------------");
  }

  void printEntries() {
    List<String> pageEntries = this.getCurrentPageEntries();
    ArrayList<Integer> maxColumnLengths = calculateMaxColumnLength(pageEntries);

    for(String entry: pageEntries) {
      printPageEntry(entry, maxColumnLengths);
    }
  }

  void simplePrint(String content) {
    System.out.println(content);
  }

  ArrayList<Integer> calculateMaxColumnLength(List<String> pageEntries) {
    ArrayList<Integer> maxLengthPerColumn = new ArrayList<>();
    for (String entry: pageEntries) {
      var splitted = entry.split(";");
      for (var i = 0; i < splitted.length; i++) {
        var valueLength = splitted[i].length();
        if (maxLengthPerColumn.size() <= i) {
          maxLengthPerColumn.add(valueLength);
          continue;
        }

        var columnLength = maxLengthPerColumn.get(i);
        if (valueLength > columnLength) {
          maxLengthPerColumn.set(i, valueLength);
        }
      }
    }

    return maxLengthPerColumn;
  }

  List<String> getCurrentPageEntries() {
    int from = pageSize * currentPage;
    int to = from + pageSize;

    if (to > this.data.getEntries().size()) {
      to = this.data.getEntries().size();
    }

    return this.data.getEntries().subList(from, to);
  }

  int getLastPageCount() {
    int pages = this.data.getEntries().size() / this.pageSize;
    if (pages < this.data.getEntries().size() * this.pageSize) {
      return pages + 1;
    }
    return pages;
  }

  String formatLine(String line) {
    return line.replace(";", " | ");
  }

  void printPageEntry(String rawEntry, ArrayList<Integer> maxLengthPerColumn) {
    StringBuffer buffer = new StringBuffer();
    var splitted = rawEntry.split(";");
    String value;
    for (var i = 0; i < splitted.length; i++) {
      value = splitted[i];
      buffer.append(value);

      if (value.length() < maxLengthPerColumn.get(i)) {
        for (var j = 0; j < maxLengthPerColumn.get(i) - value.length(); j++) {
          buffer.append(" ");
        }
      }

      buffer.append(" | ");

    }
    simplePrint(buffer.toString());
  }

}
