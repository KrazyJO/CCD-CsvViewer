package de.wbg.jotte;

import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CsvReader {

  public CSVData readFile(String path) {
    CSVData data = new CSVData();
    Path p = Paths.get("src/de/wbg/jotte/persons.csv");
    try {
      BufferedReader bufferedReader = Files.newBufferedReader(p, StandardCharsets.UTF_8);

      // headline
      String headline = bufferedReader.readLine();
      String[] components = headline.split(";");
      data.setHeadline(components);

      // entries
      while (bufferedReader.ready()) {
        String l = bufferedReader.readLine(); //headline;
        if (l != null) {
          data.getEntries().add(l);
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("file not found");
      return null;
    }

    return data;
  }

}
