package com.mipt.pavelstarosvitskiy.reflection;

public class FloatNumber {
  @Range(message = "Should be from -5.0 to 20.0", min = -5, max = 20)
  float number;

  public FloatNumber(float number) {
    this.number = number;
  }
}
