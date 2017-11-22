package test.com.ishmuradov.otus.homework5;

import com.ishmuradov.otus.homework5.testframework.After;
import com.ishmuradov.otus.homework5.testframework.Before;
import com.ishmuradov.otus.homework5.testframework.Test;

public class MyTests2 {

  @Before
  public void myBeforeMethod() {
    System.out.println(">> MyTests2\t: myBeforeMethod()");
  }
  
  @After
  public void myAfterMethod() {
    System.out.println(">> MyTests2\t: myAfterMethod()");
  }
  
  @Test
  public void test_myFirstTest() {
    System.out.println(">> MyTests2\t: test_myFirstTest()");
  }
  
}
