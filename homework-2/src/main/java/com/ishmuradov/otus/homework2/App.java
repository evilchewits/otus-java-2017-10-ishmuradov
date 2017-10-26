package com.ishmuradov.otus.homework2;

import java.util.function.IntFunction;

public class App {

  public static void main(String[] args) {
    IntFunction<Object> emptyObjectGenerator = i -> new Object();             /* x64: obj header(mark word(8) + klass pointer(4)) + padding(4) = 16 */
    IntFunction<Object> integerGenerator = i -> new Integer(i);               /* x64: obj header(12)  + private int(4) = 16 */
    IntFunction<Object> emptyStringInPoolGenerator = i -> new String("");     /* x64, jre 1.7+: obj header(12) + private int(4) + private ref to string in pool(4) + padding(4) = 24 */
    IntFunction<Object> emptyStringGenerator = i -> new String(new char[0]);  /* x64, jre 1.7+: obj header(12) + private int(4) + private ref(4) + empty array(16) + padding(4) = 40 */
    IntFunction<Object> myClassInstanceGenerator = i -> new MyClass();        /* see MyClass definition */

    System.out.println("Size of objects:");
    System.out.println("new Object(): " + new MemoryEvaluator(emptyObjectGenerator).evaluate());
    System.out.println("new Integer(i): " + new MemoryEvaluator(integerGenerator).evaluate());
    System.out.println("new String(\"\"): " + new MemoryEvaluator(emptyStringInPoolGenerator).evaluate());
    System.out.println("new String(new char[0]): " + new MemoryEvaluator(emptyStringGenerator).evaluate());
    System.out.println("new MyClass(): " + new MemoryEvaluator(myClassInstanceGenerator).evaluate());
  }

}
