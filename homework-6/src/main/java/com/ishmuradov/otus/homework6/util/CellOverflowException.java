package com.ishmuradov.otus.homework6.util;

public class CellOverflowException extends RuntimeException {
  
  public CellOverflowException(String message) {
    System.err.println(message);
  }

}
