package test.com.ishmuradov.otus;

import com.ishmuradov.otus.homework5.testframework.After;
import com.ishmuradov.otus.homework5.testframework.Before;
import com.ishmuradov.otus.homework5.testframework.Test;

public class MyTests3 {

  @Before
  public void myBeforeMethod() {
    System.out.println(">> MyTests3\t: myBeforeMethod()");
  }
  
  @Before
  public void myBeforeMethod2() {
    System.out.println(">> MyTests3\t: myBeforeMethod2()");
  }
  
  @After
  public void myAfterMethod() {
    System.out.println(">> MyTests3\t: myAfterMethod()");
  }
  
  @After
  public void myAfterMethod2() {
    System.out.println(">> MyTests3\t: myAfterMethod2()");
  }
  
  @Test
  public void test_myFirstTest() {
    System.out.println(">> MyTests3\t: test_myFirstTest()");
  }
  
}
