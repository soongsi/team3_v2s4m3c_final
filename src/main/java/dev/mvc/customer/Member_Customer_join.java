package dev.mvc.customer;

import org.springframework.web.multipart.MultipartFile;

/*
SELECT r.m_no as r_mno, r.m_name as r_mname, r.email as r_email,
           c.cs_no, c.m_no, c.cs_type, c.cs_file1, c.cs_thumb1, cs_size1 ,cs_title, c.cs_contents, c.cs_rdate, c.cs_cnt
FROM member r, customer c
WHERE r.m_no = c.m_no
ORDER BY r.m_no ASC, c.cs_rdate DESC;
 */
public class Member_Customer_join {
//-------------------------------------------------------------------
//  Member table 
//-------------------------------------------------------------------
  /** 부모 테이블 회원 번호 */
  private int r_mno;
  /** 부모 테이블 회원 아이디 */
  private String r_mid = "";
  /** 부모 테이블 회원 이름 */
  private String r_mname = "";
  /** 부모 테이블 회원 이메일 */
  private String r_email = "";
  /** 부모 테이블 회원 패스워드  */
  private String r_mpasswd="";
  
//-------------------------------------------------------------------
//  Customer table 
//-------------------------------------------------------------------
  /** 질문 번호 */
  private int cs_no;       
  /** 회원 번호 */
  private int m_no;       
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
  /** 파일 업로드   */
  private MultipartFile file1MF;
  
  
  public int getR_mno() {
    return r_mno;
  }
  public void setR_mno(int r_mno) {
    this.r_mno = r_mno;
  }
  public String getR_mname() {
    return r_mname;
  }
  public void setR_mname(String r_mname) {
    this.r_mname = r_mname;
  }
  public String getR_email() {
    return r_email;
  }
  public void setR_email(String r_email) {
    this.r_email = r_email;
  }
  public int getCs_no() {
    return cs_no;
  }
  public void setCs_no(int cs_no) {
    this.cs_no = cs_no;
  }
  public int getM_no() {
    return m_no;
  }
  public void setM_no(int m_no) {
    this.m_no = m_no;
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
  public String getR_mid() {
    return r_mid;
  }
  public void setR_id(String r_mid) {
    this.r_mid = r_mid;
  }
  public String getR_mpasswd() {
    return r_mpasswd;
  }
  public void setR_mpasswd(String r_mpasswd) {
    this.r_mpasswd = r_mpasswd;
  }
  
  
  public MultipartFile getFile1MF() {
    return file1MF;
  }
  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }

  
  
  
  
}
