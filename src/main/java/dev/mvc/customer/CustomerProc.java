package dev.mvc.customer;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Tool;


@Component("dev.mvc.customer.CustomerProc")
public class CustomerProc implements CustomerProcInter {

  @Autowired
  private CustomerDAOInter customerDAO;
  
  public CustomerProc() {
    System.out.println("--> CustomerProc created");
  }
  
  @Override
  public int create(CustomerVO customerVO) {
    int cnt = this.customerDAO.create(customerVO);
    return cnt;
  }
  
  @Override  
  public List<CustomerVO> list_all() {
    List<CustomerVO> list = this.customerDAO.list_all();
    return list;
  }
  
  @Override  
  public List<CustomerVO> list_by_mno(int m_no) {
    List<CustomerVO> list = this.customerDAO.list_by_mno(m_no);
    return list;
  }
  
  @Override 
  public  List<CustomerVO> list_by_mno_paging(HashMap<String, Object> map) {
    /* 
    ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
    1 ������ ���� rownum: nowPage = 1, (1 - 1) * 10 --> 0 
    2 ������ ���� rownum: nowPage = 2, (2 - 1) * 10 --> 10
    3 ������ ���� rownum: nowPage = 3, (3 - 1) * 10 --> 20
    */
    int beginOfPage = ((Integer)map.get("nowPage") - 1) * Customer.RECORD_PER_PAGE;
   
    // ���� rownum ����
    // 1 ������ = 0 + 1, 2 ������ = 10 + 1, 3 ������ = 20 + 1 
    int startNum = beginOfPage + 1;
    
    //  ���� rownum
    // 1 ������ = 0 + 10, 2 ������ = 0 + 20, 3 ������ = 0 + 30
    int endNum = beginOfPage + Customer.RECORD_PER_PAGE;   
    /*
    1 ������: WHERE r >= 1 AND r <= 10
    2 ������: WHERE r >= 11 AND r <= 20
    3 ������: WHERE r >= 21 AND r <= 30
    */
    map.put("startNum", startNum);
    map.put("endNum", endNum);
   
    List<CustomerVO> list = this.customerDAO.list_by_mno_paging(map);
    
    return list;
    
  }
  
  @Override 
  public int search_count(int m_no) {
    int count = this.customerDAO.search_count(m_no);
    return count;
  }
  
  @Override 
  public String pagingBox(String listFile, int m_no, int search_count, int nowPage) {
    int totalPage = (int)(Math.ceil((double)search_count/Customer.RECORD_PER_PAGE)); // ��ü ������  
    
    int totalGrp = (int)(Math.ceil((double)totalPage/Customer.PAGE_PER_BLOCK));// ��ü �׷� 
    
    int nowGrp = (int)(Math.ceil((double)nowPage/Customer.PAGE_PER_BLOCK));    // ���� �׷� 
    
    int startPage = ((nowGrp - 1) * Customer.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    
    int endPage = (nowGrp * Customer.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
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
    str.append("<DIV id='paging'>"); 
//    str.append("���� ������: " + nowPage + " / " + totalPage + "  "); 
 
    // ���� 10�� �������� �̵�
    // nowGrp: 1 (1 ~ 10 page)
    // nowGrp: 2 (11 ~ 20 page)
    // nowGrp: 3 (21 ~ 30 page) 
    // ���� 2�׷��� ���: (2 - 1) * 10 = 1�׷��� ������ ������ 10
    // ���� 3�׷��� ���: (3 - 1) * 10 = 2�׷��� ������ ������ 20
    int _nowPage = (nowGrp-1) * Customer.PAGE_PER_BLOCK;  
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='"+listFile+"?nowPage="+_nowPage+"&m_no="+m_no+"'>����</A></span>"); 
    } 
 
    // �߾��� ������ ���
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ // ������ �������� �Ѿ�ٸ� ���� ��� ����
        break; 
      } 
  
      if (nowPage == i){ // �������� ������������ ���ٸ� CSS ����(������ ��)
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� �������� �̵��� �����ϵ��� ��ũ�� ����
        str.append("<span class='span_box_1'><A href='"+listFile+"?nowPage="+i+"&m_no="+m_no+"'>"+i+"</A></span>");   
      } 
    } 
 
