package com.pololanguage.sorters

import org.junit.Test
import static groovy.test.GroovyAssert.assertEquals

class SortSpecTest {
  @Test
  void contstructorTest() {
    final String expectedPattern = 'a*5\\\n'
    final String expectedFolder = 'folders/out_01/'
    SortSpec spec_01 = new SortSpec(expectedPattern, expectedFolder)

    assertEquals(expectedPattern, spec_01.rule.toString())
    assertEquals(new File(expectedFolder).getAbsolutePath(), spec_01.folder.getAbsolutePath())
  }
}
