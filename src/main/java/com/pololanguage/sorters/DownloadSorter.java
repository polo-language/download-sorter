package com.pololanguage.sorters;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Stores and processes sort rules to move/copy files from specified
 * hot folders to specified destination directories.
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
  public void setProcessor(Processor processor) {
    this.processor = processor;
    processor.initialize(getHotFolders(), getSortSpecs());
  }

  /** Adds input directory to list. */
  public void addHotFolder(String dir) {
    Path path = Paths.get(dir);
    if (!hotFolders.contains(path)) {
      hotFolders.add(path);
    }
  }

  /** Adds SortSpec to list. */
  public void addSortSpec(SortSpec spec) {
    sortSpecs.add(spec);
  }

  /** Creates a {@code SortSpec} from the parameters and adds it to the list. */
  public void addSortSpec(String rule, String dir, RuleType type) {
    addSortSpec(new SortSpec(rule, dir, type));
  }

  /** Get unmodifiable copy of the hot folders. */
  List<Path> getHotFolders() {
    return Collections.unmodifiableList(hotFolders);
  }

  /** Get unmodifiable copy of the sort specs. */
  List<SortSpec> getSortSpecs() {
    return Collections.unmodifiableList(sortSpecs);
  }

  /** Returns the processor description. */
  public String getProcessorDescription() {
    return (processor == null) ? null : processor.getDescription();
  }
}