    // 10�� ���� �������� �̵�
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // ���� 1�׷��� ���: (1 * 10) + 1 = 2�׷��� ���������� 11
    // ���� 2�׷��� ���: (2 * 10) + 1 = 3�׷��� ���������� 21
    _nowPage = (nowGrp * Customer.PAGE_PER_BLOCK)+1;  
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='"+listFile+"?nowPage="+_nowPage+"&m_no="+m_no+"'>����</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
    
  }
  
  @Override
  public List<Member_Customer_join> list_join() {
    List<Member_Customer_join> list = this.customerDAO.list_join();
    return list;
  }
  
  @Override
  public List<Member_Customer_join> list_my_inquiry(int m_no) {
    List<Member_Customer_join> list = this.customerDAO.list_my_inquiry(m_no);
    return list;
  }
  
  @Override
  public CustomerVO read_img(int cs_no) {
    CustomerVO customerVO = this.customerDAO.read_img(cs_no);
    
    String cs_title= customerVO.getCs_title();
    String cs_contents = customerVO.getCs_contents();
    
    cs_title = Tool.convertChar(cs_title);   // Ư�� ���� ó��
    cs_contents = Tool.convertChar(cs_contents); // Ư�� ���� ó��
    
    customerVO.setCs_title(cs_title);
    customerVO.setCs_contents(cs_contents);
    
    /*
     * if(customerVO.getCs_type().equals("A01")) { customerVO.setCs_type("��ǰ����"); }
     * else if(customerVO.getCs_type().equals("A02")) {
     * customerVO.setCs_type("�������"); } else
     * if(customerVO.getCs_type().equals("A03")) { customerVO.setCs_type("ȯ��"); }
     * else if(customerVO.getCs_type().equals("A04")) {
     * customerVO.setCs_type("��������"); } else
     * if(customerVO.getCs_type().equals("A99")) { customerVO.setCs_type("��Ÿ"); }
     */
    
    return customerVO;
  }
  
  @Override
  public Member_Customer_join read(int cs_no) {
    Member_Customer_join member_Customer_join = this.customerDAO.read(cs_no);
    
    String cs_title= member_Customer_join.getCs_title();
    String cs_contents = member_Customer_join.getCs_contents();
    
    cs_title = Tool.convertChar(cs_title);   // Ư�� ���� ó��
    cs_contents = Tool.convertChar(cs_contents); // Ư�� ���� ó��
    
    member_Customer_join.setCs_title(cs_title);
    member_Customer_join.setCs_contents(cs_contents);
    
    return member_Customer_join;
  }
  
  @Override
  public int update(Member_Customer_join member_Customer_join) {
    int cnt = this.customerDAO.update(member_Customer_join);
    return cnt;
  } 
  
  @Override
  public int passwd_check(HashMap<String,Object> hashMap) {
    int passwd_cnt = this.customerDAO.passwd_check(hashMap);
    return passwd_cnt;
  }
  
  @Override
  public Member_Customer_join read_delete(int cs_no) {
    Member_Customer_join customerVO = this.customerDAO.read(cs_no);
    return customerVO;
  }

  
  @Override
  public int delete(int cs_no) {
    int cnt = this.customerDAO.delete(cs_no);
    return cnt;
  }
  
  @Override      
  public int img_create(Member_Customer_join customerVO) {
    int cnt = this.customerDAO.update_img(customerVO);   // �̹��� ���
    return cnt;
  }

  @Override     
  public int img_update(Member_Customer_join customerVO) {
    int cnt = this.customerDAO.update_img(customerVO);  // �̹��� ����
    return cnt;
  }

  @Override     
  public int img_delete(Member_Customer_join customerVO) {
    int cnt = this.customerDAO.update_img(customerVO);   // �̹��� ����
    return cnt;
  }











  




  

}
