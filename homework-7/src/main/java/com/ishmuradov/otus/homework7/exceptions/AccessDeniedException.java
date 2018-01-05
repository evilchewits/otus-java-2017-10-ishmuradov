package com.ishmuradov.otus.homework7.exceptions;

public class AccessDeniedException extends RuntimeException {

  public AccessDeniedException(String message) {
    System.err.println(message);
  }

}
