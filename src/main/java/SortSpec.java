package com.pololanguage.sorters;

import java.io.File;
import java.util.regex.Pattern;

/**
 * Immutable class holding regex sort rule and destination folder for matches.
 */
public class SortSpec {
  final Pattern rule;
  final File folder;

  SortSpec(Pattern rule, File folder) {
    this.rule = rule;
    this.folder = folder;
  }

  SortSpec(String rule, String folder) {
    this.rule = Pattern.compile(rule);
    this.folder = new File(folder);
  }
}
