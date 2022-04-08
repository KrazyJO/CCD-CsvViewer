package de.wbg.jotte;

import java.util.ArrayList;
import java.util.List;

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
}
