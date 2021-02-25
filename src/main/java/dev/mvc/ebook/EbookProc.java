package dev.mvc.ebook;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Tool;


@Component("dev.mvc.ebook.EbookProc")
public class EbookProc implements EbookProcInter {
  
  @Autowired
  private EbookDAOInter ebookDAO;
  
  public EbookProc() {
    System.out.println( "--> EbookProc created." );
  }

  @Override
  public int create(EbookVO ebookVO) {
    int cnt = 0;
    cnt = this.ebookDAO.create(ebookVO);
    
    return cnt;
  }

  @Override
  public List<EbookVO> list_ebno_desc() {
    List<EbookVO> list = this.ebookDAO.list_ebno_desc();
    
    return list;
  }
  
  @Override
  public List<EbookVO> list_ebno_desc_latest() {
    List<EbookVO> list = this.ebookDAO.list_ebno_desc_latest();
    
    return list;
  }
  
  @Override
  public List<EbookVO> list_ebno_desc_paging(HashMap<String, Object> map) {
    /* 
      ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
    1 ������ ���� rownum: nowPage = 1, (1 - 1) * 10 --> 0 
    2 ������ ���� rownum: nowPage = 2, (2 - 1) * 10 --> 10
    3 ������ ���� rownum: nowPage = 3, (3 - 1) * 10 --> 20
    */
    int beginOfPage = ((Integer)map.get("nowPage") - 1) * Ebook.RECORD_PER_PAGE;
   
    // ���� rownum ����
    // 1 ������ = 0 + 1, 2 ������ = 10 + 1, 3 ������ = 20 + 1 
    int startNum = beginOfPage + 1; 
    //  ���� rownum
    // 1 ������ = 0 + 10, 2 ������ = 0 + 20, 3 ������ = 0 + 30
    int endNum = beginOfPage + Ebook.RECORD_PER_PAGE;   
    /*
    1 ������: WHERE r >= 1 AND r <= 10
    2 ������: WHERE r >= 11 AND r <= 20
    3 ������: WHERE r >= 21 AND r <= 30
    */
    map.put("startNum", startNum);
    map.put("endNum", endNum);
    
    List<EbookVO> list = this.ebookDAO.list_ebno_desc_paging(map);
    
    return list;
  }
  
  @Override
  public List<EbookVO> list_ebook_category(int cateno) {
    List<EbookVO> list = this.ebookDAO.list_ebook_category(cateno);
    
    return list;
  }

  @Override
  public EbookVO read(int eb_no) {
    EbookVO ebookVO = null;
    ebookVO = this.ebookDAO.read(eb_no);
    
    String useinfo = ebookVO.getEb_useinfo();
    useinfo = Tool.convertChar(useinfo);
    
    ebookVO.setEb_useinfo(useinfo);
    
    return ebookVO;
  }

  @Override
  public int update(EbookVO ebookVO) {
    int cnt = 0;
    cnt = this.ebookDAO.update(ebookVO);
    
    return cnt;
  }

  @Override
  public int delete(int eb_no) {
    int cnt = 0;
    cnt = this.ebookDAO.delete(eb_no);
    
    return cnt;
  }

  @Override
  public List<EbookVO> list_by_cateno_search_paging(HashMap<String, Object> map) {
    /* 
      ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
    1 ������ ���� rownum: nowPage = 1, (1 - 1) * 10 --> 0 
    2 ������ ���� rownum: nowPage = 2, (2 - 1) * 10 --> 10
    3 ������ ���� rownum: nowPage = 3, (3 - 1) * 10 --> 20
    */
    int beginOfPage = ((Integer)map.get("nowPage") - 1) * Ebook.RECORD_PER_PAGE;
   
    // ���� rownum ����
    // 1 ������ = 0 + 1, 2 ������ = 10 + 1, 3 ������ = 20 + 1 
    int startNum = beginOfPage + 1; 
    //  ���� rownum
    // 1 ������ = 0 + 10, 2 ������ = 0 + 20, 3 ������ = 0 + 30
    int endNum = beginOfPage + Ebook.RECORD_PER_PAGE;   
    /*
    1 ������: WHERE r >= 1 AND r <= 10
    2 ������: WHERE r >= 11 AND r <= 20
    3 ������: WHERE r >= 21 AND r <= 30
    */
    map.put("startNum", startNum);
    map.put("endNum", endNum);
   
    List<EbookVO> list = this.ebookDAO.list_by_cateno_search_paging(map);
    
    return list;
  }
  
  @Override
  public int search_count(HashMap<String, Object> hashMap) {
    int cnt = ebookDAO.search_count(hashMap);
    return cnt;
  }
  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param listFile ��� ���ϸ� 
   * @param categrpno ī�װ���ȣ 
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  @Override
  public String pagingBox(String listFile, int cateno, int search_count, int nowPage, String word, int type){ 
    int totalPage = (int)(Math.ceil((double)search_count/Ebook.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Ebook.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Ebook.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Ebook.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Ebook.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
    StringBuffer str = new StringBuffer(); 
     
    str.append("<style type='text/css'>"); 
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}"); 
    str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}"); 
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}"); 
    str.append("  .span_box_1{"); 
    str.append("    text-align: center;");    
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("  .span_box_2{"); 
    str.append("    text-align: center;");    
    str.append("    background-color: #668db4;"); 
    str.append("    color: #FFFFFF;"); 
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("</style>"); 
    str.append("<ul class='pagination justify-content-center'>"); 
//    str.append("���� ������: " + nowPage + " / " + totalPage + "  "); 
 
    // ���� 10�� �������� �̵�
    // nowGrp: 1 (1 ~ 10 page)
    // nowGrp: 2 (11 ~ 20 page)
    // nowGrp: 3 (21 ~ 30 page) 
    // ���� 2�׷��� ���: (2 - 1) * 10 = 1�׷��� ������ ������ 10
    // ���� 3�׷��� ���: (3 - 1) * 10 = 2�׷��� ������ ������ 20
    int _nowPage = (nowGrp-1) * Ebook.PAGE_PER_BLOCK;  
    if (nowGrp >= 2){ 
      str.append("<li class='page-item'><A class='page-link' href='"+listFile+"?&word="+word+"&nowPage="+_nowPage+"&cateno="+cateno+"&type="+type+"'>����</A></span>"); 
    } 
 
    // �߾��� ������ ���
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ // ������ �������� �Ѿ�ٸ� ���� ��� ����
        break; 
      } 
  
      if (nowPage == i){ // �������� ������������ ���ٸ� CSS ����(������ ��)
        str.append("<li class='page-item active'><a class='page-link' href='#'>"+i+"</a></li>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� �������� �̵��� �����ϵ��� ��ũ�� ����
        str.append("<li class='page-item'><A class='page-link' href='"+listFile+"?word="+word+"&nowPage="+i+"&cateno="+cateno+"&type="+type+"'>"+i+"</A></span>");   
      } 
    } 
 
    // 10�� ���� �������� �̵�
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // ���� 1�׷��� ���: (1 * 10) + 1 = 2�׷��� ���������� 11
    // ���� 2�׷��� ���: (2 * 10) + 1 = 3�׷��� ���������� 21
    _nowPage = (nowGrp * Ebook.PAGE_PER_BLOCK)+1;  
    if (nowGrp < totalGrp){ 
      str.append("<li class='page-item'><A href='"+listFile+"?&word="+word+"&nowPage="+_nowPage+"&cateno="+cateno+"&type="+type+"'>����</A></span>"); 
    } 
    str.append("</ul>"); 
     
    return str.toString(); 
  }

  


}
