package de.wbg.jotte;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CsvOutputTest {

  @Test
  public void firstTest() {
    Assertions.assertTrue(true);
  }

  @Test
  void testFormatLine() {

    CSVData data = new CSVData();
    CsvOutput outputter = new CsvOutput(data); // -> deswegen sind Konstruktoren doof!

    String input = "abc";
    String expected = "abc";
    String formattedOutput = outputter.formatLine(input);
    Assertions.assertEquals(expected, formattedOutput);

    input = "Peter;42;New York";
    expected = "Peter | 42 | New York";
    formattedOutput = outputter.formatLine(input);
    Assertions.assertEquals(expected, formattedOutput);
  }

}