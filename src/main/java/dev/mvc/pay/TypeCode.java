package dev.mvc.pay;

public class TypeCode {
  private Pay pay;       // ������
  private String value;  // ��
  
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