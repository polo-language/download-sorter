package com.pololanguage.sorters;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Stores and processes regex sort rules to move/copy files from specified
 * hot folders to specified destination folders
 */
public class DownloadSorter implements Runnable {
  /** List of folders to scan for matches - 'hot folders' */
  final private ArrayList<Path> hotFolders;

  /** List of sort rules and destination folders */
  final private ArrayList<SortSpec> sortSpecs;

  /**
   * The processor that determines how/when scanning and sorting occurs.
   * Not final - can be reset during runtime.
   */
  private Processor processor;

  DownloadSorter() {
    hotFolders = new ArrayList<>();
    sortSpecs = new ArrayList<>();
  }

  @Override
  public void run() {
    if (processor != null) {
      processor.run();
    }
  }

  /** Sets the concrete processor. */
  void setProcessor(Processor processor) {
    this.processor = processor;
  }

  /** Adds input directory to list. */
  void addHotFolder(Path dir) {
    hotFolders.add(dir);
  }

  /** Get unmodifiable copy of the hot folders. */
  List getHotFolders() {
    return Collections.unmodifiableList(hotFolders);
  }

  /** Get unmodifiable copy of the sort specs. */
  List getSortSpecs() {
    return Collections.unmodifiableList(sortSpecs);
  }

  /** Adds SortSpec to list. */
  void addSortSpec(Pattern pattern, Path dir) {
    sortSpecs.add(new SortSpec(pattern, dir));
  }

  /** Adds SortSpec to list - all arguments String. */
  void addSortSpec(String patternString, String path) {
    addSortSpec(Pattern.compile(patternString), Paths.get(path));
  }

  /** Returns the processor description. */
  public String getProcessorDescription() {
    return (processor == null) ? null : processor.getDescription();
  }
}
