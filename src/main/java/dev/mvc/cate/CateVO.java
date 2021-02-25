package dev.mvc.cate;

/*
CREATE TABLE cate(
    cateno                            NUMBER(10)     NOT NULL    PRIMARY KEY,
    cg_no                       NUMBER(10)     NOT NULL,
    name                              VARCHAR2(100)    NOT NULL,
    seqno                             NUMBER(10)     DEFAULT 1     NOT NULL,
    visible                             CHAR(1)    DEFAULT 'Y'     NOT NULL,
    rdate                              DATE     NOT NULL
  FOREIGN KEY (cg_no) REFERENCES categrp (cg_no)
); 
 */
public class CateVO {
  /** ī�װ� ��ȣ */
  private int cateno;  
  /** ī�װ� �׷� ��ȣ */
  private int cg_no;
  /**  ī�װ� �̸� */
  private String name;
  /** ��� ���� */
  private int seqno;
  /** ��� ��� */
  private String visible;
  /** ����� */
  private String rdate;
  
  public int getCateno() {
    return cateno;
  }
  public void setCateno(int cateno) {
    this.cateno = cateno;
  }
  public int getCg_no() {
    return cg_no;
  }
  public void setCg_no(int cg_no) {
    this.cg_no = cg_no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getSeqno() {
    return seqno;
  }
  public void setSeqno(int seqno) {
    this.seqno = seqno;
  }
  public String getVisible() {
    return visible;
  }
  public void setVisible(String visible) {
    this.visible = visible;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
    
  
  
  
}