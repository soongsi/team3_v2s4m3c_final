<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />

<DIV class='title_line'>
  회원 목록
</DIV>

<div class="col-md-6 float-left ebook_search_form mb-2">

  <div class="form-group" style="display: inline-block;width: 100%"> 
      <form name="frm" action="./list.do" method="GET" class="form-horizontal">
        <input type="text" name="word" id="word" value="" class="form-control">
        <button type="submit" class="btn">검색</button>
      </form>      
  </div>
  
</div>

<div class="col-md-6 float-right mb-2">
  <div class="float-right" style="margin-left: 5px;">
    <a href="./create.do" class="btn btn-primary" style="color: #fff;">등록</a>
  </div>
</div>
 

<TABLE class='adm_ebook table table-striped'>
  <colgroup>
    <col style="width: 5%;"/>
    <col style='width: 5%;'/>
    <col style='width: 40%;'/>
    <col style='width: 20%;'/>
    <col style='width: 15%;'/>           
    <col style='width: 10%;'/>
  </colgroup>
  <thead>  
  <TR>
    <th class="th_bs">
      <input type="checkbox" id="chk_all" name="chk_all" value="1">
    </th>
    <TH class="th_bs">번호</TH>
    <TH class="th_bs">아이디</TH>
    <TH class="th_bs">연락처</TH>
    <TH class="th_bs">가입일</TH>
    <TH class="th_bs">기타</TH>
  </TR>
  </thead>
  
  <tbody>
  <fmt:setLocale value = "ko_KR"/>
  <c:forEach var="memberVO" items="${list }">
    <c:set var="m_no" value="${memberVO.m_no }"/>
      <tr>
        <td class="td_bs">
          <input type="checkbox" id="chk_m_no" name="chk_m_no" value="${m_no }">
        </td>
        <td class="td_bs">${m_no }</td>
        <td class="td_bs_left">
          <a href="./read.do?m_no=${m_no }">${memberVO.id }</a>
        </td>
        <td class="td_bs">
          ${memberVO.tel }
        </td>
        <td class="td_bs">${memberVO.mdate }</td>      
        <td class="td_bs">
          
        </td>
      </tr>
  </c:forEach>
  </tbody> 
</TABLE>

<div class='ebook_paging my-4'>${paging }</div>

<script type="text/javascript">
  $("#chk_all").click( function () {
    $( "input:checkbox[name='chk_m_no']" ).prop( "checked", function () {
      return !$(this).prop( "checked" );
    } );
  });
</script>
<jsp:include page="/adm/menu/bottom.jsp" flush='false' />