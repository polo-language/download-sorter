package com.pololanguage.sorters;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
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
      public void run() {}
      @Override
      public String getDescription() {
        return description;
      }
    });
    assertEquals(description, sorter.getProcessorDescription());
  }

  @Test
  public void testAddHotFolder() {
    final Path dir_01 = Paths.get("test/folder_01");
    final Path dir_02 = Paths.get("test/folder_02");
    int initialSize = sorter.getHotFolders().size();
    sorter.addHotFolder(dir_01);
    assertEquals(initialSize, sorter.getHotFolders().size() - 1);
    sorter.addHotFolder(dir_02);
    assertEquals(initialSize, sorter.getHotFolders().size() - 2);
  }

  @Test
  public void testAddSortSpec() {
    final Pattern regex = Pattern.compile("test*\t[0-9]");
    final Path dir = Paths.get("test/folder_01");
    int initialSize = sorter.getSortSpecs().size();
    sorter.addSortSpec(regex, dir);
    assertEquals(initialSize, sorter.getSortSpecs().size() - 1);
  }

  @Test
  public void testAddSortSpecStrings() {
    final String regex = "test*\t[0-9]";
    final String dirString = "test/folder_01";
    int initialSize = sorter.getSortSpecs().size();
    sorter.addSortSpec(regex, dirString);
    assertEquals(initialSize, sorter.getSortSpecs().size() - 1);
  }
}
