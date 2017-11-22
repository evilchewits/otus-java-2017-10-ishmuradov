package com.ishmuradov.otus.homework5.testframework;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import com.google.common.reflect.ClassPath;

public class TestFramework {

  /**
   * Run tests in a class or package
   * 
   * This method scans for methods annotated with @Test in the given class
   * or all classes in the given package, creates a new instance of the class
   * for each found method, and do the following (for each method annotated with @Test):
   *   1. calls all methods annotated with @Before for current class (order of invocation is not determined),
   *   2. calls a method annotated with @Test
   *   3. calls all methods annotated with @After for current class (order of invocation is not determined),
   * 
   * @param string - fully qualified name of the class or package
   * @throws ReflectiveOperationException 
   */
  public static void run(String classOrPackageName) throws ReflectiveOperationException {
    final Set<Class<?>> classes = new HashSet<>();
    
    // Find a single class or all classes in the package
    try {
      classes.add(Class.forName(classOrPackageName));
    } catch (ClassNotFoundException cnfe) {
      try {
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();

        for (final ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
          if (info.getName().startsWith(classOrPackageName + ".")) {
            classes.add(info.load());
          }
        }
      } catch (Exception e) {
        throw new ReflectiveOperationException(e);
      }
    }
    
    if (classes.size() == 0) {
      throw new ClassNotFoundException("Class or package not found: " + classOrPackageName);
    }
    
    // Invoke methods annotated by @Before, @Test, @After in each class
    int testCounter = 0;
    
    for (Class<?> clazz : classes) {
      Set<Method> beforeMethods = ReflectionHelper.getAnnotatedMethods(clazz, "com.ishmuradov.otus.homework5.testframework.Before");
      Set<Method> testMethods   = ReflectionHelper.getAnnotatedMethods(clazz, "com.ishmuradov.otus.homework5.testframework.Test");
      Set<Method> afterMethods  = ReflectionHelper.getAnnotatedMethods(clazz, "com.ishmuradov.otus.homework5.testframework.After");
      
      if (testMethods.size() == 0) {
        continue;
      }
      
      testCounter++;
      
      Object object = ReflectionHelper.instantiate(clazz);
      
      for (Method testMethod : testMethods) {
        // @Before
        for (Method beforeMethod : beforeMethods) {
          ReflectionHelper.callMethod(object, beforeMethod.getName());
        }
        
        // @Test
        ReflectionHelper.callMethod(object, testMethod.getName());
        
        // @After
        for (Method afterMethod : afterMethods) {
          ReflectionHelper.callMethod(object, afterMethod.getName());
        }
      }
    }
    
    if (testCounter == 0) {
      System.out.println("No test found in class or package: " + classOrPackageName);
    }
 
  }

}
