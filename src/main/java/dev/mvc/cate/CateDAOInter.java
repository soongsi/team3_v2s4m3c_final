package dev.mvc.cate;

import java.util.List;

//MyBATIS의 <mapper namespace="dev.mvc.categrp.CategrpDAOInter">에 선언 
public interface CateDAOInter {
  // 매퍼에 들어가는 메소드명은 MyBATIS의 XML 파일안의 id와 동일해야합니다.
  
  /**
   * 카테고리 등록
   * @param cateVO
   * @return 등록된 레코드 갯수
   */
  public int create(CateVO cateVO);
  
  
  /**
   * 카테고리 목록
   * @param
   * @return 레코드 목록
   */
  public List<CateVO> list_cateno_asc();
  
  /**
   * 출력 번호 순서로 오름차순
   * @param
   * @return 레코드 목록
   */
  public List<CateVO> list_seqno_asc();
  
  /**
   * 카테고리 그룹에 포함된 카테고리 목록
   * @param cg_no
   * @return 레코드 목록
   */
  public List<CateVO> list_cgno_cate(int cg_no);
  
  /**
   * 통합 VO 기반 join
   * @return
   */
  public List<Categrp_Cate_join> list_join();
  
  /**
   * 통합 VO 기반 join
   * @return
   */
  public List<Categrp_Cate_join> list_join_by_cgno(int cg_no); 
  
  /**
   * 조회
   * @param cateno
   * @return 레코드 조회
   */
  public CateVO read(int cateno);
  
  /**
   * 수정
   * @param cateVO
   * @return 수정된 레코드 개수
   */
  public int update(CateVO cateVO);
  
  /**
   * 삭제
   * @param cateno
   * @return 삭제된 레코드 개수
   */
  public int delete(int cateno);
  
  /**
   * 출력 순서 상향
   * @param cateno
   * @return 수정된 레코드 개수
   */
  public int update_seqno_up(int cateno);
  
  /**
   * 출력 순서 하향
   * @param cateno
   * @return 수정된 레코드 개수
   */
  public int update_seqno_down(int cateno);
  
  /**
   * 출력 모드 수정
   * @param cateVO
   * @return 수정된 레코드 개수
   */
  public int update_visible(CateVO cateVO);
  
  
  
}
