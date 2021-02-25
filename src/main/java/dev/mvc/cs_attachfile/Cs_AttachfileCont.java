package dev.mvc.cs_attachfile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.cart.CartVO;
import dev.mvc.customer.CustomerProcInter;
import dev.mvc.customer.CustomerVO;
import dev.mvc.customer.Member_Customer_join;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class Cs_AttachfileCont {
  
  // 부모 프로세스 임포트
  @Autowired
  @Qualifier("dev.mvc.customer.CustomerProc")
  private CustomerProcInter customerProc;
  
  @Autowired
  @Qualifier("dev.mvc.cs_attachfile.Cs_AttachfileProc")
  private Cs_AttachfileProcInter cs_attachfileProc;
  
  public Cs_AttachfileCont() {
    System.out.println("--> Cs_AttachfileCont created.");
  }
  
  
  /**
   * http://localhost:9090/team3_testgit/cs_attachfile/create.do?cs_no=4
   * 파일 등록 폼
   * @param cs_no
   * @return
   */
  @RequestMapping(value = "/cs_attachfile/create.do", method = RequestMethod.GET)
  public ModelAndView create(int cs_no) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cs_attachfile/create"); // webapp/cs_attachfile/create.jsp
    return mav;
  }
  
  
  /**
   * http://localhost:9090/team3_testgit/cs_attachfile/create.do?cs_no=4
   * 파일 등록 처리
   * @param cs_no
   * @return
   */
  @RequestMapping(value = "/cs_attachfile/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, 
                                        Cs_AttachfileVO cs_attachfileVO, 
                                        int cs_no) {
    ModelAndView mav = new ModelAndView();
    
    // ---------------------------------------------------------------
    // 파일 전송 코드 시작
    // ---------------------------------------------------------------
    cs_no = cs_attachfileVO.getCs_no();        //  부모글 번호
    String fname = "";                             //   원본 파일명
    String fupname = "";                          //  업로드된 파일명
    long fsize = 0;                                  //  파일 사이즈
    String thumb = "";                             // Preview 이미지
    int upload_count = 0;                         // 정상처리된 레코드 갯수

    String upDir = Tool.getRealPath(request, "/cs_attachfile/storage");

    // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
    List<MultipartFile> fnamesMF = cs_attachfileVO.getFnamesMF();

    int count = fnamesMF.size(); // 전송 파일 갯수
    if (count > 0) {
      for (MultipartFile multipartFile : fnamesMF) { // 파일 추출, 1개이상 파일 처리
        fsize = multipartFile.getSize(); // 파일 크기
        if (fsize > 0) { // 파일 크기 체크
          fname = multipartFile.getOriginalFilename(); // 원본 파일명
          fupname = Upload.saveFileSpring(multipartFile, upDir); // 파일 저장, 업로드된 파일명

          if (Tool.isImage(fname)) { // 이미지인지 검사
            thumb = Tool.preview(upDir, fupname, 200, 150); // thumb 이미지 생성
          }
        }
        Cs_AttachfileVO vo = new Cs_AttachfileVO();
        vo.setCs_no(cs_no);
        vo.setFname(fname);
        vo.setFupname(fupname);
        vo.setThumb(thumb);
        vo.setFsize(fsize);

        // 파일 1건 등록 정보 dbms 저장, 파일이 20개이면 20개의 record insert.
        upload_count = upload_count + this.cs_attachfileProc.create(vo);
      }
    }
    // -----------------------------------------------------
    // 파일 전송 코드 종료
    // -----------------------------------------------------
    mav.addObject("cs_no", cs_no);   // redirect: parameter 적용
    mav.addObject("upload_count", upload_count);   // redirect: parameter 적용
    mav.addObject("url", "create_msg");   // redirect: parameter 적용
    
    
    mav.setViewName("redirect:/cs_attachfile/msg.do"); // webapp/cs_attachfile/create.jsp
    return mav;
  }
  
  
  /**
   * http://localhost:9090/team3_testgit/cs_attachfile/list.do
   * 파일 등록 폼
   * @param cs_no
   * @return
   */
  @RequestMapping(value = "/cs_attachfile/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();

    List<Cs_AttachfileVO> list = this.cs_attachfileProc.list();
    mav.addObject("list", list);

    mav.setViewName("/cs_attachfile/list");

    return mav;
  }
  
  @RequestMapping(value = "/cs_attachfile/delete.do", method = RequestMethod.POST)
  public ModelAndView delete_proc(HttpServletRequest request, int attach_no,
      @RequestParam(value = "cs_no", defaultValue = "0") int cs_no, String rurl) {
    ModelAndView mav = new ModelAndView();
    
    Cs_AttachfileVO cs_attachfileVO = this.cs_attachfileProc.read(attach_no);
    
    String upDir = Tool.getRealPath(request, "/cs_attachfile/storage");  //  절대 경로
    Tool.deleteFile(upDir, cs_attachfileVO.getFupname()); //  Folder에서 1건의 파일 삭제
    Tool.deleteFile(upDir, cs_attachfileVO.getThumb());   //  1건의 Thumb 파일 삭제

    this.cs_attachfileProc.delete(attach_no); // 삭제
    
    List<Cs_AttachfileVO> list = this.cs_attachfileProc.list();  // 전체 목록 새로고침
    mav.addObject("list", list);
    mav.addObject("cs_no", cs_no);
    
    mav.setViewName("redirect:/cs_attachfile/"+rurl);  // list.jsp에서 온 rurl 주소로 redirect 
    
    return mav;
  }
   
  /**
   * Ajax 기반 삭제 처리 
   * @param cartVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/cs_attachfile/delete_ajax.do", method = RequestMethod.POST)
  public int delete_ajax(HttpServletRequest request, int[] attachno_list)  {
  
    int result = 0;        
    String upDir = Tool.getRealPath(request, "/cs_attachfile/storage");  
  
    for(int attach_no:attachno_list) {
      // System.out.println("--> attach_no: " + attach_no);
      Cs_AttachfileVO cs_attachfileVO= this.cs_attachfileProc.read(attach_no); 
  
      Tool.deleteFile(upDir, cs_attachfileVO.getFupname()); 
      Tool.deleteFile(upDir, cs_attachfileVO.getThumb());  
  
      result = this.cs_attachfileProc.delete(attach_no);
    
    }
    
    return result;
  }  
    
  
  @RequestMapping(value = "/cs_attachfile/msg.do", method = RequestMethod.GET) 
  public ModelAndView msg(String url) {
    ModelAndView mav = new ModelAndView();

    // 등록 처리 메시지: create_msg --> /cs_attachfile/create_msg.jsp
    // 수정 처리 메시지: update_msg --> /cs_attachfile/update_msg.jsp
    // 삭제 처리 메시지: delete_msg --> /cs_attachfile/delete_msg.jsp
    mav.setViewName("/cs_attachfile/" + url); // forward

    return mav; // forward
  }
  
  
  
}
