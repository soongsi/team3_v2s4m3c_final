package dev.mvc.notice;

import java.util.HashMap;
import java.util.List;


public interface NoticeDAOInter {
  
  /**
   * 공지사항 등록
   * @param noticeVO
   * @return
   */
  public int create(NoticeVO noticeVO);
  
  /**
   * 공지사항 목록
   * @return
   */
  public List<NoticeVO> list_noticeno_asc();
  
  /**
   * 공지사항 조회
   * @param noticeno
   * @return
   */
  public NoticeVO read(int noticeno);
  
  /**
   * 수정용 조회
   * @param noticeno
   * @return
   */
  public NoticeVO read_update(int noticeno);
  
  /**
   * 공지사항 수정
   * @param noticeVO
   * @return
   */
  public int update(NoticeVO noticeVO);
 
  /**
   * 패스워드 검사
   * @param hashMap
   * @return
   */
  public int passwd_check(HashMap hashMap);
  
  /**
   * 공지사항 삭제
   * @param noticeno
   * @return
   */
  public int delete(int noticeno);
  
  /**
   * 이미지 변경
   * @param noticeVO
   * @return
   */
  public int update_img(NoticeVO noticeVO);
  
  /**
   * visible 수정
   * @param noticeVO
   * @return
   */
  public int update_visible(NoticeVO noticeVO);
}
