package com.mipt.pavelstarosvitskiy.reflection;

public class OneNumber {
  @Range(message = "From -15 to 50", min = -15, max = 50)
  long number;

  public OneNumber(long number) {
    this.number = number;
  }
}
