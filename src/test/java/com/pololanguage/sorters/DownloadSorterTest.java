package com.pololanguage.sorters;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

public class DownloadSorterTest {
  static DownloadSorter sorter;

  @BeforeClass
  public static void setup() {
    sorter =  new DownloadSorter();
  }

  @Test
  public void testSetProcessor() {
    final String description = "testSetProcessor description";
    assertNull(sorter.getProcessorDescription());
    sorter.setProcessor(new Processor() {
      @Override
      public void run() { /* do nothing */ }
      @Override
      public void stop() { /* do nothing */ }
      @Override
      public void initialize(List hotFolders, List sortSpecs) { /* do nothing */ }
      @Override
      public void update() { /* do nothing */ }
      
      @Override
      public String getDescription() {
        return description;
      }
    });
    assertEquals(description, sorter.getProcessorDescription());
  }

  @Test
  public void testAddHotFolder() {
    final String dir1 = "test/folder_01";
    final String dir2 = "test/folder_02";
    int initialSize = sorter.getHotFolders().size();
    sorter.addHotFolder(dir1);
    assertEquals(initialSize, sorter.getHotFolders().size() - 1);
    sorter.addHotFolder(dir2);
    assertEquals(initialSize, sorter.getHotFolders().size() - 2);
  }

  @Test
  public void testAddSortSpec() {
    final String rule = "test*\t[0-9]";
    final String dir = "test/folder_01";
    int initialSize = sorter.getSortSpecs().size();
    sorter.addSortSpec(rule, dir, RuleType.REGEX);
    assertEquals(initialSize, sorter.getSortSpecs().size() - 1);
  }

  @Test
  public void testAddSortSpecStrings() {
    final String rule = "test*\t[0-9]";
    final String dirString = "test/folder_01";
    int initialSize = sorter.getSortSpecs().size();
    sorter.addSortSpec(rule, dirString, RuleType.REGEX);
    assertEquals(initialSize, sorter.getSortSpecs().size() - 1);
  }
}
