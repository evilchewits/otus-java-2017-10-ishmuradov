package com.ishmuradov.otus.homework6.util;

public class AccessDeniedException extends RuntimeException {

  public AccessDeniedException(String message) {
    System.err.println(message);
  }

}
