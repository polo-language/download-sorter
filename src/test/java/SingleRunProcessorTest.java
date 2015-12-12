package com.pololanguage.sorters;

import java.io.File;
import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SingleRunProcessorTest {
  static final String inPath = System.getProperty("SINGLERUN_PATH");
  static final String rootOutPath = System.getProperty("TEST_OUT_PATH");
  static DownloadSorter sorter;
  static Processor processor;
  static String description;
  static File inDir;
  static File outDir;

  @BeforeClass
  public static void setup() {
    assertNotNull(inPath);
    assertNotNull(rootOutPath);

    inDir = new File(inPath);
    outDir = new File(rootOutPath, "singlerun");
    sorter =  new DownloadSorter();
    processor = new SingleRunProcessor();
    description = processor.getDescription();
    sorter.setProcessor(processor);
    outDir.mkdir();
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
    //sorter.addHotFolder(inDir);
    // TODO:
    // create and add hot folder
    // create output folders and add sort specs 
    // run sort
    // verify sort
  }
}
