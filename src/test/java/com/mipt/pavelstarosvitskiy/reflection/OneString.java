package com.mipt.pavelstarosvitskiy.reflection;

public class OneString {
  @Size(message = "Should have from 5 to 30 characters", min = 5, max = 30)
  String string;

  public OneString(String string) {
    this.string = string;
  }
}
