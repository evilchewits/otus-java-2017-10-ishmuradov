package com.ishmuradov.otus.homework8;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import com.ishmuradov.otus.homework8.utils.ReflectionHelper;

public class JsonSerializer {

  public static JsonValue serialize(Object obj) {
    JsonValue jsonValue = null;

    if (obj == null) {
      jsonValue = JsonValue.NULL;
    } else if (obj.getClass().equals(boolean.class) || obj.getClass().equals(Boolean.class)) {
      jsonValue = (boolean) obj ? JsonValue.TRUE : JsonValue.FALSE;
    } else if (obj.getClass().isPrimitive() && obj.getClass().equals(boolean.class) || Number.class.isInstance(obj)) {
      jsonValue = Json.createValue(((Number) obj).doubleValue());
    } else if (obj.getClass().getName().equals("java.lang.String")) {
      jsonValue = Json.createValue((String) obj);
    // TODO: simplify
    } else if (obj.getClass().isArray() || Collection.class.isInstance(obj)) {
      JsonArrayBuilder jsonBuilder = Json.createArrayBuilder();
      Collection<?> arr = null;
      
      if (obj.getClass().isArray()) {
        if (obj.getClass().getComponentType().isPrimitive()) {
          Object[] array  = new Object[Array.getLength(obj)];
          for (int i = 0; i < array.length; i++) {
            array[i] = Array.get(obj, i);
          }
          arr = Arrays.asList(array);
        } else {
          arr = Arrays.asList((Object[]) obj);
        }
      } else {
        arr = (Collection<?>) obj;
      }
      
      for (Object item : arr) {
        jsonBuilder.add(serialize(item));
      }
      jsonValue = jsonBuilder.build();
    } else {
      Field[] fields = obj.getClass().getDeclaredFields();
      JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
      for (Field field : fields) {
        jsonBuilder.add(field.getName(), serialize(ReflectionHelper.getFieldValue(obj, field.getName())));
      }
      jsonValue = jsonBuilder.build();
    }
    
    return jsonValue;
  }
}
