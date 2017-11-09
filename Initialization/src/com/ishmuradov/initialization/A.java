package com.ishmuradov.initialization;

public class A {

  protected String VARIABLE = "A";
  
  static {
    System.out.println(">> inside A static block");
  }
  
  {
    System.out.println(">> inside A non-static block");
    printVariable();
  }
  
  protected A() {
    System.out.println(">> inside A conctructor");
    printVariable();
  }
  
  public void printVariable() {
    System.out.println(VARIABLE);
  }
  
}
