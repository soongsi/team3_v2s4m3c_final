package dev.mvc.ncate;

/*
CREATE TABLE ncate(
    ncate_no                          NUMBER(10)     NOT NULL    PRIMARY KEY,
    ncate_name                        VARCHAR2(100)    NOT NULL,
    nseqno                            NUMBER(10)     NOT NULL,
    nvisible                          CHAR(1)    NOT NULL,
    ncate_rdate                       DATE     NOT NULL,
    ncate_cnt                         NUMBER(10)     NOT NULL
);

 */
public class NcateVO {
  private int ncate_no;  
 
  private String ncate_name;
  
  private int nseqno;

  private String nvisible;
  
  private String ncate_rdate;
  
  private int ncate_cnt;

  public int getNcate_no() {
    return ncate_no;
  }

  public void setNcate_no(int ncate_no) {
    this.ncate_no = ncate_no;
  }

  public String getNcate_name() {
    return ncate_name;
  }

  public void setNcate_name(String ncate_name) {
    this.ncate_name = ncate_name;
  }

  public int getNseqno() {
    return nseqno;
  }

  public void setNseqno(int nseqno) {
    this.nseqno = nseqno;
  }

  public String getNvisible() {
    return nvisible;
  }

  public void setNvisible(String nvisible) {
    this.nvisible = nvisible;
  }

  public String getNcate_rdate() {
    return ncate_rdate;
  }

  public void setNcate_rdate(String ncate_rdate) {
    this.ncate_rdate = ncate_rdate;
  }

  public int getNcate_cnt() {
    return ncate_cnt;
  }

  public void setNcate_cnt(int ncate_cnt) {
    this.ncate_cnt = ncate_cnt;
  } 
  
}


