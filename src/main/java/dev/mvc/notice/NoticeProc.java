package dev.mvc.notice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Tool;

@Component("dev.mvc.notice.NoticeProc")
public class NoticeProc implements NoticeProcInter {
  
  @Autowired
  private NoticeDAOInter noticeDAO;

  public NoticeProc() {
    System.out.println("--> NoticeProc created.");
  }

  @Override
  public int create(NoticeVO noticeVO) {
    int cnt = this.noticeDAO.create(noticeVO);
    return cnt;
  }

  @Override
  public List<NoticeVO> list_noticeno_asc() {
    List<NoticeVO> list = this.noticeDAO.list_noticeno_asc();
    return list;
  }

  /**
   * 조회
   */
  @Override
  public NoticeVO read(int noticeno) {
    NoticeVO noticeVO = this.noticeDAO.read(noticeno);
    
    String title = noticeVO.getTitle();
    String content = noticeVO.getContent();
    
    title = Tool.convertChar(title);  // 특수 문자 처리
    // content = Tool.convertChar(content); // Ckeditor 사용시 사용하지 말 것.
    
    noticeVO.setTitle(title);
    // contentsVO.setContent(content);  // Ckeditor 사용시 사용하지 말 것.
    
    return noticeVO;
  }
  
  @Override
  public NoticeVO read_update(int noticeno) {
    NoticeVO noticeVO = this.noticeDAO.read(noticeno);
    return noticeVO;
  }

  @Override
  public int update(NoticeVO noticeVO) {
    int cnt = this.noticeDAO.update(noticeVO);
    return cnt;
  }
  
  @Override
  public int passwd_check(HashMap hashMap) {
    int cnt = this.noticeDAO.passwd_check(hashMap);
    return cnt;
  }
  
  @Override
  public int img_create(NoticeVO noticeVO) {
    int cnt = this.noticeDAO.update_img(noticeVO);
    return cnt;
  }
  
  @Override
  public int img_update(NoticeVO noticeVO) {
    int cnt = this.noticeDAO.update_img(noticeVO);
    return cnt;
  }

  @Override
  public int img_delete(NoticeVO noticeVO) {
    int cnt = this.noticeDAO.update_img(noticeVO);
    return cnt;
  }


  @Override
  public int delete(int noticeno) {
    int passwd_cnt = this.noticeDAO.delete(noticeno);
    return passwd_cnt;
  }

  /**
   * Y -> N, N -> Y
   */
  @Override
  public int update_visible(NoticeVO noticeVO) {
    if (noticeVO.getVisible().equalsIgnoreCase("Y")) {
      noticeVO.setVisible("N");
    } else {
      noticeVO.setVisible("Y");
    }
        
    int cnt = this.noticeDAO.update_visible(noticeVO);
    return cnt;
  }

  


}
