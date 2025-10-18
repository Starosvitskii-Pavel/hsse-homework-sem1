package com.mipt.pavelstarosvitskiy.collections;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CollectionPerformanceTester {
  @Test
  @Order(1)
  public void printTableHeader() {
    System.out.printf("%38s %5c %12s \n", "ArrayList", '|', "LinkedList");
  }

  @Test
  @Order(2)
  public void addToEndTest() {
    ArrayList<Integer> arrayList = new ArrayList<>();
    LinkedList<Integer> linkedList = new LinkedList<>();

    // ArrayList
    long startTime = System.nanoTime();
    for (int i = 0; i < 10000; i++) {
      arrayList.add(i, i);
    }
    long endTime = System.nanoTime();
    long arrayListResult = endTime - startTime;

    // linkedList
    startTime = System.nanoTime();
    for (int i = 0; i < 10000; i++) {
      linkedList.add(i, i);
    }
    endTime = System.nanoTime();
    long linkedListResult = endTime - startTime;

    System.out.printf("Adding to the end: %13d %11c %5d \n", arrayListResult/1000, '|', linkedListResult/1000);
  }

  @Test
  @Order(3)
  public void addToStartTest() {
    ArrayList<Integer> arrayList = new ArrayList<>();
    LinkedList<Integer> linkedList = new LinkedList<>();

    // ArrayList
    long startTime = System.nanoTime();
    for (int i = 0; i < 10000; i++) {
      arrayList.add(0, i);
    }
    long endTime = System.nanoTime();
    long arrayListResult = endTime - startTime;

    // linkedList
    startTime = System.nanoTime();
    for (int i = 0; i < 10000; i++) {
      linkedList.add(0, i);
    }
    endTime = System.nanoTime();
    long linkedListResult = endTime - startTime;

    System.out.printf("Adding to the start: %12d %10c %5d \n", arrayListResult/1000, '|', linkedListResult/1000);
  }

  @Test
  @Order(4)
  public void addToMiddleTest() {
    ArrayList<Integer> arrayList = new ArrayList<>();
    LinkedList<Integer> linkedList = new LinkedList<>();

    // ArrayList
    long startTime = System.nanoTime();
    for (int i = 0; i < 10000; i++) {
      arrayList.add(arrayList.size()/2, i);
    }
    long endTime = System.nanoTime();
    long arrayListResult = endTime - startTime;

    // linkedList
    startTime = System.nanoTime();
    for (int i = 0; i < 10000; i++) {
      linkedList.add(linkedList.size()/2, i);
    }
    endTime = System.nanoTime();
    long linkedListResult = endTime - startTime;

    System.out.printf("Adding to the middle: %11d %10c %7d \n", arrayListResult/1000, '|', linkedListResult/1000);
  }

  @Test
  @Order(5)
  public void getElementTest() {
    ArrayList<Integer> arrayList = new ArrayList<>();
    LinkedList<Integer> linkedList = new LinkedList<>();

    // ArrayList
    for (int i = 0; i < 10000; i++) {
      arrayList.add(0, i);
    }

    long startTime = System.nanoTime();
    for (int i = 0; i < 10000; i++) {
      arrayList.get(i);
    }
    long endTime = System.nanoTime();
    long arrayListResult = endTime - startTime;


    // linkedList
    for (int i = 0; i < 10000; i++) {
      linkedList.add(0, i);
    }

    startTime = System.nanoTime();
    for (int i = 0; i < 10000; i++) {
      linkedList.get(i);
    }
    endTime = System.nanoTime();
    long linkedListResult = endTime - startTime;

    System.out.printf("Getting the element: %11d %11c %7d \n", arrayListResult/1000, '|', linkedListResult/1000);
  }

  @Test
  @Order(6)
  public void removeFirstElementTest() {
    ArrayList<Integer> arrayList = new ArrayList<>();
    LinkedList<Integer> linkedList = new LinkedList<>();

    // ArrayList
    for (int i = 0; i < 10000; i++) {
      arrayList.add(0, i);
    }

    long startTime = System.nanoTime();
    for (int i = 0; i < 10000; i++) {
      arrayList.remove(0);
    }
    long endTime = System.nanoTime();
    long arrayListResult = endTime - startTime;


    // linkedList
    for (int i = 0; i < 10000; i++) {
      linkedList.add(0, i);
    }

    startTime = System.nanoTime();
    for (int i = 0; i < 10000; i++) {
      linkedList.remove(0);
    }
    endTime = System.nanoTime();
    long linkedListResult = endTime - startTime;

    System.out.printf("Removing the first element: %5d %10c %5d \n", arrayListResult/1000, '|', linkedListResult/1000);
  }

  @Test
  @Order(7)
  public void removeLastElementTest() {
    ArrayList<Integer> arrayList = new ArrayList<>();
    LinkedList<Integer> linkedList = new LinkedList<>();

    // ArrayList
    for (int i = 0; i < 10000; i++) {
      arrayList.add(0, i);
    }

    long startTime = System.nanoTime();
    for (int i = 0; i < 10000; i++) {
      arrayList.remove(arrayList.size()-1);
    }
    long endTime = System.nanoTime();
    long arrayListResult = endTime - startTime;


    // linkedList
    for (int i = 0; i < 10000; i++) {
      linkedList.add(0, i);
    }

    startTime = System.nanoTime();
    for (int i = 0; i < 10000; i++) {
      linkedList.remove(linkedList.size()-1);
    }
    endTime = System.nanoTime();
    long linkedListResult = endTime - startTime;

    System.out.printf("Removing the last element: %5d %11c %5d \n", arrayListResult/1000, '|', linkedListResult/1000);
  }
}
