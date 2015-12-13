package com.pololanguage.sorters;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SortSpecTest {
  private static final String fakePathString = "folders/out";
  @Test
  public void testContstructor() {
    final Pattern expectedPattern = Pattern.compile("a*5\\\n");
    final Path expectedDir = Paths.get(fakePathString);
    SortSpec spec_01 = new SortSpec(expectedPattern, expectedDir);

    assertEquals(expectedPattern, spec_01.rule);
    assertEquals(expectedDir, spec_01.dir);
  }

  @Test
  public void testContstructorStrings() {
    final String expectedPattern = "a*5\\\n";
    final String expectedDirString = fakePathString;
    SortSpec spec_01 = new SortSpec(expectedPattern, expectedDirString);

    assertEquals(expectedPattern, spec_01.rule.toString());
    assertEquals(Paths.get(expectedDirString), spec_01.dir);
  }
}
