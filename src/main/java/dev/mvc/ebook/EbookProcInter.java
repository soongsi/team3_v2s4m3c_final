package dev.mvc.ebook;

import java.util.HashMap;
import java.util.List;

public interface EbookProcInter {
  
  /**
   * ���
   * @param ebookVO
   * @return ��ϵ� ���ڵ� ��
   */
  public int create(EbookVO ebookVO);
  
  /**
   * �̺� ��ü ���
   * @return
   */
  public List<EbookVO> list_ebno_desc();
  
  /**
   * �̺� ��ü ��� + ����¡
   * @return
   */
  public List<EbookVO> list_ebno_desc_paging(HashMap<String, Object> map);
  
  /**
   * ī�װ����� ��ϵ� �̺� ���
   * @param cateno
   * @return
   */
  public List<EbookVO> list_ebook_category(int cateno);
  
  /**
   * �ֱ� ��ϵ� �̺� 10��
   * @return
   */
  public List<EbookVO> list_ebno_desc_latest();
  
  /**
   * �˻� + ����¡ ���
   * @param map
   * @return
   */
  public List<EbookVO> list_by_cateno_search_paging(HashMap<String, Object> map);
  
  /**
   * ī�װ����� �˻� ���ڵ� ����
   * @param hashMap
   * @return
   */
  public int search_count(HashMap<String, Object> hashMap);
  
  /**
   * ������ ��� ���ڿ� ����, Box ����
   * @param listFile ��� ���ϸ� 
   * @param categrpno ī�װ�����ȣ
   * @param search_count �˻� ����
   * @param nowPage ���� ������, nowPage�� 1���� ����
   * @param word �˻���
   * @return
   */
  public String pagingBox(String listFile, int categrpno, int search_count, int nowPage, String word, int type);
  
  /**
   * ��ȸ
   * @param eb_no
   * @return
   */
  public EbookVO read(int eb_no);
  
  /**
   * ����
   * @param ebookVO
   * @return
   */
  public int update(EbookVO ebookVO);
  
  /**
   * ����
   * @param eb_no
   * @return
   */
  public int delete(int eb_no);
}