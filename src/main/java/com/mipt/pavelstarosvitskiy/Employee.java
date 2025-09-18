package com.mipt.pavelstarosvitskiy;

public abstract class Employee {
    public abstract void Work(int num);

    public boolean GoHome(String str1, String str2) {
        return str1.equals(str2);
    }
}
