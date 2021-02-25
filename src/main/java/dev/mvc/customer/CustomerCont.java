package dev.mvc.customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.admin.AdminProcInter;
import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import dev.mvc.pay.CodeTable;
import dev.mvc.pay.TypeCode;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class CustomerCont {

  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc")
  private AdminProcInter adminProc;
  
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc") 
  private MemberProcInter memberProc;
  
  @Autowired
  @Qualifier("dev.mvc.customer.CustomerProc") 
  private CustomerProcInter customerProc;

  public CustomerCont() {
    System.out.println("--> CustomerCont created.");
  }
  
  /**
   * 등록폼 http://localhost:9090/team3/customer/create.do
   * @return
   */
  @RequestMapping(value="/customer/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session,
                                @RequestParam(value="m_no", defaultValue="1") int m_no) {
    ModelAndView mav = new ModelAndView();
    
    if (memberProc.isMember(session)== true) {
      System.out.println("get m_no:"+(int)(session.getAttribute("m_no")));
      
      ArrayList<TypeCode> type_list = CodeTable.getTypeCode();
      mav.addObject("m_no", (int)(session.getAttribute("m_no")));   // m_no 전달
      mav.addObject("type_list", type_list);
      mav.setViewName("/customer/create"); // /webapp/customer/create.jsp 
    } else {
      mav.setViewName("redirect:/member/login_need.jsp"); // /webapp/member/login_need.jsp    
    }

    return mav;
  }
  
  /**
   * 등록 처리 http://localhost:9090/team3_testgit/customer/create.do
   * @return
   */
  @RequestMapping(value="/customer/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, CustomerVO customerVO,
                                         HttpSession session, @RequestParam(value="m_no", defaultValue="1") int m_no) {
    
    ModelAndView mav = new ModelAndView();
    
    // -------------------------------------------------------------------
    // 파일 전송 코드 시작
    // -------------------------------------------------------------------
    String file1 = "";         // 메인 이미지
    String thumb1 = "";     // 메인 이미지 미리보기
        
    String upDir = Tool.getRealPath(request, "/adm/customer/storage/main_images"); // 절대 경로
    // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
    MultipartFile mf = customerVO.getFile1MF();                // mf에 File1MF 값 저장
    long size1 = mf.getSize();                                // File1MF 파일 크기 반환
    if (size1 > 0) {                                              // File1MF 파일 사이즈 체크
      // mp3 = mf.getOriginalFilename(); // 원본 파일명, spring.jpg
      // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
      file1 = Upload.saveFileSpring(mf, upDir); 
      
      if (Tool.isImage(file1)) { // 이미지인지 검사
        // thumb 이미지 생성후 파일명 리턴됨, width: 30, height: 50
        thumb1 = Tool.preview(upDir, file1, 50, 30); 
      }
      
    }  
    customerVO.setCs_file1(file1);
    customerVO.setCs_thumb1(thumb1);
    customerVO.setCs_size1(size1);
    // -------------------------------------------------------------------
    // 파일 전송 코드 종료
    // -------------------------------------------------------------------
       
    int cnt = this.customerProc.create(customerVO);   // 글 등록
    mav.addObject("cnt", cnt);  
    mav.addObject("m_no", (int)(session.getAttribute("m_no")));
    mav.addObject("id", session.getAttribute("id"));
    
    System.out.println("m_no:"+(int)(session.getAttribute("m_no")));
    System.out.println("id:"+session.getAttribute("id"));
    System.out.println("cnt:"+cnt);
    
    mav.addObject("url", "create_msg");
    mav.setViewName("redirect:/customer/msg.do");  // redirect

    return mav;
  }
  
  /**
   * 전체 목록 (관리자만 접근 가능)
   * @return
   */
  @RequestMapping(value="/adm/customer/list_all.do", method = RequestMethod.GET)
  public ModelAndView list(CustomerVO customerVO,
                                    @RequestParam(value="nowPage", defaultValue="1") int nowPage,
                                    HttpSession session) { 
    ModelAndView mav = new ModelAndView();
    
    /*
    if (adminProc.isAdmin(session) == true) {
      List<CustomerVO> list = this.customerProc.list_all();
      mav.addObject("list", list);
      mav.addObject("nowPage", nowPage);
      mav.setViewName("/adm/customer/list_all");
    } else {
      mav.setViewName("redirect:/adm/admin/login_need.jsp"); // /webapp/adm/admin/login_need.jsp    
    }
   */
    List<CustomerVO> list = this.customerProc.list_all();
    mav.addObject("list", list);
    mav.addObject("nowPage", nowPage);
    mav.setViewName("/adm/customer/list_all");
    
    return mav;
  }
  
  /**
   * 전체 목록 (관리자만 접근 가능할 예정)
   * @return
   */
  @RequestMapping(value="/customer/list_all.do", method = RequestMethod.GET)
  public ModelAndView list(CustomerVO customerVO,
                                    @RequestParam(value="nowPage", defaultValue="1") int nowPage) { 
    ModelAndView mav = new ModelAndView();
    
    List<CustomerVO> list = this.customerProc.list_all();
    
    mav.addObject("list", list);
    mav.addObject("nowPage", nowPage);
    
    mav.setViewName("/customer/list_all");

    
    return mav;
  }

  /**
   * 페이징 + 목록 지원 (관리자만 접근 가능)
   * @return
   */
  @RequestMapping(value="/adm/customer/list_by_mno_paging.do", method = RequestMethod.GET)
  public ModelAndView list_by_mno_paging(@RequestParam(value="m_no", defaultValue="3") int m_no, 
                                                         @RequestParam(value="cs_no", defaultValue="3") int cs_no, 
                                                         @RequestParam(value="nowPage", defaultValue="1") int nowPage,
                                                         HttpSession session
  ) {
    System.out.println("--> nowPage: " + nowPage);
    
    ModelAndView mav = new ModelAndView();
    
    // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("m_no", m_no);  // #{m_no}
    map.put("nowPage", nowPage);  //  페이지에 출력할 레코드 범위(갯수) 산출  
    
    /*
    if (adminProc.isAdmin(session) == true ) {
      // 페이징 + 메인 이미지 목록
      List<CustomerVO> list = this.customerProc.list_by_mno_paging(map);
      mav.addObject("list", list);
      
      // 검색된 레코드 갯수
      int search_count = this.customerProc.search_count(m_no);
      mav.addObject("search_count", search_count);
      
      String paging = this.customerProc.pagingBox("list_by_mno_paging.do", m_no, search_count, nowPage);
      mav.addObject("paging", paging);
      mav.addObject("nowPage", nowPage);
      
      mav.setViewName("/adm/customer/list_by_mno_paging");  //  /webapp/adm/customer/list_by_mno_paging.jsp
    } else {
      mav.setViewName("redirect:/adm/admin/login_need.jsp"); // /webapp/adm/admin/login_need.jsp    
    }
    */
    // 페이징 + 메인 이미지 목록
    List<CustomerVO> list = this.customerProc.list_by_mno_paging(map);
    mav.addObject("list", list);
    
    // 검색된 레코드 갯수
    int search_count = this.customerProc.search_count(m_no);
    mav.addObject("search_count", search_count);
    
    String paging = this.customerProc.pagingBox("list_by_mno_paging.do", m_no, search_count, nowPage);
    mav.addObject("paging", paging);
    mav.addObject("nowPage", nowPage);
    
    mav.setViewName("/adm/customer/list_by_mno_paging");  //  /webapp/adm/customer/list_by_mno_paging.jsp
    
    return mav;
  }
  
  /**
   *  Member+Customer Join 전체 목록
   * @param customerVO
   * @return
   */
  @RequestMapping(value="/adm/customer/list_join.do", method = RequestMethod.GET)
  public ModelAndView list_join(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    /*
    if (adminProc.isAdmin(session) == true) {
      List<Member_Customer_join> list = this.customerProc.list_join();
      mav.addObject("list", list);
      mav.setViewName("/adm/customer/list_join");
    } else {
      mav.setViewName("redirect:/adm/admin/login_need.jsp"); // /webapp/adm/admin/login_need.jsp    
    }
    */
    
    List<Member_Customer_join> list = this.customerProc.list_join();
    mav.addObject("list", list);
    mav.setViewName("/adm/customer/list_join");
    
    return mav;
  }
  
  /**
   * 회원번호 별 문의 목록 (관리자만 접근 가능)
   * @return
   */
  @RequestMapping(value="/adm/customer/list_by_mno.do", method = RequestMethod.GET)
  public ModelAndView list_by_mno(int m_no, CustomerVO customerVO, HttpSession session) { 
    ModelAndView mav = new ModelAndView();
    
    /*
    if (adminProc.isAdmin(session) == true) {
      List<CustomerVO> list = this.customerProc.list_by_mno(m_no);
      mav.addObject("list", list);
      mav.setViewName("/adm/customer/list_by_mno");
    } else {
      mav.setViewName("redirect:/adm/admin/login_need.jsp"); // /webapp/adm/admin/login_need.jsp    
    }
    */
    List<CustomerVO> list = this.customerProc.list_by_mno(m_no);
    mav.addObject("list", list);
    mav.setViewName("/adm/customer/list_by_mno");
    
    return mav;
  }
  
  
  /**
   * 회원이 쓴 글 조회
   * @return
   */
  @RequestMapping(value="/adm/customer/read.do", method=RequestMethod.GET)
  public ModelAndView read_img(HttpSession session, int cs_no) {
    ModelAndView mav = new ModelAndView();
    CustomerVO customerVO = this.customerProc.read_img(cs_no);
    
    mav.addObject("customerVO", customerVO);  
    mav.addObject("cs_no", customerVO.getCs_no());      // send the PK value to the View
    
    mav.setViewName("/adm/customer/read_img");

    return mav;
  }
  
  /**
   * 나의 문의 조회(회원용) 
   * @return
   */
  @RequestMapping(value="/customer/read.do", method=RequestMethod.GET)
  public ModelAndView read(HttpSession session, 
      @RequestParam(value="cs_no", defaultValue="1") int cs_no,
      @RequestParam(value="m_no", defaultValue="1") int m_no) {
    ModelAndView mav = new ModelAndView();
    
    if (memberProc.isMember(session) == true) {
      Member_Customer_join member_Customer_join = this.customerProc.read(cs_no); 
      
      mav.addObject("member_Customer_join", member_Customer_join);
      
      if(member_Customer_join.getCs_type().equals("A01")) {
        member_Customer_join.setCs_type("상품문의");
      } else if(member_Customer_join.getCs_type().equals("A02")) {
        member_Customer_join.setCs_type("결제장애");
      } else if(member_Customer_join.getCs_type().equals("A03")) {
        member_Customer_join.setCs_type("환불");
      } else if(member_Customer_join.getCs_type().equals("A04")) {
        member_Customer_join.setCs_type("개선사항");
      } else if(member_Customer_join.getCs_type().equals("A99")) {
        member_Customer_join.setCs_type("기타");
      }
      mav.addObject("cs_no", member_Customer_join.getCs_no());      // send the PK value to the View
      mav.addObject("cs_type", member_Customer_join.getCs_type());  //  set fixed cs_type value
      mav.setViewName("/customer/read_img");
    } else {
      mav.setViewName("redirect:/member/login_need.jsp");
    }
    
    return mav;
  }  
  
  /**
   * 레코드 한 건 변경, 첨부파일 변경X
   * @param cs_no
   * @return
   */
  @RequestMapping(value="/adm/customer/update.do", method=RequestMethod.GET )
  public ModelAndView update(int cs_no) {
    ModelAndView mav = new ModelAndView();
    
    Member_Customer_join member_Customer_join = this.customerProc.read(cs_no);
    mav.addObject("member_Customer_join", member_Customer_join);

    if(member_Customer_join.getCs_type().equals("A01")) {
      member_Customer_join.setCs_type("상품문의");
    } else if(member_Customer_join.getCs_type().equals("A02")) {
      member_Customer_join.setCs_type("결제장애");
    } else if(member_Customer_join.getCs_type().equals("A03")) {
      member_Customer_join.setCs_type("환불");
    } else if(member_Customer_join.getCs_type().equals("A04")) {
      member_Customer_join.setCs_type("개선사항");
    } else if(member_Customer_join.getCs_type().equals("A99")) {
      member_Customer_join.setCs_type("기타");
    }
    
    mav.addObject("cs_type", member_Customer_join.getCs_type());  //  set fixed cs_type value
       
    mav.setViewName("/adm/customer/update");

    return mav;
  }
  
  /**
   * 내 모든 문의 list
   * @return
   */
  @RequestMapping(value="/customer/list_my_inquiry.do", method=RequestMethod.GET)
  public ModelAndView list_my_inquiry(@RequestParam(value="m_no", defaultValue="1") int m_no,
                                                   @RequestParam(value="cs_no", defaultValue="1")  int cs_no, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    if (memberProc.isMember(session) == true) { 
      List<Member_Customer_join> list = this.customerProc.list_my_inquiry(m_no);
      
      Member_Customer_join member_Customer_join = this.customerProc.read(cs_no);
      
      if(member_Customer_join.getCs_type().equals("A01")) {
        member_Customer_join.setCs_type("상품문의");
      } else if(member_Customer_join.getCs_type().equals("A02")) {
        member_Customer_join.setCs_type("결제장애");
      } else if(member_Customer_join.getCs_type().equals("A03")) {
        member_Customer_join.setCs_type("환불");
      } else if(member_Customer_join.getCs_type().equals("A04")) {
        member_Customer_join.setCs_type("개선사항");
      } else if(member_Customer_join.getCs_type().equals("A99")) {
        member_Customer_join.setCs_type("기타");
      }
      
      mav.addObject("cs_type", member_Customer_join.getCs_type());  //  set fixed cs_type value
      mav.addObject("list", list);
      mav.setViewName("/customer/list_my_inquiry");
    } else {
      mav.setViewName("redirect:/member/login_need.jsp");
    }

    return mav;
  }  
  
  /**
   * 수정 폼, 첨부파일 변경X 
   * @param cs_no
   * @return
   */
  @RequestMapping(value="/customer/update.do", method=RequestMethod.GET )
  public ModelAndView update(@RequestParam(value="cs_no", defaultValue="1") int cs_no, 
                                          Member_Customer_join member_Customer_join) {
    ModelAndView mav = new ModelAndView();
    
    member_Customer_join = this.customerProc.read(cs_no);  // 수정 레코드 읽기
    mav.addObject("member_Customer_join", member_Customer_join);

    if(member_Customer_join.getCs_type().equals("A01")) {
      member_Customer_join.setCs_type("상품문의");
    } else if(member_Customer_join.getCs_type().equals("A02")) {
      member_Customer_join.setCs_type("결제장애");
    } else if(member_Customer_join.getCs_type().equals("A03")) {
      member_Customer_join.setCs_type("환불");
    } else if(member_Customer_join.getCs_type().equals("A04")) {
      member_Customer_join.setCs_type("개선사항");
    } else if(member_Customer_join.getCs_type().equals("A99")) {
      member_Customer_join.setCs_type("기타");
    }
    
    mav.addObject("cs_type", member_Customer_join.getCs_type());  //  set fixed cs_type value
       
    mav.setViewName("/customer/update");

    return mav;
  }
  
  /**
   * 수정 처리, 첨부파일 X 
   * @param member_Customer_join
   * @return
   */
  @RequestMapping(value="/customer/update.do", method=RequestMethod.POST )
  public ModelAndView update(Member_Customer_join member_Customer_join) {
    ModelAndView mav = new ModelAndView();
    
    int passwd_cnt = 0;    // 패스워드 일치 체크
    int cnt = 0;  // 수정처리된 레코드 수
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("cs_no", member_Customer_join.getCs_no()); // cs_no 값 전달
    hashMap.put("m_no", member_Customer_join.getM_no()); // m_no 값 전달
    hashMap.put("r_mpasswd", member_Customer_join.getR_mpasswd()); // r_mpasswd 값 전달
    
    passwd_cnt = this.customerProc.passwd_check(hashMap);
    
    // MemberVO memberVO = this.memberProc.read(member_Customer_join.getM_no());
    
    if(passwd_cnt == 1) {  // 패스워드 일치
      cnt = this.customerProc.update(member_Customer_join);
      mav.setViewName("redirect:/customer/read.do?cs_no="+member_Customer_join.getCs_no());
    } else {
      mav.addObject("cnt", cnt);
      mav.addObject("url","update_msg");
      // mav.addObject("m_no", member_Customer_join.getM_no());
      // mav.addObject("m_id", memberVO.getM_id());
      mav.setViewName("redirect:/customer/msg.do");
    }

    return mav;
  }
  
  /**
   * 특정 회원의 모든 문의 목록
   * @return
   */
  @RequestMapping(value="/adm/customer/list_mno_inquiry.do", method=RequestMethod.GET)
  public ModelAndView list_mno_inquiry(@RequestParam(value="m_no", defaultValue="1") int m_no,
                                                     @RequestParam(value="adm_id", defaultValue=" ") String adm_id,
                                                     HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    if (adminProc.isAdmin(session) == true) { 
      List<Member_Customer_join> list = this.customerProc.list_my_inquiry(m_no);

      mav.addObject("list", list);
      mav.setViewName("/adm/customer/list_mno_inquiry");
    } else {
      mav.setViewName("redirect:/adm/admin/login_need.jsp");
    }
    
    return mav;
  }  
  
  /**
   * 레코드 한 건 변경, 첨부파일 변경X
   * @param cs_no
   * @return
   */
  /*
   * @RequestMapping(value="/adm/customer/update.do", method=RequestMethod.GET )
   * public ModelAndView update(int cs_no) { ModelAndView mav = new
   * ModelAndView();
   * 
   * Member_Customer_join customerVO = this.customerProc.read(cs_no);
   * mav.addObject("customerVO", customerVO);
   * 
   * if(customerVO.getCs_type().equals("A01")) { customerVO.setCs_type("상품문의"); }
   * else if(customerVO.getCs_type().equals("A02")) {
   * customerVO.setCs_type("결제장애"); } else
   * if(customerVO.getCs_type().equals("A03")) { customerVO.setCs_type("환불"); }
   * else if(customerVO.getCs_type().equals("A04")) {
   * customerVO.setCs_type("개선사항"); } else
   * if(customerVO.getCs_type().equals("A99")) { customerVO.setCs_type("기타"); }
   * 
   * mav.addObject("cs_type", customerVO.getCs_type()); // set fixed cs_type value
   * 
   * mav.setViewName("/adm/customer/update");
   * 
   * return mav; }
   */
  
  /**
   * 레코드 한 건 수정 처리, 첨부파일 변경X
   * @param customerVO
   * @return
   */
  /*
   * @RequestMapping(value="/adm/customer/update.do", method=RequestMethod.POST )
   * public ModelAndView update(Member_Customer_join customerVO) { ModelAndView
   * mav = new ModelAndView();
   * 
   * int cnt = this.customerProc.update(customerVO); // 레코드 한 건 수정
   * 
   * mav.addObject("cs_no", customerVO.getCs_no()); mav.addObject("m_no",
   * customerVO.getM_no()); // m_no 값 전달
   * 
   * if(cnt == 1) { // 성공일 경우
   * mav.setViewName("redirect:/adm/customer/list_all.do"); // 목록으로 --> 후에 해당 회원
   * 문의 목록으로 가게 바꿀 것임. } else { // 실패일 경우 mav.addObject("url","update_msg");
   * mav.setViewName("redirect:/adm/customer/msg_adm.do"); }
   * 
   * return mav; }
   */

  
  /**
   * 삭제 폼
   * @param cs_no
   * @return
   */
  @RequestMapping(value = "/customer/delete.do", method= RequestMethod.GET)
  public ModelAndView delete(HttpSession session,int cs_no) {
    ModelAndView mav = new ModelAndView();
    
    int m_no = (int)session.getAttribute("m_no");
    
    if(m_no == this.customerProc.read(cs_no).getM_no()) {
      Member_Customer_join member_Customer_join = this.customerProc.read(cs_no);  // 삭제 레코드 읽기
      mav.addObject("member_Customer_join", member_Customer_join);
      
      mav.setViewName("/customer/delete");
    } else {
      mav.setViewName("redirect:/member/login_need.jsp");
    }
    
    
    return mav;
  }
  
  /**
   * 삭제 처리 + 파일 삭제
   * @param cs_no
   * @param m_no
   * @param passwd
   * @return
   */
  @RequestMapping(value = "/customer/delete.do", method= RequestMethod.POST)
  public ModelAndView delete(HttpServletRequest request,
                                        HttpSession session,
                                        int cs_no,
                                        int m_no,
                                        String r_mpasswd) {
    ModelAndView mav = new ModelAndView();
    
    Member_Customer_join member_Customer_join = this.customerProc.read(cs_no);  // 삭제 레코드 읽기

    String title = member_Customer_join.getCs_title();
    mav.addObject("title", title);
    
    // 현재 패스워드 검사
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("cs_no", cs_no); // cs_no 값 전달
    hashMap.put("r_mpasswd", r_mpasswd); // r_mpasswd 값 전달
    
    int passwd_cnt = 0;  // 패스워드 일치 레코드 수
    int cnt = 0;             // 삭제된 레코드 수 
    
    passwd_cnt = this.customerProc.passwd_check(hashMap);  // 패스워드 검사 처리
    boolean sw = false;    
    
    if( passwd_cnt == 1 ) {  // 패스워드 일치 할 경우
      cnt = this.customerProc.delete(cs_no);   //  삭제 처리

      String upDir = Tool.getRealPath(request, "/adm/customer/storage/main_images");  // 절대경로
      sw = Tool.deleteFile(upDir, member_Customer_join.getCs_file1());  // Folder에서 1건의 파일 삭제
      sw = Tool.deleteFile(upDir, member_Customer_join.getCs_thumb1());  // Folder에서 1건의 썸네일파일 삭제  
     
    } 
    
    mav.addObject("cnt", cnt);                       // add request 
    mav.addObject("passwd_cnt", passwd_cnt); // add a request 
    
    mav.addObject("m_no", (int)(session.getAttribute("m_no")));   // m_no 전달
    // mav.addObject("m_id", member_Customer_join.getR_mid());
    mav.addObject("url", "delete_msg");
    
    mav.setViewName("redirect:/customer/msg.do");

    return mav;
  }
  
  
  /**
   * 삭제 폼(관리자용)
   * @param cs_no
   * @return
   */
  @RequestMapping(value = "/adm/customer/delete.do", method= RequestMethod.GET)
  public ModelAndView read_delete(int cs_no) {
    ModelAndView mav = new ModelAndView();
    
    Member_Customer_join customerVO = this.customerProc.read_delete(cs_no);  // 삭제용 조회 process
    mav.addObject("customerVO", customerVO);
    mav.setViewName("/adm/customer/delete");
    
    return mav;
  }
  
  /**
   * 삭제 처리 (관리자용)
   * @param cs_no
   * @param m_no
   * @param passwd
   * @return
   */
  @RequestMapping(value = "/adm/customer/delete.do", method= RequestMethod.POST)
  public ModelAndView read_delete(HttpServletRequest request,
                                        int cs_no,
                                        int m_no,
                                        String cs_passwd) {
    ModelAndView mav = new ModelAndView();
    
    Member_Customer_join customerVO = this.customerProc.read(cs_no);  // 해당 레코드 조회
    String title = customerVO.getCs_title(); 
    mav.addObject("title", title);
    m_no = customerVO.getM_no();
    mav.addObject("m_no", m_no);
    
    // 현재 패스워드 검사
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("cs_no", cs_no);
    hashMap.put("cs_passwd", cs_passwd);
    
    int passwd_cnt = this.customerProc.passwd_check(hashMap);  // 패스워드 일치 확인 실행
    int cnt =0;             // 처리된 레코드 갯수
    
    boolean sw = false;    
    if(passwd_cnt==1) {  // 패스워드 일치 할 경우 삭제 처리
      cnt = this.customerProc.delete(cs_no);   //  레코드 한 건 삭제 process
      String upDir = Tool.getRealPath(request, "/adm/customer/storage/main_images");  // 절대경로
      sw = Tool.deleteFile(upDir, customerVO.getCs_file1());  // Folder에서 1건의 파일 삭제
      sw = Tool.deleteFile(upDir, customerVO.getCs_thumb1());  // Folder에서 1건의 썸네일파일 삭제  
    }
    
    if(cnt == 1) {
      mav.addObject("cnt", cnt);
      mav.addObject("passwd_cnt", passwd_cnt);
      mav.setViewName("redirect:/adm/customer/list_all.do");
    } else {
      mav.addObject("url", "delete_msg");
      mav.setViewName("redirect:/adm/customer/msg.do");
    }
 
    return mav;
  }
  
  /**
   * 새로고침을 방지하는 메시지 출력
   * @return
   */
  @RequestMapping(value="/adm/customer/msg.do", method=RequestMethod.GET)
  public ModelAndView msg_adm(String url){
    ModelAndView mav = new ModelAndView();
    
    // 등록 처리 메시지: create_msg --> /폴더명/create_msg.jsp
    // 수정 처리 메시지: update_msg --> /폴더명/update_msg.jsp
    // 삭제 처리 메시지: delete_msg --> /폴더명/delete_msg.jsp
    mav.setViewName("/adm/customer/" + url); // forward
    
    return mav; // forward
  }
  
  
  /**
   * 새로고침을 방지하는 메시지 출력
   * @return
   */
  @RequestMapping(value="/customer/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();
    
    // 등록 처리 메시지: create_msg --> /폴더명/create_msg.jsp
    // 수정 처리 메시지: update_msg --> /폴더명/update_msg.jsp
    // 삭제 처리 메시지: delete_msg --> /폴더명/delete_msg.jsp
    mav.setViewName("/customer/" + url); // forward
    
    return mav; // forward
  }
  
  
}
