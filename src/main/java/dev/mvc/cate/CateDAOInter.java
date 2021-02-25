package dev.mvc.cate;

import java.util.List;

//MyBATIS�� <mapper namespace="dev.mvc.categrp.CategrpDAOInter">�� ���� 
public interface CateDAOInter {
  // ���ۿ� ���� �޼ҵ���� MyBATIS�� XML ���Ͼ��� id�� �����ؾ��մϴ�.
  
  /**
   * ī�װ� ���
   * @param cateVO
   * @return ��ϵ� ���ڵ� ����
   */
  public int create(CateVO cateVO);
  
  
  /**
   * ī�װ� ���
   * @param
   * @return ���ڵ� ���
   */
  public List<CateVO> list_cateno_asc();
  
  /**
   * ��� ��ȣ ������ ��������
   * @param
   * @return ���ڵ� ���
   */
  public List<CateVO> list_seqno_asc();
  
  /**
   * ī�װ� �׷쿡 ���Ե� ī�װ� ���
   * @param cg_no
   * @return ���ڵ� ���
   */
  public List<CateVO> list_cgno_cate(int cg_no);
  
  /**
   * ���� VO ��� join
   * @return
   */
  public List<Categrp_Cate_join> list_join();
  
  /**
   * ���� VO ��� join
   * @return
   */
  public List<Categrp_Cate_join> list_join_by_cgno(int cg_no); 
  
  /**
   * ��ȸ
   * @param cateno
   * @return ���ڵ� ��ȸ
   */
  public CateVO read(int cateno);
  
  /**
   * ����
   * @param cateVO
   * @return ������ ���ڵ� ����
   */
  public int update(CateVO cateVO);
  
  /**
   * ����
   * @param cateno
   * @return ������ ���ڵ� ����
   */
  public int delete(int cateno);
  
  /**
   * ��� ���� ����
   * @param cateno
   * @return ������ ���ڵ� ����
   */
  public int update_seqno_up(int cateno);
  
  /**
   * ��� ���� ����
   * @param cateno
   * @return ������ ���ڵ� ����
   */
  public int update_seqno_down(int cateno);
  
  /**
   * ��� ��� ����
   * @param cateVO
   * @return ������ ���ڵ� ����
   */
  public int update_visible(CateVO cateVO);
  
  
  
}
