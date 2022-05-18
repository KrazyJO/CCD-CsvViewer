package de.wbg.jotte;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CsvOutputTest {

  private CsvOutput getTestOutputter() {
    CsvReader reader = new CsvReader();
    CSVData data = reader.readFile("src/de/wbg/jotte/persons.csv");
    CsvOutput outputter = new CsvOutput(data); // -> deswegen sind Konstruktoren doof!
    return  outputter;
  }

  @Test
  public void firstTest() {
    Assertions.assertTrue(true);
  }

  @Test
  void testFormatLine() {
    CsvOutput outputter = getTestOutputter();

    String formattedOutput = outputter.formatLine("abc");
    Assertions.assertEquals("abc", formattedOutput);

    formattedOutput = outputter.formatLine("Peter;42;New York");
    Assertions.assertEquals("Peter | 42 | New York", formattedOutput);
  }

  @Test
  void testGetCurrentPageEntries() {
    CsvOutput outputter = getTestOutputter();

    List<String> entries = outputter.getCurrentPageEntries();

    //page: 0
    Assertions.assertTrue(entries.get(0).startsWith("Peter"));
    // Peter;42;New York
    Assertions.assertTrue(entries.get(1).startsWith("Paul"));
    // Paul;57;London
    Assertions.assertTrue(entries.get(2).startsWith("Mary"));
    // Mary;35;Munich

    //page: 1
    outputter.setCurrentPage(1);
    entries = outputter.getCurrentPageEntries();
    // Jaques;66;Paris
    Assertions.assertTrue(entries.get(0).startsWith("Jaques"));
    // Yuri;23;Moscow
    Assertions.assertTrue(entries.get(1).startsWith("Yuri"));
    // Stephanie;47;Stockholm
    Assertions.assertTrue(entries.get(2).startsWith("Stephanie"));

    //page: 2
    outputter.setCurrentPage(2);
    entries = outputter.getCurrentPageEntries();
    // Nadia;29;Madrid
    Assertions.assertTrue(entries.get(0).startsWith("Nadia"));
  }

  @Test
  void testGetLastPageCount() {
    CsvOutput outputter = getTestOutputter();
    Assertions.assertEquals(3, outputter.getLastPageCount());
  }

}