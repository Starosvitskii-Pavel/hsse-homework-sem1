package com.mipt.pavelstarosvitskiy.collections;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class StudentUtils {
  public static ArrayList<Student> findStudentsByGradeRange(Map<Integer, Student> map, double minGrade, double maxGrade) {
    ArrayList<Student> result = new ArrayList<>();
    for (int i : map.keySet()) {
      Student student = map.get(i);
      if (minGrade <= student.getGrade() && maxGrade >= student.getGrade()) {
        result.add(map.get(i));
      }
    }
    return result;
  }

  public static ArrayList<Student> getTopNStudents(TreeMap<Integer, Student> map, int n) {
    ArrayList<Student> result = new ArrayList<>();
    Integer[] keyArray = map.keySet().toArray(new Integer[0]);
    for (int i = 0; i < n; i++) {
      result.add(map.get(keyArray[i]));
    }
    return result;
  }
}
