package com.mipt.pavelstarosvitskiy.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class CustomListTest {
  public abstract CustomList<Integer> createList();

  @Test
  public void shouldAddElement() {
    CustomList<Integer> list = createList();
    assertEquals(0, list.size());

    for (int i = 0; i < 100; i++) {
      list.add(i);
      assertEquals(i, list.get(i));
      assertEquals(i+1, list.size());
    }
  }

  @Test
  public void ShouldGetElement() {
    CustomList<Integer> list = createList();
    list.add(1);
    assertEquals(1, list.get(0));

    list.add(900_000);
    assertEquals(900_000, list.get(1));
    assertEquals(1, list.get(0));

    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.get(2);
    });
  }

  @Test
  void shouldRemoveElement() {
    CustomList<Integer> list = createList();

    for (int i = 0; i < 100; i++) list.add(i);

    list.remove(99);
    assertEquals(99, list.size());

    list.remove(0);
    assertEquals(1, list.get(0));
    assertEquals(98, list.size());

    assertThrows(IndexOutOfBoundsException.class, () -> {
      list.remove(98);
    });
  }


  @Test
  void shouldGetSize() {
    CustomList<Integer> list = createList();
    for (int i = 0; i < 100; i++) {
      assertEquals(i, list.size());
      list.add(1);
    }
  }

  @Test
  void shouldCheckIfEmpty() {
    CustomList<Integer> list = createList();
    assertTrue(list.isEmpty());

    list.add(1);
    assertFalse(list.isEmpty());

    list.remove(0);
    assertTrue(list.isEmpty());
  }
}
