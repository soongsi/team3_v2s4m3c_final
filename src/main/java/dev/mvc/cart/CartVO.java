package dev.mvc.cart;

import java.util.Date;

public class CartVO {
 /* 
      create table cart (
          cart_no         number(10)          not null,
          user_id         varchar2(20)        not null,
          eb_no           number(10)          not null,
          rdate            date                  default sysdate,
          primary key(cart_no, user_id), 
          FOREIGN KEY (user_id) REFERENCES member (id),
          FOREIGN KEY (eb_no) REFERENCES ebook (eb_no)
      );
 */ 
  
   /* 카트 번호 */
  private int cart_no;
  /* 아이디 */
  private String user_id="";
  /* 이북 번호 */
  private int eb_no;
  /* 담긴 날짜  */
  private Date rdate;
  
  private String eb_title = "";     // 제목
  private int eb_price;         // 상품 가격
  private String eb_thumb = "";       // 썸네일
  private int r;           // rownum
  
  
  public int getCart_no() {
    return cart_no;
  }
  public void setCart_no(int cart_no) {
    this.cart_no = cart_no;
  }
  public String getUser_id() {
    return user_id;
  }
  public void setUser_id(String user_id) {
    this.user_id = user_id;
  }
  public int getEb_no() {
    return eb_no;
  }
  public void setEb_no(int eb_no) {
    this.eb_no = eb_no;
  }
  public Date getRdate() {
    return rdate;
  }
  public void setRdate(Date rdate) {
    this.rdate = rdate;
  } 
  
  
  public int getEb_price() {
    return eb_price;
  }
  public void setEb_price(int eb_price) {
    this.eb_price = eb_price;
  }
  public String getEb_title() {
    return eb_title;
  }
  public void setEb_title(String eb_title) {
    this.eb_title = eb_title;
  }
  public String getEb_thumb() {
    return eb_thumb;
  }
  public void setEb_thumb(String eb_thumb) {
    this.eb_thumb = eb_thumb;
  }
  public int getR() {
    return r;
  }
  public void setR(int r) {
    this.r = r;
  }


  
  
  
}
