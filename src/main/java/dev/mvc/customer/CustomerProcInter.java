package dev.mvc.customer;

import java.util.HashMap;
import java.util.List;

public interface CustomerProcInter {
  
  /**
   * 등록
   * @param customerVO
   * @return 등록된 레코드 수
   */
  public int create(CustomerVO customerVO);
  
  /**
   * 전체 문의 목록 
   * @return
   */
  public List<CustomerVO> list_all();
  
  /**
   * 회원번호 별 문의 목록 
   * @return
   */
  public  List<CustomerVO> list_by_mno(int m_no);

  /**
   * Paging + 회원번호 별 문의 목록 
   * @return
   */
  public  List<CustomerVO> list_by_mno_paging(HashMap<String, Object> map);
  
  /**
   * 회원번호별 전체 레코드 갯수
   * @param m_no
   * @return
   */
  public int search_count(int m_no);
  
  /**
   * page 목록 문자열 생성, Box 형태
   * @param listFile 목록 파일명 
   * @param m_no 회원 번호
   * @param search_count 검색(전체) 레코드수 
   * @param nowPage 현재 페이지, nowPage는 1부터 시작
   * @return
   */
  public String pagingBox(String listFile, int m_no, int search_count, int nowPage);
  
  /**
   * 통합 VO 기반 join
   * @param mno
   * @return
   */
  public List<Member_Customer_join> list_join();
  
  /**
   * 레코드 한 건 조회
   * @param cs_no
   * @return
   */
  public CustomerVO read_img(int cs_no);
      
  /**
   * 레코드 한 건 조회
   * @param cs_no
   * @return
   */
  public Member_Customer_join read(int cs_no);
  
  /**
   * 나의 문의내역 조회
   * @param cs_no
   * @return
   */
  public List<Member_Customer_join> list_my_inquiry(int m_no);
  
  /**
   * 수정(회원용)
   * @param member_Customer_join
   * @return
   */
  public int update(Member_Customer_join member_Customer_join);
  
  /**
   * 패스워드 일치 검사(회원용)
   * @param hashMap
   * @return
   */
  public int passwd_check(HashMap<String,Object> hashMap);  
  
  /**
   * 삭제용 조회
   * @param cs_no
   * @return
   */
  public Member_Customer_join read_delete(int cs_no);
  
  /**
   * 레코드 한 건 삭제
   * @param cs_no
   * @return
   */
  public int delete(int cs_no);
  
  /**
   *  이미지 등록
   * @param contentsVO
   * @return
   */
  public int img_create(Member_Customer_join customerVO);
  
  /**
   *  이미지 수정
   * @param contentsVO
   * @return
   */
  public int img_update(Member_Customer_join customerVO);
  
  /**
   *  이미지 삭제
   * @param contentsVO
   * @return
   */
  public int img_delete(Member_Customer_join customerVO);
  

  
  
  
}
