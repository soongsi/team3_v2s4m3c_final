package dev.mvc.ebook;

import org.springframework.web.multipart.MultipartFile;

public class EbookVO {
  /*
    eb_no                             NUMBER(10)     NOT NULL    PRIMARY KEY,
    cateno                            NUMBER(10)     NULL ,
    eb_title                          VARCHAR2(200)    NOT NULL,
    eb_author                         VARCHAR2(100)    NOT NULL,
    eb_publisher                      VARCHAR2(20)     NOT NULL,
    eb_pdate                          DATE     NULL ,
    eb_useinfo                          VARCHAR2(500)   NULL,
    eb_device                           VARCHAR2(500)   NOT NULL,
    eb_infor                          LONG     NULL ,
    eb_price                          NUMBER(10)     NOT NULL,
    eb_saletot                        NUMBER(10)     DEFAULT 0     NULL ,
    eb_page                           NUMBER(10)     NULL ,
    eb_file1                                   VARCHAR(100)          NULL,
    eb_size1                                 NUMBER(10)      DEFAULT 0 NULL, 
    eb_file2                                   VARCHAR(100)          NULL,
    eb_size2                                 NUMBER(10)      DEFAULT 0 NULL, 
    eb_thumb                                VARCHAR(100)          NULL,
    word                                 VARCHAR(300)          NULL,
    rdate                             DATE     NOT NULL,
   */
  
  private int eb_no = 0;            // �̺� ��ȣ
  private int cateno = 0;           // ī�װ� ��ȣ
  private String eb_title = "";     // ����
  private String eb_author = "";    // ����
  private String eb_publisher = "";    // ���ǻ�  
  private String eb_pdate = "";     // �Ⱓ��
  private String eb_useinfo = "";     // �̿�ȳ�
  private String eb_device = "";     // �������
  private String eb_infor = "";     // �Ұ�
  private int eb_price = 0;         // ����
  private int eb_saletot = 0;       // �Ǹŷ�
  private int eb_page = 0;          // ������ ��
  private String eb_file1 = "";          // �̺� ���ϸ�
  private long eb_size1 = 0;          // �̺� ���� ������
  private String eb_file2 = "";          // ���� �̹��� ���ϸ�
  private long eb_size2 = 0;          // ���� �̹��� ���� ������
  private String eb_thumb = "";       // �����
  private String word = "";        // �˻���
  private String rdate = "";        // �����
  
  private int r = 0;           // rownum

  private MultipartFile file1MF;
  private MultipartFile file2MF;
  
  
  public int getEb_no() {
    return eb_no;
  }
  public void setEb_no(int eb_no) {
    this.eb_no = eb_no;
  }
  public int getCateno() {
    return cateno;
  }
  public void setCateno(int cateno) {
    this.cateno = cateno;
  }
  
  public String getEb_title() {
    return eb_title;
  }
  public void setEb_title(String eb_title) {
    this.eb_title = eb_title;
  }
  
  public String getEb_author() {
    return eb_author;
  }
  public void setEb_author(String eb_author) {
    this.eb_author = eb_author;
  }
  
  public String getEb_publisher() {
    return eb_publisher;
  }
  public void setEb_publisher(String eb_publisher) {
    this.eb_publisher = eb_publisher;
  }
  
  public String getEb_pdate() {
    return eb_pdate;
  }
  public void setEb_pdate(String eb_pdate) {
    this.eb_pdate = eb_pdate;
  }
  
  public String getEb_useinfo() {
    return eb_useinfo;
  }
  public void setEb_useinfo(String eb_useinfo) {
    this.eb_useinfo = eb_useinfo;
  }
  
  public String getEb_device() {
    return eb_device;
  }
  public void setEb_device(String eb_device) {
    this.eb_device = eb_device;
  }
  
  public String getEb_infor() {
    return eb_infor;
  }
  public void setEb_infor(String eb_infor) {
    this.eb_infor = eb_infor;
  }
  
  public int getEb_price() {
    return eb_price;
  }
  public void setEb_price(int eb_price) {
    this.eb_price = eb_price;
  }
  
  public int getEb_saletot() {
    return eb_saletot;
  }
  public void setEb_saletot(int eb_saletot) {
    this.eb_saletot = eb_saletot;
  }
  
  public int getEb_page() {
    return eb_page;
  }
  public void setEb_page(int eb_page) {
    this.eb_page = eb_page;
  }
  
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  } 
  
  public String getEb_file1() {
    return eb_file1;
  }
  public void setEb_file1(String eb_file1) {
    this.eb_file1 = eb_file1;
  }
  
  public long getEb_size1() {
    return eb_size1;
  }
  public void setEb_size1(long eb_size1) {
    this.eb_size1 = eb_size1;
  }
  
  public MultipartFile getFile1MF() {
    return file1MF;
  }
  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }
  
  public String getEb_file2() {
    return eb_file2;
  }
  public void setEb_file2(String eb_file2) {
    this.eb_file2 = eb_file2;
  }
  
  public long getEb_size2() {
    return eb_size2;
  }
  public void setEb_size2(long eb_size2) {
    this.eb_size2 = eb_size2;
  }
  
  public MultipartFile getFile2MF() {
    return file2MF;
  }
  public void setFile2MF(MultipartFile file2mf) {
    file2MF = file2mf;
  }
  
  public String getEb_thumb() {
    return eb_thumb;
  }
  public void setEb_thumb(String eb_thumb) {
    this.eb_thumb = eb_thumb;
  }
  
  public String getWord() {
    return word;
  }
  public void setWord(String word) {
    this.word = word;
  }
  
  public int getR() {
    return r;
  }
  public void setRownum(int r) {
    this.r = r;
  }
  
}
