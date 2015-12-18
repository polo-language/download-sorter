package com.pololanguage.sorters;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.FileSystems;
import java.nio.file.WatchService;
import java.nio.file.WatchKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Watches hot-folders and sorts as changes occur.
 */
public class WatchServiceProcessor implements Processor {
  private List<Path> hotFolders;
  private List<SortSpec> sortSpecs;
  
  private final WatchService watcher;
  private final Map<WatchKey,Path> keys;

  WatchServiceProcessor() throws IOException {
    watcher = FileSystems.getDefault().newWatchService();
    keys = new HashMap<WatchKey,Path>();
  }

  @Override
  public String getDescription() {
    return "File watcher processor: "+
           "watches hot folders for any changes.";
  }

  @Override
  public void initialize(List<Path> hotFolders, List<SortSpec> sortSpecs) {
    this.hotFolders = hotFolders;
    this.sortSpecs = sortSpecs;
  }

  @Override
  public void update() {
    // TODO
  }

  @Override
  public void run() {
    // TODO
  }

  @Override
  public void stop() {
    // TODO
  }


}
