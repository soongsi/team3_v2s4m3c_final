package dev.mvc.categrp;

public class CategrpVO {
/**
 *  CREATE TABLE categrp(
    cg_no                             NUMBER(10)     NOT NULL    PRIMARY KEY,
    cg_name                           VARCHAR2(100)    NOT NULL,
    cg_seqno                          NUMBER(10)     NOT NULL,
    cg_visible                        CHAR(1)    DEFAULT 'Y'     NOT NULL,
    cg_rdate                          DATE     NOT NULL
    );
 */
  
  private int cg_no = 0;            // ī�װ� �׷� ��ȣ
  private String cg_name = "";      // ī�װ� �׷� �̸�
  private int cg_seqno = 0;         // ī�װ� �׷� ��� ����
  private String cg_visible = "";   // ī�װ� �׷� ��� ����
  private String cg_rdate = "";     // ī�װ� �׷� ��� ��¥
  
  public int getCg_no() {
    return cg_no;
  }
  public void setCg_no(int cg_no) {
    this.cg_no = cg_no;
  }
  public String getCg_name() {
    return cg_name;
  }
  public void setCg_name(String cg_name) {
    this.cg_name = cg_name;
  }
  public int getCg_seqno() {
    return cg_seqno;
  }
  public void setCg_seqno(int cg_seqno) {
    this.cg_seqno = cg_seqno;
  }
  public String getCg_visible() {
    return cg_visible;
  }
  public void setCg_visible(String cg_visible) {
    this.cg_visible = cg_visible;
  }
  public String getCg_rdate() {
    return cg_rdate;
  }
  public void setCg_rdate(String cg_rdate) {
    this.cg_rdate = cg_rdate;
  }
  
  
}
