package dev.mvc.survey;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SurveyCont {

  @Autowired
  @Qualifier("dev.mvc.survey.SurveyProc")
  private SurveyProcInter surveyProc;
  
  
  public SurveyCont() {
    System.out.println("--> SurveyCont created.");
  }
  
  // ��� ��
  @RequestMapping(value="/adm/survey/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/adm/survey/create");
    
    return mav;
  }
  
  /**
   * http://localhost:9090/team3_testgit/adm/survey/create.do
   * ��� ó��
   * @param surveyVO
   * @return
   */
  @RequestMapping(value="/adm/survey/create.do", method=RequestMethod.POST)
  public ModelAndView create(SurveyVO surveyVO) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.surveyProc.create(surveyVO);
    
    if (cnt == 1) {
      mav.setViewName("redirect:/adm/survey/list.do");
    } else {
      mav.addObject("cnt", cnt);      
      mav.addObject("url", "create_msg");
      mav.setViewName("redirect:/adm/survey/msg.do");
    }
    
    return mav;
  }
  
  // ������ ���
  @RequestMapping(value="/adm/survey/list.do", method=RequestMethod.GET)
  public ModelAndView list_surveyno_desc() {
    ModelAndView mav = new ModelAndView();
  
    // List<SurveyVO> list = this.surveyProc.list_surveyno_desc();
    List<SurveyVO> list = this.surveyProc.list_seqno_asc();
    mav.addObject("list", list);
    
    mav.setViewName("/adm/survey/list");
     
    return mav;
  }
  
  // ȸ�� ���
  @RequestMapping(value="/survey/list.do", method=RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    
    List<SurveyVO> list = this.surveyProc.list_surveyno_desc();
    mav.addObject("list", list);
    
    mav.setViewName("/survey/list");
    
    return mav;
  }
  
  
  // ���� ��ȸ
  @RequestMapping(value="/adm/survey/read_update.do", method=RequestMethod.GET)
  public ModelAndView read_update(int survey_no) {
    ModelAndView mav = new ModelAndView();
  
    SurveyVO surveyVO = this.surveyProc.read(survey_no);
    mav.addObject("surveyVO", surveyVO);
    
    String title = "�������� - ���� > " + surveyVO.getTitle();
    mav.addObject("title", title);
    
    mav.setViewName("/adm/survey/read_update");
    
    List<SurveyVO> list = this.surveyProc.list_surveyno_desc();
    mav.addObject("list", list);    
    
    return mav;
  } 
  
  // ���� ó��
  @RequestMapping(value="/adm/survey/update.do", method=RequestMethod.POST)
  public ModelAndView update(SurveyVO surveyVO) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.surveyProc.update(surveyVO);
    
    if(cnt == 1) {
      mav.setViewName("redirect:/adm/survey/list.do");
    } else {
      mav.addObject("cnt", cnt);
      mav.addObject("url", "update_msg");
      mav.setViewName("redirect:/adm/survey/msg.do");
    }
    
    return mav;
  } 
  
  /**
   * continueyn ����
   * @param surveyVO
   * @return
   */
  @RequestMapping(value = "/adm/survey/update_continueyn.do", method = RequestMethod.GET)
  public ModelAndView update_continueyn(SurveyVO surveyVO) {
    ModelAndView mav = new ModelAndView();

    System.out.println("--> survey_no:"+surveyVO.getSurvey_no());
    System.out.println("--> continueyn:"+surveyVO.getContinueyn());
    
    int cnt = this.surveyProc.update_continueyn(surveyVO);
    mav.addObject("cnt", cnt);
    
    mav.setViewName("redirect:/adm/survey/list.do");  // request ��ü�� ������ �ȵ�.
    
    return mav;
  }
  
  /**
   * ������
   * @param categrpno
   * @return
   */
  @RequestMapping(value = "/adm/survey/read_delete.do", method = RequestMethod.GET)
  public ModelAndView read_delete(int survey_no) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/adm/survey/read_delete"); 
    
    SurveyVO surveyVO = this.surveyProc.read(survey_no);
    mav.addObject("surveyVO", surveyVO);
    
    List<SurveyVO> list = this.surveyProc.list_surveyno_desc();
    mav.addObject("list", list);

    return mav; // forward
  }
  
  /**
   * ���� ó��
   * @param categrpno
   * @return
   */
  @RequestMapping(value = "/adm/survey/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(int survey_no) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.surveyProc.delete(survey_no);
    
    if(cnt != 0) {
      mav.setViewName("redirect:/adm/survey/list.do");
    } else {
      mav.addObject("cnt", cnt);
      mav.addObject("url", "delete_msg");
      mav.setViewName("redirect:/adm/survey/msg.do");
    }

    return mav;
  }
  
  
  
  
  
  /**
   * ������ ������ - ���ΰ�ħ�� �����ϴ� �޽��� ���
   * @return
   */
  @RequestMapping(value="/adm/survey/msg.do", method=RequestMethod.GET)
  public ModelAndView msgtoA(String url){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/adm/survey/" + url); // forward
    return mav; // forward
  }
  /**
   * ȸ�� ������ - ���ΰ�ħ�� �����ϴ� �޽��� ���
   * @return
   */
  @RequestMapping(value="/survey/msg.do", method=RequestMethod.GET)
  public ModelAndView msgtoM(String url){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/survey/" + url); // forward
    return mav; // forward
  }

}
