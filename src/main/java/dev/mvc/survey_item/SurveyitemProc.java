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

  
  // �׸� ����
  @Override
  public int create(SurveyitemVO surveyitemVO) {
    int cnt = this.surveyitemDAO.create(surveyitemVO);
    return cnt;
  }

   // �׸� ����Ʈ list_itemno_asc
  @Override
  public List<SurveyitemVO> list_itemno_asc(int survey_no) {
    List<SurveyitemVO> list = this.surveyitemDAO.list_itemno_asc(survey_no);
    return list;
  }
  
  // �׸� ����Ʈ list_seqno_asc
  @Override
  public List<SurveyitemVO> list_seqno_asc(int survey_no) {
    List<SurveyitemVO> list = this.surveyitemDAO.list_seqno_asc(survey_no);
    return list;
  }

  // ��ȸ
  @Override
  public SurveyitemVO read(int item_no) {
    SurveyitemVO surveyitemVO = this.surveyitemDAO.read(item_no);
    return surveyitemVO;
  }

  // ����
  @Override
  public int update(SurveyitemVO surveyitemVO) {
    int cnt = this.surveyitemDAO.update(surveyitemVO);
    return cnt;
  }

  // ���� ó��
  @Override
  public int delete(int item_no) {
    int cnt = this.surveyitemDAO.delete(item_no);
    return cnt;
  }

  // ���� �� ����
  @Override
  public int update_rcnt_up(int item_no) {
    int cnt = this.surveyitemDAO.update_rcnt_up(item_no);
    return cnt;
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  

}
