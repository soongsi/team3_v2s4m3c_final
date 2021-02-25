package dev.mvc.cs_attachfile;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Cs_AttachfileVO {

  /*
  CREATE TABLE customer_attachfile(
          attach_no                  NUMBER(10)         NOT NULL         PRIMARY KEY,
          cs_no                          NUMBER(10)         NULL ,
          fname                          VARCHAR2(200)         NOT NULL,
          fupname                        VARCHAR2(200)         NOT NULL,
          thumb                          VARCHAR2(200)         NULL ,
          fsize                           NUMBER(10)         DEFAULT 0         NOT NULL,
          rdate                           DATE               DEFAULT sysdate,
    FOREIGN KEY (cs_no) REFERENCES customer (cs_no)
  );

*/
    
    private int attach_no;          // 첨부 번호
    private int cs_no;                // 문의 번호(FK)
    private String fname="";       // 원본 파일명
    private String fupname="";    // 업로드 파일명
    private String thumb="";       // Thumb 이미지 파일명
    private long fsize;               // 파일 크기
    private String rdate="";         // 등록일
    
    /** Form의 파일을 MultipartFile로 변환하여 List에 저장  */
    private List<MultipartFile> fnamesMF;
    
    public int getAttach_no() {
      return attach_no;
    }
    public void setAttach_no(int attach_no) {
      this.attach_no = attach_no;
    }
    public int getCs_no() {
      return cs_no;
    }
    public void setCs_no(int cs_no) {
      this.cs_no = cs_no;
    }
    public String getFname() {
      return fname;
    }
    public void setFname(String fname) {
      this.fname = fname;
    }
    public String getFupname() {
      return fupname;
    }
    public void setFupname(String fupname) {
      this.fupname = fupname;
    }
    public String getThumb() {
      return thumb;
    }
    public void setThumb(String thumb) {
      this.thumb = thumb;
    }
    public long getFsize() {
      return fsize;
    }
    public void setFsize(long fsize) {
      this.fsize = fsize;
    }
    public String getRdate() {
      return rdate;
    }
    public void setRdate(String rdate) {
      this.rdate = rdate;
    }
    public List<MultipartFile> getFnamesMF() {
      return fnamesMF;
    }
    public void setFnamesMF(List<MultipartFile> fnamesMF) {
      this.fnamesMF = fnamesMF;
    }
  
  
    
    
    
}
