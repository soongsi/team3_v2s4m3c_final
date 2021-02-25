package dev.mvc.memberph;

import java.util.List;

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
public class MemberphCont {
  @Autowired
  @Qualifier("dev.mvc.memberph.MemberphProc")
  private MemberphProcInter memberphProc; 
  
  public MemberphCont() {
    System.out.println("--> MemberphCont created.");
  }
  
  /**
   * 등록폼
   * http://localhost:9090/team3/memberph/create.do
   * @return
   */
  @RequestMapping(value="/memberph/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/memberph/create"); // /webapp/memberph/create.jsp
    
    return mav;  // forward
  }
  
  /**
   * 등록 처리
   * http://localhost:9090/team3/memberph/create.do
   * @return
   */
  @RequestMapping(value="/memberph/create.do", method=RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, MemberphVO memberphVO) {
    ModelAndView mav = new ModelAndView();
    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String file1 = "";     // main image
    String thumb1 = ""; // preview image
        
    String upDir = Tool.getRealPath(request, "/memberph/storage/main_images"); // 절대 경로
    // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
    MultipartFile mf = memberphVO.getFile1MF();
    System.out.println("mf: " + memberphVO.getFile1MF());
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
    
    memberphVO.setFile1(file1);
    memberphVO.setThumb1(thumb1);
    memberphVO.setSize1(size1);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------
    
    int cnt = this.memberphProc.create(memberphVO); // 등록 처리
    
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
    mav.addObject("orderno", memberphVO.getOrderno());
    mav.addObject("url", "create_msg"); // // webapp/memberph/create_msg.jsp
    
    
    mav.setViewName("/memberph/create_msg"); // /webapp/memberph/create.jsp
    
    return mav;  // forward
  }
  
  /**
   * 목록
   * http://localhost:9090/team3/memberph/list.do
   * @return
   */
  @RequestMapping(value="/memberph/list.do", method=RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/memberph/list"); // /webapp/memberph/create.jsp
    
    List<MemberphVO> list = this.memberphProc.list_orderno_asc();
    mav.addObject("list" ,list);
    
    return mav;  // forward
  }
  
  /**
   * 조회
   * http://localhost:9090/team3/memberph/read.do
   * @return
   */
  @RequestMapping(value="/memberph/read.do", method=RequestMethod.GET)
  public ModelAndView read_update(int orderno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/memberph/read"); // /webapp/memberph/read_update.jsp
    
    MemberphVO memberphVO = this.memberphProc.read(orderno);
    mav.addObject("memberphVO" ,memberphVO);
    
    return mav;  // forward
  }
  
  /**
   * 수정 폼
   * @return
   */
  @RequestMapping(value="/memberph/update.do", method=RequestMethod.GET )
  public ModelAndView update(int orderno) {
    ModelAndView mav = new ModelAndView();
    
    MemberphVO memberphVO = this.memberphProc.read_update(orderno); // 수정용 읽기
    mav.addObject("MemberphVO", memberphVO); // request.setAttribute("memberphVO", memberphVO);
    
    mav.setViewName("/memberph/update"); // webapp/memberph/update.jsp
    
    return mav;
  }
  
  /**
   * 수정 처리
   * @param memberphVO
   * @return
   */
  @RequestMapping(value="/memberph/update.do", method=RequestMethod.POST )
  public ModelAndView update(MemberphVO memberphVO) {
    
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.memberphProc.update(memberphVO);
    mav.addObject("cnt", cnt); // request에 저장
    
    mav.setViewName("/memberph/update_msg"); // webapp/memberph/update_msg.jsp
    
    return mav;
  }
  
  /**
   * 삭제폼
   * http://localhost:9090/resort/memberph/delete.do
   * @return
   */
  @RequestMapping(value="/memberph/delete.do", method=RequestMethod.GET)
  public ModelAndView read_delete(int orderno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/memberph/delete"); // /webapp/memberph/delete.jsp
    
    MemberphVO memberphVO = this.memberphProc.read(orderno);
    mav.addObject("memberphVO" ,memberphVO);
    
    return mav;  // forward
  }
   
  /**
   * 삭제 처리
   * @param memberphno
   * @return
   */
  @RequestMapping(value="/memberph/delete.do", method=RequestMethod.POST )
  public ModelAndView delete(HttpServletRequest request, int orderno) {
    
    ModelAndView mav = new ModelAndView();
    MemberphVO memberphVO = this.memberphProc.read(orderno);
    
    int cnt = this.memberphProc.delete(orderno);
        
    String upDir = Tool.getRealPath(request, "/memberph/storage/main_images"); // 절대 경로
    Tool.deleteFile(upDir, memberphVO.getFile1());  // Folder에서 1건의 파일 삭제
    Tool.deleteFile(upDir, memberphVO.getThumb1());  // Folder에서 1건의 파일 삭제
    
    mav.addObject("cnt", cnt); // request에 저장
    
    mav.setViewName("/memberph/delete_msg"); // webapp/memberph/update_msg.jsp
    
    return mav;
  }
  
  /**
   * 메인 이미지 등록 폼 http://localhost:9090/resort/memberph/img_create.do
   * 
   * @return
   */
  @RequestMapping(value = "/memberph/img_create.do", method = RequestMethod.GET)
  public ModelAndView img_create(int orderno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/memberph/img_create"); // /webapp/memberph/img_create.jsp

    MemberphVO memberphVO = this.memberphProc.read(orderno);
    mav.addObject("memberphVO", memberphVO);
    
    return mav; // forward
  }
  
  /**
   * 메인 이미지 등록 처리 http://localhost:9090/resort/memberph/img_create.do
   * 
   * @return
   */
  @RequestMapping(value = "/memberph/img_create.do", method = RequestMethod.POST)
  public ModelAndView img_create(HttpServletRequest request, MemberphVO memberphVO) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = 0;             // 수정된 레코드 갯수 
    
    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String file1 = "";     // main image
    String thumb1 = ""; // preview image
        
    String upDir = Tool.getRealPath(request, "/memberph/storage/main_images"); // 절대 경로
    // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
    // <input type='file' class="form-control" name='file1MF' id='file1MF' 
    //           value='' placeholder="파일 선택" multiple="multiple">
    MultipartFile mf = memberphVO.getFile1MF();
    long size1 = mf.getSize();  // 파일 크기
    if (size1 > 0) { // 파일 크기 체크
      // mp3 = mf.getOriginalFilename(); // 원본 파일명, spring.jpg
      // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
      file1 = Upload.saveFileSpring(mf, upDir); 
      
      if (Tool.isImage(file1)) { // 이미지인지 검사
        // thumb 이미지 생성후 파일명 리턴됨, width: 120, height: 80
        thumb1 = Tool.preview(upDir, file1, 200, 150);
      }
    }    
      
    memberphVO.setFile1(file1);
    memberphVO.setThumb1(thumb1);
    memberphVO.setSize1(size1);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------
    
    mav.setViewName("redirect:/memberph/read.do?orderno=" + memberphVO.getOrderno());
    
    cnt = this.memberphProc.img_create(memberphVO);
    // mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
    

    mav.setViewName("/memberph/update_msg"); // webapp/memberph/update_msg.jsp
      

    mav.addObject("cnt", cnt); // request에 저장
            
    return mav;    
  }
  
  /**
   * 메인 이미지 등록 폼 http://localhost:9090/resort/memberph/img_update.do
   * 
   * @return
   */
  @RequestMapping(value = "/memberph/img_update.do", method = RequestMethod.GET)
  public ModelAndView img_update(int orderno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/memberph/img_update"); // /webapp/memberph/img_update.jsp

    MemberphVO memberphVO = this.memberphProc.read(orderno);
    mav.addObject("memberphVO", memberphVO);
    
    return mav; // forward
  }
  
  /**
   * 메인 이미지 삭제 처리 http://localhost:9090/resort/memberph/img_delete.do
   * 
   * @return
   */
  @RequestMapping(value = "/memberph/img_delete.do", method = RequestMethod.POST)
  public ModelAndView img_delete(HttpServletRequest request, int orderno) {
    ModelAndView mav = new ModelAndView();

    int cnt = 0;             // 수정된 레코드 갯수 
    
    // -------------------------------------------------------------------
    // 파일 삭제 코드 시작
    // -------------------------------------------------------------------
    // 삭제할 파일 정보를 읽어옴.
    MemberphVO memberphVO = memberphProc.read(orderno);
    // System.out.println("file1: " + memberphVO.getFile1());
    
    String file1 = memberphVO.getFile1().trim();
    String thumb1 = memberphVO.getThumb1();
    long size1 = memberphVO.getSize1();
    
    String upDir = Tool.getRealPath(request, "/memberph/storage/main_images"); // 절대 경로
    Tool.deleteFile(upDir, memberphVO.getFile1());  // Folder에서 1건의 파일 삭제
    Tool.deleteFile(upDir, memberphVO.getThumb1());  // Folder에서 1건의 파일 삭제
    
    file1 = "";
    thumb1 = "";
    size1 = 0;
    
    memberphVO.setFile1(file1);
    memberphVO.setThumb1(thumb1);
    memberphVO.setSize1(size1);
    
    // System.out.println("sw: " + sw);
    // -------------------------------------------------------------------
    // 파일 삭제 종료 시작
    // -------------------------------------------------------------------
    
    mav.setViewName("redirect:/memberph/read.do?orderno=" + orderno);
    
    cnt = this.memberphProc.img_delete(memberphVO);
    // mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
    
    mav.setViewName("/memberph/delete_msg"); // webapp/memberph/delete_msg.jsp
    mav.addObject("cnt", cnt); // request에 저장
            
    return mav;    
  }
  
  /**
   * 메인 이미지 수정 처리 http://localhost:9090/resort/memberph/img_update.do
   * 기존 이미지 삭제후 새로운 이미지 등록(수정 처리)
   * @return
   */
  @RequestMapping(value = "/memberph/img_update.do", method = RequestMethod.POST)
  public ModelAndView img_update(HttpServletRequest request, MemberphVO memberphVO) {
    ModelAndView mav = new ModelAndView();

    int cnt = 0;             // 수정된 레코드 갯수 
    

    // -------------------------------------------------------------------
    // 파일 삭제 코드 시작
    // -------------------------------------------------------------------
    // 삭제할 파일 정보를 읽어옴.
    MemberphVO vo = memberphProc.read(memberphVO.getOrderno());
    // System.out.println("file1: " + memberphVO.getFile1());
    
    String file1 = vo.getFile1().trim();
    String thumb1 = vo.getThumb1();
    long size1 = 0;
    
    String upDir = Tool.getRealPath(request, "/memberph/storage/main_images"); // 절대 경로
    Tool.deleteFile(upDir, memberphVO.getFile1());  // Folder에서 1건의 파일 삭제
    Tool.deleteFile(upDir, memberphVO.getThumb1());  // Folder에서 1건의 파일 삭제
    
    // System.out.println("sw: " + sw);
    // -------------------------------------------------------------------
    // 파일 삭제 종료 시작
    // -------------------------------------------------------------------
    
    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
 // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
    // <input type='file' class="form-control" name='file1MF' id='file1MF' 
    //           value='' placeholder="파일 선택" multiple="multiple">
    MultipartFile mf = memberphVO.getFile1MF();
    size1 = mf.getSize();  // 파일 크기
    if (size1 > 0) { // 파일 크기 체크
      // mp3 = mf.getOriginalFilename(); // 원본 파일명, spring.jpg
      // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
      file1 = Upload.saveFileSpring(mf, upDir); 
      
      if (Tool.isImage(file1)) { // 이미지인지 검사
        // thumb 이미지 생성후 파일명 리턴됨, width: 120, height: 80
        thumb1 = Tool.preview(upDir, file1, 200, 150); 
      }
    }    
    
    memberphVO.setFile1(file1);
    memberphVO.setThumb1(thumb1);
    memberphVO.setSize1(size1);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------

    mav.setViewName("redirect:/memberph/read.do?orderno=" + memberphVO.getOrderno());
    
    cnt = this.memberphProc.img_create(memberphVO);
    // mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
    mav.addObject("orderno", memberphVO.getOrderno());
    mav.addObject("cnt", cnt); // request에 저장
    mav.setViewName("/memberph/update_msg"); // webapp/memberph/update_msg.jsp

    
            
    return mav;    
  }

}
