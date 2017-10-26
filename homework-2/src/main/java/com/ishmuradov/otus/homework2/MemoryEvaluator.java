package com.ishmuradov.otus.homework2;

import java.util.function.IntFunction;

public class MemoryEvaluator {
  private IntFunction<Object> objectGenerator;
  private int size = 10_000_000;
  Runtime runtime = Runtime.getRuntime();

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

    return (runtime.totalMemory() - runtime.freeMemory() - mem) / size;
  }
}
