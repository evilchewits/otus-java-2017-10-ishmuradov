package test.com.ishmuradov.otus.homework14;

import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

public class TestUtils {

  private TestUtils() {}
  
  public static int[] getUnsortedRandomIntArray(int size) {
    Random random = new Random();
    int[] arr = new int[size];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = random.nextInt();
    }
    while (ArrayUtils.isSorted(arr) && arr.length > 1) {
      ArrayUtils.shuffle(arr);
    }
    return arr;
  }
}
