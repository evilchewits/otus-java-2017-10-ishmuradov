package com.ishmuradov.otus.homework6.util;

public class AuthenticationException extends RuntimeException {

  public AuthenticationException(String message) {
    System.err.println(message);
  }

}
