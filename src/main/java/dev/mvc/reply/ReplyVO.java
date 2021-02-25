package dev.mvc.reply;
/*
    reply_no                         NUMBER(10)     NOT NULL    PRIMARY KEY,
    review_no                         NUMBER(10)     NOT NULL,
    memberno                          NUMBER(6)    NOT NULL,
    reply_cont                      VARCHAR2(3000)     NOT NULL,
    reply_pw                          VARCHAR2(20)     NOT NULL,
    reply_date                        DATE     NOT NULL,
 */
public class ReplyVO {
  private int reply_no;
  
  private int review_no;
  
  private int m_no;
  
  private String reply_cont="";
  
  private String reply_pw="";
  
  private String reply_date="";

  public int getReply_no() {
    return reply_no;
  }

  public void setReply_no(int reply_no) {
    this.reply_no = reply_no;
  }

  public int getReview_no() {
    return review_no;
  }

  public void setReview_no(int review_no) {
    this.review_no = review_no;
  }

 
  public int getM_no() {
    return m_no;
  }

  public void setM_no(int m_no) {
    this.m_no = m_no;
  }

  public String getReply_cont() {
    return reply_cont;
  }

  public void setReply_cont(String reply_cont) {
    this.reply_cont = reply_cont;
  }

  public String getReply_pw() {
    return reply_pw;
  }

  public void setReply_pw(String reply_pw) {
    this.reply_pw = reply_pw;
  }

  public String getReply_date() {
    return reply_date;
  }

  public void setReply_date(String reply_date) {
    this.reply_date = reply_date;
  }
  

}
