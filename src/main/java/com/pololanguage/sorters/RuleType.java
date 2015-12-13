package com.pololanguage.sorters;

public enum RuleType {
  GLOB ("glob"),
  REGEX ("regex");

  private final String string;

  RuleType(String string) {
    this.string = string;
  }

  public String toString() {
    return string;
  }
}
