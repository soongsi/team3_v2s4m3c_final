package dev.mvc.categrp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategrpCont {
  
  @Autowired
  @Qualifier("dev.mvc.categrp.CategrpProc")
  private CategrpProcInter categrpProc;
  
  public CategrpCont() {
    System.out.println( "--> CategrpCont created." );
  }
  
  @RequestMapping(value="/adm/categrp/create.do", method=RequestMethod.POST)
  public ModelAndView create(CategrpVO categrpVO) {
    ModelAndView mav = new ModelAndView();
    String status = "";
    
    int cnt = this.categrpProc.create(categrpVO);
    
    if ( cnt == 1 ) {
      status = "ok";
    } else {
      status = "fail";
    }
    
    mav.setViewName("redirect:/adm/categrp/list.do");
    mav.addObject("status", status);
    
    return mav;
  }
  
  @RequestMapping(value="/adm/categrp/list.do", method=RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    
    String title = "ī�װ� �׷� ���";
    mav.addObject("title", title);
    
    mav.setViewName("/adm/categrp/list");
    
    List<CategrpVO> list = this.categrpProc.list_cgseqno_asc();
    mav.addObject("list", list);
    
    return mav;
  }
  
  /**
   * ��ȸ + ������
   * 
   * http://localhost:9090/resort/categrp/read_update.do
   * @return
   */
  @RequestMapping(value="/adm/categrp/read_update.do", method=RequestMethod.GET)
  public ModelAndView read_update(int cg_no) {
    // request.setAttribute("categrpno", categrpno); �ڵ�����
    ModelAndView mav = new ModelAndView();
    
    CategrpVO categrpVO = this.categrpProc.read(cg_no);
    mav.addObject("categrpVO", categrpVO);
    
    String title = "ī�װ� �׷� ���� > " + categrpVO.getCg_name() ;
    mav.addObject("title", title);
    
    mav.setViewName("/adm/categrp/read_update");     // /webapp/categrp/read_update.jsp
    
    
    List<CategrpVO> list = this.categrpProc.list_cgseqno_asc();
    mav.addObject("list", list);
    
    return mav;   // forward
  }
  
  /**
   * ���� ó��
   * 
   * @param categrpVO
   * @return
   */
  @RequestMapping(value = "/adm/categrp/update.do", method = RequestMethod.POST)
  public ModelAndView update(CategrpVO categrpVO) {
    // request.setAttribute("categrpno", categrpno); �ڵ�����
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.categrpProc.update(categrpVO);
    
    String status = "";
    
    if ( cnt == 1 ) {
      status = "ok";
    } else {
      status = "fail";
    }
    
    mav.addObject("status", status);
    
    mav.setViewName("redirect:/adm/categrp/list.do");
    //mav.setViewName("/categrp/update_msg");     // /webapp/categrp/update_msg.jsp
    
    return mav;   // forward
  }
  
  /**
   * ������
   * 
   * http://localhost:9090/resort/categrp/read_delete.do
   * @return
   */
  @RequestMapping(value="/adm/categrp/read_delete.do", method=RequestMethod.GET)
  public ModelAndView read_delete(int cg_no) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/adm/categrp/read_delete");     // /webapp/categrp/read_delete.jsp
        
    CategrpVO categrpVO = this.categrpProc.read(cg_no);
    mav.addObject("categrpVO", categrpVO);
    
    String title = "ī�װ� �׷� ���� > " + categrpVO.getCg_name();
    mav.addObject("title", title);
    
    List<CategrpVO> list = this.categrpProc.list_cgseqno_asc();
    mav.addObject("list", list);
    
    return mav;   // forward
  }
  
  /**
   * ���� ó��
   * 
   * @param categrpVO
   * @return
   */
  @RequestMapping(value="/adm/categrp/delete.do", method=RequestMethod.POST)
  public ModelAndView delete(int cg_no) {
    ModelAndView mav = new ModelAndView();
    String status = "";
    
    int cnt = this.categrpProc.delete(cg_no);
    
    if ( cnt == 1 ) {
      status = "ok";
    } else {
      status = "fail";
    }
    
    mav.addObject("status", status);
    
    mav.setViewName("redirect:/adm/categrp/list.do");
    
    //mav.setViewName("/categrp/delete_msg");     // /webapp/categrp/delete_msg.jsp
       
    return mav;   // forward
  }
  
  
  /**
   * �켱���� ���� up 10 �� 1
   * @param cg_no ��ȸ�� ī�װ� ��ȣ
   * @return
   */
  @RequestMapping(value="/adm/categrp/update_seqno_up.do", method=RequestMethod.GET)
  public ModelAndView update_seqno_up(int cg_no) {
    ModelAndView mav = new ModelAndView();
    CategrpVO categrpVO = this.categrpProc.read(cg_no);
    mav.addObject("categrpVO", categrpVO);
    
    this.categrpProc.update_seqno_up(cg_no);
    //mav.addObject("cnt", cnt);
    
    mav.setViewName("redirect:/adm/categrp/list.do");
    
    return mav;
  }

  /**
   * �켱���� ���� up 1 �� 10
   * @param cg_no ��ȸ�� ī�װ� ��ȣ
   * @return
   */
  @RequestMapping(value="/adm/categrp/update_seqno_down.do", method=RequestMethod.GET)
  public ModelAndView update_seqno_down(int cg_no) {
    ModelAndView mav = new ModelAndView();
    CategrpVO categrpVO = this.categrpProc.read(cg_no);
    mav.addObject("categrpVO", categrpVO);
    
    this.categrpProc.update_seqno_down(cg_no);
    //mav.addObject("cnt", cnt);
    
    mav.setViewName("redirect:/adm/categrp/list.do");
    
    return mav;
  }
  
  /**
   * ��¸�� ����
   * 
   * @param categrpVO
   * @return
   */
  @RequestMapping(value = "/adm/categrp/update_visible.do", method = RequestMethod.GET)
  public ModelAndView update_visible(CategrpVO categrpVO) {
    // request.setAttribute("categrpno", categrpno); �ڵ�����
    ModelAndView mav = new ModelAndView();
    
    this.categrpProc.update_visible(categrpVO);
    //mav.addObject("cnt", cnt);
    mav.setViewName("redirect:/adm/categrp/list.do");
    
    return mav;   // forward
  }
  
  
}
