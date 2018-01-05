package com.ishmuradov.otus.homework7.exceptions;

public class AuthenticationException extends RuntimeException {

  public AuthenticationException(String message) {
    System.err.println(message);
  }

}
