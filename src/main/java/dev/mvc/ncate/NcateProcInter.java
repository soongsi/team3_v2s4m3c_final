package dev.mvc.ncate;

import java.util.List;

public interface NcateProcInter {
  /**
   * 등록
   * @param cateVO
   * @return 등록된 갯수
   */
  public int create(NcateVO ncateVO);
  
  /**
   * 목록
   * @return
   */
  public List<NcateVO> list_ncateno_asc();
  
  /**
   * 목록 - 출력순서 정렬
   * @return
   */
  public List<NcateVO> list_nseqno_asc();
  
  /**
   * 조회, 수정폼
   * @param cateno 카테고리 번호, PK
   * @return
   */
  public NcateVO read(int ncate_no);
  
  /**
   * 수정 처리
   * @param ncateVO
   * @return
   */
  public int update(NcateVO ncateVO);
  
  /**
   * 삭제 처리 
   * @param ncate_no
   * @return
   */
  public int delete(int ncate_no);
 
  /**
   * 출력순서상향 
   * @param ncate_no
   * @return
   */
  public int update_nseqno_up(int ncate_no);

 
  /**
   * 출력순서하향
   * @param cateno
   * @return
   */
  public int update_nseqno_down(int ncate_no); 
  
  /**
   * 출력모드
   * @param ncateVO
   * @return
   */
  public int update_nvisible(NcateVO ncateVO);
  
  /**
   * 글 수 증가
   * @return
   */
  public int increaseCnt(int ncate_no);    

  /**
   * 글 수 감소
   * @return
   */
  public int decreaseCnt(int ncate_no);

  
}


