package dev.mvc.survey_chart;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.survey.SurveyProcInter;
import dev.mvc.survey.SurveyVO;
import dev.mvc.survey_item.SurveyitemProcInter;
import dev.mvc.survey_item.SurveyitemVO;

@Controller
public class SurveychartCont {

  @Autowired
  @Qualifier("dev.mvc.survey.SurveyProc")
  private SurveyProcInter surveyProc;
  
  @Autowired 
  @Qualifier("dev.mvc.survey_item.SurveyitemProc")
  private SurveyitemProcInter surveyitemProc;
  
  @Autowired 
  @Qualifier("dev.mvc.survey_chart.SurveychartProc")
  private SurveychartProcInter surveychartProc;
 
  
  public SurveychartCont() {
    System.out.println( "--> SurveychartCont created." );
  }
  
  
  @RequestMapping(value = "/surveychart/doc_result.do", method = RequestMethod.GET)
  public ModelAndView list_by_surveyno(int survey_no) { 
    ModelAndView mav = new ModelAndView();
    
    SurveyVO surveyVO = this.surveyProc.read(survey_no);  //  SurveyProc 접근
    mav.addObject("survey_title", surveyVO.getTitle());
    
    // List<SurveyitemVO> list = this.surveyitemProc.list_itemno_asc(survey_no);
    List<SurveychartVO> list = this.surveychartProc.list(survey_no);
    mav.addObject("list", list);
    
    mav.setViewName("/surveychart/list");
    
    return mav;
  }

  @RequestMapping(value="/surveychart/chart_result.do", method=RequestMethod.GET)
  public ModelAndView list_by_surveyno_googlechart(int survey_no){
    ModelAndView mav=new ModelAndView();
    
    SurveyVO surveyVO = this.surveyProc.read(survey_no);  //  SurveyProc 접근 
    List<SurveychartVO>  list=  this.surveychartProc.list(survey_no);
    mav.addObject("survey_title", surveyVO.getTitle());
    mav.addObject("list", list);
    mav.setViewName("/surveychart/chart_result");
    
    System.out.println(" list size  : " + list.size());
    
    String str ="[";
    str +="['Item' , 'cnt'] ,";
    int num =0;
    for(SurveychartVO  vo : list){
      
      str +="['";
      str  += vo.getItem();
      str +="' , ";
      str += vo.getCnt();
      str +=" ]";
      
      num ++;
      if(num<list.size()){
        str +=",";
      }   
    }
    str += "]";
    mav.addObject("str", str);
    
    return mav;  
  }
  
  
  
  /**
   * 회원 페이지 - 새로고침을 방지하는 메시지 출력
   * @return
   */
  @RequestMapping(value="/surveychart/msg.do", method=RequestMethod.GET)
  public ModelAndView msgtoA(String url){
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/surveychart/" + url); // forward
    return mav; // forward
  }

}
