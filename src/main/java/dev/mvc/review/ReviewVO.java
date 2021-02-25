package dev.mvc.review;

/*
review_no                         NUMBER(10)     NOT NULL    PRIMARY KEY,
eb_no                             NUMBER(10)     NOT NULL,
m_no                          NUMBER(10)     NOT NULL ,
review_content                    CLOB    NOT NULL,
review_grade                      NUMBER(5)    NOT NULL,
review_rdate                      DATE     NOT NULL,
review_good                       NUMBER(10)     NOT NULL,
*/

public class ReviewVO {
  
  private int review_no;

  private int eb_no;

  private int m_no;

  private String review_content="";
  
  private String review_pw="";

  private String review_grade="";
 
  private String review_rdate="";

  private String review_good="";
  
  public int getReview_no() {
    return review_no;
  }
  public void setReview_no(int review_no) {
    this.review_no = review_no;
  }
  public int getEb_no() {
    return eb_no;
  }
  public void setEb_no(int eb_no) {
    this.eb_no = eb_no;
  }

  
  
  public int getM_no() {
    return m_no;
  }
  public void setM_no(int m_no) {
    this.m_no = m_no;
  }
  public String getReview_content() {
    return review_content;
  }
  public void setReview_content(String review_content) {
    this.review_content = review_content;
  }
  
  public String getReview_pw() {
    return review_pw;
  }
  public void setReview_pw(String review_pw) {
    this.review_pw = review_pw;
  }
  public String getReview_grade() {
    return review_grade;
  }
  public void setReview_grade(String review_grade) {
    this.review_grade = review_grade;
  }
  public String getReview_rdate() {
    return review_rdate;
  }
  public void setReview_rdate(String review_rdate) {
    this.review_rdate = review_rdate;
  }
  public String getReview_good() {
    return review_good;
  }
  public void setReview_good(String review_good) {
    this.review_good = review_good;
  }
  
  
}
