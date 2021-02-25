<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.ArrayList" %> 
<%@ page import="dev.mvc.pay.TypeCode" %>
<%@ page import="dev.mvc.pay.CodeTable" %>

<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />

<c:set var="m_no" value="${customerVO.m_no }" />
  <DIV class="title_line">
    전체 문의
  </DIV>

  <ASIDE class="aside_left" style="margin-left: 3px;">
    <A href="./list_join.do" style='margin-left: 2px;'>(JOIN) 전체 문의</A>
    <span class='menu_divide' >│</span> 
    <A href="javascript:location.reload();">새로고침</A>   
  </ASIDE>
  
  <DIV class='menu_line'></DIV>
  
  <div style='width: 100%;'>
    <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width: 15%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 25%;"></col>
        <col style="width: 25%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th style='text-align: center;'>등록일</th>
          <th style='text-align: center;'>문의유형</th>          
          <th style='text-align: center;'>파일</th>
          <th style='text-align: center;'>제목</th>
          <th style='text-align: center; color: navy;'>회원번호별 문의</th>
          <th style='text-align: center;'>조회수</th>
        </tr>
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="customerVO" items="${list}">
          <c:set var="cs_no" value="${customerVO.cs_no}" />
          <c:set var="cs_thumb1" value="${customerVO.cs_thumb1}" />
          <c:set var="cs_type" value="${customerVO.cs_type}" />
          <c:set var="m_no" value="${customerVO.m_no}" />
          <tr> 
            <td style='vertical-align: middle; text-align: center;'>${customerVO.cs_rdate.substring(0, 10)}</td>
            <td style='vertical-align: middle; text-align: center;'>${cs_type}</td>
            <td style='vertical-align: middle; text-align: center;'>
              <c:choose>
                <c:when test="${cs_thumb1.endsWith('jpg') || cs_thumb1.endsWith('png') || cs_thumb1.endsWith('gif')}">
                  <a href="./read.do?cs_no=${cs_no}&m_no=${customerVO.m_no}&nowPage=${param.nowPage}">
                    <IMG src="../../customer/storage/main_images/${cs_thumb1}" style="width: 100px; height:110px;">
                  </a>
                </c:when>
                <c:otherwise> <!-- 이미지가 아닌 일반 파일 -->
                  <a href="./read.do?cs_no=${cs_no}&m_no=${customerVO.m_no}&nowPage=${param.nowPage}">
                   ${customerVO.cs_file1}
                  </a>
                </c:otherwise>
              </c:choose>
            </td>  
            <td style='vertical-align: middle; text-align: center;'>
              <a href="./read.do?cs_no=${cs_no}&m_no=${customerVO.m_no}&nowPage=${param.nowPage}">${customerVO.cs_title}</a> 
            </td> 
            <td style='vertical-align: middle; text-align: center; font-weight: bolder;'>
              <a href="./list_by_mno_paging.do?m_no=${customerVO.m_no}&nowPage=1">${m_no}</a>
            </td>
            <td style='vertical-align: middle; text-align: center;'>${customerVO.cs_cnt}</td>  
          </tr>
        </c:forEach>
        
      </tbody>
    </table>
    <br><br>
  </div>
 
<jsp:include page="/adm/menu/bottom.jsp" flush='false' /> 