<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.ArrayList" %> 
<%@ page import="dev.mvc.pay.TypeCode" %>
<%@ page import="dev.mvc.pay.CodeTable" %>

<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />

<c:set var="m_no" value="${customerVO.m_no }" />
  <DIV class="title_line">
    통합 고객 1:1문의
  </DIV>
<%--   <ASIDE class="aside_left">
    <A href='../categrp/list.do'>카테고리 그룹</A> >
    <A href="../cate/list.do?categrpno=${categrpVO.categrpno}">${categrpVO.name}</A> > ${cateVO.name}
  </ASIDE> --%>
  <ASIDE class="aside_right">
<%--     <A href="../../customer/create.do?m_no=${customerVO.m_no}">문의하기</A>
    <span class='menu_divide' >│</span> --%>
    <A href="javascript:location.reload();">새로고침</A>  
  </ASIDE> 
  <DIV class='menu_line'></DIV>
  
  <div style='width: 100%;'>
    <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width: 10%;"></col>
        <col style="width: 13%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 17%;"></col>
        <col style="width: 25%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
      </colgroup>
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th style='text-align: center;'>등록일</th>
          <th style='text-align: center;'>성함_이메일</th>
          <th style='text-align: center;'>문의유형</th>          
          <th style='text-align: center;'>파일</th>
          <th style='text-align: center;'>제목</th>
          <th style='text-align: center;'>회원no</th>
          <th style='text-align: center;'>조회수</th>
        </tr>
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="member_Customer_join" items="${list}">
          <c:set var="r_mname" value="${member_Customer_join.r_mname}" />
          <c:set var="r_email" value="${member_Customer_join.r_email}" />
          <c:set var="cs_rdate" value="${member_Customer_join.cs_rdate}" />
          <c:set var="cs_thumb1" value="${member_Customer_join.cs_thumb1}" />
          <c:set var="cs_type" value="${member_Customer_join.cs_type}" />
          <c:set var="cs_file1" value="${member_Customer_join.cs_file1}" />
          <c:set var="cs_title" value="${member_Customer_join.cs_title}" />
          <c:set var="cs_no" value="${member_Customer_join.cs_no}" />
          <c:set var="r_mno" value="${member_Customer_join.r_mno}" />
          <c:set var="cs_cnt" value="${member_Customer_join.cs_cnt}" />
          <tr> 
            <td style='vertical-align: middle; text-align: center;'>${cs_rdate.substring(0, 10)}</td>
            <td style='vertical-align: middle; text-align: center;'>${r_mname}${r_email}</td>
            <td style='vertical-align: middle; text-align: center;'>${cs_type}</td>
            <td style='vertical-align: middle; text-align: center;'>
              <c:choose>
                <c:when test="${cs_thumb1.endsWith('jpg') || cs_thumb1.endsWith('png') || cs_thumb1.endsWith('gif')}">
                 <a href="./read.do?cs_no=${cs_no}&m_no=${param.m_no}&m_id=${param.m_id}">
                  <IMG src="../../customer/storage/main_images/${cs_thumb1}" style="width: 100px; height:110px;">
                 </a>  
                </c:when>
                <c:otherwise> <!-- 이미지가 아닌 일반 파일 -->
                  ${cs_file1}
                </c:otherwise>
              </c:choose>
            </td>  
            <td style='vertical-align: middle; text-align: center;'>
              <a href="./read.do?cs_no=${cs_no}">${cs_title}</a> 
            </td> 
            <td style='vertical-align: middle; text-align: center;'>
             <a href="./list_by_mno.do?m_no=${member_Customer_join.m_no}">
              ${r_mno}
             </a>
            </td>
            <td style='vertical-align: middle; text-align: center;'>${cs_cnt}</td>  
          </tr>
        </c:forEach>
        
      </tbody>
    </table>
    <br><br>
  </div>
 
<jsp:include page="/adm/menu/bottom.jsp" flush='false' /> 