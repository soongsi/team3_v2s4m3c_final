<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.ArrayList" %> 
<%@ page import="dev.mvc.pay.TypeCode" %>
<%@ page import="dev.mvc.pay.CodeTable" %>

<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />

<input type="hidden" name="m_no" value="${param.m_no}">
  <DIV class="title_line" style="margin-left: 3px;">
   <A href='./list_all.do?nowPage=1'> 전체 문의</A> > 회원번호 : ${param.m_no}번 목록
  </DIV>
  
<!--   <ASIDE class="aside_left">
    <A href="../member/list_all.do">고객 리스트</A> >
    <A href="./list_all.do"> 모든 1:1 문의</A>
  </ASIDE> -->
  <ASIDE class="aside_left" style="margin-left: 3px;">
<!--     <A href="../../customer/create.do" style='margin-left: 2px;'>문의하기</A>
    <span class='menu_divide' >│</span> -->
    <A href="./list_join.do">통합 고객 1:1문의</A>
    <span class='menu_divide' >│</span> 
    <A href="javascript:location.reload();">새로고침</A>   
  </ASIDE>
  
  <DIV class='menu_line'></DIV>
  
  <div style='width: 100%;'>
    <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 25%;"></col>
        <col style="width: 30%;"></col>
        <col style="width: 10%;"></col>
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th style='text-align: center;'>등록일</th>
          <th style='text-align: center;'>회원번호</th>
          <th style='text-align: center;'>문의유형</th>          
          <th style='text-align: center;'>파일</th>
          <th style='text-align: center;'>제목</th>
          <th style='text-align: center;'>조회수</th>
        </tr>
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="customerVO" items="${list}">
          <c:set var="m_no" value="${customerVO.m_no}" />
          <c:set var="cs_no" value="${customerVO.cs_no}" />
          <c:set var="cs_thumb1" value="${customerVO.cs_thumb1}" />
          <c:set var="cs_type" value="${customerVO.cs_type}" />
          <c:if test="${param.m_no == m_no}">
            <tr> 
              <td style='vertical-align: middle; text-align: center;'>${customerVO.cs_rdate.substring(0, 10)}</td>
              <td style='vertical-align: middle; text-align: center;'>${param.m_no}</td>
              <td style='vertical-align: middle; text-align: center;'>${cs_type}</td>
              <td style='vertical-align: middle; text-align: center;'>
                <c:choose>
                  <c:when test="${cs_thumb1.endsWith('jpg') || cs_thumb1.endsWith('png') || cs_thumb1.endsWith('gif')}">
                    <a href="./read.do?cs_no=${cs_no}&m_no=${param.m_no}&m_id=${param.m_id}">
                      <IMG src="../../customer/storage/main_images/${cs_thumb1}" style="width: 100px; height:110px;">
                    </a>
                  </c:when>
                  <c:otherwise> <!-- 이미지가 아닌 일반 파일 -->
                    <a href="./read.do?cs_no=${cs_no}&m_no=${param.m_no}&m_id=${param.m_id}">
                     ${customerVO.cs_file1}
                    </a>
                  </c:otherwise>
                </c:choose>
              </td>  
              <td style='vertical-align: middle; text-align: center;'>
                <a href="./read.do?cs_no=${cs_no}&m_no=${param.m_no}&nowPage=${param.nowPage}">
                 ${customerVO.cs_title}
                </a> 
              </td> 
              <td style='vertical-align: middle; text-align: center;'>${customerVO.cs_cnt}</td>  
            </tr>
            </c:if>
        </c:forEach>
      </tbody>
    </table>
    <br><br>
    <DIV class='bottom_menu'>${paging }</DIV>
  </div>
 
<jsp:include page="/adm/menu/bottom.jsp" flush='false' /> 