package dev.mvc.review;

import java.util.HashMap;
import java.util.List;


public interface ReviewProcInter {
  /**
   * ���� ���
   * @param cateVO
   * @return
   */
  public int create(ReviewVO reviewVO);
  
  /**
   * ���� ��� - ��ϼ� ����
   * @return
   */
  public List<ReviewVO> list_reviewno_asc();
  
  /**
   * ���� ��ȸ
   * @param review_no
   * @return
   */
  public ReviewVO read(int review_no);
  
  /**
   * ������ ��ȸ
   * @param review_no
   * @return
   */
  public ReviewVO read_update(int review_no);
  
  /**
   * ���� ó��
   * @param reviewVO
   * @return
   */
  public int update(ReviewVO reviewVO);
  
  /**
   * �н����� �˻�
   * @param hashMap
   * @return
   */
  public int passwd_check(HashMap<String, Object> hashMap);
  
  /**
   * ����
   * @param review_no
   * @return
   */
  public int delete(int review_no);
  
  
}
