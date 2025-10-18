package com.mipt.pavelstarosvitskiy.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

  @Test
  void testEquals() {
    Student student1 = new Student(22, "Maria", 9.55);
    Student student2 = new Student(21, "Maria", 9.55);
    Student student3 = new Student(22, "Brooke", 9.55);
    Student student4 = new Student(22, "Maria", 9.56);
    Student student5 = new Student(22, "Maria", 9.55);

    assertNotEquals(student1, student2);
    assertNotEquals(student1, student3);
    assertNotEquals(student1, student4);
    assertEquals(student1, student5);
  }

  @Test
  void testHashCode() {
    Student student1 = new Student(19, "Luigi", 8.64);
    Student student2 = new Student(20, "Luigi", 8.64);
    Student student3 = new Student(19, "Mario", 8.64);
    Student student4 = new Student(19, "Luigi", 8.63);
    Student student5 = new Student(19, "Luigi", 8.64);

    assertNotEquals(student1.hashCode(), student2.hashCode());
    assertNotEquals(student1.hashCode(), student3.hashCode());
    assertNotEquals(student1.hashCode(), student4.hashCode());
    assertEquals(student1.hashCode(), student5.hashCode());

  }
}