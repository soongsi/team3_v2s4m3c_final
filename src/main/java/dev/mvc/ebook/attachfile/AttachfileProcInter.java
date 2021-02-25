package dev.mvc.ebook.attachfile;

import java.util.List;

public interface AttachfileProcInter {

  /**
   * 등록
   * 
   * @param attachfileVO
   * @return
   */
  public int create(AttachfileVO attachfileVO);

  /**
   * 전체 이미지 목록
   * 
   * @return
   */
  public List<AttachfileVO> list();

  /**
   * 조회
   * 
   * @param attachfileno
   * @return
   */
  public AttachfileVO read(int attachfileno);

  /**
   * FK eb_no에 따른 파일 목록
   * 
   * @param eb_no
   * @return
   */
  public List<AttachfileVO> list_by_ebno(int eb_no);

  /**
   * 삭제
   * 
   * @param attachfileno
   * @return
   */
  public int delete(int attachfileno);

  /**
   * 부모키별 컨텐츠 개수 산출
   * 
   * @param eb_no
   * @return
   */
  public int count_by_ebno(int eb_no);

  /**
   * FK 부모키를 이용한 모든 레코드 삭제
   * 
   * @param eb_no
   * @return
   */
  public int delete_by_ebno(int eb_no);

}