package com.mipt.pavelstarosvitskiy.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Custom implementation of ArrayList. Contains several basic methods to work with data.
 *
 * @param <A> Element type
 */
public class CustomArrayList<A> implements CustomList<A> {
  private int size;
  private int capacity;
  private Object[] array;

  /**
   * Constructs a custom array list.
   *
   * @param capacity Of a custom array list. Should not be negative or equal to zero. Default value is 8.
   * @throws IllegalArgumentException if the capacity is negative or equal to zero.
   */
  public CustomArrayList(int capacity) {
    if (capacity <= 0) throw new IllegalArgumentException();
    this.capacity = capacity;
    this.size = 0;
    this.array = new Object[capacity];
  }

  /**
   * Constructs a custom array list.
   * Default value of its capacity is 8.
   */
  public CustomArrayList() {
    this.capacity = 8;
    this.size = 0;
    this.array = new Object[capacity];
  }

  /**
   * @return The iterator of the elements in a custom array list.
   */
  @Override
  public Iterator<A> iterator() {
    return new CustomIterator();
  }

  /**
   * Adds a new element to the end of an existing custom array list.
   *
   * @param newElement Element that will be added. Should not be null.
   * @throws NullPointerException if the newElement is null.
   */
  @Override
  public void add(Object newElement) {
    if (newElement == null) throw new NullPointerException();
    if (size == capacity) {
      capacity *= 3;
      capacity /= 2;
      Object[] newArray = new Object[capacity];
      System.arraycopy(array, 0, newArray, 0, capacity / 2);
      array = newArray;
    }

    array[size] = newElement;
    size++;
  }

  /**
   * @param index of an element.
   * @return An element with provided index.
   * @throws IndexOutOfBoundsException if the index is negative, greater than or equal to the custom array list size.
   */
  @Override
  public Object get(int index) {
    if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    return array[index];
  }

  /**
   * @param index of an element that will get removed.
   * @throws IndexOutOfBoundsException if the index is negative, greater than or equal to the custom array list size.
   */
  @Override
  public void remove(int index) {
    if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

    Object[] newArray = new Object[capacity];
    System.arraycopy(array, 0, newArray, 0, index);
    System.arraycopy(array, index + 1, newArray, index, size - index - 1);
    array = newArray;
    size--;
  }

  /**
   * @return The quantity of elements in a custom array list.
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * @return True whenever this custom array list contains zero elements.
   */
  @Override
  public boolean isEmpty() {
    return (size == 0);
  }

  private class CustomIterator implements Iterator<A> {
    private int currentIndex = 0;

    @Override
    public boolean hasNext() {
      return (currentIndex < size);
    }

    @Override
    public A next() {
      if (!hasNext()) throw new NoSuchElementException();
      return (A) array[currentIndex++];
    }

    @Override
    public void remove() {
      Iterator.super.remove();
    }
  }

}
