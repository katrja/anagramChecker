package com.test.anagram.exception;

public class ValidationMessage {

  private String fieldName;

  private String invalidValue;

  private String validationMessage;

  public ValidationMessage(String fieldName, String invalidValue, String validationMessage) {
    this.fieldName = fieldName;
    this.invalidValue = invalidValue;
    this.validationMessage = validationMessage;
  }

  public String getFieldName() {
    return fieldName;
  }

  public String getInvalidValue() {
    return invalidValue;
  }

  public String getValidationMessage() {
    return validationMessage;
  }
}
