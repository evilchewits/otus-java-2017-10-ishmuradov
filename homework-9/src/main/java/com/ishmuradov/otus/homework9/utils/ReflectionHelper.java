package com.ishmuradov.otus.homework9.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
  
  /**
   * Get all (even private) fields of the given class,
   * along with all fields of all its super classes.
   */
  public static List<Field> getInheritedFields(Class<?> type) {
    List<Field> result = new ArrayList<Field>();

    Class<?> i = type;
    while (i != null && i != Object.class) {
      Collections.addAll(result, i.getDeclaredFields());
      i = i.getSuperclass();
    }

    return result;
  }
  
  /**
   * Set field value of the current object even if the field is in a superclass.
   */
  public static Object getInheritedFieldValue(Object object, String name) {
    Field field = null;
    boolean isAccessible = true;
    Object value = null;
    try {
      field = object.getClass().getDeclaredField(name); // getField() for public
                                                        // fields
    } catch (NoSuchFieldException e) {
      field = null;
    }

    Class<?> superClass = object.getClass().getSuperclass();
    while (field == null && superClass != null) {
      try {
        field = superClass.getDeclaredField(name);
      } catch (NoSuchFieldException nsfe) {
        superClass = superClass.getSuperclass();
      }
    }

    try {
      isAccessible = field.isAccessible();
      field.setAccessible(true);
      value = field.get(object);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } finally {
      if (field != null && !isAccessible) {
        field.setAccessible(false);
      }
    }
    
    return value;
  }

  /**
   * Set field value of the current object even if the field is in a superclass.
   */
  public static void setInheritedFieldValue(Object object, String name, Object value) {
    Field field = null;
    boolean isAccessible = true;
    try {
      field = object.getClass().getDeclaredField(name); // getField() for public
                                                        // fields
    } catch (NoSuchFieldException e) {
      field = null;
    }

    Class<?> superClass = object.getClass().getSuperclass();
    while (field == null && superClass != null) {
      try {
        field = superClass.getDeclaredField(name);
      } catch (NoSuchFieldException nsfe) {
        superClass = superClass.getSuperclass();
      }
    }

    try {
      isAccessible = field.isAccessible();
      field.setAccessible(true);
      field.set(object, value);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } finally {
      if (field != null && !isAccessible) {
        field.setAccessible(false);
      }
    }
  }

}