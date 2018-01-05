package com.ishmuradov.otus.homework7.exceptions;

public class CellOverflowException extends RuntimeException {
  
  public CellOverflowException(String message) {
    System.err.println(message);
  }

}
