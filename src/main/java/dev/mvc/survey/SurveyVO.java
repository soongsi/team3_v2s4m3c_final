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
  
    private int survey_no;    // ���� ��ȣ
    private String title="";   // ���� ����
    private String startdate="";   // ������
    private String enddate="";   // ����
    private int cnt;   // ���� �ο�
    private String continueyn="";   // �������
    private String survey_result="";  // �������
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
