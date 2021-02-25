package dev.mvc.cs_attachfile;

import java.util.List;

public interface Cs_AttachfileProcInter {

  // 파일 등록
  public int create(Cs_AttachfileVO cs_attachfileVO);
  
  // 업로드 목록
  public List<Cs_AttachfileVO> list();
  
  // 조회
  public Cs_AttachfileVO read(int attach_no);
  
  //  삭제
  public int delete(int attach_no);
  
  
  
  
}
