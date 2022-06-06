package de.wbg.jotte.tests;

import de.wbg.jotte.CSVData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CSVDataTest {

    private CSVData generateTestCSVData() {
        CSVData data = new CSVData();
        String headline[] = {"No.", "Name", "Age", "City"};

        data.setHeadline(headline);
        data.getEntries().add("Peter;42;New York");
        data.getEntries().add("Paul;57;London");
        data.getEntries().add("Mary;35;Munich");

        return data;
    }

    @Test
    public void testSortColumnByName() {
        CSVData data = generateTestCSVData();
        boolean canSort = data.sortByColumn("name");
        Assertions.assertTrue(canSort);

        Assertions.assertEquals("Mary", data.getEntries().get(0).split(";")[0]);
        Assertions.assertEquals("Paul", data.getEntries().get(1).split(";")[0]);
        Assertions.assertEquals("Peter", data.getEntries().get(2).split(";")[0]);
    }

    @Test
    public void testSortColumnByAge() {
        CSVData data = generateTestCSVData();
        boolean canSort = data.sortByColumn("age");
        Assertions.assertTrue(canSort);

        Assertions.assertEquals("Mary", data.getEntries().get(0).split(";")[0]);
        Assertions.assertEquals("Paul", data.getEntries().get(2).split(";")[0]);
        Assertions.assertEquals("Peter", data.getEntries().get(1).split(";")[0]);
    }

    @Test
    public void testSortColumnColumnNotFound() {
        CSVData data = generateTestCSVData();
        boolean canSort = data.sortByColumn("NotGiven");
        Assertions.assertFalse(canSort);

        Assertions.assertEquals("Mary", data.getEntries().get(2).split(";")[0]);
        Assertions.assertEquals("Paul", data.getEntries().get(1).split(";")[0]);
        Assertions.assertEquals("Peter", data.getEntries().get(0).split(";")[0]);
    }
}
