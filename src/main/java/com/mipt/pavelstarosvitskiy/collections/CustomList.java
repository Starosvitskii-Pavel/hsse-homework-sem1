package com.mipt.pavelstarosvitskiy.collections;

/**
 * An interface that has several methods - basic functionality of the custom lists.
 * @param <A>
 */
public interface CustomList<A> extends  Iterable<A>{
  /**
   * Adds a new element to an existing custom list.
   * @param newElement Element that will be added.
   */
  void add(A newElement);

  /**
   * @param index of an element.
   * @return An element with provided index.
   */
  Object get(int index);

  /**
   * @param index of an element that will get removed.
   */
  void remove(int index);

  /**
   * @return The quantity of elements in a custom list.
   */
  int size();

  /**
   * @return True whenever this custom array list contains zero elements.
   */
  boolean isEmpty();
}
