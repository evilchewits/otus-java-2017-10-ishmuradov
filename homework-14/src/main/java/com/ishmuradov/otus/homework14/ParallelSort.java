package com.ishmuradov.otus.homework14;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

public class ParallelSort {

  private ParallelSort() {
  }

  /**
   * Sorts array in parallel threads using Arrays.sort(...) in each thread.
   * 
   * @param array
   * @param numThreads
   */
  public static void sort(int[] array, int numThreads) {
    if (array.length < numThreads) {
      numThreads = array.length;
    }

    int[][] arrays = new int[numThreads][];
    int subArraySize = array.length / numThreads;
    if (array.length % numThreads != 0) {
      subArraySize++;
    }

    Thread[] threads = new Thread[numThreads];
    for (int i = 0; i < numThreads; i++) {
      int startIndexInclusive = i * subArraySize;
      int endIndexExclusive = (i + 1) * subArraySize;
      int[] currArray = ArrayUtils.subarray(array, startIndexInclusive, endIndexExclusive);
      arrays[i] = currArray;
      Thread currThread = new Thread(() -> Arrays.sort(currArray));
      threads[i] = currThread;
      currThread.start();
    }

    try {
      for (int i = 0; i < numThreads; i++) {
        threads[i].join();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.arraycopy(mergeSortedArrays(arrays), 0, array, 0, array.length);
  }

  /**
   * Accepts sorted arrays and returns the merged sorted array of them.
   * 
   * @param arrays
   * @return
   */
  protected static int[] mergeSortedArrays(int[]... arrays) {
    // Create result array
    int n = 0;
    for (int[] a : arrays) {
      n += a.length;
    }
    int[] result = new int[n];

    // Start at index 0 in each source array
    int[] idx = new int[arrays.length];

    // Merge source arrays into result array
    for (int i = 0; i < n; i++) {

      // Find smallest value
      int minJ = -1, minVal = 0;
      for (int j = 0; j < arrays.length; j++) {
        if (idx[j] < arrays[j].length) {
          int val = arrays[j][idx[j]];
          if (minJ == -1 || val < minVal) {
            minJ = j;
            minVal = val;
          }
        }
      }

      // Add to result array and step forward in appropriate source array
      result[i] = minVal;
      idx[minJ]++;
    }

    return result;
  }
}
