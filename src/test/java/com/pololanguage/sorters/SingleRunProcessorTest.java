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
import static org.junit.Assert.fail;

public class SingleRunProcessorTest {
  static final String inDir = System.getProperty("SINGLERUN_PATH");
  static final String rootOutPath = System.getProperty("TEST_OUT_PATH");
  static DownloadSorter sorter;
  static Processor processor;
  static String description;
  static Path outDir;

  @BeforeClass
  public static void setup() {
    assertNotNull(inDir);
    assertNotNull(rootOutPath);

    sorter =  new DownloadSorter();
    processor = new SingleRunProcessor();
    description = processor.getDescription();
    sorter.setProcessor(processor);

    try {
      outDir = Files.createTempDirectory(Paths.get(rootOutPath), "singlerun_");
    } catch (IOException err) {
      fail("Unable to create temp output directory for test in: "+ rootOutPath);
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
    final int NUM_EXPTECTED = 3;
    String rule = "*.{jpg,jpeg}";
    Path out;

    sorter.addHotFolder(inDir);
    try {
      out = Files.createTempDirectory(outDir, "JPG_");
    } catch (IOException err) {
      fail("Unable to create temp output directory for test: "+ err.getMessage());
      return; /* for the compiler */
    }
    sorter.addSortSpec(rule, out.toString(), RuleType.GLOB);

    sorter.run();

    try {
      assertEquals(NUM_EXPTECTED, getNumMatches(out, rule, RuleType.GLOB));
    } catch (IOException err) {
      fail(err.toString());
    }
  }

  @Test
  public void testRunRegex() {
    final int NUM_EXPTECTED = 2;
    String rule = ".*?\\.pdf";
    Path out;

    sorter.addHotFolder(inDir);
    try {
      out = Files.createTempDirectory(outDir, "PDF_");
    } catch (IOException err) {
      fail("Unable to create temp output directory for test: "+ err.getMessage());
      return; /* for the compiler */
    }
    sorter.addSortSpec(rule, out.toString(), RuleType.REGEX);

    sorter.run();

    try {
      assertEquals(NUM_EXPTECTED, getNumMatches(out, rule, RuleType.REGEX));
    } catch (IOException err) {
      fail(err.toString());
    }
  }

  /** Utility method to determine number of matching files in directory */
  private int getNumMatches(Path dir, String rule, RuleType type) throws IOException {
    int numMatches = 0;
    PathMatcher matcher = FileSystems.getDefault().getPathMatcher(type.toString() +":"+ rule);

    try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
      for (Path file : stream) {
        if (matcher.matches(file)) {
          ++numMatches;
        }
      }
    }
    return numMatches;
  }
}
