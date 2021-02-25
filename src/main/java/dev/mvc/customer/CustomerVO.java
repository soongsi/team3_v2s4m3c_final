package dev.mvc.customer;

import org.springframework.web.multipart.MultipartFile;

/*
 CREATE TABLE customer(
    cs_no                             NUMBER(10)         NOT NULL    PRIMARY KEY,
    cs_type                           VARCHAR2(20)     NOT NULL,
    cs_title                              VARCHAR2(30)     NOT NULL,
    cs_contents                       CLOB                   NOT NULL,
    cs_file1                              VARCHAR2(100)    NULL ,
    cs_thumb1                         VARCHAR2(100)    NULL ,
    cs_size1                          NUMBER(10)         DEFAULT 0     NULL ,
    cs_rdate                          DATE                     NOT NULL,
    cs_cnt                            NUMBER(10)         DEFAULT 0     NOT NULL,
    m_no                              NUMBER(10)         NULL,
    cs_passwd                           VARCHAR2(60)     DEFAULT '123456'    NOT NULL,
        FOREIGN KEY (m_no) REFERENCES member (m_no)
);
 */
public class CustomerVO {

  /** 질문 번호 */
  private int cs_no;       
  /** 질문 유형  */
  private String cs_type="";
  /** 제목 */
  private String cs_title="";
  /** 내용 */
  private String cs_contents="";
  /** 메인 이미지 */
  private String cs_file1="";
  /** 메인 이미지 미리보기 */
  private String cs_thumb1="";
  /** 저장된 파일 크기 */
  private long cs_size1;
  /** 등록 날짜 */
  private String cs_rdate="";
  /** 문의글 조회수  */
  private int cs_cnt;
  /** 고객 번호 */
  private int m_no;
  /** 수정삭제 비밀번호 */
  private String cs_passwd;
  /** 
   * 이미지 MultipartFile 
   *<input type='file' class="form-control" name='file1MF' id='file1MF' 
   *          value='' placeholder="파일 선택" multiple="multiple">
   */
  private MultipartFile file1MF;
  public int getCs_no() {
    return cs_no;
  }
  public void setCs_no(int cs_no) {
    this.cs_no = cs_no;
  }
  public String getCs_type() {
    return cs_type;
  }
  public void setCs_type(String cs_type) {
    this.cs_type = cs_type;
  }
  public String getCs_title() {
    return cs_title;
  }
  public void setCs_title(String cs_title) {
    this.cs_title = cs_title;
  }
  public String getCs_contents() {
    return cs_contents;
  }
  public void setCs_contents(String cs_contents) {
    this.cs_contents = cs_contents;
  }
  public String getCs_file1() {
    return cs_file1;
  }
  public void setCs_file1(String cs_file1) {
    this.cs_file1 = cs_file1;
  }
  public String getCs_thumb1() {
    return cs_thumb1;
  }
  public void setCs_thumb1(String cs_thumb1) {
    this.cs_thumb1 = cs_thumb1;
  }
  public long getCs_size1() {
    return cs_size1;
  }
  public void setCs_size1(long cs_size1) {
    this.cs_size1 = cs_size1;
  }
  public String getCs_rdate() {
    return cs_rdate;
  }
  public void setCs_rdate(String cs_rdate) {
    this.cs_rdate = cs_rdate;
  }
  public int getCs_cnt() {
    return cs_cnt;
  }
  public void setCs_cnt(int cs_cnt) {
    this.cs_cnt = cs_cnt;
  }
  public int getM_no() {
    return m_no;
  }
  public void setM_no(int m_no) {
    this.m_no = m_no;
  }
  public String getCs_passwd() {
    return cs_passwd;
  }
  public void setCs_passwd(String cs_passwd) {
    this.cs_passwd = cs_passwd;
  }
  public MultipartFile getFile1MF() {
    return file1MF;
  }
  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }
  
  

  
  
  
  
  
  
  
  
  
}
