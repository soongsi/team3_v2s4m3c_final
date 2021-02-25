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

  // ���
  @Override
  public int create(SurveyVO surveyVO) {
    int cnt = this.surveyDAO.create(surveyVO);
    return cnt;
  }

  // ���
  @Override
  public List<SurveyVO> list_surveyno_desc() {
    List<SurveyVO> list = this.surveyDAO.list_surveyno_desc();
    return list;
  }
  
  // ��� list_seqno_asc
  @Override
  public List<SurveyVO> list_seqno_asc() {
    List<SurveyVO> list = this.surveyDAO.list_seqno_asc();
    return list;
  }
  
  // �б�
  @Override
  public SurveyVO read(int survey_no) {
    SurveyVO surveyVO = this.surveyDAO.read(survey_no);
    return surveyVO;
  }

  // ����
  @Override
  public int update(SurveyVO surveyVO) {
    int cnt = this.surveyDAO.update(surveyVO);
    return cnt;
  }
  
  /**
   *  Y -> N, N -> Y
   *  continueyn ����
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
