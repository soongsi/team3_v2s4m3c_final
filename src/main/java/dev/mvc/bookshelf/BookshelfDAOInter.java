package dev.mvc.bookshelf;

import java.util.List;

public interface BookshelfDAOInter {
  /**
   * ���
   * @return ó���� ���ڵ� ����
   */ 
  public int create (BookshelfVO bookshelfVO);
  
  /**
   * ���
   * @return ���ڵ� ���
   */
  public List<BookshelfVO> list_bookshelfno_asc();
  
  /**
   * ��ȸ
   * @param contentsno
   * @return
   */
  public BookshelfVO read(int bookshelfno);

  /**
   * ������ ��ȸ
   * @param m_no
   * @return
   */
  public BookshelfVO read_update(int bookshelfno);
  
  /**
   * ����
   * @param memberVO
   * @return ó���� ���ڵ� ����
   */
  public int update(BookshelfVO bookshelfVO);
  
  
  public int delete(int bookshelfno);
  
  /**
   * �̹��� ���� 
   * @param bookshelfVO
   * @return
   */
  public int update_img(BookshelfVO bookshelfVO);

}
