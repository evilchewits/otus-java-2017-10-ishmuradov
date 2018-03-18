package test.com.ishmuradov.otus.homework14;

import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import com.ishmuradov.otus.homework14.ParallelSort;

public class TestParallelSort {

  @Test
  public void test_sort() {
//    int[] arrGiant = TestUtils.getUnsortedRandomIntArray(100_000_000);
//    long start = System.nanoTime();
//    ParallelSort.sort(arrGiant, 4);
//    long end = System.nanoTime();
//    System.out.println((end - start) * 1e-9 + " s");
//    assertTrue(ArrayUtils.isSorted(arrGiant));
    
    int[] arrBig = TestUtils.getUnsortedRandomIntArray(1000);
    ParallelSort.sort(arrBig, 4);
    assertTrue(ArrayUtils.isSorted(arrBig));

    int[] arrSmall = TestUtils.getUnsortedRandomIntArray(3);
    ParallelSort.sort(arrSmall, 4);
    assertTrue(ArrayUtils.isSorted(arrSmall));
  }

}
