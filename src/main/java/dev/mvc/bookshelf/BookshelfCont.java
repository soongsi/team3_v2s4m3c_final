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
   * 등록폼
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
   * 등록 처리
   * http://localhost:9090/team3/bookshelf/create.do
   * @return
   */
  @RequestMapping(value="/bookshelf/create.do", method=RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, BookshelfVO bookshelfVO) {
    ModelAndView mav = new ModelAndView();
    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String file1 = "";     // main image
    String thumb1 = ""; // preview image
        
    String upDir = Tool.getRealPath(request, "/bookshelf/storage/main_images"); // 절대 경로
    // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
    MultipartFile mf = bookshelfVO.getFile1MF();
    System.out.println("mf: " + bookshelfVO.getFile1MF());
    long size1 = mf.getSize();  // 파일 크기
    System.out.println("size1: " + size1);
    if (size1 > 0) { // 파일 크기 체크
      // mp3 = mf.getOriginalFilename(); // 원본 파일명, spring.jpg
      // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
      file1 = Upload.saveFileSpring(mf, upDir); 
      
      if (Tool.isImage(file1)) { // 이미지인지 검사
        // thumb 이미지 생성후 파일명 리턴됨, width: 120, height: 80
        thumb1 = Tool.preview(upDir, file1, 200, 150);
      }
      
    }    
    
    bookshelfVO.setFile1(file1);
    bookshelfVO.setThumb1(thumb1);
    bookshelfVO.setSize1(size1);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------
    
    int cnt = this.bookshelfProc.create(bookshelfVO); // 등록 처리
    
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
    mav.addObject("bookshelfno", bookshelfVO.getBookshelfno());
    mav.addObject("url", "create_msg"); // // webapp/bookshelf/create_msg.jsp
    
    
    mav.setViewName("/bookshelf/create_msg"); // /webapp/bookshelf/create.jsp
    
    return mav;  // forward
  }
  
  
}
