package dev.mvc.categrp;

import java.util.List;

public interface CategrpDAOInter {
  
  /**
   * 카테고리 그룹 등록
   * @param categrpVO
   * @return 처리된 레코드 갯수
   */
  public int create(CategrpVO categrpVO);
  
  /**
   * 카테고리 그룹 목록
   * @return 번호 오름차순 레코드 목록
   */
  public List<CategrpVO> list_cgno_asc();
  
  /**
   * 카테고리 그룹 목록
   * @return 순서 오름차순 레코드 목록
   */
  public List<CategrpVO> list_cgseqno_asc();
  
  /**
   * 카테고리 그룹 조회
   * @param cg_no
   * @return
   */
  public CategrpVO read(int cg_no);
  
  /**
   * 수정
   * @param categrpVO
   * @return 
   */
  public int update(CategrpVO categrpVO);
  
  /**
   * 삭제 처리
   * @param cg_no
   * @return 처리된 레코드 갯수
   */
  public int delete(int cg_no);
  
  /**
   * 출력 순서 상향
   * @param cg_no
   * @return 처리된 레코드 갯수
   */
  public int update_seqno_up(int cg_no);
  
  /**
   * 출력 순서 하향
   * @param cg_no
   * @return 처리된 레코드 갯수
   */
  public int update_seqno_down(int cg_no);
  
  /**
   * 출력 모드 변경
   * @param categrpVO
   * @return 처리된 레코드 갯수
   */
  public int update_visible(CategrpVO categrpVO);
}
