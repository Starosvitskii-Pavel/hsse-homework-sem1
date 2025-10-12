package com.mipt.pavelstarosvitskiy.generics;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtils {
  public static <T> List<T> mergeLists(List<? extends T> list1,
                                       List<? extends T> list2) {
    List<T> list = new ArrayList<>();
    if (list1 != null)
      for (T element : list1) list.add(element);

    if (list2 != null)
      for (T element : list2) list.add(element);

    return list;
  }

  public static <T> void addAll(List<? super T> destination,
                                List<? extends T> source) {
    if (destination == null || source == null) return;
    for (T element : source) destination.add(element);
  }
}