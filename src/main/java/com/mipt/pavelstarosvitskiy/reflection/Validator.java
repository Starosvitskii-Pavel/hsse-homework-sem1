package com.mipt.pavelstarosvitskiy.reflection;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validator {
  public static ValidationResult validate(Object object) throws IllegalAccessException {
    ValidationResult validationResult = new ValidationResult();

    for (Field field : object.getClass().getDeclaredFields()) {
      field.setAccessible(true);
      Object value = field.get(object);
      if ((field.isAnnotationPresent(NotNull.class)) && (value == null)) {
        NotNull notNullAnnotation = field.getAnnotation(NotNull.class);
        validationResult.addError(notNullAnnotation.message());
      }

      if (field.isAnnotationPresent(Range.class)) {
        Range rangeAnnotation = field.getAnnotation(Range.class);
        long min = rangeAnnotation.min();
        long max = rangeAnnotation.max();

        if (value instanceof Number) {
          long numberValue = ((Number) value).longValue();
          if ((numberValue < min) || (numberValue > max)) {
            validationResult.addError(rangeAnnotation.message());
          }
        }
      }

      if (field.isAnnotationPresent(Size.class)) {
        Size sizeAnnotation = field.getAnnotation(Size.class);
        long min = sizeAnnotation.min();
        long max = sizeAnnotation.max();

        if (value instanceof String) {
          String stringValue = (String) value;
          if ((stringValue.length() < min) || (stringValue.length() > max)) {
            validationResult.addError(sizeAnnotation.message());
          }
        }
      }

      if (field.isAnnotationPresent(Email.class)) {
        Email emailAnnotation = field.getAnnotation(Email.class);

        if (value instanceof String) {
          String stringValue = (String) value;

          final Pattern VALID_EMAIL_ADDRESS_REGEX =
              Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(stringValue);

            if (!matcher.matches()) validationResult.addError(emailAnnotation.message());

        }
      }

    }
    return validationResult;
  }
}
