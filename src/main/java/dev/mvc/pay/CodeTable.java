package dev.mvc.pay;

import java.util.ArrayList;

public class CodeTable {

  public static synchronized ArrayList<TypeCode> getTypeCode(){ //  synchronized : 동시접속으로 인한 다운 방지
     ArrayList<TypeCode> list = new ArrayList<TypeCode>();
     list.add(new TypeCode(Pay.상품문의, "A01"));
     list.add(new TypeCode(Pay.결제장애, "A02"));
     list.add(new TypeCode(Pay.환불, "A03"));
     list.add(new TypeCode(Pay.개선사항, "A04"));
     list.add(new TypeCode(Pay.기타, "A99"));
   
     return list;
  }
  
//  public static void main(String args[]) {
//    Pay pay = Pay.결재;
//    System.out.println(pay);
//    System.out.println(pay.ordinal());  // enum 배열의 인덱스 출력
//    System.out.println(Pay.주문.ordinal());  // index : 0
//    
//    pay = Pay.주문;
//    if(pay == Pay.주문) { // 비교 연산
//      System.out.println("주문이 접수 되었습니다");     
//    }    
//  }
  
}
