package dev.mvc.ebook.attachfile;

import java.util.List;

public interface AttachfileProcInter {

  /**
   * ���
   * 
   * @param attachfileVO
   * @return
   */
  public int create(AttachfileVO attachfileVO);

  /**
   * ��ü �̹��� ���
   * 
   * @return
   */
  public List<AttachfileVO> list();

  /**
   * ��ȸ
   * 
   * @param attachfileno
   * @return
   */
  public AttachfileVO read(int attachfileno);

  /**
   * FK eb_no�� ���� ���� ���
   * 
   * @param eb_no
   * @return
   */
  public List<AttachfileVO> list_by_ebno(int eb_no);

  /**
   * ����
   * 
   * @param attachfileno
   * @return
   */
  public int delete(int attachfileno);

  /**
   * �θ�Ű�� ������ ���� ����
   * 
   * @param eb_no
   * @return
   */
  public int count_by_ebno(int eb_no);

  /**
   * FK �θ�Ű�� �̿��� ��� ���ڵ� ����
   * 
   * @param eb_no
   * @return
   */
  public int delete_by_ebno(int eb_no);

}