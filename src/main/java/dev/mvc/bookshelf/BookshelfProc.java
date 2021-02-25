package dev.mvc.bookshelf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.bookshelf.BookshelfProc")
public class BookshelfProc implements BookshelfProcInter{
  @Autowired
  private BookshelfDAOInter BookshelfDAO;
  
  public BookshelfProc() {
    System.out.println("--> BookshelfProc create.");
  }
  
  @Override
  public int create(BookshelfVO bookshelfVO) {
    int cnt = this.BookshelfDAO.create(bookshelfVO);
    return cnt;
  }

  @Override
  public List<BookshelfVO> list_bookshelfno_asc() {
    List<BookshelfVO> list = this.BookshelfDAO.list_bookshelfno_asc();
    return list;
  }

  @Override
  public BookshelfVO read(int bookshelfno) {
    BookshelfVO read = this.BookshelfDAO.read(bookshelfno);
    return read;
  }

  @Override
  public BookshelfVO read_update(int bookshelfno) {
    BookshelfVO bookshelfVO = this.BookshelfDAO.read(bookshelfno);
    return bookshelfVO;
  }

  @Override
  public int update(BookshelfVO bookshelfVO) {
    int cnt = this.BookshelfDAO.update(bookshelfVO);
    return cnt;
  }

  @Override
  public int delete(int bookshelfno) {
    int cnt = this.BookshelfDAO.delete(bookshelfno);
    return cnt;
  }

  @Override
  public int update_img(BookshelfVO bookshelfVO) {
    int cnt = this.BookshelfDAO.update_img(bookshelfVO);
    return cnt;
  }

  @Override
  public int create_img(BookshelfVO bookshelfVO) {
    int cnt = this.BookshelfDAO.update_img(bookshelfVO);
    return cnt;
  }

  @Override
  public int delete_img(BookshelfVO bookshelfVO) {
    int cnt = this.BookshelfDAO.update_img(bookshelfVO);
    return cnt;
  }

}
