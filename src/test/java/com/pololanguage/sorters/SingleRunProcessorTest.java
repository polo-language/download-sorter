package com.pololanguage.sorters;

import java.io.IOException;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class SingleRunProcessorTest {
  static final String inPath = System.getProperty("SINGLERUN_PATH");
  static final String rootOutPath = System.getProperty("TEST_OUT_PATH");
  static DownloadSorter sorter;
  static Processor processor;
  static String description;
  static Path inDir;
  static Path outDir;

  @BeforeClass
  public static void setup() {
    assertNotNull(inPath);
    assertNotNull(rootOutPath);

    inDir = Paths.get(inPath);
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
  public void testRun() {
    Pattern patternPDF = Pattern.compile("*.pdf");
    Path outPDF;

    try {
      outPDF = Files.createTempDirectory(outDir, "PDF_");
    } catch (IOException err) {
      fail("Unable to create temp output directory for test in: "+ rootOutPath);
    }
    sorter.addHotFolder(inDir);
    // TODO:
    // create output folders and add sort specs 
    // run sort
    // verify sort 
  }
}
