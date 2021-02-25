package dev.mvc.pay;

public class TypeCode {
  private Pay pay;       // 열거형
  private String value;  // 값
  
  public TypeCode(Pay pay, String value) {
    this.pay = pay;
    this.value = value;
  }

  public Pay getPay() {
    return pay;
  }

  public String getValue() {
    return value;
  }
  
}