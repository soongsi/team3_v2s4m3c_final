package dev.mvc.ebook.attachfile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Tool;

@Component("dev.mvc.ebook.attachfile.AttachfileProc")
public class AttachfileProc implements AttachfileProcInter {

  @Autowired
  private AttachfileDAOInter attachfileDAO;
  
  public AttachfileProc(){
    System.out.println("--> AttachfileProc created.");
  }

  @Override
  public int create(AttachfileVO attachfileVO) {
    int cnt = this.attachfileDAO.create(attachfileVO);
    return cnt;
  }

  @Override
  public List<AttachfileVO> list() {
    List<AttachfileVO> list = null;
    list = this.attachfileDAO.list();
    
    return list;
  }
  
  @Override
  public AttachfileVO read(int attachfileno) {
    AttachfileVO attachfileVO = this.attachfileDAO.read(attachfileno);
    
    return attachfileVO;
  }

  /**
   * 첨부 파일 목록, 파일 용량 단위 출력
   */
  @Override
  public List<AttachfileVO> list_by_ebno(int eb_no) {
    List<AttachfileVO> list = this.attachfileDAO.list_by_ebno(eb_no);
    for (AttachfileVO attachfileVO : list) {
      long fsize = attachfileVO.getFsize();
      String flabel = Tool.unit(fsize);  // 파일 단위 적용
      attachfileVO.setFlabel(flabel);
    }
    return list;
  }

  @Override
  public int delete(int attachfileno) {
    int cnt = this.attachfileDAO.delete(attachfileno);
    
    return cnt;
  }

  @Override
  public int count_by_ebno(int eb_no) {
    int cnt = this.attachfileDAO.count_by_ebno(eb_no);
    
    return cnt;
  }

  @Override
  public int delete_by_ebno(int eb_no) {
    int cnt = this.attachfileDAO.delete_by_ebno(eb_no);
    
    return cnt;
  }
  
  
}