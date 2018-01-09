package com.ishmuradov.otus.homework8.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by tully.
 */
@SuppressWarnings("SameParameterValue")
public class ReflectionHelper {
  private ReflectionHelper() {
  }

  public static <T> T instantiate(Class<T> type, Object... args) {
    try {
      if (args.length == 0) {
        return type.newInstance();
      } else {
        return type.getConstructor(toClasses(args)).newInstance(args);
      }
    } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static Object getFieldValue(Object object, String name) {
    Field field = null;
    boolean isAccessible = true;
    try {
      field = object.getClass().getDeclaredField(name); // getField() for public
                                                        // fields
      isAccessible = field.isAccessible();
      field.setAccessible(true);
      return field.get(object);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    } finally {
      if (field != null && !isAccessible) {
        field.setAccessible(false);
      }
    }
    return null;
  }

  public static void setFieldValue(Object object, String name, Object value) {
    Field field = null;
    boolean isAccessible = true;
    try {
      field = object.getClass().getDeclaredField(name); // getField() for public
                                                        // fields
      isAccessible = field.isAccessible();
      field.setAccessible(true);
      field.set(object, value);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    } finally {
      if (field != null && !isAccessible) {
        field.setAccessible(false);
      }
    }
  }

  public static Object callMethod(Object object, String name, Object... args) {
    Method method = null;
    boolean isAccessible = true;
    try {
      method = object.getClass().getDeclaredMethod(name, toClasses(args));
      isAccessible = method.isAccessible();
      method.setAccessible(true);
      return method.invoke(object, args);
    } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
      e.printStackTrace();
    } finally {
      if (method != null && !isAccessible) {
        method.setAccessible(false);
      }
    }
    return null;
  }

  private static Class<?>[] toClasses(Object[] args) {
    List<Class<?>> classes = Arrays.stream(args).map(Object::getClass).collect(Collectors.toList());
    return classes.toArray(new Class<?>[classes.size()]);
  }

  /**
   * Returns all public methods of <i>type</i> annotated with
   * <i>annotationName</i>.
   * 
   * @param type
   *          - a class where to scan annotated method
   * @param annotationName
   *          - a fully qualified name of the annotation class
   * @return
   */
  @SuppressWarnings("unchecked")
  public static Set<Method> getAnnotatedMethods(Class<?> type, String annotationName) throws ClassNotFoundException {
    final Class<Annotation> clazz = (Class<Annotation>) Class.forName(annotationName);
    return Stream.of(type.getMethods()).filter(m -> m.isAnnotationPresent(clazz)).collect(Collectors.toSet());
  }
  
}