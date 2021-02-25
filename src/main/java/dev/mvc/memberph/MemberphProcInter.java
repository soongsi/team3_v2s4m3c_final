package dev.mvc.memberph;

import java.util.List;


public interface MemberphProcInter {
  
  /**
   * 등록
   * @return 처리된 레코드 갯수
   */ 
  public int create (MemberphVO memberphVO);
  
  /**
   * 목록
   * @return 레코드 목록
   */
  public List<MemberphVO> list_orderno_asc();
  
  /**
   * 조회
   * @param contentsno
   * @return
   */
  public MemberphVO read(int orderno);

  /**
   * 수정용 조회
   * @param m_no
   * @return
   */
  public MemberphVO read_update(int orderno);
  
  /**
   * 수정
   * @param memberVO
   * @return 처리된 레코드 갯수
   */
  public int update(MemberphVO memberphVO);
  
  
  public int delete(int orderno);
  
  /**
   * 이미지 등록
   * @param memberphVO
   * @return
   */
  public int img_create(MemberphVO memberphVO);
  
  /**
   * 이미지 수정
   * @param memberphVO
   * @return
   */
  public int img_update(MemberphVO memberphVO);
  
  /**
   * 이미지 삭제
   * @param memberphVO
   * @return
   */
  public int img_delete(MemberphVO memberphVO);
}
