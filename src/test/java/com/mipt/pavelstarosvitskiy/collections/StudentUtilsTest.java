package com.mipt.pavelstarosvitskiy.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class StudentUtilsTest {

  @Test
  void shouldFindStudentsByGradeRange() {
    HashMap<Integer, Student> hashMap = new HashMap<>();
    Student student1 = new Student(1, "John", 9.99);
    Student student2 = new Student(2, "Paul", 9.09);
    Student student3 = new Student(3, "George", 9.01);
    Student student4 = new Student(4, "Ringo", 10.00);

    hashMap.put(student1.getId(), student1);
    hashMap.put(student2.getId(), student2);
    hashMap.put(student3.getId(), student3);
    hashMap.put(student4.getId(), student4);

    ArrayList<Student> result1 = StudentUtils.findStudentsByGradeRange(hashMap, 9.00, 10.00);
    ArrayList<Student> expectedResult1 = new ArrayList<>();
    expectedResult1.add(student1);
    expectedResult1.add(student2);
    expectedResult1.add(student3);
    expectedResult1.add(student4);

    assertEquals(expectedResult1, result1);

    ArrayList<Student> result2 = StudentUtils.findStudentsByGradeRange(hashMap, 9.10, 10.00);
    ArrayList<Student> expectedResult2 = new ArrayList<>();
    expectedResult2.add(student1);
    expectedResult2.add(student4);

    assertEquals(expectedResult2, result2);

    ArrayList<Student> result3 = StudentUtils.findStudentsByGradeRange(hashMap, 6.60, 7.50);
    ArrayList<Student> expectedResult3 = new ArrayList<>();

    assertEquals(expectedResult3, result3);
  }

  @Test
  void shouldGetTopNStudents() {
    TreeMap<Integer, Student> treeMap = new TreeMap<>(Comparator.reverseOrder());
    Student student1 = new Student(98, "Olimar", 9.96);
    Student student2 = new Student(20004, "Louie", 3.00);
    Student student3 = new Student(5, "Aplh", 8.04);
    Student student4 = new Student(777, "Britanny", 8.50);

    treeMap.put(student1.getId(), student1);
    treeMap.put(student2.getId(), student2);
    treeMap.put(student3.getId(), student3);
    treeMap.put(student4.getId(), student4);

    ArrayList<Student> result1 = StudentUtils.getTopNStudents(treeMap, 4);
    ArrayList<Student> expectedResult1 = new ArrayList<>();
    expectedResult1.add(student2);
    expectedResult1.add(student4);
    expectedResult1.add(student1);
    expectedResult1.add(student3);

    assertEquals(expectedResult1, result1);

    ArrayList<Student> result2 = StudentUtils.getTopNStudents(treeMap, 2);
    ArrayList<Student> expectedResult2 = new ArrayList<>();
    expectedResult2.add(student2);
    expectedResult2.add(student4);

    assertEquals(expectedResult2, result2);
  }
}