package dev.mvc.survey_item;

import java.util.List;

public interface SurveyitemDAOInter {

  // 항목 생성
  public int create(SurveyitemVO surveyitemVO);
  
  // 항목 리스트 list_itemno_asc
  public List<SurveyitemVO> list_itemno_asc(int survey_no);
  
  // 항목 리스트 list_seqno_asc
  public List<SurveyitemVO> list_seqno_asc(int survey_no);
  
  // 조회
  public SurveyitemVO read(int item_no);
  
  // 수정
  public int update(SurveyitemVO surveyitemVO);
  
  // 삭제
  public int delete(int item_no);
  
  // 집계 수 증가
  public int update_rcnt_up(int item_no);
  
  
  
  
  
  
  
}
