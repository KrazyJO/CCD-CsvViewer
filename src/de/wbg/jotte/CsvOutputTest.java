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

    String formattedOutput = outputter.formatLine("abc");
    Assertions.assertEquals("abc", formattedOutput);

    formattedOutput = outputter.formatLine("Peter;42;New York");
    Assertions.assertEquals("Peter | 42 | New York", formattedOutput);
  }

}