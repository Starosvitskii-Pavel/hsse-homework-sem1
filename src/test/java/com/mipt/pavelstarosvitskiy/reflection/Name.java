package com.mipt.pavelstarosvitskiy.reflection;

public class Name {
  @NotNull(message = "Should not be null")
  String name;

  public Name(String name) {
    this.name = name;
  }

  public Name() {}
}
