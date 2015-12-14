package com.pololanguage.sorters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Immutable class holding sort rule and destination directory for matched files.
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

  /** Returns true if file matches the rule. */
  boolean matches(Path file) {
    return matcher.matches(file);
  }

  /** Copies file to directory and returns true if it matches the rule. */
  boolean copyIfMatches(Path file) throws IOException {
    //Path path = Paths.get(file);
    if (matches(file)) {
      Files.copy(file, directory.resolve(file.getFileName()), StandardCopyOption.COPY_ATTRIBUTES);
      return true;
    }
    return false;
  }

  /** Moves file to directory and returns true if it matches the rule */
  boolean moveIfMatches(Path file) throws IOException {
    //Path path = Paths.get(file);
    if (matches(file)) {
      Files.move(file, directory.resolve(file.getFileName()), StandardCopyOption.ATOMIC_MOVE);
      return true;
    }
    return false;
  }

  /** Returns a {@code String} representation of the rule. */
  String getRule() {
    return rule;
  }

  /** Returns a {@code String} representation of the directory. */
  String getDirectory() {
    return directory.toString();
  }
}
