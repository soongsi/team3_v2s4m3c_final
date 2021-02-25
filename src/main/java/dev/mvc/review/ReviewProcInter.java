package dev.mvc.review;

import java.util.HashMap;
import java.util.List;


public interface ReviewProcInter {
  /**
   * 리뷰 등록
   * @param cateVO
   * @return
   */
  public int create(ReviewVO reviewVO);
  
  /**
   * 리뷰 목록 - 등록순 정렬
   * @return
   */
  public List<ReviewVO> list_reviewno_asc();
  
  /**
   * 리뷰 조회
   * @param review_no
   * @return
   */
  public ReviewVO read(int review_no);
  
  /**
   * 수정용 조회
   * @param review_no
   * @return
   */
  public ReviewVO read_update(int review_no);
  
  /**
   * 수정 처리
   * @param reviewVO
   * @return
   */
  public int update(ReviewVO reviewVO);
  
  /**
   * 패스워드 검사
   * @param hashMap
   * @return
   */
  public int passwd_check(HashMap<String, Object> hashMap);
  
  /**
   * 삭제
   * @param review_no
   * @return
   */
  public int delete(int review_no);
  
  
}
