package de.wbg.jotte;

import java.util.ArrayList;
import java.util.List;

public class CsvOutput {

    CSVData data;
    int pageSize = 3;
    String formattetHeadline;

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
        List<String> pageEntries = this.getCurrentPageEntries();
        ArrayList<Integer> maxColumnLengths = calculateMaxColumnLength(pageEntries);
        printHeadline(maxColumnLengths);
        printSeparator();
        printEntries(maxColumnLengths);
    }

    void printHeadline(ArrayList<Integer> maxColumnLengths) {
      generateHeadline(data.getHeadline(), maxColumnLengths);
    }

    void printSeparator() {
        String result = generateSeparatorLine();
        simplePrint(result);

    }

    String generateSeparatorLine() {
      if (formattetHeadline != null) {
        StringBuilder builder = new StringBuilder();
        builder.append("-".repeat(formattetHeadline.length() - 1));
        int index = formattetHeadline.indexOf("|");
        //replace first pipe character
        builder.setCharAt(index, '+');

        //replace all other pipe character
        while ((index = formattetHeadline.indexOf("|", index + 1)) != -1) {
          builder.setCharAt(index, '+');
        }

        return builder.toString();

      } else {
        //default separator
        return "------------------------------------";
      }
    }

    void printEntries(ArrayList<Integer> maxColumnLengths) {
        List<String> pageEntries = this.getCurrentPageEntries();

        for (String entry : pageEntries) {
            printPageEntry(entry, maxColumnLengths);
        }
    }

    void generateHeadline(String[] headline, ArrayList<Integer> maxColumnLengths) {

        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < headline.length; i++) {
            buffer.append(headline[i]);
            int size1 = headline[i].length();
            int size2 = maxColumnLengths.get(i);
            int result;
            if (headline[i].length() < maxColumnLengths.get(i) || headline[i].length() > maxColumnLengths.get(i)) {
                if (size1 <= size2) {
                    result = (maxColumnLengths.get(i) + 1) - headline[i].length();
                } else {
                    result = headline[i].length() - (maxColumnLengths.get(i) + 1);
                }
                for (var j = 0; j < result; j++) {
                    buffer.append(" ");
                }
                buffer.append("| ");
            }
        }
        formattetHeadline = buffer.toString();
        simplePrint(buffer.toString());

    }



    void simplePrint(String content) {
        System.out.println(content);
    }

    ArrayList<Integer> calculateMaxColumnLength(List<String> pageEntries) {
        ArrayList<Integer> maxLengthPerColumn = new ArrayList<>();
        for (String entry : pageEntries) {
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

        ArrayList<String> numberedEntries = new ArrayList<>();
        String entry;
        for (var i = from; i < to; i++) {
            entry = i + 1 + "." + ";" + this.data.getEntries().get(i);
            numberedEntries.add(entry);
        }
        return numberedEntries;
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
        StringBuilder buffer = new StringBuilder();
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
