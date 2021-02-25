package dev.mvc.bookshelf;

import java.util.List;

public interface BookshelfDAOInter {
  /**
   * 등록
   * @return 처리된 레코드 갯수
   */ 
  public int create (BookshelfVO bookshelfVO);
  
  /**
   * 목록
   * @return 레코드 목록
   */
  public List<BookshelfVO> list_bookshelfno_asc();
  
  /**
   * 조회
   * @param contentsno
   * @return
   */
  public BookshelfVO read(int bookshelfno);

  /**
   * 수정용 조회
   * @param m_no
   * @return
   */
  public BookshelfVO read_update(int bookshelfno);
  
  /**
   * 수정
   * @param memberVO
   * @return 처리된 레코드 갯수
   */
  public int update(BookshelfVO bookshelfVO);
  
  
  public int delete(int bookshelfno);
  
  /**
   * 이미지 변경 
   * @param bookshelfVO
   * @return
   */
  public int update_img(BookshelfVO bookshelfVO);

}
