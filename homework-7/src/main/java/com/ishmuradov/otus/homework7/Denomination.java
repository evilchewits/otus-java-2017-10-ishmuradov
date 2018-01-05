package com.ishmuradov.otus.homework7;

public enum Denomination {
  FIFTY         (50),
  HUNDRED       (100),
  TWO_HUNDRED   (200),
  FIVE_HUNDRED  (500),
  THOUSAND      (1000),
  TWO_THOUSAND  (2000),
  FIVE_THOUSAND (5000);
  
  private final long value;

  private Denomination(long value) {
    this.value = value;
  }
  
  public long getValue() {
    return value;
  }
}
