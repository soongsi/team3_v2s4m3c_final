package dev.mvc.bookshelf;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class BookshelfCont {
  @Autowired
  @Qualifier("dev.mvc.bookshelf.BookshelfProc")
  private BookshelfProcInter bookshelfProc;
  
  public BookshelfCont() {
    System.out.println("--> BookshelfCont created.");
  }
  
  
  /**
   * �����
   * http://localhost:9090/team3/bookshelf/create.do
   * @return
   */
  @RequestMapping(value="/bookshelf/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/bookshelf/create"); // /webapp/bookshelf/create.jsp
    
    return mav;  // forward
  }
  
  /**
   * ��� ó��
   * http://localhost:9090/team3/bookshelf/create.do
   * @return
   */
  @RequestMapping(value="/bookshelf/create.do", method=RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, BookshelfVO bookshelfVO) {
    ModelAndView mav = new ModelAndView();
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String file1 = "";     // main image
    String thumb1 = ""; // preview image
        
    String upDir = Tool.getRealPath(request, "/bookshelf/storage/main_images"); // ���� ���
    // ���� ������ ����� fnamesMF ��ü�� ������.
    MultipartFile mf = bookshelfVO.getFile1MF();
    System.out.println("mf: " + bookshelfVO.getFile1MF());
    long size1 = mf.getSize();  // ���� ũ��
    System.out.println("size1: " + size1);
    if (size1 > 0) { // ���� ũ�� üũ
      // mp3 = mf.getOriginalFilename(); // ���� ���ϸ�, spring.jpg
      // ���� ���� �� ���ε�� ���ϸ��� ���ϵ�, spring.jsp, spring_1.jpg...
      file1 = Upload.saveFileSpring(mf, upDir); 
      
      if (Tool.isImage(file1)) { // �̹������� �˻�
        // thumb �̹��� ������ ���ϸ� ���ϵ�, width: 120, height: 80
        thumb1 = Tool.preview(upDir, file1, 200, 150);
      }
      
    }    
    
    bookshelfVO.setFile1(file1);
    bookshelfVO.setThumb1(thumb1);
    bookshelfVO.setSize1(size1);
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    
    int cnt = this.bookshelfProc.create(bookshelfVO); // ��� ó��
    
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
    mav.addObject("bookshelfno", bookshelfVO.getBookshelfno());
    mav.addObject("url", "create_msg"); // // webapp/bookshelf/create_msg.jsp
    
    
    mav.setViewName("/bookshelf/create_msg"); // /webapp/bookshelf/create.jsp
    
    return mav;  // forward
  }
  
  
}
