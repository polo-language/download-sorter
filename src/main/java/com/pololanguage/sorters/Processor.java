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

  /** Notifies processor that a change has been made to the hot-folders or sort specs */
  void update();

  /** Shuts this processor down */
  void stop();
}
