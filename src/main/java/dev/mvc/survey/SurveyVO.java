package dev.mvc.survey;

/*
  CREATE TABLE survey(
      survey_no                         NUMBER(10)     NOT NULL    PRIMARY KEY,
      seqno                               NUMBER(10)       NOT NULL,
      title                             VARCHAR2(100)    NOT NULL,
      startdate                       VARCHAR2(30)     NOT NULL,
      enddate                         VARCHAR2(30)     NOT NULL,
      cnt                               NUMBER(10)     DEFAULT 0     NOT NULL,
      continueyn                        VARCHAR2(5)    DEFAULT 'Y'     NOT NULL,
      survey_result                     VARCHAR2(100)    NULL                               
  );
 */
public class SurveyVO {
  
    private int survey_no;    // 설문 번호
    private String title="";   // 설문 제목
    private String startdate="";   // 시작일
    private String enddate="";   // 종료
    private int cnt;   // 참여 인원
    private String continueyn="";   // 진행상태
    private String survey_result="";  // 설문결과
    private int seqno;
  
  
    public int getSurvey_no() {
      return survey_no;
    }
    public void setSurvey_no(int survey_no) {
      this.survey_no = survey_no;
    }
    public String getTitle() {
      return title;
    }
    public void setTitle(String title) {
      this.title = title;
    }
    public String getStartdate() {
      return startdate;
    }
    public void setStartdate(String startdate) {
      this.startdate = startdate;
    }
    public String getEnddate() {
      return enddate;
    }
    public void setEnddate(String enddate) {
      this.enddate = enddate;
    }
    public int getCnt() {
      return cnt;
    }
    public void setCnt(int cnt) {
      this.cnt = cnt;
    }
    public String getContinueyn() {
      return continueyn;
    }
    public void setContinueyn(String continueyn) {
      this.continueyn = continueyn;
    }
    public String getSurvey_result() {
      return survey_result;
    }
    public void setSurvey_result(String survey_result) {
      this.survey_result = survey_result;
    }
    public int getSeqno() {
      return seqno;
    }
    public void setSeqno(int seqno) {
      this.seqno = seqno;
    }
  
  
  
  
  
  
  
}
