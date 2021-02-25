package dev.mvc.survey_item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.survey_item.SurveyitemProc")
public class SurveyitemProc implements SurveyitemProcInter {

  @Autowired
  private SurveyitemDAOInter surveyitemDAO;
  
  public SurveyitemProc() {
    System.out.println( "--> SurveyitemProc created." );
  }

  
  // 항목 생성
  @Override
  public int create(SurveyitemVO surveyitemVO) {
    int cnt = this.surveyitemDAO.create(surveyitemVO);
    return cnt;
  }

   // 항목 리스트 list_itemno_asc
  @Override
  public List<SurveyitemVO> list_itemno_asc(int survey_no) {
    List<SurveyitemVO> list = this.surveyitemDAO.list_itemno_asc(survey_no);
    return list;
  }
  
  // 항목 리스트 list_seqno_asc
  @Override
  public List<SurveyitemVO> list_seqno_asc(int survey_no) {
    List<SurveyitemVO> list = this.surveyitemDAO.list_seqno_asc(survey_no);
    return list;
  }

  // 조회
  @Override
  public SurveyitemVO read(int item_no) {
    SurveyitemVO surveyitemVO = this.surveyitemDAO.read(item_no);
    return surveyitemVO;
  }

  // 수정
  @Override
  public int update(SurveyitemVO surveyitemVO) {
    int cnt = this.surveyitemDAO.update(surveyitemVO);
    return cnt;
  }

  // 삭제 처리
  @Override
  public int delete(int item_no) {
    int cnt = this.surveyitemDAO.delete(item_no);
    return cnt;
  }

  // 집계 수 증가
  @Override
  public int update_rcnt_up(int item_no) {
    int cnt = this.surveyitemDAO.update_rcnt_up(item_no);
    return cnt;
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  

}
