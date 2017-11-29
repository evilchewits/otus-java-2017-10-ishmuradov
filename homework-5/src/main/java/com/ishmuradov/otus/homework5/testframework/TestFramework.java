package com.ishmuradov.otus.homework5.testframework;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

public class TestFramework {
  
  public static final String TEST_CLASSES_PATH = "target/test-classes";

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
   * @throws ClassNotFoundException 
   */
  public static void runTests(String classOrPackageName) throws ClassNotFoundException {
    final Set<Class<?>> classes = findClasses(classOrPackageName);
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
  
  /**
   * Finds classes in test packages (those that are put to
   * "target/test-classes" with default Maven configuration).
   * 
   * @param classOrPackageName
   * @return
   * @throws ClassNotFoundException
   */
  public static Set<Class<?>> findClasses(String classOrPackageName) throws ClassNotFoundException {
    final Set<Class<?>> foundClasses;
    final Set<Class<?>> classes = new HashSet<>();
    
    URL testClassesURL = null;
    try {
      testClassesURL = Paths.get(TEST_CLASSES_PATH).toUri().toURL();
    } catch (IllegalArgumentException | NullPointerException | MalformedURLException e) {
      throw new RuntimeException("Bad/missing TEST_CLASSES_PATH: " + TEST_CLASSES_PATH);
    }

    URLClassLoader urlClassLoader = URLClassLoader.newInstance(new URL[] { testClassesURL }, 
       ClasspathHelper.staticClassLoader());
    String parentPackage = (classOrPackageName.lastIndexOf(".") != -1)
        ? classOrPackageName.substring(0, classOrPackageName.lastIndexOf("."))
        : "";

    Reflections reflections = new Reflections(new ConfigurationBuilder()
        .addUrls(ClasspathHelper.forClassLoader(urlClassLoader))
        .addClassLoader(urlClassLoader)
        .setScanners(new SubTypesScanner(false))
        .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(parentPackage))));
    
    foundClasses = reflections.getSubTypesOf(Object.class);

    for (Class<?> clazz : foundClasses) {
      if (clazz.getName().equals(classOrPackageName) || clazz.getName().startsWith(classOrPackageName)
          && clazz.getName().substring(classOrPackageName.length(), clazz.getName().length()).indexOf(".") != -1) {
        classes.add(clazz);
      }
    }

    if (classes.size() == 0) {
      throw new ClassNotFoundException("Class or package not found: " + classOrPackageName);
    }
    
    return classes;
  }

}
