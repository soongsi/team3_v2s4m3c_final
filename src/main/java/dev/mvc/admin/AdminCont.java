package dev.mvc.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminCont {
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc") // �̸� ����
  private AdminProcInter adminProc;
  
  public AdminCont(){
    System.out.println("--> AdminCont created.");
  }
  
  @RequestMapping(value= {"/adm/","/adm/*"}, method=RequestMethod.GET)
  public ModelAndView adm(HttpSession session) {
    ModelAndView mav = new ModelAndView();
        
    boolean sw = this.adminProc.isAdmin(session);
    
    if ( sw == false ) {
      mav.setViewName("redirect:/admin/login.do");
    } else {
      mav.setViewName("/adm/index");  
    }
    
    return mav;
  }
  /**
   * ������ ���
   * http://localhost:9090/resort/admin/list.do
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/admin/list.do", 
                             method = RequestMethod.GET,
                             produces = "text/plain;charset=UTF-8")
  public String list(String id, String passwd) {
    return adminProc.list();
  }
  
  /**
   * ������ �α��� ��
   * http://localhost:9090/resort/admin/login.do
   * @return
   */
  @RequestMapping(value = "/admin/login.do", 
                             method = RequestMethod.GET)
  public ModelAndView login(HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/admin/login_form");
    return mav;
  }
  
  /**
   * ������ �α��� ó��
   * http://localhost:9090/resort/admin/login.do 
   * @return
   */
  @RequestMapping(value = "/admin/login.do", 
                             method = RequestMethod.POST)
  public ModelAndView login_proc(HttpServletRequest request,
                                                 HttpServletResponse response,
                                                 HttpSession session,
                                                 String id_admin, String passwd_admin) {
    ModelAndView mav = new ModelAndView();
    
    boolean sw = adminProc.login(id_admin, passwd_admin);
    if (sw == true) { // �α��� ����
      session.setAttribute("id_admin", id_admin);
      session.setAttribute("passwd_admin", passwd_admin);
      
      mav.setViewName("redirect:/adm/");  
    } else {
      mav.setViewName("redirect:/admin/login_fail_msg.jsp");
    }
        
    return mav;
  }
  
  /**
   * ������ �α׾ƿ� ó��
   * http://localhost:9090/resort/admin/logout.do 
   * @param session
   * @return
   */
  @RequestMapping(value="/admin/logout.do", 
                             method=RequestMethod.GET)
  public ModelAndView logout(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    session.invalidate(); // ��� session ���� ����
    
    mav.setViewName("redirect:/admin/logout_msg.jsp");
    
    return mav;
  }
  
}