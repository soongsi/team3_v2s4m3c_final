package dev.mvc.notice;

import java.util.HashMap;
import java.util.List;


public interface NoticeProcInter {
  
  /**
   * 생성
   * @param noticeVO
   * @return
   */
  public int create(NoticeVO noticeVO);
  
  /**
   * 목록
   * @return
   */
  public List<NoticeVO> list_noticeno_asc();
  
  /**
   * 조회
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
   * 수정
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
   * 이미지 등록
   * @param noticeVO
   * @return
   */
  public int img_create(NoticeVO noticeVO);
  
  /**
   * 이미지 수정
   * @param noticeVO
   * @return
   */
  public int img_update(NoticeVO noticeVO);

  /**
   * 이미지 삭제
   * @param noticeVO
   * @return
   */
  public int img_delete(NoticeVO noticeVO);
  
  
  /**
   * 공지사항 삭제
   * @param noticeno
   * @return
   */
  public int delete(int noticeno);
  
  /**
   * visible 수정
   * @param noticeVO
   * @return
   */
  public int update_visible(NoticeVO noticeVO);
}
