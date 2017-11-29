package com.ishmuradov.otus.homework5;

import com.ishmuradov.otus.homework5.testframework.TestFramework;

public class App {
  
  public static void main(String[] args) throws ReflectiveOperationException {
    
    System.out.println("[1] run tests in class test.com.ishmuradov.otus.homework5.MyTests");
    TestFramework.runTests("test.com.ishmuradov.otus.homework5.MyTests");
    
    System.out.println("\n[2] run tests in package test.com.ishmuradov.otus.homework5");
    TestFramework.runTests("test.com.ishmuradov.otus.homework5");
    
    System.out.println("\n[3] run tests in package test.com.ishmuradov.otus");
    TestFramework.runTests("test.com.ishmuradov.otus");
    
    System.out.println("\n[4] run tests in com.ishmuradov.otus.homework5.App");
    TestFramework.runTests("com.ishmuradov.otus.homework5.App");
    
//    System.out.println("\n[5] run tests in fake.class.or.package.name");
//    TestFramework.runTests("fake.class.or.package.name");
    
  }
  
}
