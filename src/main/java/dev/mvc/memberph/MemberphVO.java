package dev.mvc.memberph;

import org.springframework.web.multipart.MultipartFile;

/*
CREATE TABLE memberph(
    orderno                         NUMBER(10)     NOT NULL    PRIMARY KEY,
    m_no                          NUMBER(10)     NOT NULL,
    p_number                        NUMBER(20)     NOT NULL,
    p_infor                           VARCHAR2(50)     NOT NULL,
    p_amount                        NUMBER(20)     NOT NULL,
    p_state                           VARCHAR2(30)     NOT NULL,
  FOREIGN KEY (m_no) REFERENCES memberin (m_no)
);
*/

public class MemberphVO {
  private int orderno;
  private int m_no;
  private int p_number;
  private String p_infor;
  private int p_amount;
  private String p_state;
  
  /** 이미지 */
  private String file1 ="";
  /** preview 이미지 preview */
  private String thumb1 ="";
  /** 저장된 파일 사이즈 */
  private long size1;
  public String getFile1() {
    return file1;
  }
  public void setFile1(String file1) {
    this.file1 = file1;
  }
  public String getThumb1() {
    return thumb1;
  }
  public void setThumb1(String thumb1) {
    this.thumb1 = thumb1;
  }
  public long getSize1() {
    return size1;
  }
  public void setSize1(long size1) {
    this.size1 = size1;
  }
  public MultipartFile getFile1MF() {
    return file1MF;
  }
  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }
  /** 
   * 이미지 MultipartFile
   *  
   * */
  private MultipartFile file1MF;
  
  public int getOrderno() {
    return orderno;
  }
  public void setOrderno(int orderno) {
    this.orderno = orderno;
  }
  public int getMemberno() {
    return m_no;
  }
  public void setMemberno(int m_no) {
    this.m_no = m_no;
  }
  public int getP_number() {
    return p_number;
  }
  public void setP_number(int p_number) {
    this.p_number = p_number;
  }
  public String getP_infor() {
    return p_infor;
  }
  public void setP_infor(String p_infor) {
    this.p_infor = p_infor;
  }
  public int getP_amount() {
    return p_amount;
  }
  public void setP_amount(int p_amount) {
    this.p_amount = p_amount;
  }
  public String getP_state() {
    return p_state;
  }
  public void setP_state(String p_state) {
    this.p_state = p_state;
  }
  
  

}
