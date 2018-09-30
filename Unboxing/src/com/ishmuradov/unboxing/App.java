package com.ishmuradov.unboxing;

public class App {
  private static Integer i;
  
  public static void main(String[] args) {
    doStuff(i);
  }
  
  public static void doStuff(int i) {
    System.out.println(i);
  }
}
