package dev.mvc.categrp;

import java.util.List;

public interface CategrpDAOInter {
  
  /**
   * ī�װ� �׷� ���
   * @param categrpVO
   * @return ó���� ���ڵ� ����
   */
  public int create(CategrpVO categrpVO);
  
  /**
   * ī�װ� �׷� ���
   * @return ��ȣ �������� ���ڵ� ���
   */
  public List<CategrpVO> list_cgno_asc();
  
  /**
   * ī�װ� �׷� ���
   * @return ���� �������� ���ڵ� ���
   */
  public List<CategrpVO> list_cgseqno_asc();
  
  /**
   * ī�װ� �׷� ��ȸ
   * @param cg_no
   * @return
   */
  public CategrpVO read(int cg_no);
  
  /**
   * ����
   * @param categrpVO
   * @return 
   */
  public int update(CategrpVO categrpVO);
  
  /**
   * ���� ó��
   * @param cg_no
   * @return ó���� ���ڵ� ����
   */
  public int delete(int cg_no);
  
  /**
   * ��� ���� ����
   * @param cg_no
   * @return ó���� ���ڵ� ����
   */
  public int update_seqno_up(int cg_no);
  
  /**
   * ��� ���� ����
   * @param cg_no
   * @return ó���� ���ڵ� ����
   */
  public int update_seqno_down(int cg_no);
  
  /**
   * ��� ��� ����
   * @param categrpVO
   * @return ó���� ���ڵ� ����
   */
  public int update_visible(CategrpVO categrpVO);
}
