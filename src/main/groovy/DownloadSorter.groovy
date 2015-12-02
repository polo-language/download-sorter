package com.pololanguage.sorters

import java.util.regex.Pattern


class DownloadSorter implements Runnable {
  final private ArrayList<File> inFolders           // hot folder array
  final private ArrayList<SortSpec> sortSpecs       // specification of sort rules and output folders
  private Runnable processor

  DownloadSorter() {
    inFolders = new ArrayList<>()
    sortSpecs = new ArrayList<>()
  }

  @Override
  void run() {}
  void scanFolders() {}

  /** Set the concrete processor */
  void setProcessor(Runnable processor) {
    this.processor = processor
  }

  /** Add input directory to list */
  void addInput(File file) {
    inFolders.add(file)
  }

  /** Add SortSpec to list */
  void addSortSpec(Pattern pattern, File folder) {
    sortSpecs.add(new SortSpec(pattern, folder))
  }

  /** Convenience method with all String arguments */
  void addSortSpec(String patternString, String path) {
    addSortSpec(Pattern.compile(patternString), new File(path))
  }
  
}
