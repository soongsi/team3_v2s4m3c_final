package dev.mvc.cate;

/*
    SELECT r.categrpno as r_categrpno, r.name as r_name,
               c.cateno, c.categrpno, c.name, c.seqno, c.visible, c.rdate, c.cnt    FROM categrp r, cate c
    WHERE r.categrpno = c.categrpno
    ORDER BY r.categrpno ASC, c.seqno ASC
 */
public class Categrp_Cate_join {
  // -------------------------------------------------------------------
  // Categrp table
  // -------------------------------------------------------------------
  /** �θ� ���̺� ī�װ� �׷� ��ȣ */
  private int g_cg_no;
  /** �θ� ���̺� ī�װ� �׷� �̸� */
  private String g_name;
  /** ī�װ� ��ȣ */
  
  // -------------------------------------------------------------------
  // Cate table
  // -------------------------------------------------------------------  
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
  /** ��ϵ� �� �� */
  private int cnt;
  
  public int getG_cg_no() {
    return g_cg_no;
  }
  public void setG_cg_no(int g_cg_no) {
    this.g_cg_no = g_cg_no;
  }
  public String getG_name() {
    return g_name;
  }
  public void setG_name(String g_name) {
    this.g_name = g_name;
  }
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
  public int getCnt() {
    return cnt;
  }
  public void setCnt(int cnt) {
    this.cnt = cnt;
  }
  
}