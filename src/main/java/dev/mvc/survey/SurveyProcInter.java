package dev.mvc.survey;

import java.util.List;

public interface SurveyProcInter {

  // 설문 등록
  public int create(SurveyVO surveyVO);
  
  // 목록 list_surveyno_desc
  public List<SurveyVO> list_surveyno_desc();
  
  // 목록 list_seqno_asc
  public List<SurveyVO> list_seqno_asc();
  
  // 읽기
  public SurveyVO read(int survey_no);
  
  //수정
  public int update(SurveyVO surveyVO);
  
  // continueyn 수정
  public int update_continueyn(SurveyVO surveyVO);
  
  // 레코드 하나 삭제
  public int delete(int survey_no);
  
  
  
  
  
  
  
  
}
