package com.ishmuradov.initialization;

public class B extends A {

  static {
    System.out.println(">> inside B static block");
  }
  
  {
    System.out.println(">> inside B non-static block; set VARIABLE = B");
    VARIABLE = "B";
    printVariable();
  }
  
  public B() {
    System.out.println(">> inside B conctructor");
    printVariable();
  }
  
}
