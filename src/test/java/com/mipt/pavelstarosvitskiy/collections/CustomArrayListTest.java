package com.mipt.pavelstarosvitskiy.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayListTest extends CustomListTest {

  @Override
  public CustomList<Integer> createList() {
    return new CustomArrayList<>();
  }

  @Test
  public void shouldNotAllowWrongCapacity() {
    assertThrows(IllegalArgumentException.class, () -> {
      CustomArrayList<Integer> list = new CustomArrayList<>(0);
    });

    assertThrows(IllegalArgumentException.class, () -> {
      CustomArrayList<Integer> list = new CustomArrayList<>(-1);
    });
  }
}