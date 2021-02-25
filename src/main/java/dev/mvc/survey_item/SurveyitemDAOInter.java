package dev.mvc.survey_item;

import java.util.List;

public interface SurveyitemDAOInter {

  // �׸� ����
  public int create(SurveyitemVO surveyitemVO);
  
  // �׸� ����Ʈ list_itemno_asc
  public List<SurveyitemVO> list_itemno_asc(int survey_no);
  
  // �׸� ����Ʈ list_seqno_asc
  public List<SurveyitemVO> list_seqno_asc(int survey_no);
  
  // ��ȸ
  public SurveyitemVO read(int item_no);
  
  // ����
  public int update(SurveyitemVO surveyitemVO);
  
  // ����
  public int delete(int item_no);
  
  // ���� �� ����
  public int update_rcnt_up(int item_no);
  
  
  
  
  
  
  
}
