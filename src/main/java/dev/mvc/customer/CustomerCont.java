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
   * ����� http://localhost:9090/team3/customer/create.do
   * @return
   */
  @RequestMapping(value="/customer/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpSession session,
                                @RequestParam(value="m_no", defaultValue="1") int m_no) {
    ModelAndView mav = new ModelAndView();
    
    if (memberProc.isMember(session)== true) {
      System.out.println("get m_no:"+(int)(session.getAttribute("m_no")));
      
      ArrayList<TypeCode> type_list = CodeTable.getTypeCode();
      mav.addObject("m_no", (int)(session.getAttribute("m_no")));   // m_no ����
      mav.addObject("type_list", type_list);
      mav.setViewName("/customer/create"); // /webapp/customer/create.jsp 
    } else {
      mav.setViewName("redirect:/member/login_need.jsp"); // /webapp/member/login_need.jsp    
    }

    return mav;
  }
  
  /**
   * ��� ó�� http://localhost:9090/team3_testgit/customer/create.do
   * @return
   */
  @RequestMapping(value="/customer/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, CustomerVO customerVO,
                                         HttpSession session, @RequestParam(value="m_no", defaultValue="1") int m_no) {
    
    ModelAndView mav = new ModelAndView();
    
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String file1 = "";         // ���� �̹���
    String thumb1 = "";     // ���� �̹��� �̸�����
        
    String upDir = Tool.getRealPath(request, "/adm/customer/storage/main_images"); // ���� ���
    // ���� ������ ����� fnamesMF ��ü�� ������.
    MultipartFile mf = customerVO.getFile1MF();                // mf�� File1MF �� ����
    long size1 = mf.getSize();                                // File1MF ���� ũ�� ��ȯ
    if (size1 > 0) {                                              // File1MF ���� ������ üũ
      // mp3 = mf.getOriginalFilename(); // ���� ���ϸ�, spring.jpg
      // ���� ���� �� ���ε�� ���ϸ��� ���ϵ�, spring.jsp, spring_1.jpg...
      file1 = Upload.saveFileSpring(mf, upDir); 
      
      if (Tool.isImage(file1)) { // �̹������� �˻�
        // thumb �̹��� ������ ���ϸ� ���ϵ�, width: 30, height: 50
        thumb1 = Tool.preview(upDir, file1, 50, 30); 
      }
      
    }  
    customerVO.setCs_file1(file1);
    customerVO.setCs_thumb1(thumb1);
    customerVO.setCs_size1(size1);
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
       
    int cnt = this.customerProc.create(customerVO);   // �� ���
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
   * ��ü ��� (�����ڸ� ���� ����)
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
   * ��ü ��� (�����ڸ� ���� ������ ����)
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
   * ����¡ + ��� ���� (�����ڸ� ���� ����)
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
    
    // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("m_no", m_no);  // #{m_no}
    map.put("nowPage", nowPage);  //  �������� ����� ���ڵ� ����(����) ����  
    
    /*
    if (adminProc.isAdmin(session) == true ) {
      // ����¡ + ���� �̹��� ���
      List<CustomerVO> list = this.customerProc.list_by_mno_paging(map);
      mav.addObject("list", list);
      
      // �˻��� ���ڵ� ����
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
    // ����¡ + ���� �̹��� ���
    List<CustomerVO> list = this.customerProc.list_by_mno_paging(map);
    mav.addObject("list", list);
    
    // �˻��� ���ڵ� ����
    int search_count = this.customerProc.search_count(m_no);
    mav.addObject("search_count", search_count);
    
    String paging = this.customerProc.pagingBox("list_by_mno_paging.do", m_no, search_count, nowPage);
    mav.addObject("paging", paging);
    mav.addObject("nowPage", nowPage);
    
    mav.setViewName("/adm/customer/list_by_mno_paging");  //  /webapp/adm/customer/list_by_mno_paging.jsp
    
    return mav;
  }
  
  /**
   *  Member+Customer Join ��ü ���
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
   * ȸ����ȣ �� ���� ��� (�����ڸ� ���� ����)
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
   * ȸ���� �� �� ��ȸ
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
   * ���� ���� ��ȸ(ȸ����) 
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
        member_Customer_join.setCs_type("��ǰ����");
      } else if(member_Customer_join.getCs_type().equals("A02")) {
        member_Customer_join.setCs_type("�������");
      } else if(member_Customer_join.getCs_type().equals("A03")) {
        member_Customer_join.setCs_type("ȯ��");
      } else if(member_Customer_join.getCs_type().equals("A04")) {
        member_Customer_join.setCs_type("��������");
      } else if(member_Customer_join.getCs_type().equals("A99")) {
        member_Customer_join.setCs_type("��Ÿ");
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
   * ���ڵ� �� �� ����, ÷������ ����X
   * @param cs_no
   * @return
   */
  @RequestMapping(value="/adm/customer/update.do", method=RequestMethod.GET )
  public ModelAndView update(int cs_no) {
    ModelAndView mav = new ModelAndView();
    
    Member_Customer_join member_Customer_join = this.customerProc.read(cs_no);
    mav.addObject("member_Customer_join", member_Customer_join);

    if(member_Customer_join.getCs_type().equals("A01")) {
      member_Customer_join.setCs_type("��ǰ����");
    } else if(member_Customer_join.getCs_type().equals("A02")) {
      member_Customer_join.setCs_type("�������");
    } else if(member_Customer_join.getCs_type().equals("A03")) {
      member_Customer_join.setCs_type("ȯ��");
    } else if(member_Customer_join.getCs_type().equals("A04")) {
      member_Customer_join.setCs_type("��������");
    } else if(member_Customer_join.getCs_type().equals("A99")) {
      member_Customer_join.setCs_type("��Ÿ");
    }
    
    mav.addObject("cs_type", member_Customer_join.getCs_type());  //  set fixed cs_type value
       
    mav.setViewName("/adm/customer/update");

    return mav;
  }
  
  /**
   * �� ��� ���� list
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
        member_Customer_join.setCs_type("��ǰ����");
      } else if(member_Customer_join.getCs_type().equals("A02")) {
        member_Customer_join.setCs_type("�������");
      } else if(member_Customer_join.getCs_type().equals("A03")) {
        member_Customer_join.setCs_type("ȯ��");
      } else if(member_Customer_join.getCs_type().equals("A04")) {
        member_Customer_join.setCs_type("��������");
      } else if(member_Customer_join.getCs_type().equals("A99")) {
        member_Customer_join.setCs_type("��Ÿ");
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
   * ���� ��, ÷������ ����X 
   * @param cs_no
   * @return
   */
  @RequestMapping(value="/customer/update.do", method=RequestMethod.GET )
  public ModelAndView update(@RequestParam(value="cs_no", defaultValue="1") int cs_no, 
                                          Member_Customer_join member_Customer_join) {
    ModelAndView mav = new ModelAndView();
    
    member_Customer_join = this.customerProc.read(cs_no);  // ���� ���ڵ� �б�
    mav.addObject("member_Customer_join", member_Customer_join);

    if(member_Customer_join.getCs_type().equals("A01")) {
      member_Customer_join.setCs_type("��ǰ����");
    } else if(member_Customer_join.getCs_type().equals("A02")) {
      member_Customer_join.setCs_type("�������");
    } else if(member_Customer_join.getCs_type().equals("A03")) {
      member_Customer_join.setCs_type("ȯ��");
    } else if(member_Customer_join.getCs_type().equals("A04")) {
      member_Customer_join.setCs_type("��������");
    } else if(member_Customer_join.getCs_type().equals("A99")) {
      member_Customer_join.setCs_type("��Ÿ");
    }
    
    mav.addObject("cs_type", member_Customer_join.getCs_type());  //  set fixed cs_type value
       
    mav.setViewName("/customer/update");

    return mav;
  }
  
  /**
   * ���� ó��, ÷������ X 
   * @param member_Customer_join
   * @return
   */
  @RequestMapping(value="/customer/update.do", method=RequestMethod.POST )
  public ModelAndView update(Member_Customer_join member_Customer_join) {
    ModelAndView mav = new ModelAndView();
    
    int passwd_cnt = 0;    // �н����� ��ġ üũ
    int cnt = 0;  // ����ó���� ���ڵ� ��
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("cs_no", member_Customer_join.getCs_no()); // cs_no �� ����
    hashMap.put("m_no", member_Customer_join.getM_no()); // m_no �� ����
    hashMap.put("r_mpasswd", member_Customer_join.getR_mpasswd()); // r_mpasswd �� ����
    
    passwd_cnt = this.customerProc.passwd_check(hashMap);
    
    // MemberVO memberVO = this.memberProc.read(member_Customer_join.getM_no());
    
    if(passwd_cnt == 1) {  // �н����� ��ġ
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
   * Ư�� ȸ���� ��� ���� ���
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
   * ���ڵ� �� �� ����, ÷������ ����X
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
   * if(customerVO.getCs_type().equals("A01")) { customerVO.setCs_type("��ǰ����"); }
   * else if(customerVO.getCs_type().equals("A02")) {
   * customerVO.setCs_type("�������"); } else
   * if(customerVO.getCs_type().equals("A03")) { customerVO.setCs_type("ȯ��"); }
   * else if(customerVO.getCs_type().equals("A04")) {
   * customerVO.setCs_type("��������"); } else
   * if(customerVO.getCs_type().equals("A99")) { customerVO.setCs_type("��Ÿ"); }
   * 
   * mav.addObject("cs_type", customerVO.getCs_type()); // set fixed cs_type value
   * 
   * mav.setViewName("/adm/customer/update");
   * 
   * return mav; }
   */
  
  /**
   * ���ڵ� �� �� ���� ó��, ÷������ ����X
   * @param customerVO
   * @return
   */
  /*
   * @RequestMapping(value="/adm/customer/update.do", method=RequestMethod.POST )
   * public ModelAndView update(Member_Customer_join customerVO) { ModelAndView
   * mav = new ModelAndView();
   * 
   * int cnt = this.customerProc.update(customerVO); // ���ڵ� �� �� ����
   * 
   * mav.addObject("cs_no", customerVO.getCs_no()); mav.addObject("m_no",
   * customerVO.getM_no()); // m_no �� ����
   * 
   * if(cnt == 1) { // ������ ���
   * mav.setViewName("redirect:/adm/customer/list_all.do"); // ������� --> �Ŀ� �ش� ȸ��
   * ���� ������� ���� �ٲ� ����. } else { // ������ ��� mav.addObject("url","update_msg");
   * mav.setViewName("redirect:/adm/customer/msg_adm.do"); }
   * 
   * return mav; }
   */

  
  /**
   * ���� ��
   * @param cs_no
   * @return
   */
  @RequestMapping(value = "/customer/delete.do", method= RequestMethod.GET)
  public ModelAndView delete(HttpSession session,int cs_no) {
    ModelAndView mav = new ModelAndView();
    
    int m_no = (int)session.getAttribute("m_no");
    
    if(m_no == this.customerProc.read(cs_no).getM_no()) {
      Member_Customer_join member_Customer_join = this.customerProc.read(cs_no);  // ���� ���ڵ� �б�
      mav.addObject("member_Customer_join", member_Customer_join);
      
      mav.setViewName("/customer/delete");
    } else {
      mav.setViewName("redirect:/member/login_need.jsp");
    }
    
    
    return mav;
  }
  
  /**
   * ���� ó�� + ���� ����
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
    
    Member_Customer_join member_Customer_join = this.customerProc.read(cs_no);  // ���� ���ڵ� �б�

    String title = member_Customer_join.getCs_title();
    mav.addObject("title", title);
    
    // ���� �н����� �˻�
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("cs_no", cs_no); // cs_no �� ����
    hashMap.put("r_mpasswd", r_mpasswd); // r_mpasswd �� ����
    
    int passwd_cnt = 0;  // �н����� ��ġ ���ڵ� ��
    int cnt = 0;             // ������ ���ڵ� �� 
    
    passwd_cnt = this.customerProc.passwd_check(hashMap);  // �н����� �˻� ó��
    boolean sw = false;    
    
    if( passwd_cnt == 1 ) {  // �н����� ��ġ �� ���
      cnt = this.customerProc.delete(cs_no);   //  ���� ó��

      String upDir = Tool.getRealPath(request, "/adm/customer/storage/main_images");  // ������
      sw = Tool.deleteFile(upDir, member_Customer_join.getCs_file1());  // Folder���� 1���� ���� ����
      sw = Tool.deleteFile(upDir, member_Customer_join.getCs_thumb1());  // Folder���� 1���� ��������� ����  
     
    } 
    
    mav.addObject("cnt", cnt);                       // add request 
    mav.addObject("passwd_cnt", passwd_cnt); // add a request 
    
    mav.addObject("m_no", (int)(session.getAttribute("m_no")));   // m_no ����
    // mav.addObject("m_id", member_Customer_join.getR_mid());
    mav.addObject("url", "delete_msg");
    
    mav.setViewName("redirect:/customer/msg.do");

    return mav;
  }
  
  
  /**
   * ���� ��(�����ڿ�)
   * @param cs_no
   * @return
   */
  @RequestMapping(value = "/adm/customer/delete.do", method= RequestMethod.GET)
  public ModelAndView read_delete(int cs_no) {
    ModelAndView mav = new ModelAndView();
    
    Member_Customer_join customerVO = this.customerProc.read_delete(cs_no);  // ������ ��ȸ process
    mav.addObject("customerVO", customerVO);
    mav.setViewName("/adm/customer/delete");
    
    return mav;
  }
  
  /**
   * ���� ó�� (�����ڿ�)
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
    
    Member_Customer_join customerVO = this.customerProc.read(cs_no);  // �ش� ���ڵ� ��ȸ
    String title = customerVO.getCs_title(); 
    mav.addObject("title", title);
    m_no = customerVO.getM_no();
    mav.addObject("m_no", m_no);
    
    // ���� �н����� �˻�
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("cs_no", cs_no);
    hashMap.put("cs_passwd", cs_passwd);
    
    int passwd_cnt = this.customerProc.passwd_check(hashMap);  // �н����� ��ġ Ȯ�� ����
    int cnt =0;             // ó���� ���ڵ� ����
    
    boolean sw = false;    
    if(passwd_cnt==1) {  // �н����� ��ġ �� ��� ���� ó��
      cnt = this.customerProc.delete(cs_no);   //  ���ڵ� �� �� ���� process
      String upDir = Tool.getRealPath(request, "/adm/customer/storage/main_images");  // ������
      sw = Tool.deleteFile(upDir, customerVO.getCs_file1());  // Folder���� 1���� ���� ����
      sw = Tool.deleteFile(upDir, customerVO.getCs_thumb1());  // Folder���� 1���� ��������� ����  
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
   * ���ΰ�ħ�� �����ϴ� �޽��� ���
   * @return
   */
  @RequestMapping(value="/adm/customer/msg.do", method=RequestMethod.GET)
  public ModelAndView msg_adm(String url){
    ModelAndView mav = new ModelAndView();
    
    // ��� ó�� �޽���: create_msg --> /������/create_msg.jsp
    // ���� ó�� �޽���: update_msg --> /������/update_msg.jsp
    // ���� ó�� �޽���: delete_msg --> /������/delete_msg.jsp
    mav.setViewName("/adm/customer/" + url); // forward
    
    return mav; // forward
  }
  
  
  /**
   * ���ΰ�ħ�� �����ϴ� �޽��� ���
   * @return
   */
  @RequestMapping(value="/customer/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();
    
    // ��� ó�� �޽���: create_msg --> /������/create_msg.jsp
    // ���� ó�� �޽���: update_msg --> /������/update_msg.jsp
    // ���� ó�� �޽���: delete_msg --> /������/delete_msg.jsp
    mav.setViewName("/customer/" + url); // forward
    
    return mav; // forward
  }
  
  
}
