<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />

<DIV class='title_line'>
  ${title }
  <div class="float-right" style="margin-left: 5px;">
    <a href="./create.do" class="btn btn-primary" style="color: #fff;">등록</a>
  </div>
</DIV>

 
<c:if test="${not empty param.cateno }">
<div class="list_change">
  <span class="listing">
    <a href="./list.do?cateno=${param.cateno }">
      <i class="fas fa-list"></i>
    </a>
  </span>
  <span class="listing">
    <a href="./list_thumb.do?cateno=${param.cateno }">
      <i class="fas fa-th-large"></i>
    </a>
  </span>
</div>
</c:if> 
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
    <TH class="th_bs">제목</TH>
    <TH class="th_bs">저자</TH>
    <TH class="th_bs">출판사</TH>
    <TH class="th_bs">가격</TH>
  </TR>
  </thead>
  
  <tbody>
  <fmt:setLocale value = "ko_KR"/>
  <c:forEach var="ebookVO" items="${list }">
    <c:set var="eb_no" value="${ebookVO.eb_no }"/>
      <tr>
        <td class="td_bs">
          <input type="checkbox" id="chk_eb_no" name="chk_eb_no" value="${eb_no }">
        </td>
        <td class="td_bs">${eb_no }</td>
        <td class="td_bs_left">
          <a href="./read.do?eb_no=${eb_no }">${ebookVO.eb_title }</a>
        </td>
        <td class="td_bs">
          <c:choose>
            <c:when test="${ebookVO.eb_author.length() > 20 }">
              ${ebookVO.eb_author.substring(0, 20) } ...
            </c:when>
            <c:otherwise>
              ${ebookVO.eb_author }
            </c:otherwise>
          </c:choose>
        </td>
        <td class="td_bs">${ebookVO.eb_publisher }</td>      
        <td class="td_bs">
          <fmt:formatNumber value = "${ebookVO.eb_price}" pattern="#,### 원" />
        </td>
      </tr>
  </c:forEach>
  </tbody>
 
</TABLE>
<script type="text/javascript">
  $("#chk_all").click( function () {
    $( "input:checkbox[name='chk_eb_no']" ).prop( "checked", function () {
      return !$(this).prop( "checked" );
    } );
  });
</script>
<jsp:include page="/adm/menu/bottom.jsp" flush='false' />