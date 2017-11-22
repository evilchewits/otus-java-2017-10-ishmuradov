package test.com.ishmuradov.otus.homework5;

import com.ishmuradov.otus.homework5.testframework.TestFramework;

public class TestRunner {

  public static void main(String[] args) throws ReflectiveOperationException {
    System.out.println("[1] run tests in class test.com.ishmuradov.otus.homework5.MyTests");
    TestFramework.run("test.com.ishmuradov.otus.homework5.MyTests");
    
    System.out.println("\n[2] run tests in package test.com.ishmuradov.otus.homework5");
    TestFramework.run("test.com.ishmuradov.otus.homework5");
    
    System.out.println("\n[3] run tests in test.com.ishmuradov.otus.homework5.TestRunner");
    TestFramework.run("test.com.ishmuradov.otus.homework5.TestRunner");
    
//    System.out.println("\n[4] run tests in fake.class.or.package.name");
//    TestFramework.run("fake.class.or.package.name");
  }

}
