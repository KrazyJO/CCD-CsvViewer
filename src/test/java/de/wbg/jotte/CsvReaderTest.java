package de.wbg.jotte;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import de.wbg.jotte.CSVData;
import de.wbg.jotte.CsvReader;
import org.junit.jupiter.api.Test;

class CsvReaderTest {

  @Test
  void transformLinesToData() {
    CsvReader reader = new CsvReader();
    CSVData data = new CSVData();

    ArrayList<String> input = new ArrayList<>();
    input.add("Name;Age;City");
    input.add("Peter;42;New York");
    input.add("Paul;57;London");

    reader.transformLinesToData(input, data);

    assertNotNull(data.getHeadline());
    assertEquals(2, data.getEntries().size());

    assertEquals(data.getHeadline().length, 4);
    assertEquals("No.", data.getHeadline()[0]);
    assertEquals("Name", data.getHeadline()[1]);
    assertEquals("Age", data.getHeadline()[2]);
    assertEquals("City", data.getHeadline()[3]);

    assertEquals(data.getEntries().get(0), "Peter;42;New York");
    assertEquals(data.getEntries().get(1), "Paul;57;London");
  }

  @Test
  void transformLine() {
    CsvReader reader = new CsvReader();

    String input = "abc";
    String[] actual = reader.transformLine(input);
    assertEquals(actual.length, 1);

    input = "abc;abc;abc";
    actual = reader.transformLine(input);
    assertEquals(actual.length, 3);
  }
}