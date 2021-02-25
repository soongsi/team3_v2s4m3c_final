package dev.mvc.ebook;

import java.util.HashMap;
import java.util.List;


public interface EbookDAOInter {
  
  /**
   * 등록
   * @param ebookVO
   * @return 등록된 레코드 수
   */
  public int create(EbookVO ebookVO);
  
  /**
   * 이북 전체 목록
   * @return
   */
  public List<EbookVO> list_ebno_desc();
  
  /**
   * 이북 전체 목록 + 페이징
   * @return
   */
  public List<EbookVO> list_ebno_desc_paging(HashMap<String, Object> map);
  
  /**
   * 카테고리에 등록된 이북 목록
   * @param cateno
   * @return
   */
  public List<EbookVO> list_ebook_category(int cateno);
  
  /**
   * 최근 등록된 이북 10개
   * @return
   */
  public List<EbookVO> list_ebno_desc_latest();
  
  /**
   * 검색 + 페이징 목록
   * @param map
   * @return
   */
  public List<EbookVO> list_by_cateno_search_paging(HashMap<String, Object> map);
  
  /**
   * 검색 레코드 갯수
   * @param hashMap
   * @return
   */
  public int search_count(HashMap<String, Object> hashMap);
  
  /**
   * 조회
   * @param eb_no
   * @return
   */
  public EbookVO read(int eb_no);
  
  /**
   * 수정
   * @param ebookVO
   * @return
   */
  public int update(EbookVO ebookVO);
  
  /**
   * 삭제
   * @param eb_no
   * @return
   */
  public int delete(int eb_no);
}
