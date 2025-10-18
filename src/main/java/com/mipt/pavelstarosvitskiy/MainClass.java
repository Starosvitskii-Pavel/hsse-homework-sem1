package com.mipt.pavelstarosvitskiy;

import com.mipt.pavelstarosvitskiy.collections.CustomArrayList;

import java.util.ArrayList;

public class MainClass {
    private int num;
    private String str;
    protected static double b;
    public final long NUMBER = 427;

    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            System.out.println("Iter: " + i);
        }

      CustomArrayList<Object> customArray = new CustomArrayList<>();
      System.out.println(customArray.isEmpty());
      customArray.add(56);
      customArray.add(32);
      customArray.add(65);
      System.out.println(customArray.get(0));
      System.out.println(customArray.isEmpty());
      customArray.remove(1);
      System.out.println(customArray.get(1));
      System.out.println();

      for (Object number : customArray) {
        System.out.println(number);
      }


    }
}
