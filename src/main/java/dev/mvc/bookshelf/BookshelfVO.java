package dev.mvc.bookshelf;


import org.springframework.web.multipart.MultipartFile;

/*CREATE TABLE bookshelf(
    bookshelfno                       NUMBER(20)     NOT NULL    PRIMARY KEY,
    m_no                          NUMBER(10)     NOT NULL,
    p_name                        VARCHAR2(20)     NOT NULL,
    p_infor                         VARCHAR2(50)     NOT NULL,
    file1                                   VARCHAR(100)          NULL,
    thumb1                              VARCHAR(100)          NULL,
    size1                                 NUMBER(10)      DEFAULT 0 NULL,  
  FOREIGN KEY (m_no) REFERENCES memberin (m_no)
);*/

public class BookshelfVO {
  private int bookshelfno;
  private int m_no;
  private int p_name;
  private String p_infor;
  /** 이미지 */
  private String file1 ="";
  /** preview 이미지 preview */
  private String thumb1 ="";
  /** 저장된 파일 사이즈 */
  private long size1;
  /** 
   * 이미지 MultipartFile
   *  
   * */
  private MultipartFile file1MF;
  
  public MultipartFile getFile1MF() {
    return file1MF;
  }
  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }
  public int getBookshelfno() {
    return bookshelfno;
  }
  public void setBookshelfno(int bookshelfno) {
    this.bookshelfno = bookshelfno;
  }
  public int getMemberno() {
    return m_no;
  }
  public void setMemberno(int m_no) {
    this.m_no = m_no;
  }
  public int getP_name() {
    return p_name;
  }
  public void setP_name(int p_name) {
    this.p_name = p_name;
  }
  public String getP_infor() {
    return p_infor;
  }
  public void setP_infor(String p_infor) {
    this.p_infor = p_infor;
  }
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
  
  

  
  
}
