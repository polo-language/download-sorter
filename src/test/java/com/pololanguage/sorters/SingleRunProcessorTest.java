package com.pololanguage.sorters;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SingleRunProcessorTest {
  static final String inDir = System.getProperty("SINGLERUN_PATH");
  static final String ourRootDir = System.getProperty("TEST_OUT_PATH");
  static DownloadSorter sorter;
  static Processor processor;
  static String description;
  static Path outDir;

  @BeforeClass
  public static void setup() {
    assertNotNull(inDir);
    assertNotNull(ourRootDir);

    sorter =  new DownloadSorter();
    processor = new SingleRunProcessor();
    description = processor.getDescription();
    sorter.setProcessor(processor);
    sorter.addHotFolder(inDir);

    try {
      outDir = Files.createTempDirectory(Paths.get(ourRootDir), "singlerun_");
    } catch (IOException err) {
      fail("Unable to create temp output directory for test in: "+ ourRootDir);
    }
  }

  @Test
  public void testGetDescription() {
    final String expectedDesc = "Single run processor: " + 
                                "scans hot folders and sorts once per run.";
    assertEquals(expectedDesc, description);
    assertEquals(expectedDesc, sorter.getProcessorDescription());
  }

  @Test
  public void testRunGlob() {
    matchRunner(3, RuleType.GLOB, "*.{jpg,jpeg}", "GLOB_JPG");
  }

  @Test
  public void testRunGlobPrefix() {
    matchRunner(2, RuleType.GLOB, "a*", "GLOB_A_PREFIX");
  }

  @Test
  public void testRunRegex() {
    matchRunner(2, RuleType.REGEX, ".*?\\.pdf", "REGEX_PDF");
  }

  /** Utility method for running matching test */
  private void matchRunner(final int EXPECTED, final RuleType type, final String rule, final String prefix) {
    Path out;

    try {
      out = Files.createTempDirectory(outDir, prefix +"_");
    } catch (IOException err) {
      fail("Unable to create temp output directory for test: "+ err.getMessage());
      return; /* for the compiler */
    }
    sorter.addSortSpec(rule, out.toString(), type);

    sorter.run();

    try {
      assertEquals(EXPECTED, getNumMatches(out, rule, type));
    } catch (IOException err) {
      fail(err.toString());
    }
  }

  /** Utility method to determine number of matching files in directory */
  private static int getNumMatches(Path dir, String rule, RuleType type) throws IOException {
    final PathMatcher matcher = FileSystems.getDefault().getPathMatcher(type.toString() +":"+ rule);
    int numMatches = 0;

    try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
      for (Path file : stream) {
        if (matcher.matches(file.getFileName())) {
          ++numMatches;
        }
      }
    }
    return numMatches;
  }
}
