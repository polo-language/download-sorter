package com.pololanguage.sorters;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Scans hot folders and sorts once per run.
 */
public class SingleRunProcessor implements Processor {
  private List<Path> hotFolders;
  private List<SortSpec> sortSpecs;

  @Override
  public String getDescription() {
    return "Single run processor: " + 
           "scans hot folders and sorts once per run.";
  }

  @Override
  public void initialize(List<Path> hotFolders, List<SortSpec> sortSpecs) {
    this.hotFolders = hotFolders;
    this.sortSpecs = sortSpecs;
  }

  @Override
  public void update() {
    /*
    Do nothing.
    Processor reads current state on run, no need to update.
    */
  }

  @Override
  public void run() {
    for (Path folder : hotFolders) {
      try (DirectoryStream<Path> stream = Files.newDirectoryStream(folder)) {
        for (Path file : stream) {
          for (SortSpec spec : sortSpecs) {
            spec.moveIfMatches(file);
          }
        }
      } catch (IOException err) {
        // TODO
      }
    }
  }

  @Override
  public void stop() {
    /*
    Do nothing.
    This processor scans one time per run only, then stops.
    There is no ongoing operation to stop.
    */
  }
}
