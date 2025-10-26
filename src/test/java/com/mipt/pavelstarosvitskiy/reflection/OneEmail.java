package com.mipt.pavelstarosvitskiy.reflection;

public class OneEmail {
  @Email(message = "Should be valid email: example@mail.domain")
  String email;

  public OneEmail(String email) {
    this.email = email;
  }
}
