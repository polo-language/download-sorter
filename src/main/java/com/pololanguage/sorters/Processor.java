package com.pololanguage.sorters;

import java.nio.file.Path;
import java.util.List;

/**
 * The processor that determines how/when scanning and sorting occurs.
 */
public interface Processor extends Runnable {
  /** Returns a user-friendly String describing the processor type, functioning, etc. */
  String getDescription();

  /** Provides processor with references to hot folders and sort specs */
  void initialize(List<Path> hotFolders, List<SortSpec> sortSpecs);

  /** Shuts this processor down */
  void stop();
}
