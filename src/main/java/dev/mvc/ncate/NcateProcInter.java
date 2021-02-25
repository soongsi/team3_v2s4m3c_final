package dev.mvc.ncate;

import java.util.List;

public interface NcateProcInter {
  /**
   * ���
   * @param cateVO
   * @return ��ϵ� ����
   */
  public int create(NcateVO ncateVO);
  
  /**
   * ���
   * @return
   */
  public List<NcateVO> list_ncateno_asc();
  
  /**
   * ��� - ��¼��� ����
   * @return
   */
  public List<NcateVO> list_nseqno_asc();
  
  /**
   * ��ȸ, ������
   * @param cateno ī�װ� ��ȣ, PK
   * @return
   */
  public NcateVO read(int ncate_no);
  
  /**
   * ���� ó��
   * @param ncateVO
   * @return
   */
  public int update(NcateVO ncateVO);
  
  /**
   * ���� ó�� 
   * @param ncate_no
   * @return
   */
  public int delete(int ncate_no);
 
  /**
   * ��¼������� 
   * @param ncate_no
   * @return
   */
  public int update_nseqno_up(int ncate_no);

 
  /**
   * ��¼�������
   * @param cateno
   * @return
   */
  public int update_nseqno_down(int ncate_no); 
  
  /**
   * ��¸��
   * @param ncateVO
   * @return
   */
  public int update_nvisible(NcateVO ncateVO);
  
  /**
   * �� �� ����
   * @return
   */
  public int increaseCnt(int ncate_no);    

  /**
   * �� �� ����
   * @return
   */
  public int decreaseCnt(int ncate_no);

  
}


