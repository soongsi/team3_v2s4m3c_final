package dev.mvc.cate;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.categrp.CategrpProcInter;
import dev.mvc.categrp.CategrpVO;

@Controller
public class CateCont {
  
  @Autowired
  @Qualifier("dev.mvc.categrp.CategrpProc")
  private CategrpProcInter categrpProc;
  
  @Autowired
  @Qualifier("dev.mvc.cate.CateProc")
  private CateProcInter cateProc;
  
  public CateCont() {
    System.out.println( "--> CateCont created." );
  }
  
  /**
   * 등록폼
   * @return
   */
  /*
  @RequestMapping(value="/adm/cate/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/adm/cate/create");
    
    return mav;
  }
  */
  
  @RequestMapping(value="/adm/cate/create.do", method=RequestMethod.POST)
  public ModelAndView create(CateVO cateVO) {
    ModelAndView mav = new ModelAndView();
    String status = "";
    
    int cnt = this.cateProc.create(cateVO);
    if ( cnt == 1 ) {
      status = "ok";
    } else {
      status = "fail";
    }
    
    int cg_no = cateVO.getCg_no();
    
    mav.setViewName("redirect:/adm/cate/list.do?cg_no="+cg_no);
    mav.addObject("status", status);
    
    return mav;
  }
  
  /**
   * 카테고리 전체 목록
   * @return
   */
  @RequestMapping(value="/adm/cate/list_all.do", method=RequestMethod.GET)
  public ModelAndView list_all() {
    ModelAndView mav = new ModelAndView();
    
    String title = "카테고리 전체 목록";   
    mav.addObject("title", title);
    
    //List<CateVO> list = this.cateProc.list_cateno_asc();
    //List<CateVO> list = this.cateProc.list_seqno_asc();
    List<Categrp_Cate_join> list = this.cateProc.list_join();
    mav.addObject("list", list);

    mav.setViewName("/adm/cate/list_all");
    
    return mav;
  }
  
  /**
   * 카테고리 그룹에 속한 카테고리 목록
   * @return
   */
//  @RequestMapping(value="/adm/cate/list.do", method=RequestMethod.GET)
//  public ModelAndView list(int cg_no) {
//    
//    ModelAndView mav = new ModelAndView();
//    
//    String categrp_name = this.categrpProc.read(cg_no).getCg_name();
//    
//    String title = categrp_name; 
//    mav.addObject("title", title);
//    
//    mav.setViewName("/adm/cate/list");
//    List<CateVO> list = this.cateProc.list_cgno_cate(cg_no);
//    mav.addObject("list", list);
//    
//    return mav;    
//  }
  
  /**
   * categrp + cate join 전체 목록
   * @param cg_no
   * @return
   */
  @RequestMapping(value = "/adm/cate/list.do", method = RequestMethod.GET)
  public ModelAndView list_join_by_cgno(int cg_no) {
    ModelAndView mav = new ModelAndView();

    CategrpVO categrpVO = this.categrpProc.read(cg_no);
    mav.addObject("categrpVO", categrpVO);
    
    String title = categrpVO.getCg_name();
    mav.addObject("title",title);

    List<Categrp_Cate_join> list = this.cateProc.list_join_by_cgno(cg_no);
    mav.addObject("list", list); // request.setAttribute("list", list);

    mav.setViewName("/adm/cate/list"); // webapp/cate/list_join_by_categrpno.jsp
    return mav;
  }
  
  
  /**
   * 카테고리 목록 Ajax로 출력
   * @param cg_no
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/adm/cate/cate_list_by_ajax.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public String cate_list_by_ajax(@RequestParam(value = "cg_no", defaultValue = "0") int cg_no) {
    
    List<CateVO> list = this.cateProc.list_cgno_cate(cg_no);

    JSONObject json = new JSONObject();
    json.put("list", list);

    return json.toString();
  }
  
  
  /**
   * 조회 + 수정폼
   * @return
   */
  @RequestMapping(value="/adm/cate/read_update.do", method=RequestMethod.GET)
  public ModelAndView read_update(int cateno) {
    ModelAndView mav = new ModelAndView();
    
    CateVO cateVO = this.cateProc.read(cateno);
    int cg_no = cateVO.getCg_no();     
    String cg_name = this.categrpProc.read(cg_no).getCg_name();
    
    String cate_name = cateVO.getName();
    
    String title = cg_name + " > " + cate_name + " 수정";
    mav.addObject("title", title);
    
    mav.setViewName("/adm/cate/read_update");
    
    List<CateVO> list = this.cateProc.list_cgno_cate(cg_no);
    
    mav.addObject("list", list);
    mav.addObject("cateVO", cateVO);
    
    return mav;
  }
  
