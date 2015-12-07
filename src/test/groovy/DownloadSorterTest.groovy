package com.pololanguage.sorters

import java.util.regex.Pattern
import org.junit.Test
import org.junit.BeforeClass
import static groovy.test.GroovyAssert.assertEquals
import static groovy.test.GroovyAssert.assertNull
import static groovy.test.GroovyAssert.assertNotNull

class DownloadSorterTest {
  static DownloadSorter sorter

  @BeforeClass
  static void setup() {
    sorter =  new DownloadSorter()
  }

  @Test
  void testSetProcessor() {
    assertNull(sorter.processor)
    sorter.setProcessor({ }) /* the empty closure is taken as a Runnable! */
    assertNotNull(sorter.processor)
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

  @Test
  void testAddSortSpec() {
    final Pattern regex = Pattern.compile('test*\t[0-9]')
    final File file = new File('test/folder_01')
    def initialSize = sorter.sortSpecs.size()
    sorter.addSortSpec(regex, file)
    assertEquals(initialSize, sorter.sortSpecs.size() - 1)
  }

  @Test
  void testAddSortSpecStrings() {
    final String regex = 'test*\t[0-9]'
    final String file = 'test/folder_01'
    def initialSize = sorter.sortSpecs.size()
    sorter.addSortSpec(regex, file)
    assertEquals(initialSize, sorter.sortSpecs.size() - 1)
  }
  // TODO
}
