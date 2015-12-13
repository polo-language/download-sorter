package com.pololanguage.sorters;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

/**
 * Immutable class holding regex sort rule and destination folder for matches.
 */
public class SortSpec {
  private final Pattern rule;
  private final Path directory;

  SortSpec(Pattern rule, Path dir) {
    this.rule = rule;
    this.directory = dir;
  }

  SortSpec(String rule, String dir) {
    this.rule = Pattern.compile(rule);
    this.directory = Paths.get(dir);
  }

  public String getRule() {
    return rule.pattern();
  }

  public String getDirectory() {
    return directory.toString();
  }
}
