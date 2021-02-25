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
    페이지에서 출력할 시작 레코드 번호 계산 기준값, nowPage는 1부터 시작
    1 페이지 시작 rownum: nowPage = 1, (1 - 1) * 10 --> 0 
    2 페이지 시작 rownum: nowPage = 2, (2 - 1) * 10 --> 10
    3 페이지 시작 rownum: nowPage = 3, (3 - 1) * 10 --> 20
    */
    int beginOfPage = ((Integer)map.get("nowPage") - 1) * Customer.RECORD_PER_PAGE;
   
    // 시작 rownum 결정
    // 1 페이지 = 0 + 1, 2 페이지 = 10 + 1, 3 페이지 = 20 + 1 
    int startNum = beginOfPage + 1;
    
    //  종료 rownum
    // 1 페이지 = 0 + 10, 2 페이지 = 0 + 20, 3 페이지 = 0 + 30
    int endNum = beginOfPage + Customer.RECORD_PER_PAGE;   
    /*
    1 페이지: WHERE r >= 1 AND r <= 10
    2 페이지: WHERE r >= 11 AND r <= 20
    3 페이지: WHERE r >= 21 AND r <= 30
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
    int totalPage = (int)(Math.ceil((double)search_count/Customer.RECORD_PER_PAGE)); // 전체 페이지  
    
    int totalGrp = (int)(Math.ceil((double)totalPage/Customer.PAGE_PER_BLOCK));// 전체 그룹 
    
    int nowGrp = (int)(Math.ceil((double)nowPage/Customer.PAGE_PER_BLOCK));    // 현재 그룹 
    
    int startPage = ((nowGrp - 1) * Customer.PAGE_PER_BLOCK) + 1; // 특정 그룹의 페이지 목록 시작  
    
    int endPage = (nowGrp * Customer.PAGE_PER_BLOCK);             // 특정 그룹의 페이지 목록 종료   
     
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
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("  }"); 
    str.append("  .span_box_2{"); 
    str.append("    text-align: center;");    
    str.append("    background-color: #668db4;"); 
    str.append("    color: #FFFFFF;"); 
    str.append("    font-size: 1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #cccccc;"); 
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/"); 
    str.append("  }"); 
    str.append("</style>"); 
    str.append("<DIV id='paging'>"); 
//    str.append("현재 페이지: " + nowPage + " / " + totalPage + "  "); 
 
    // 이전 10개 페이지로 이동
    // nowGrp: 1 (1 ~ 10 page)
    // nowGrp: 2 (11 ~ 20 page)
    // nowGrp: 3 (21 ~ 30 page) 
    // 현재 2그룹일 경우: (2 - 1) * 10 = 1그룹의 마지막 페이지 10
    // 현재 3그룹일 경우: (3 - 1) * 10 = 2그룹의 마지막 페이지 20
    int _nowPage = (nowGrp-1) * Customer.PAGE_PER_BLOCK;  
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='"+listFile+"?nowPage="+_nowPage+"&m_no="+m_no+"'>이전</A></span>"); 
    } 
 
    // 중앙의 페이지 목록
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ // 마지막 페이지를 넘어갔다면 페이 출력 종료
        break; 
      } 
  
      if (nowPage == i){ // 페이지가 현재페이지와 같다면 CSS 강조(차별을 둠)
        str.append("<span class='span_box_2'>"+i+"</span>"); // 현재 페이지, 강조 
      }else{
        // 현재 페이지가 아닌 페이지는 이동이 가능하도록 링크를 설정
        str.append("<span class='span_box_1'><A href='"+listFile+"?nowPage="+i+"&m_no="+m_no+"'>"+i+"</A></span>");   
      } 
    } 
 
    // 10개 다음 페이지로 이동
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // 현재 1그룹일 경우: (1 * 10) + 1 = 2그룹의 시작페이지 11
    // 현재 2그룹일 경우: (2 * 10) + 1 = 3그룹의 시작페이지 21
    _nowPage = (nowGrp * Customer.PAGE_PER_BLOCK)+1;  
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='"+listFile+"?nowPage="+_nowPage+"&m_no="+m_no+"'>다음</A></span>"); 
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
    
    cs_title = Tool.convertChar(cs_title);   // 특수 문자 처리
    cs_contents = Tool.convertChar(cs_contents); // 특수 문자 처리
    
    customerVO.setCs_title(cs_title);
    customerVO.setCs_contents(cs_contents);
    
    /*
     * if(customerVO.getCs_type().equals("A01")) { customerVO.setCs_type("상품문의"); }
     * else if(customerVO.getCs_type().equals("A02")) {
     * customerVO.setCs_type("결제장애"); } else
     * if(customerVO.getCs_type().equals("A03")) { customerVO.setCs_type("환불"); }
     * else if(customerVO.getCs_type().equals("A04")) {
     * customerVO.setCs_type("개선사항"); } else
     * if(customerVO.getCs_type().equals("A99")) { customerVO.setCs_type("기타"); }
     */
    
    return customerVO;
  }
  
  @Override
  public Member_Customer_join read(int cs_no) {
    Member_Customer_join member_Customer_join = this.customerDAO.read(cs_no);
    
    String cs_title= member_Customer_join.getCs_title();
    String cs_contents = member_Customer_join.getCs_contents();
    
    cs_title = Tool.convertChar(cs_title);   // 특수 문자 처리
    cs_contents = Tool.convertChar(cs_contents); // 특수 문자 처리
    
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
    int cnt = this.customerDAO.update_img(customerVO);   // 이미지 등록
    return cnt;
  }

  @Override     
  public int img_update(Member_Customer_join customerVO) {
    int cnt = this.customerDAO.update_img(customerVO);  // 이미지 수정
    return cnt;
  }

  @Override     
  public int img_delete(Member_Customer_join customerVO) {
    int cnt = this.customerDAO.update_img(customerVO);   // 이미지 삭제
    return cnt;
  }











  




  

}
