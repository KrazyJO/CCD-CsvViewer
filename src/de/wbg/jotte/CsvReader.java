package de.wbg.jotte;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CsvReader {

  public CSVData readFile(String path) {
    CSVData data = new CSVData();

    ArrayList<String> lines = readLinesFromFile(path);
    transformLinesToData(lines, data);

    return data;
  }

  ArrayList<String> readLinesFromFile(String path) {
    Path p = Paths.get(path);
    ArrayList<String> lines = new ArrayList<>();

    try {
      BufferedReader bufferedReader = Files.newBufferedReader(p, StandardCharsets.UTF_8);

      while (bufferedReader.ready()) {
        String l = bufferedReader.readLine(); //headline;
        if (l != null) {
          lines.add(l);
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("file not found");
      return null;
    }

    return lines;
  }

  void transformLinesToData(ArrayList<String> lines, CSVData data) {
    data.setHeadline(transformLine("No.;" +lines.get(0)));
    lines.subList(1, lines.size()).forEach(l -> {
      data.getEntries().add(l);
    });
  }

  String[] transformLine(String line) {
    return line.split(";");
  }

}
