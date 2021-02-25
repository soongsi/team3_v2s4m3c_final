package dev.mvc.customer;

import java.util.HashMap;
import java.util.List;


public interface CustomerDAOInter {

  /**
   * ���
   * @param customerVO
   * @return ��ϵ� ���ڵ� ��
   */
  public int create(CustomerVO customerVO);
  
  /**
   * ��ü ���� ��� 
   * @return
   */
  public  List<CustomerVO> list_all();
  
  /**
   * ȸ����ȣ �� ���� ��� 
   * @return
   */
  public  List<CustomerVO> list_by_mno(int m_no);
  
  /**
   * Paging + ȸ����ȣ �� ���� ��� 
   * @return
   */
  public  List<CustomerVO> list_by_mno_paging(HashMap<String, Object> map);
  
  /**
   * ȸ����ȣ�� ��ü ���ڵ� ����
   * @param m_no
   * @return
   */
  public int search_count(int m_no);
  
  /**
   * ���� VO ��� join
   * @param mno
   * @return
   */
  public List<Member_Customer_join> list_join();
  
  /**
   * ���ڵ� �� �� ��ȸ
   * @param cs_no
   * @return
   */
  public CustomerVO read_img(int cs_no);
  
  /**
   * ���� VO ���ڵ� �� �� ��ȸ
   * @param cs_no
   * @return
   */
  public Member_Customer_join read(int cs_no);
  
  /**
   * ���� ���ǳ��� ��ȸ
   * @param cs_no
   * @return
   */
  public List<Member_Customer_join> list_my_inquiry(int m_no);

  /**
   * ����(ȸ����)
   * @param member_Customer_join
   * @return
   */
  public int update(Member_Customer_join member_Customer_join);
  
  /**
   * �н����� ��ġ �˻�(ȸ����)
   * @param hashMap
   * @return
   */
  public int passwd_check(HashMap<String,Object> hashMap);
  
  /**
   * ���ڵ� �� �� ����
   * @param cs_no
   * @return
   */
  public int delete(int cs_no);
 
  /**
   *  ���� �̹��� ����
   * @param customerVO
   * @return
   */
  public int update_img(Member_Customer_join customerVO);
  

  

  
  
}
