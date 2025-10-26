package com.mipt.pavelstarosvitskiy.reflection;

import java.lang.reflect.Field;

public class AnnotationUtils {
  public static Field findField (Object target, String fieldName) throws Exception {
    for (Field field : target.getClass().getDeclaredFields()) {
      if (field.getName().equals(fieldName)) return field;
    }
    throw new NoSuchFieldException();
  }
}
