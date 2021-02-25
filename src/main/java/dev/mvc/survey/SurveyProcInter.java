package dev.mvc.survey;

import java.util.List;

public interface SurveyProcInter {

  // ���� ���
  public int create(SurveyVO surveyVO);
  
  // ��� list_surveyno_desc
  public List<SurveyVO> list_surveyno_desc();
  
  // ��� list_seqno_asc
  public List<SurveyVO> list_seqno_asc();
  
  // �б�
  public SurveyVO read(int survey_no);
  
  //����
  public int update(SurveyVO surveyVO);
  
  // continueyn ����
  public int update_continueyn(SurveyVO surveyVO);
  
  // ���ڵ� �ϳ� ����
  public int delete(int survey_no);
  
  
  
  
  
  
  
  
}
