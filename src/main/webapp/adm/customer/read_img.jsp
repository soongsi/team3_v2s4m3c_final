<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />

<c:set var="cs_no" value="${customerVO.cs_no}" />
<c:set var="m_no" value="${customerVO.m_no }" />

  <DIV class="title_line">
   <A href='./list_all.do?nowPage=1'> 전체 문의</A> >
   <A href="./list_by_mno_paging.do?m_no=${m_no}&nowPage=${param.nowPage}">
    회원번호 : ${param.m_no} -
   </A> 
   ${customerVO.cs_title} 
  </DIV>
  
  <ASIDE class="aside_left" style="margin-left: 3px;">
    <A href='../customer/create.do'>문의하기</A>
    <span class='menu_divide' > | </span>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' > | </span>
    <A href='./update.do?cs_no=${cs_no}&cs_type=${customerVO.cs_type}&m_no=${customerVO.m_no}&nowPage=${param.nowPage}'>수정</A>
    <span class='menu_divide' > | </span> 
    <A href='./delete.do?cs_no=${cs_no}&nowPage=${param.nowPage}'>삭제</A>
  </ASIDE>
  
  <DIV class='menu_line'></DIV>

  <FORM name='frm' method="get" action='./update.do' style="margin-top: 10px;">
      <input type="hidden" name="cs_no" value="${cs_no}">
      <fieldset class="fieldset">
        <ul>
          <li class="li_none" style='border-bottom: solid 1px #AAAAAA;'>
            조회수: <span>${customerVO.cs_cnt}</span>
            <span class='menu_divide'>|</span>
            등록일: <span>${customerVO.cs_rdate.substring(0, 16)}</span>
          </li>
          <li class="li_none">
             <c:set var="cs_file1" value="${customerVO.cs_file1}" />
             <c:if test="${cs_file1.endsWith('jpg') || cs_file1.endsWith('png') || cs_file1.endsWith('gif')}">
                <div style="width: 50%; float: left; margin-right: 10px;">
                  <IMG src="../../customer/storage/main_images/${cs_file1}" style="height:450px; ">
                </div>      
             </c:if>
            <DIV>${customerVO.cs_contents}</DIV>
          </li>
        </ul>
      </fieldset>
  </FORM>

<jsp:include page="/adm/menu/bottom.jsp" flush='false' /> 