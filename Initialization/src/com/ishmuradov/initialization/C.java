package com.ishmuradov.initialization;

public class C extends A {

  static {
    System.out.println(">> inside C static block");
  }
  
  {
    System.out.println(">> inside C non-static block; set VARIABLE = C");
    VARIABLE = "C";
    printVariable();
  }
  
  public C() {
    System.out.println(">> inside C conctructor");
    printVariable();
  }
  
}
