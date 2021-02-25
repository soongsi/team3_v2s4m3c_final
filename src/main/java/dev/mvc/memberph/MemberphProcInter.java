package dev.mvc.memberph;

import java.util.List;


public interface MemberphProcInter {
  
  /**
   * ���
   * @return ó���� ���ڵ� ����
   */ 
  public int create (MemberphVO memberphVO);
  
  /**
   * ���
   * @return ���ڵ� ���
   */
  public List<MemberphVO> list_orderno_asc();
  
  /**
   * ��ȸ
   * @param contentsno
   * @return
   */
  public MemberphVO read(int orderno);

  /**
   * ������ ��ȸ
   * @param m_no
   * @return
   */
  public MemberphVO read_update(int orderno);
  
  /**
   * ����
   * @param memberVO
   * @return ó���� ���ڵ� ����
   */
  public int update(MemberphVO memberphVO);
  
  
  public int delete(int orderno);
  
  /**
   * �̹��� ���
   * @param memberphVO
   * @return
   */
  public int img_create(MemberphVO memberphVO);
  
  /**
   * �̹��� ����
   * @param memberphVO
   * @return
   */
  public int img_update(MemberphVO memberphVO);
  
  /**
   * �̹��� ����
   * @param memberphVO
   * @return
   */
  public int img_delete(MemberphVO memberphVO);
}
