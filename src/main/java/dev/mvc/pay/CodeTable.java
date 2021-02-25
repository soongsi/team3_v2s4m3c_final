package dev.mvc.pay;

import java.util.ArrayList;

public class CodeTable {

  public static synchronized ArrayList<TypeCode> getTypeCode(){ //  synchronized : ������������ ���� �ٿ� ����
     ArrayList<TypeCode> list = new ArrayList<TypeCode>();
     list.add(new TypeCode(Pay.��ǰ����, "A01"));
     list.add(new TypeCode(Pay.�������, "A02"));
     list.add(new TypeCode(Pay.ȯ��, "A03"));
     list.add(new TypeCode(Pay.��������, "A04"));
     list.add(new TypeCode(Pay.��Ÿ, "A99"));
   
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
