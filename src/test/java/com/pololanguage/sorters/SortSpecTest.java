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
  public void testContstructorGlob() {
    final String expectedPatternString = "*\\.java";
    final String expectedDirString = fakePathString;
    SortSpec spec_01 = new SortSpec(expectedPatternString,
                                    expectedDirString,
                                    RuleType.GLOB);
    assertEquals(expectedPatternString, spec_01.getRule());
    assertEquals(expectedDirString, spec_01.getDirectory());
  }

  @Test
  public void testContstructorRegex() {
    final String expectedPatternString = ".*?\\.java";
    final String expectedDirString = fakePathString;
    SortSpec spec_01 = new SortSpec(expectedPatternString,
                                    expectedDirString,
                                    RuleType.REGEX);
    assertEquals(expectedPatternString, spec_01.getRule());
    assertEquals(expectedDirString, spec_01.getDirectory());
  }
}
