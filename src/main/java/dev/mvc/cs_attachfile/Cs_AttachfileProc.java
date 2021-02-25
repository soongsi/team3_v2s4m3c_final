package dev.mvc.cs_attachfile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.cs_attachfile.Cs_AttachfileProc")
public class Cs_AttachfileProc implements Cs_AttachfileProcInter {

  @Autowired
  private Cs_AttachfileDAOInter cs_attachfileDAO;

  // 파일 등록
  @Override
  public int create(Cs_AttachfileVO cs_attachfileVO) {
    int cnt = this.cs_attachfileDAO.create(cs_attachfileVO);
    return cnt;
  }

  // 업로드 리스트
  @Override
  public List<Cs_AttachfileVO> list() {
    List<Cs_AttachfileVO> list = this.cs_attachfileDAO.list();
    return list;
  }

  // 조회
  @Override
  public Cs_AttachfileVO read(int attach_no) {
    Cs_AttachfileVO cs_AttachfileVO = this.cs_attachfileDAO.read(attach_no);
    return cs_AttachfileVO;
  }
  
  // 삭제
  @Override
  public int delete(int attach_no) {
    int cnt = this.cs_attachfileDAO.delete(attach_no);
    return cnt;
  }


  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
