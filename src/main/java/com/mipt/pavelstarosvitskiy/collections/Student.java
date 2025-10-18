package com.mipt.pavelstarosvitskiy.collections;

public class Student {
  private int id;
  private String name;
  private double grade;

  public Student(int id, String name, double grade) {
    this.id = id;
    this.name = name;
    this.grade = grade;
  }

  @Override
  public boolean equals(Object obj) {
    if (getClass() != obj.getClass()) return false;

    Student student2 = (Student) obj;
    if (this.id != student2.id) return false;
    if (!this.name.equals(student2.name)) return false;
    return this.grade == student2.grade;
  }

  @Override
  public int hashCode() {
    int result = 31;
    result = 17 * result + name.hashCode();
    result = 17 * result + id;
    result = 17 * result + ((int) Math.round(grade*1000));
    return result;
  }

  public double getGrade() {
    return grade;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }
}
