package com.pololanguage.sorters

import java.util.regex.Pattern

class SortSpec {
  final Pattern rule
  final File folder

  SortSpec(String rule, String folder) {
    this.rule = Pattern.compile(rule)
    this.folder = new File(folder)
  }
}
