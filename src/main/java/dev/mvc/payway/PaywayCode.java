package dev.mvc.payway;

public class PaywayCode {
  private Payway payway;       // ������
  private String code;  // ��
  
  public PaywayCode(Payway payway, String code) {
    this.payway = payway;
    this.code = code;
  }

  public Payway getPayway() {
    return payway;
  }

  public String getCode() {
    return code;
  }
  
}