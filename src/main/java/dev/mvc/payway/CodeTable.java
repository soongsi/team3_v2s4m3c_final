package dev.mvc.payway;

import java.util.ArrayList;

public class CodeTable {

  public static synchronized ArrayList<PaywayCode> getPaywayCode(){ //  synchronized : 동시접속으로 인한 다운 방지
     ArrayList<PaywayCode> list = new ArrayList<PaywayCode>();
     list.add(new PaywayCode(Payway.신용카드, "Card"));
     list.add(new PaywayCode(Payway.계좌이체, "Bank Transfer"));
     list.add(new PaywayCode(Payway.카카오페이, "Kakao Pay"));
   
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
