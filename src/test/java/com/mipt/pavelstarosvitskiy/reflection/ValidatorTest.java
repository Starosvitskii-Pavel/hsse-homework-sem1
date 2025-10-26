package com.mipt.pavelstarosvitskiy.reflection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValidatorTest {

  @Test
  void shouldValidateNotNull() throws IllegalAccessException {
    final Name name = new Name("");

    final ValidationResult result = Validator.validate(name);

    Assertions.assertTrue(result.isValid());
  }

  @Test
  void shouldNotValidateNotNull() throws IllegalAccessException {
    final Name name = new Name();

    final ValidationResult result = Validator.validate(name);

    Assertions.assertFalse(result.isValid());
  }

  @Test
  void shouldValidateRange() throws IllegalAccessException {
    final OneNumber numberOne = new OneNumber(0);

    final ValidationResult result = Validator.validate(numberOne);

    Assertions.assertTrue(result.isValid());
  }

  @Test
  void shouldValidateRangeAtLowerBound() throws IllegalAccessException {
    final OneNumber numberOne = new OneNumber(-15);

    final ValidationResult result = Validator.validate(numberOne);

    Assertions.assertTrue(result.isValid());
  }

  @Test
  void shouldValidateRangeAtUpperBound() throws IllegalAccessException {
    final OneNumber numberOne = new OneNumber(50);

    final ValidationResult result = Validator.validate(numberOne);

    Assertions.assertTrue(result.isValid());
  }

  @Test
  void shouldValidateRangeFloat() throws IllegalAccessException {
    final FloatNumber numberOne = new FloatNumber(0.0F);

    final ValidationResult result = Validator.validate(numberOne);

    Assertions.assertTrue(result.isValid());
  }

  @Test
  void shouldNotValidateRange() throws IllegalAccessException {
    final OneNumber numberOne = new OneNumber(-999);

    final ValidationResult result = Validator.validate(numberOne);

    Assertions.assertFalse(result.isValid());
  }

  @Test
  void shouldNotValidateRangeAtLowerBound() throws IllegalAccessException {
    final OneNumber numberOne = new OneNumber(-16);

    final ValidationResult result = Validator.validate(numberOne);

    Assertions.assertFalse(result.isValid());
  }

  @Test
  void shouldNotValidateRangeAtUpperBound() throws IllegalAccessException {
    final OneNumber numberOne = new OneNumber(51);

    final ValidationResult result = Validator.validate(numberOne);

    Assertions.assertFalse(result.isValid());
  }

  @Test
  void shouldValidateSize() throws IllegalAccessException {
    final OneString stringOne = new OneString("___________");

    final ValidationResult result = Validator.validate(stringOne);

    Assertions.assertTrue(result.isValid());
  }

  @Test
  void shouldValidateSizeAtLowerBound() throws IllegalAccessException {
    final OneString stringOne = new OneString("12345");

    final ValidationResult result = Validator.validate(stringOne);

    Assertions.assertTrue(result.isValid());
  }

  @Test
  void shouldValidateSizeAtUpperBound() throws IllegalAccessException {
    final OneString stringOne = new OneString("123456789012345678901234567890");

    final ValidationResult result = Validator.validate(stringOne);

    Assertions.assertTrue(result.isValid());
  }

  @Test
  void shouldNotValidateSize() throws IllegalAccessException {
    final OneString stringOne = new OneString("");

    final ValidationResult result = Validator.validate(stringOne);

    Assertions.assertFalse(result.isValid());
  }

  @Test
  void shouldNotValidateSizeAtLowerBound() throws IllegalAccessException {
    final OneString stringOne = new OneString("1234");

    final ValidationResult result = Validator.validate(stringOne);

    Assertions.assertFalse(result.isValid());
  }

  @Test
  void shouldNotValidateSizeAtUpperBound() throws IllegalAccessException {
    final OneString stringOne = new OneString("123456789012345678901234567890A");

    final ValidationResult result = Validator.validate(stringOne);

    Assertions.assertFalse(result.isValid());
  }

  @Test
  void shouldValidateEmail() throws IllegalAccessException {
    final OneEmail emailOne = new OneEmail("dan2004@gmail.com");

    final ValidationResult result = Validator.validate(emailOne);

    Assertions.assertTrue(result.isValid());
  }

  @Test
  void shouldValidateEmailAtLowerBounds() throws IllegalAccessException {
    final OneEmail emailOne = new OneEmail("d@m.de");

    final ValidationResult result = Validator.validate(emailOne);

    Assertions.assertTrue(result.isValid());
  }

  @Test
  void shouldNotValidateEmail() throws IllegalAccessException {
    final OneEmail emailOne = new OneEmail("yaddayaddayadda");

    final ValidationResult result = Validator.validate(emailOne);

    Assertions.assertFalse(result.isValid());
  }

  @Test
  void shouldNotValidateEmailAtLowerBounds() throws IllegalAccessException {
    final OneEmail emailOne = new OneEmail("d@m.d");

    final ValidationResult result = Validator.validate(emailOne);

    Assertions.assertFalse(result.isValid());
  }

  @Test
  void shouldValidateMultiple() throws IllegalAccessException {
    final User user = new User();
    user.setName("Alex");
    user.setEmail("alex123@gmail.com");
    user.setPassword("password");
    user.setAge(18);

    final ValidationResult result = Validator.validate(user);

    Assertions.assertTrue(result.isValid());

  }

  @Test
  void shouldValidateMultipleAtLowerBounds() throws IllegalAccessException {
    final User user = new User();

    user.setName("Al");
    user.setEmail("a@y.de");
    user.setPassword("passwd");
    user.setAge(0);

    final ValidationResult result = Validator.validate(user);
    Assertions.assertTrue(result.isValid());
  }

  @Test
  void shouldValidateMultipleAtUpperBounds() throws IllegalAccessException {
    final User user = new User();

    user.setName("AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwx");
    user.setEmail("AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwx@yahoo.com");
    user.setPassword("01234567890123456789");
    user.setAge(150);

    final ValidationResult result = Validator.validate(user);
    Assertions.assertTrue(result.isValid());
  }

  @Test
  void shouldNotValidateMultipleAtLowerBounds() throws IllegalAccessException {
    final User user = new User();

    user.setName("A");
    user.setEmail("a@y.d");
    user.setPassword("passw");
    user.setAge(-1);

    final ValidationResult result = Validator.validate(user);
    Assertions.assertEquals(4, result.getErrors().size());
  }

  @Test
  void shouldNotValidateMultipleAtUpperBounds() throws IllegalAccessException {
    final User user = new User();

    user.setName("AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxy");
    user.setEmail("AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwx@yahoo.com");
    user.setPassword("012345678901234567890");
    user.setAge(151);

    final ValidationResult result = Validator.validate(user);
    Assertions.assertEquals(3, result.getErrors().size());
  }

  @Test
  void testGetError() throws IllegalAccessException {
    final User user = new User();

    user.setEmail("gg@gmail.com");
    user.setPassword("qwertyiop");
    user.setAge(1);

    final ValidationResult result = Validator.validate(user);
    Assertions.assertEquals("Имя не может быть null", result.getError(0));
  }
}