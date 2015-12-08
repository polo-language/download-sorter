package com.pololanguage.sorters;

import java.io.File;
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
  final private ArrayList<File> hotFolders;

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
  void addHotFolder(File file) {
    hotFolders.add(file);
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
  void addSortSpec(Pattern pattern, File folder) {
    sortSpecs.add(new SortSpec(pattern, folder));
  }

  /** Adds SortSpec to list - all arguments String. */
  void addSortSpec(String patternString, String path) {
    addSortSpec(Pattern.compile(patternString), new File(path));
  }

  /** Returns the processor description. */
  public String getProcessorDescription() {
    return (processor == null) ? null : processor.getDescription();
  }
}
