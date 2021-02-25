package dev.mvc.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface MemberProcInter {
  /**
   * �ߺ� ���̵� �˻�
   * @param id
   * @return �ߺ� ���̵� ����
   */
  public int checkID(String id);

  /**
   * ȸ�� ����
   * @param memberVO
   * @return
   */
  public int create(MemberVO memberVO);
  
  /**
   * ���
   * @return
   */
  public List<MemberVO> list();
  
  /**
   * ȸ�� ��ȸ
   * @param m_no
   * @return
   */
  public MemberVO read(int m_no);
  
  /**
   * ȸ�� ���� ��ȸ
   * @param id
   * @return
   */
  public MemberVO readById(String id);
  
  /**
   * ���� ó��
   * @param memberVO
   * @return
   */
  public int update(MemberVO memberVO);
  
  /**
   * ȸ�� ���� ó��
   * @param m_no
   * @return
   */
  public int delete(int m_no);
  
  /**
   * ���� �н����� �˻�
   * @param map
   * @return 0: ��ġ���� ����, 1: ��ġ��
   */
  public int passwd_check(HashMap<String, Object> map);
  
  /**
   * �н����� ����
   * @param map
   * @return ����� �н����� ����
   */
  public int passwd_update(HashMap<String, Object> map);
  
  /**
   * �α��� 
   * @param map
   * @return
   */
  public int login(Map<String, Object> map);
  
  /**
   * �α��ε� ȸ�� �������� �˻��մϴ�.
   * @param session
   * @return true: ������
   */
  public boolean isMember(HttpSession session);  
  
}