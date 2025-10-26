package com.mipt.pavelstarosvitskiy.reflection;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
  private boolean isValid;
  private List<String> errors;

  public ValidationResult(List<String> errors) {
    this.errors = errors;
    this.isValid = errors.isEmpty();
  }

  public ValidationResult() {
    this.errors = new ArrayList<>();
    this.isValid = true;
  }

  public void addError(String error) {
    errors.add(error);
    isValid = false;
  }

  public String getError(int index) {
    return errors.get(index);
  }

  public boolean isValid() {
    return isValid;
  }

  public List<String> getErrors() {
    return errors;
  }
}
