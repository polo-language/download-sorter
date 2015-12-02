package com.pololanguage.sorters

import org.junit.Test
import org.junit.BeforeClass
import static groovy.test.GroovyAssert.assertEquals

class DownloadSorterTest {
  static DownloadSorter sorter

  @BeforeClass
  static void setup() {
    sorter =  new DownloadSorter()
  }

  @Test
  void testAddInput() {
    final File file_01 = new File('test/folder_01')
    final File file_02 = new File('test/folder_02')
    def initialSize = sorter.inFolders.size()
    sorter.addInput(file_01)
    assertEquals(initialSize, sorter.inFolders.size() - 1)
    sorter.addInput(file_02)
    assertEquals(initialSize, sorter.inFolders.size() - 2)
  }

  // TODO
}
