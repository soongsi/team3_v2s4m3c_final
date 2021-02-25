package dev.mvc.notice;

import java.util.HashMap;
import java.util.List;


public interface NoticeDAOInter {
  
  /**
   * �������� ���
   * @param noticeVO
   * @return
   */
  public int create(NoticeVO noticeVO);
  
  /**
   * �������� ���
   * @return
   */
  public List<NoticeVO> list_noticeno_asc();
  
  /**
   * �������� ��ȸ
   * @param noticeno
   * @return
   */
  public NoticeVO read(int noticeno);
  
  /**
   * ������ ��ȸ
   * @param noticeno
   * @return
   */
  public NoticeVO read_update(int noticeno);
  
  /**
   * �������� ����
   * @param noticeVO
   * @return
   */
  public int update(NoticeVO noticeVO);
 
  /**
   * �н����� �˻�
   * @param hashMap
   * @return
   */
  public int passwd_check(HashMap hashMap);
  
  /**
   * �������� ����
   * @param noticeno
   * @return
   */
  public int delete(int noticeno);
  
  /**
   * �̹��� ����
   * @param noticeVO
   * @return
   */
  public int update_img(NoticeVO noticeVO);
  
  /**
   * visible ����
   * @param noticeVO
   * @return
   */
  public int update_visible(NoticeVO noticeVO);
}
