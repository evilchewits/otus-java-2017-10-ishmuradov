package com.ishmuradov.otus.homework2;

import java.util.function.IntFunction;

public class MemoryEvaluator {
  private IntFunction<Object> objectGenerator;
  private int size = 100_000;
  Runtime runtime = Runtime.getRuntime();
  private static Object tmp;

  public MemoryEvaluator(IntFunction<Object> objectGenerator) {
    super();
    this.objectGenerator = objectGenerator;
  }

  public long evaluate() {
    Object[] array = new Object[size];
    System.gc();
    long mem = runtime.totalMemory() - runtime.freeMemory();

    for (int i = 0; i < size; i++) {
      array[i] = objectGenerator.apply(i);
    }

    System.gc();
    MemoryEvaluator.tmp = array; // use "array" somehow, otherwise it can be killed by garbage collector
    
    return Math.round((double) (runtime.totalMemory() - runtime.freeMemory() - mem) / size);
  }
}
