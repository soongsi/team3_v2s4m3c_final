package dev.mvc.ncate;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.notice.NoticeProcInter;

@Controller
public class NcateCont {
 
  @Autowired
  @Qualifier("dev.mvc.ncate.NcateProc")
  private NcateProcInter ncateProc;
  
  @Autowired
  @Qualifier("dev.mvc.notice.NoticeProc")
  private NoticeProcInter noticeProc;

  public NcateCont() {
    System.out.println("--> NcateCont created.");
  }

  @RequestMapping(value = "/ncate/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/ncate/create"); 

    return mav;
  }

  
  @RequestMapping(value = "/ncate/create.do", method = RequestMethod.POST)
  public ModelAndView create(NcateVO ncateVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/ncate/create_msg");
    
    int ncate_cnt = this.ncateProc.create(ncateVO);
    mav.addObject("ncate_cnt", ncate_cnt);

    return mav;
  }
  
  
  @RequestMapping(value = "/ncate/list_all.do", method = RequestMethod.GET)
  public ModelAndView list_all() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/ncate/list_all"); 

    List<NcateVO> list = this.ncateProc.list_nseqno_asc();
    mav.addObject("list", list);

    return mav;
  }
  
  /**
   * ��ȸ + ������ /ncate/read_update.do
   * 
   * @return
   */
  @RequestMapping(value = "/ncate/read_update.do", method = RequestMethod.GET)
  public ModelAndView read_update(int ncate_no) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/ncate/read_update"); 

    NcateVO ncateVO = this.ncateProc.read(ncate_no);
    mav.addObject("ncateVO", ncateVO);
    
    List<NcateVO> list = this.ncateProc.list_nseqno_asc();
    mav.addObject("list", list);

    return mav; 
  }
  
  
  @RequestMapping(value = "/ncate/update.do", method = RequestMethod.POST)
  public ModelAndView update(NcateVO ncateVO) {
    ModelAndView mav = new ModelAndView();

    int ncate_cnt = this.ncateProc.update(ncateVO);
    mav.addObject("ncate_cnt", ncate_cnt); 
    
    mav.setViewName("/ncate/update_msg"); 

    return mav;
  }
  
  /**
   * ��ȸ + ������ /ncate/read_delete.do
   * 
   * @return
   */
  @RequestMapping(value = "/ncate/read_delete.do", method = RequestMethod.GET)
  public ModelAndView read_delete(int ncate_no) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/ncate/read_delete"); 

    NcateVO ncateVO = this.ncateProc.read(ncate_no);
    mav.addObject("ncateVO", ncateVO);
    
    List<NcateVO> list = this.ncateProc.list_nseqno_asc();
    mav.addObject("list", list);

    return mav; 
  }
  
  /**
   * ���� ó��
   * 
   * @param ncateVO
   * @return
   */
  @RequestMapping(value = "/ncate/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(int ncate_no) {
    ModelAndView mav = new ModelAndView();

    int ncate_cnt = this.ncateProc.delete(ncate_no);
    mav.addObject("ncate_cnt", ncate_cnt); 

    mav.setViewName("/ncate/delete_msg"); 

    return mav;
  }
  
  /**
   * �켱���� ���� up 10 �� 1
   * @param ncate_no ī�װ� ��ȣ
   * @return
   */
  @RequestMapping(value="/ncate/update_nseqno_up.do", 
                              method=RequestMethod.GET )
  public ModelAndView update_nseqno_up(int ncate_no) {
    ModelAndView mav = new ModelAndView();

    int ncate_cnt = this.ncateProc.update_nseqno_up(ncate_no);
    // mav.addObject("ncate_cnt", ncate_cnt); // request�� ����
    
    mav.setViewName("redirect:/ncate/list_all.do"); // �Ķ���� �ڵ� ���� �ȵ�. 

    return mav;
  }
  
  /**
   * �켱���� ���� up 1 �� 10
   * @param ncate_no ī�װ� ��ȣ
   * @return
   */
  @RequestMapping(value="/ncate/update_nseqno_down.do", 
                              method=RequestMethod.GET )
  public ModelAndView update_nseqno_down(int ncate_no) {
    ModelAndView mav = new ModelAndView();

    int ncate_cnt = this.ncateProc.update_nseqno_down(ncate_no);
    // mav.addObject("ncate_cnt", ncate_cnt); // request�� ����
    
    mav.setViewName("redirect:/ncate/list_all.do"); // �Ķ���� �ڵ� ���� �ȵ�. 

    return mav;
  }
  
  /**
   * ��¸�� ����
   * 
   * @param cateVO
   * @return
   */
  @RequestMapping(value = "/ncate/update_nvisible.do", method = RequestMethod.GET)
  public ModelAndView update_nvisible(NcateVO ncateVO) {
    ModelAndView mav = new ModelAndView();

    int ncate_cnt = this.ncateProc.update_nvisible(ncateVO);
    mav.addObject("ncate_cnt", ncate_cnt); 

    if (ncate_cnt == 1) { 
      mav.setViewName("redirect:/ncate/list_all.do");  
    } else {
      NcateVO vo = this.ncateProc.read(ncateVO.getNcate_no());
      String ncate_name = vo.getNcate_name();
      mav.addObject("ncate_name", ncate_name);
      mav.setViewName("/ncate/update_nvisible_msg"); // /cate/update_visible_msg.jsp
    }
    return mav;
  }
  
}




