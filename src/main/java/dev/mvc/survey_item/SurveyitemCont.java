package dev.mvc.survey_item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.survey.SurveyProcInter;
import dev.mvc.survey.SurveyVO;

@Controller
public class SurveyitemCont {

  @Autowired  // 부모 프로세스 접근
  @Qualifier("dev.mvc.survey.SurveyProc")
  private SurveyProcInter surveyProc;
  
  @Autowired
  @Qualifier("dev.mvc.survey_item.SurveyitemProc")
  private SurveyitemProcInter surveyitemProc;
  
  public SurveyitemCont() {
    System.out.println( "--> SurveyitemCont created." );
  }

  
  // 등록폼
  @RequestMapping(value="/adm/surveyitem/create.do", method=RequestMethod.GET)
  public ModelAndView create(int survey_no) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/adm/surveyitem/create");
  
    SurveyVO surveyVO = this.surveyProc.read(survey_no);
    mav.addObject("surveyVO", surveyVO);
    
    String title = "설문 항목";
    
    mav.addObject("survey_title", surveyVO.getTitle());
    mav.addObject("title", title);
    
    return mav;
  }
  
  // 등록 처리
  @RequestMapping(value="/adm/surveyitem/create.do", method=RequestMethod.POST)
  public ModelAndView create(SurveyitemVO surveyitemVO) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.surveyitemProc.create(surveyitemVO);
    int survey_no = surveyitemVO.getSurvey_no();
    
    if(cnt!=0) {
      mav.setViewName("redirect:/adm/surveyitem/list.do?survey_no="+survey_no);
    } else {
      mav.addObject("cnt", cnt);      
      mav.addObject("url", "create_msg");
      mav.setViewName("redirect:/adm/surveyitem/msg.do");
    }
    
    return mav;
  }
  
  /**
   * 항목 리스트 http://localhost:9090/team3_testgit/adm/surveyitem/list.do
   * @param survey_no
   * @return
   */
  @RequestMapping(value = "/adm/surveyitem/list.do", method = RequestMethod.GET)
  public ModelAndView list_surveyno_item(int survey_no) {
    ModelAndView mav = new ModelAndView();
    
    SurveyVO surveyVO = this.surveyProc.read(survey_no);  //  SurveyProc 접근
    mav.addObject("survey_title", surveyVO.getTitle());
    
    // List<SurveyitemVO> list = this.surveyitemProc.list_itemno_asc(survey_no);
    List<SurveyitemVO> list = this.surveyitemProc.list_seqno_asc(survey_no);
    mav.addObject("list", list);
    
    mav.setViewName("/adm/surveyitem/list");
    
    return mav;
  }
  
  // 조회_수정
  @RequestMapping(value = "/adm/surveyitem/read_update.do", method = RequestMethod.GET)
  public ModelAndView read_update(int item_no, int survey_no) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/adm/surveyitem/read_update");
    
    SurveyVO surveyVO = this.surveyProc.read(survey_no);  //  SurveyProc 접근
    mav.addObject("survey_title", surveyVO.getTitle());
    
    SurveyitemVO surveyitemVO = this.surveyitemProc.read(item_no);
    mav.addObject("surveyitemVO", surveyitemVO);
    
    List<SurveyitemVO> list = this.surveyitemProc.list_seqno_asc(survey_no);
    mav.addObject("list", list);
    
    // System.out.println("item_seqno: "+surveyitemVO.getSeqno());
    return mav;
  }
  
  // 수정 처리
  @RequestMapping(value="/adm/surveyitem/update.do", method=RequestMethod.POST)
  public ModelAndView update(SurveyitemVO surveyitemVO) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.surveyitemProc.update(surveyitemVO);
    int survey_no = surveyitemVO.getSurvey_no();
    
    if(cnt!=0) {
      mav.setViewName("redirect:/adm/surveyitem/list.do?survey_no="+survey_no);
    } else {
      mav.addObject("cnt", cnt);      
      mav.addObject("url", "update_msg");
      mav.setViewName("redirect:/adm/surveyitem/msg.do");
    }
    
    return mav;
  }
  
  // 조회_삭제
  @RequestMapping(value = "/adm/surveyitem/read_delete.do", method = RequestMethod.GET)
  public ModelAndView read_delete(int item_no, int survey_no) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/adm/surveyitem/read_delete");
    
    SurveyVO surveyVO = this.surveyProc.read(survey_no);  //  SurveyProc 접근
    mav.addObject("survey_title", surveyVO.getTitle());
    
    SurveyitemVO surveyitemVO = this.surveyitemProc.read(item_no);
    mav.addObject("surveyitemVO", surveyitemVO);
    
    List<SurveyitemVO> list = this.surveyitemProc.list_seqno_asc(survey_no);
    mav.addObject("list", list);

    return mav;
  }
  
  
  // 삭제 처리
  @RequestMapping(value="/adm/surveyitem/delete.do", method=RequestMethod.POST)
  public ModelAndView delete(int item_no) {
    ModelAndView mav = new ModelAndView();
    
    SurveyitemVO surveyitemVO = this.surveyitemProc.read(item_no);
    int survey_no = surveyitemVO.getSurvey_no();
    
    int cnt = this.surveyitemProc.delete(item_no);
    
    if(cnt!=0) {
      mav.setViewName("redirect:/adm/surveyitem/list.do?survey_no="+survey_no);
    } else {
      mav.addObject("cnt", cnt);      
      mav.addObject("url", "delete_msg");
      mav.setViewName("redirect:/adm/surveyitem/msg.do");
    }
    
    return mav;
  }
  
  /**
   * 목록 http://localhost:9090/team3_testgit/surveyitem/list.do
   * @param survey_no
   * @return
   */
  @RequestMapping(value = "/surveyitem/list.do", method = RequestMethod.GET)
  public ModelAndView list_itemno_asc(int survey_no) {
    ModelAndView mav = new ModelAndView();
    
    SurveyVO surveyVO = this.surveyProc.read(survey_no);  //  SurveyProc 접근
    mav.addObject("survey_title", surveyVO.getTitle());
    
    // List<SurveyitemVO> list = this.surveyitemProc.list_seqno_asc(survey_no);
    List<SurveyitemVO> list = this.surveyitemProc.list_itemno_asc(survey_no);
    mav.addObject("list", list);
    
    mav.setViewName("/surveyitem/list");
    
    return mav;
  }
  
  /**
   * 집계 수 증가
   * @param survey_no
   * @param item_no
   * @return
   */
  @RequestMapping(value="/surveyitem/update_rcnt_up.do",  method=RequestMethod.POST )
    public ModelAndView update_rcnt_up(int survey_no, int item_no) {
      ModelAndView mav = new ModelAndView();

       int cnt = this.surveyitemProc.update_rcnt_up(item_no);  //  증가 처리
       
       mav.addObject("cnt", cnt);
       mav.addObject("survey_no", survey_no);
       mav.addObject("url", "update_rcnt_up_msg");
       mav.setViewName("redirect:/surveyitem/msg.do");
        
      return mav;
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  /**
   * 관리자 페이지 - 새로고침을 방지하는 메시지 출력
   * @return
   */
  @RequestMapping(value="/adm/surveyitem/msg.do", method=RequestMethod.GET)
  public ModelAndView msgtoA(String url){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/adm/surveyitem/" + url); // forward
    return mav; // forward
  }
  
  /**
   * 회원 페이지 - 새로고침을 방지하는 메시지 출력
   * @return
   */
  @RequestMapping(value="/surveyitem/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/surveyitem/" + url); // forward
    return mav; // forward
  }

  
  
  
  
  
  
  
  
  
}
