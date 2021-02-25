package dev.mvc.survey;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.survey.SurveyProc")
public class SurveyProc implements SurveyProcInter {

  @Autowired
  private SurveyDAOInter surveyDAO;
  
  public SurveyProc() {
    System.out.println("--> SurveyProc created.");
  }

  // 등록
  @Override
  public int create(SurveyVO surveyVO) {
    int cnt = this.surveyDAO.create(surveyVO);
    return cnt;
  }

  // 목록
  @Override
  public List<SurveyVO> list_surveyno_desc() {
    List<SurveyVO> list = this.surveyDAO.list_surveyno_desc();
    return list;
  }
  
  // 목록 list_seqno_asc
  @Override
  public List<SurveyVO> list_seqno_asc() {
    List<SurveyVO> list = this.surveyDAO.list_seqno_asc();
    return list;
  }
  
  // 읽기
  @Override
  public SurveyVO read(int survey_no) {
    SurveyVO surveyVO = this.surveyDAO.read(survey_no);
    return surveyVO;
  }

  // 수정
  @Override
  public int update(SurveyVO surveyVO) {
    int cnt = this.surveyDAO.update(surveyVO);
    return cnt;
  }
  
  /**
   *  Y -> N, N -> Y
   *  continueyn 수정
   */
  @Override
  public int update_continueyn(SurveyVO surveyVO) {
    if(surveyVO.getContinueyn().equalsIgnoreCase("Y")) {
      surveyVO.setContinueyn("N");
    } else {
      surveyVO.setContinueyn("Y");
    }

    int cnt = this.surveyDAO.update_continueyn(surveyVO);
    return cnt;
  }

  @Override
  public int delete(int survey_no) {
    int cnt = this.surveyDAO.delete(survey_no);
    return cnt;
  }
  
  
  
  
  
  
}
