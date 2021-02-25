package dev.mvc.notice;

import org.springframework.web.multipart.MultipartFile;

/*
 *  noticeno                         NUMBER(10)     NOT NULL    PRIMARY KEY,
    memberno                          NUMBER(10)     NULL ,
    title                             VARCHAR2(100)    NOT NULL,
    content                           CLOB          NOT NULL,
    noticeimg                         VARCHAR2(100)    NOT NULL ,
    writer                            VARCHAR2(20)     NOT NULL,
    rdate                             DATE     NOT NULL,
    visible                           CHAR(1)    DEFAULT 'Y'     NOT NULL,
 */

public class NoticeVO {
  private int noticeno;
  
  private int m_no;
  
  private int ncate_no;
  
  private String title = "";
  
  private String content = "";
  
  private String notice_pw="";
  
  private String writer = "";
  
  private String rdate="";
  
  private String ip="";
  
  private String visible;
  
  /** 이미지 */
  private String nfile1="";
  /** preview 이미지 preview */
  private String nthumb1="";
  /** 저장된 파일 사이즈 */
  private long nsize1;
  /** 이미지 MultipartFile */
  private MultipartFile nfile1MF;

  public int getNoticeno() {
    return noticeno;
  }

  public void setNoticeno(int noticeno) {
    this.noticeno = noticeno;
  }

  public int getM_no() {
    return m_no;
  }

  public void setM_no(int m_no) {
    this.m_no = m_no;
  }

  public int getNcate_no() {
    return ncate_no;
  }

  public void setNcate_no(int ncate_no) {
    this.ncate_no = ncate_no;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
  
  public String getNotice_pw() {
    return notice_pw;
  }

  public void setNotice_pw(String notice_pw) {
    this.notice_pw = notice_pw;
  }

  public String getWriter() {
    return writer;
  }

  public void setWriter(String writer) {
    this.writer = writer;
  }

  public String getRdate() {
    return rdate;
  }

  public void setRdate(String rdate) {
    this.rdate = rdate;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getVisible() {
    return visible;
  }

  public void setVisible(String visible) {
    this.visible = visible;
  }

  public String getNfile1() {
    return nfile1;
  }

  public void setNfile1(String nfile1) {
    this.nfile1 = nfile1;
  }

  public String getNthumb1() {
    return nthumb1;
  }

  public void setNthumb1(String nthumb1) {
    this.nthumb1 = nthumb1;
  }

  public long getNsize1() {
    return nsize1;
  }

  public void setNsize1(long nsize1) {
    this.nsize1 = nsize1;
  }

  public MultipartFile getNfile1MF() {
    return nfile1MF;
  }

  public void setNfile1MF(MultipartFile nfile1mf) {
    nfile1MF = nfile1mf;
  }
  
  
  

}