  /**
   * 수정 처리
   * @return
   */
  @RequestMapping(value="/adm/cate/update.do", method=RequestMethod.POST)
  public ModelAndView update(CateVO cateVO) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.cateProc.update(cateVO);
    
    String status = "";
    
    if ( cnt == 1 ) {
      status = "ok";
    } else {
      status = "fail";
    }
    
    int cg_no = cateVO.getCg_no();
    
    mav.addObject("status", status);
    //mav.setViewName("/adm/cate/update_msg");
    mav.setViewName("redirect:/adm/cate/list.do?cg_no="+cg_no);
    
    return mav;
  }
  
  /**
   * 조회 + 삭제폼
   * @return
   */
  @RequestMapping(value="/adm/cate/read_delete.do", method=RequestMethod.GET)
  public ModelAndView read_delete(int cateno) {
    ModelAndView mav = new ModelAndView();
        
    CateVO cateVO = this.cateProc.read(cateno);
    mav.addObject("cateVO", cateVO);
    
    int cg_no = cateVO.getCg_no();
    String cg_name = this.categrpProc.read(cg_no).getCg_name();
    
    String cate_name = cateVO.getName();
    
    String title = cg_name + " > " + cate_name + " 삭제";
    
    List<CateVO> list = this.cateProc.list_cgno_cate(cg_no);
    mav.addObject("list", list);
    
    mav.addObject("title", title);
    
    mav.setViewName("/adm/cate/read_delete");
        
    return mav;
  }
  
  /**
   * 삭제 처리
   * @return
   */
  @RequestMapping(value="/adm/cate/delete.do", method=RequestMethod.POST)
  public ModelAndView delete(int cateno) {
    ModelAndView mav = new ModelAndView();
    String status = "";
    
    int cnt = this.cateProc.delete(cateno);
    
    int cg_no = this.cateProc.read(cateno).getCg_no();
    
    if ( cnt == 1 ) {
      status = "ok";
    } else {
      status = "fail";
    }
    
    mav.addObject("status", status);
    
    //mav.setViewName("/adm/cate/delete_msg");
    mav.setViewName("redirect:/adm/cate/list.do?cg_no=" + cg_no);
    
    return mav;
  }
  
  /**
   * 출력 순서 상향
   * @return
   */
  @RequestMapping(value="/adm/cate/update_seqno_up.do", method=RequestMethod.GET)
  public ModelAndView update_seqno_up(int cateno) {
    ModelAndView mav = new ModelAndView();
    
    this.cateProc.update_seqno_up(cateno);
    
    int cg_no = this.cateProc.read(cateno).getCg_no();
    
    mav.setViewName("redirect:/adm/cate/list.do?cg_no=" + cg_no);
    return mav;
  }
  
  /**
   * 출력 순서 하향
   * @return
   */
  @RequestMapping(value="/adm/cate/update_seqno_down.do", method=RequestMethod.GET)
  public ModelAndView update_seqno_down(int cateno) {
    ModelAndView mav = new ModelAndView();
    
    this.cateProc.update_seqno_down(cateno);
       
    int cg_no = this.cateProc.read(cateno).getCg_no();
    
    mav.setViewName("redirect:/adm/cate/list.do?cg_no=" + cg_no);
    
    return mav;
  }
  
  /**
   * 출력 모드 변경
   * http://localhost:9090/resort/cate/update_visible.do
   * @return
   */
  @RequestMapping(value="/adm/cate/update_visible.do", method=RequestMethod.GET)
  public ModelAndView update_visible(CateVO cateVO) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.cateProc.update_visible(cateVO);
    int cg_no = cateVO.getCg_no();
    
    if ( cnt == 1 ) {
      mav.setViewName("redirect:/adm/cate/list.do?cg_no=" + cg_no);      
    } else {
      CateVO vo = this.cateProc.read(cateVO.getCateno());
      String name = vo.getName();
      mav.addObject("name", name);
      mav.setViewName("/adm/cate/update_visible_msg");
    }
    
    return mav;
  }
}
