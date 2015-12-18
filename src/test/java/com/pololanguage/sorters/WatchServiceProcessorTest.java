package com.pololanguage.sorters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.FileSystems;
import java.nio.file.WatchService;
import java.nio.file.WatchKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Watches hot-folders and sorts as changes occur.
 */
public class WatchServiceProcessorTest {
  static final String inDir = System.getProperty("WATCHSERVICE_PATH");
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
    try {
      processor = new WatchServiceProcessor();
    } catch (IOException err) {
      fail(err.getMessage());
    }
    description = processor.getDescription();
    sorter.setProcessor(processor);
    sorter.addHotFolder(inDir);

    try {
      outDir = Files.createTempDirectory(Paths.get(ourRootDir), "watchservice_");
    } catch (IOException err) {
      fail("Unable to create temp output directory for test in: "+ ourRootDir);
    }
  }

  @Test
  public void testGetDescription() {
    final String expectedDesc = "File watcher processor: "+
                                "watches hot folders for any changes.";
    assertEquals(expectedDesc, description);
    assertEquals(expectedDesc, sorter.getProcessorDescription());
  }

  // TODO
  // test sorting files already in directory
  // test add files to directory and check sorted correctly
  // test rename file extension of file in directory and check if sorted correctly
}
