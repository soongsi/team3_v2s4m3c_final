<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />

  <DIV class="title_line">
    1:1 문의 내역
  </DIV>
  
  <ASIDE class="aside_left">
    <A href="../member/list_all.do">고객 리스트</A> >
     ${customerVO.m_no}님의 문의 삭제
  </ASIDE>
  <ASIDE class="aside_right">
    <A href="javascript:location.reload();">새로고침</A>
<%--     <span class='menu_divide' >│</span>
    <A href="./list_by_mno_grid1.do?m_no=${customerVO.m_no}">GRID GALLERY</A>     --%>
  </ASIDE>
  
  <div class='menu_line'></div>
   
<DIV class='message'>
  <fieldset class='fieldset_basic'>
    <UL>
      <c:choose>
        <c:when test="${param.passwd_cnt==1}"> <!-- 패스워드 일치 -->
          <c:choose>
            <c:when test="${param.cnt == 1}"> <!-- 글 삭제 성공 -->
              <LI class='li_none'>
                <span class='span_success'>고객번호${param.m_no}번님의 ${param.title} 글을 삭제했습니다.</span>
              </LI>
            </c:when>
            <c:otherwise>    <!-- 글 삭제 실패 -->
              <LI class='li_none'>
                <span class='span_fail'>고객번호${param.m_no}번님의 ${param.title} 글 삭제에 실패했습니다.</span>
              </LI>
              <LI class='li_none'>
                <span class='span_fail'>다시 시도해주세요.</span>
              </LI>
            </c:otherwise>
          </c:choose>
        </c:when>
        <c:otherwise> <!-- 패스워드 불일치 -->
          <LI class='li_none'>
            <span class='span_fail'>패스워드가 일치하지 않습니다. 다시 시도해주세요.</span>
          </LI>
        </c:otherwise>
      </c:choose>
      
      <c:choose>
        <c:when test="${param.cnt == 1 && param.passwd_cnt == 1}">
          <LI class='li_none'>
            <button type='button' 
                        onclick="location.href='./list_all.do'"
                        class="btn btn-info content_bottom_button">전체 목록</button>                        
          </LI>
        </c:when>
        <c:otherwise>
          <LI class='li_none'>
            <button type='button' 
                        onclick="history.back();"
                        class="btn btn-info">재시도</button>
            <button type='button' 
                        onclick="location.href='./list_all.do'"
                        class="btn btn-info content_bottom_button">목록</button>                        
          </LI>
        </c:otherwise> 
      </c:choose>
      
     </UL>
  </fieldset>
 
</DIV>
 
<jsp:include page="/adm/menu/bottom.jsp" flush='false' /> 