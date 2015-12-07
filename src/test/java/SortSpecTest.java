package com.pololanguage.sorters;

import java.io.File;
import java.util.regex.Pattern;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SortSpecTest {
  @Test
  public void testContstructor() {
    final Pattern expectedPattern = Pattern.compile("a*5\\\n");
    final File expectedFolder = new File("folders/out_01/");
    SortSpec spec_01 = new SortSpec(expectedPattern, expectedFolder);

    assertEquals(expectedPattern, spec_01.rule);
    assertEquals(expectedFolder, spec_01.folder);
  }

  @Test
  public void testContstructorStrings() {
    final String expectedPattern = "a*5\\\n";
    final String expectedFolder = "folders/out_01/";
    SortSpec spec_01 = new SortSpec(expectedPattern, expectedFolder);

    assertEquals(expectedPattern, spec_01.rule.toString());
    assertEquals(new File(expectedFolder).getAbsolutePath(), 
                 spec_01.folder.getAbsolutePath());
  }
}
