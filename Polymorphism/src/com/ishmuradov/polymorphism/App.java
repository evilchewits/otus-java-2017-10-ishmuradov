package com.ishmuradov.polymorphism;

public class App {

  public static void main(String[] args) {
    C myVar = new B();
    D.myMethod(myVar);
  }

}
