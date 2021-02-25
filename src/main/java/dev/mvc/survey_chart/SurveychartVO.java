package dev.mvc.survey_chart;

/*
 
    CREATE TABLE survey_chart(
        item_no                             NUMBER(10)     NOT NULL PRIMARY KEY,
        survey_no                         NUMBER(10)     NOT NULL,
        item                        VARCHAR2(200)    NOT NULL,
        cnt                                NUMBER(10)       DEFAULT 0  NOT NULL,
       FOREIGN KEY (survey_no) REFERENCES survey (survey_no)
    );

 */
public class SurveychartVO {

  private int item_no;    // 항목 번호
  private int survey_no;  // 설문 번호
  private int cnt;           //  항목 카운트
  private String item="";  // 항목
  private int seqno;       // 항목 순서
  
  
  
  
  
  public int getItem_no() {
    return item_no;
  }
  public void setItem_no(int item_no) {
    this.item_no = item_no;
  }
  public int getSurvey_no() {
    return survey_no;
  }
  public void setSurvey_no(int survey_no) {
    this.survey_no = survey_no;
  }
  public int getCnt() {
    return cnt;
  }
  public void setCnt(int cnt) {
    this.cnt = cnt;
  }
  public String getItem() {
    return item;
  }
  public void setItem(String item) {
    this.item = item;
  }
  public int getSeqno() {
    return seqno;
  }
  public void setSeqno(int seqno) {
    this.seqno = seqno;
  }
  
  
  
  
  
  
  
  
  
  
}
