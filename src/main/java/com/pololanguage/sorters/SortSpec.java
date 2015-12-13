package com.pololanguage.sorters;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.regex.Pattern;

/**
 * Immutable class holding regex sort rule and destination folder for matches.
 */
public class SortSpec {
  private final PathMatcher matcher;
  private final String rule;
  private final Path directory;

  SortSpec(String rule, String dir, RuleType type) {
    this.rule = rule;
    this.directory = Paths.get(dir);
    matcher = FileSystems.getDefault().getPathMatcher(type.toString() +":"+ rule);
    // TODO: catch PatternSyntaxException
  }

  public String getRule() {
    return rule;
  }

  public String getDirectory() {
    return directory.toString();
  }
}
