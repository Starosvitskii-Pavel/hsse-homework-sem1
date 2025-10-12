package com.mipt.pavelstarosvitskiy.generics;

public class Calculator<T extends Number> {
  public double sum(T a, T b) {
    if (a == null && b == null) return Double.NaN;
    if (a == null) return b.doubleValue();
    if (b == null) return a.doubleValue();

    return a.doubleValue() + b.doubleValue();
  }

  public double substract(T a, T b) {
    if (a == null && b == null) return Double.NaN;
    if (a == null) return -b.doubleValue();
    if (b == null) return a.doubleValue();

    return a.doubleValue() - b.doubleValue();
  }

  public double multiply(T a, T b) {
    if (a == null && b == null) return Double.NaN;
    if (a == null) return 0.0D;
    if (b == null) return 0.0D;

    return a.doubleValue() * b.doubleValue();
  }

  public double divide(T a, T b) {
    if (a == null && b == null) return Double.NaN;
    if (a == null) return 0.0D;
    if (b == null || b.doubleValue() == 0.0) return Double.NaN;

    return a.doubleValue() / b.doubleValue();
  }
}