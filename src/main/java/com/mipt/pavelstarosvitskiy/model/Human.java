package com.mipt.pavelstarosvitskiy.model;

public class Human {
    private String name;
    private String surname;
    private int age;
    private boolean isEmployed;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public boolean isEmployed() {
        return isEmployed;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmployed(boolean employed) {
        isEmployed = employed;
    }
}
