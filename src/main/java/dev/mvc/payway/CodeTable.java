package dev.mvc.payway;

import java.util.ArrayList;

public class CodeTable {

  public static synchronized ArrayList<PaywayCode> getPaywayCode(){ //  synchronized : ������������ ���� �ٿ� ����
     ArrayList<PaywayCode> list = new ArrayList<PaywayCode>();
     list.add(new PaywayCode(Payway.�ſ�ī��, "Card"));
     list.add(new PaywayCode(Payway.������ü, "Bank Transfer"));
     list.add(new PaywayCode(Payway.īī������, "Kakao Pay"));
   
     return list;
  }
  
//  public static void main(String args[]) {
//    Pay pay = Pay.����;
//    System.out.println(pay);
//    System.out.println(pay.ordinal());  // enum �迭�� �ε��� ���
//    System.out.println(Pay.�ֹ�.ordinal());  // index : 0
//    
//    pay = Pay.�ֹ�;
//    if(pay == Pay.�ֹ�) { // �� ����
//      System.out.println("�ֹ��� ���� �Ǿ����ϴ�");     
//    }    
//  }
  
}
