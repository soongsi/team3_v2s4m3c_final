package dev.mvc.order_req;
/*
    CREATE TABLE order_req(
        order_no                        NUMBER(10)         NOT NULL    PRIMARY KEY,
        payway                            VARCHAR2(30)     NOT NULL,
        price                                NUMBER(10)        NOT NULL ,
        eb_no                             NUMBER(10)         NULL ,
        user_id                             VARCHAR2(20)         NULL,
        rdate                             DATE             default sysdate,
        FOREIGN KEY (eb_no) REFERENCES EBOOK (eb_no),
        FOREIGN KEY (user_id) REFERENCES member (id)
    );

 */
public class OrderreqVO {
  
  private int order_no;          // �ֹ���û ��ȣ
  private String payid="";       // ���� ��ȣ
  private int eb_no;              //  �̺� ��ȣ
  private int price;                //  �����ݾ�
  private String rdate="";       //  ��û�Ͻ�
  private String payway="";     // ���� ���
  private String user_id="";      //  ���̵�
  
  // REFERENCE EBOOK  TABLE
  private String eb_title = "";     // ����
  private String eb_file1 = "";          // �̺� ���ϸ�
  private String eb_thumb = "";       // �����
  private int eb_price = 0;         // ����
  

  public int getOrder_no() {
    return order_no;
  }
  public void setOrder_no(int order_no) {
    this.order_no = order_no;
  }
  public String getPayid() {
    return payid;
  }
  public void setPayid(String payid) {
    this.payid = payid;
  }
  
  
  // REFERENCE EBOOK  TABLE
  public int getEb_no() {
    return eb_no;
  }
  public void setEb_no(int eb_no) {
    this.eb_no = eb_no;
  }
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    this.price = price;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  public String getPayway() {
    return payway;
  }
  public void setPayway(String payway) {
    this.payway = payway;
  }
  public String getUser_id() {
    return user_id;
  }
  public void setUser_id(String user_id) {
    this.user_id = user_id;
  }
  public String getEb_title() {
    return eb_title;
  }
  public void setEb_title(String eb_title) {
    this.eb_title = eb_title;
  }
  public String getEb_file1() {
    return eb_file1;
  }
  public void setEb_file1(String eb_file1) {
    this.eb_file1 = eb_file1;
  }
  public String getEb_thumb() {
    return eb_thumb;
  }
  public void setEb_thumb(String eb_thumb) {
    this.eb_thumb = eb_thumb;
  }
  public int getEb_price() {
    return eb_price;
  }
  public void setEb_price(int eb_price) {
    this.eb_price = eb_price;
  }
  
  
  
  
  

}
