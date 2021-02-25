package dev.mvc.survey_chart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.survey_chart.SurveychartProc")
public class SurveychartProc implements SurveychartProcInter{

  @Autowired
  private SurveychartDAOInter surveychartDAO;
  
  public SurveychartProc() {
    System.out.println( "--> SurveychartProc created." );
  }

  
  // ¸ñ·Ï
  @Override
  public List<SurveychartVO> list(int survey_no) {
    List<SurveychartVO> list  = this.surveychartDAO.list(survey_no);
    return list;
  }


}
