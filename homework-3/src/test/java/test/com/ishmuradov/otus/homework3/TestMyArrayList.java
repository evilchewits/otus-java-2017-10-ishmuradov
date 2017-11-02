package test.com.ishmuradov.otus.homework3;

import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ishmuradov.otus.homework3.MyArrayList;

/**
 * Test MyArrayList on some methods of java.util.Collections
 * 
 * @author Ishmuradov
 * 
 */
public class TestMyArrayList {
  private List<Long> myLongList;
  private List<String> myStringList;

  @Before
  public void fillMyLists() {
    myLongList = new MyArrayList<>();
    myLongList.add(1L);
    myLongList.add(3L);
    myLongList.add(-1L);

    myStringList = new MyArrayList<>();
    myStringList.add("teapot");
    myStringList.add("mate");
    myStringList.add("tea");
  }

  @After
  public void clearAll() {
    myLongList = null;
    myStringList = null;
  }

  @Test
  public void test_addAll() {
    // test myLongList
    Collections.addAll(myLongList, 2L, 5L);
    assertEquals(5, myLongList.size());
    assertThat(myLongList, contains(1L, 3L, -1L, 2L, 5L));

    // test myStringList
    Collections.addAll(myStringList, "coffee machine", "Java");
    assertEquals(5, myStringList.size());
    assertThat(myStringList, contains("teapot", "mate", "tea", "coffee machine", "Java"));
  }

  @Test
  public void test_copy() {
    // test myLongList
    MyArrayList<Long> newLongList = new MyArrayList<>();
    newLongList.add(0L);
    Collections.copy(myLongList, newLongList);
    assertEquals(3, myLongList.size());
    assertEquals(1, newLongList.size());
    assertThat(myLongList, contains(0L, 3L, -1L));
    
    // test long arrays: size >= Collections.COPY_THRESHOLD = 10
    Collections.addAll(myLongList, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L);
    Collections.addAll(newLongList, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L);
    Collections.copy(myLongList, newLongList);
    assertEquals(13, myLongList.size());
    assertEquals(11, newLongList.size());
    assertThat(myLongList, contains(0L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 1L, 0L, 0L));
    
    // test myStringList
    MyArrayList<String> newStringList = new MyArrayList<>();
    newStringList.add("karkade");
    newStringList.add("mint");
    Collections.copy(myStringList, newStringList);
    assertEquals(3, myStringList.size());
    assertEquals(2, newStringList.size());
    assertThat(myStringList, contains("karkade", "mint", "tea"));
  }

  @Test
  public void test_sort() {
    // test myLongList
    Collections.sort(myLongList, Comparator.naturalOrder());
    assertEquals(3, myLongList.size());
    assertEquals(-1L, myLongList.get(0).longValue());
    assertEquals(1L, myLongList.get(1).longValue());

    Collections.sort(myLongList, Comparator.reverseOrder());
    assertEquals(3, myLongList.size());
    assertEquals(-1L, myLongList.get(2).longValue());
    assertEquals(1L, myLongList.get(1).longValue());

    // test myStringList
    Collections.sort(myStringList, Comparator.naturalOrder());
    assertEquals(3, myStringList.size());
    assertTrue("mate".equals(myStringList.get(0)));
    assertTrue("tea".equals(myStringList.get(1)));
    assertTrue("teapot".equals(myStringList.get(2)));

    Collections.sort(myStringList, Comparator.reverseOrder());
    assertEquals(3, myStringList.size());
    assertTrue("mate".equals(myStringList.get(2)));
    assertTrue("tea".equals(myStringList.get(1)));
    assertTrue("teapot".equals(myStringList.get(0)));
  }

}
