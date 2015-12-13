package com.pololanguage.sorters;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

/**
 * Immutable class holding regex sort rule and destination folder for matches.
 */
public class SortSpec {
  public final Pattern rule;
  public final Path dir;

  SortSpec(Pattern rule, Path dir) {
    this.rule = rule;
    this.dir = dir;
  }

  SortSpec(String rule, String dir) {
    this.rule = Pattern.compile(rule);
    this.dir = Paths.get(dir);
  }
}
