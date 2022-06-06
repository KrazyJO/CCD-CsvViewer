package de.wbg.jotte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CSVData {

  private String[] headline;
  private List<String> entries = new ArrayList<>();

  public String[] getHeadline() {
    return headline;
  }

  public void setHeadline(String[] headline) {
    this.headline = headline;
  }

  public List<String> getEntries() {
    return entries;
  }

  public void setEntries(List<String> entries) {
    this.entries = entries;
  }

  ReturnArg findHeadlineColumnNo(String columnName) {
    ReturnArg returnArg = new ReturnArg();
    for (var i = 0; i < headline.length; i++) {
      if (headline[i].toLowerCase(Locale.ROOT).equals(columnName)) {
        returnArg.isPossible = true;
        returnArg.setValue(i-1);
        break;
      }
    }
    return returnArg;
  }

  private void sortEntriesArray(int columnNo) {
    Collections.sort(entries, (String lhs,String rhs) -> {
      String lhsColumnArg = lhs.split(";")[columnNo];
      String rhsColumnArg = rhs.split(";")[columnNo];
      return lhsColumnArg.compareToIgnoreCase(rhsColumnArg);
    });
  }

  public boolean sortByColumn(String columnName) {
    ReturnArg columnNo = findHeadlineColumnNo(columnName);
    if (!columnNo.isPossible) {
      return false;
    }

    sortEntriesArray(columnNo.getIntValue());
    return true;
  }
}
