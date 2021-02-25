<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />
 
  <DIV class="title_line">
    1:1 문의 내역
  </DIV>
  
  <ASIDE class="aside_left">
    <A href="../member/list_all.do">고객 리스트</A> >
    <A href="./list_all.do"> 모든 1:1 문의</A>
  </ASIDE>
  <ASIDE class="aside_right">
    <A href="./create.do">문의하기</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list_by_mno_grid1.do?m_no=${customerVO.m_no}">GRID GALLERY</A>    
  </ASIDE>
  
  <DIV class='menu_line'></DIV>  
 
<DIV class='message'>
<input type='hidden' name='cs_type' id='cs_type' value="${param.cs_type}">
<input type='hidden' name='m_no' id='m_no' value="${param.m_no}">
  <fieldset class='fieldset_basic'>
    <UL>
      <c:choose>
        <c:when test="${param.cnt == 1 }">
          <LI class='li_none'>
            <span class='span_success'>1:1 문의가 접수 되었습니다.</span>
          </LI>
        </c:when>
        <c:otherwise>
          <LI class='li_none'>
            <span class='span_fail'>1:1 문의 등록에 실패하였습니다.</span>
          </LI>
        </c:otherwise>
      </c:choose>
      <LI class='li_none'>
        <br>
        <c:choose>
          <c:when test="${param.cnt == 1 }">
            <button type='button' 
                         onclick="location.href='./create.do?m_no=${param.m_no}'"
                         class="btn" style="border: 1px solid #ccc;">1:1 문의 등록</button>
          </c:when>
          <c:otherwise>
            <button type='button' 
                         onclick="history.back();"
                         class="btn btn-success">다시 시도</button>
          </c:otherwise>
        </c:choose>
                    
        <button type='button' 
                    onclick="location.href='./list_all.do'"
                    class="btn" style="border: 1px solid #ccc;">목록</button>
      </LI>
     </UL>
  </fieldset>
 
</DIV>
 
<jsp:include page="/adm/menu/bottom.jsp" flush='false' /> 