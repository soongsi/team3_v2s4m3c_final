<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.ArrayList" %> 
<%@ page import="dev.mvc.pay.TypeCode" %>
<%@ page import="dev.mvc.pay.CodeTable" %>
 
<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />

<input type="hidden" name="m_no" value="${param.m_no}">
  <DIV class="title_line">
    <A href="./list_join.do">통합 고객 1:1문의</A> > mno: 「${param.m_no}」 1:1문의
  </DIV>
  
  <div class="menu_line float-right">
    <A href="javascript:location.reload();">새로고침</A>  
  </div> 
  
  <div class="contentBox">
    <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width: 15%;"></col>
        <col style="width: 20%;"></col>
        <col style="width: 20%;"></col>
        <col style="width: 30%;"></col>
        <col style="width: 15%;"></col>
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th style='text-align: center;'>등록일</th>
          <th style='text-align: center;'>문의유형</th>          
          <th style='text-align: center;'>파일</th>
          <th style='text-align: center;'>제목</th>
          <th style='text-align: center;'>조회수</th>
        </tr>
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="member_Customer_join" items="${list}">
          <c:set var="r_mname" value="${member_Customer_join.r_mname}" />
          <c:set var="r_mno" value="${member_Customer_join.r_mno}" />
          <c:set var="r_mid" value="${member_Customer_join.r_mid}" />
          <c:set var="r_email" value="${member_Customer_join.r_email}" />
          <c:set var="cs_rdate" value="${member_Customer_join.cs_rdate}" />
          <c:set var="cs_thumb1" value="${member_Customer_join.cs_thumb1}" />
          <c:set var="cs_type" value="${member_Customer_join.cs_type}"  />
          <c:set var="cs_file1" value="${member_Customer_join.cs_file1}" />
          <c:set var="cs_title" value="${member_Customer_join.cs_title}" />
          <c:set var="cs_no" value="${member_Customer_join.cs_no}" />
          <c:set var="cs_cnt" value="${member_Customer_join.cs_cnt}" />
          <c:set var="adm_id" value="${sessionScope.adm_id}" />
          <c:if test="${adm_id != null}">
          <tr>
            <td style='vertical-align: middle; text-align: center;'>${cs_rdate.substring(0, 10)}</td>
            <td style='vertical-align: middle; text-align: center;'>${cs_type}</td>
            <td style='vertical-align: middle; text-align: center;'>
              <c:choose>
                <c:when test="${cs_thumb1.endsWith('jpg') || cs_thumb1.endsWith('png') || cs_thumb1.endsWith('gif')}">
                  <a href="./read.do?cs_no=${cs_no}&m_no=${param.m_no}&m_id=${param.m_id}">
                    <IMG src="../../customer/storage/main_images/${cs_thumb1}" style="width: 100px; height:110px;">
                  </a> 
                </c:when>
                <c:otherwise> <!-- 이미지가 아닌 일반 파일 -->
                  <a href="./read.do?cs_no=${cs_no}&m_no=${param.m_no}&m_id=${param.m_id}">${cs_file1}</a>
                </c:otherwise>
              </c:choose>
            </td>  
            <td style='vertical-align: middle; text-align: center;'>
              <a href="./read.do?cs_no=${cs_no}&m_no=${param.m_no}&m_id=${param.m_id}">${cs_title}</a> 
            </td>
            <td style='vertical-align: middle; text-align: center;'>${cs_cnt}</td> 
          </tr>
          </c:if>    
        </c:forEach>
      </tbody>
    </table>
    <br><br>
  </div>
 
<jsp:include page="/adm/menu/bottom.jsp" flush='false' /> 