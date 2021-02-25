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
      페이지에서 출력할 시작 레코드 번호 계산 기준값, nowPage는 1부터 시작
    1 페이지 시작 rownum: nowPage = 1, (1 - 1) * 10 --> 0 
    2 페이지 시작 rownum: nowPage = 2, (2 - 1) * 10 --> 10
    3 페이지 시작 rownum: nowPage = 3, (3 - 1) * 10 --> 20
    */
    int beginOfPage = ((Integer)map.get("nowPage") - 1) * Ebook.RECORD_PER_PAGE;
   
    // 시작 rownum 결정
    // 1 페이지 = 0 + 1, 2 페이지 = 10 + 1, 3 페이지 = 20 + 1 
    int startNum = beginOfPage + 1; 
    //  종료 rownum
    // 1 페이지 = 0 + 10, 2 페이지 = 0 + 20, 3 페이지 = 0 + 30
    int endNum = beginOfPage + Ebook.RECORD_PER_PAGE;   
    /*
    1 페이지: WHERE r >= 1 AND r <= 10
    2 페이지: WHERE r >= 11 AND r <= 20
    3 페이지: WHERE r >= 21 AND r <= 30
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
      페이지에서 출력할 시작 레코드 번호 계산 기준값, nowPage는 1부터 시작
    1 페이지 시작 rownum: nowPage = 1, (1 - 1) * 10 --> 0 
    2 페이지 시작 rownum: nowPage = 2, (2 - 1) * 10 --> 10
    3 페이지 시작 rownum: nowPage = 3, (3 - 1) * 10 --> 20
    */
    int beginOfPage = ((Integer)map.get("nowPage") - 1) * Ebook.RECORD_PER_PAGE;
   
    // 시작 rownum 결정
    // 1 페이지 = 0 + 1, 2 페이지 = 10 + 1, 3 페이지 = 20 + 1 
    int startNum = beginOfPage + 1; 
    //  종료 rownum
    // 1 페이지 = 0 + 10, 2 페이지 = 0 + 20, 3 페이지 = 0 + 30
    int endNum = beginOfPage + Ebook.RECORD_PER_PAGE;   
    /*
    1 페이지: WHERE r >= 1 AND r <= 10
    2 페이지: WHERE r >= 11 AND r <= 20
    3 페이지: WHERE r >= 21 AND r <= 30
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
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param listFile 목록 파일명 
   * @param categrpno 카테고리번호 
   * @param search_count 검색(전체) 레코드수 
   * @param nowPage     현재 페이지
   * @param word 검색어
   * @return 페이징 생성 문자열
   */ 
  @Override
  public String pagingBox(String listFile, int cateno, int search_count, int nowPage, String word, int type){ 
    int totalPage = (int)(Math.ceil((double)search_count/Ebook.RECORD_PER_PAGE)); // 전체 페이지  
    int totalGrp = (int)(Math.ceil((double)totalPage/Ebook.PAGE_PER_BLOCK));// 전체 그룹 
    int nowGrp = (int)(Math.ceil((double)nowPage/Ebook.PAGE_PER_BLOCK));    // 현재 그룹 
    int startPage = ((nowGrp - 1) * Ebook.PAGE_PER_BLOCK) + 1; // 특정 그룹의 페이지 목록 시작  
    int endPage = (nowGrp * Ebook.PAGE_PER_BLOCK);             // 특정 그룹의 페이지 목록 종료   
     
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
    str.append("<ul class='pagination justify-content-center'>"); 
//    str.append("현재 페이지: " + nowPage + " / " + totalPage + "  "); 
 
    // 이전 10개 페이지로 이동
    // nowGrp: 1 (1 ~ 10 page)
    // nowGrp: 2 (11 ~ 20 page)
    // nowGrp: 3 (21 ~ 30 page) 
    // 현재 2그룹일 경우: (2 - 1) * 10 = 1그룹의 마지막 페이지 10
    // 현재 3그룹일 경우: (3 - 1) * 10 = 2그룹의 마지막 페이지 20
    int _nowPage = (nowGrp-1) * Ebook.PAGE_PER_BLOCK;  
    if (nowGrp >= 2){ 
      str.append("<li class='page-item'><A class='page-link' href='"+listFile+"?&word="+word+"&nowPage="+_nowPage+"&cateno="+cateno+"&type="+type+"'>이전</A></span>"); 
    } 
 
    // 중앙의 페이지 목록
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ // 마지막 페이지를 넘어갔다면 페이 출력 종료
        break; 
      } 
  
      if (nowPage == i){ // 페이지가 현재페이지와 같다면 CSS 강조(차별을 둠)
        str.append("<li class='page-item active'><a class='page-link' href='#'>"+i+"</a></li>"); // 현재 페이지, 강조 
      }else{
        // 현재 페이지가 아닌 페이지는 이동이 가능하도록 링크를 설정
        str.append("<li class='page-item'><A class='page-link' href='"+listFile+"?word="+word+"&nowPage="+i+"&cateno="+cateno+"&type="+type+"'>"+i+"</A></span>");   
      } 
    } 
 
    // 10개 다음 페이지로 이동
    // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page) 
    // 현재 1그룹일 경우: (1 * 10) + 1 = 2그룹의 시작페이지 11
    // 현재 2그룹일 경우: (2 * 10) + 1 = 3그룹의 시작페이지 21
    _nowPage = (nowGrp * Ebook.PAGE_PER_BLOCK)+1;  
    if (nowGrp < totalGrp){ 
      str.append("<li class='page-item'><A href='"+listFile+"?&word="+word+"&nowPage="+_nowPage+"&cateno="+cateno+"&type="+type+"'>다음</A></span>"); 
    } 
    str.append("</ul>"); 
     
    return str.toString(); 
  }

  


}
