package com.pololanguage.sorters;

import java.io.File;
import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.assertEquals;

public class SingleRunProcessorTest {
  static DownloadSorter sorter;
  static Processor processor;
  static String description;
  static final File outDir = new File("build/tmp-test-out/SingleRunProcessor");
  static final File inDir = new File("test/in_01/");

  @BeforeClass
  public static void setup() {
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
    sorter.addHotFolder();
    // TODO:
    // create and add hot folder
    // create output folders and add sort specs 
    // run sort
    // verify sort
  }
}
