package test.com.ishmuradov.otus.homework5;

import com.ishmuradov.otus.homework5.testframework.After;
import com.ishmuradov.otus.homework5.testframework.Before;
import com.ishmuradov.otus.homework5.testframework.Test;

public class MyTests {

  @Before
  public void myBeforeMethod() {
    System.out.println(">> MyTests\t: myBeforeMethod()");
  }
  
  @After
  public void myAfterMethod() {
    System.out.println(">> MyTests\t: myAfterMethod()");
  }
  
  @Test
  public void test_myFirstTest() {
    System.out.println(">> MyTests\t: test_myFirstTest()");
  }
  
  @Test
  public void test_mySecondTest() {
    System.out.println(">> MyTests\t: test_mySecondTest()");
  }
  
}
