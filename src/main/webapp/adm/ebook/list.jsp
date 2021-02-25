<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />

<DIV class='title_line'>
  <c:set var="cateno" value="${param.cateno }" />
  <c:choose>
    <c:when test="${empty cateno || cateno == 0 }">
      ${title } (${search_count })
    </c:when>
    <c:otherwise>
      <a href="../categrp/list.do">카테고리 그룹</a> >
      <a href="../cate/list.do?cg_no=${categrpVO.cg_no }">
        ${categrpVO.cg_name }
      </a> 
      > ${cateVO.name } (${search_count })
    </c:otherwise>
  </c:choose>  
</DIV>

<div class="col-md-6 float-left ebook_search_form mb-2">

  <div class="form-group" style="display: inline-block;width: 100%"> 
      <form name="frm" action="./list.do" method="GET" class="form-horizontal">
        <input type='hidden' name='cateno' value='${cateVO.cateno }'>
        <input type="text" name="word" id="word" value="" class="form-control">
        <button type="submit" class="btn">검색</button>
      </form>      
  </div>
  
</div>

<div class="col-md-6 float-right mb-2">
  <div class="float-right" style="margin-left: 5px;">
    <a href="./create.do?cateno=${cateVO.cateno }" class="btn btn-primary" style="color: #fff;">등록</a>
  </div>
  
  <div class="list_change">
    <span class="listing">
      <a href="./list.do?cateno=${param.cateno }&type=1">
        <i class="fas fa-list"></i>
      </a>
    </span>
    <span class="listing">
      <a href="./list.do?cateno=${param.cateno }&type=2">
        <i class="fas fa-th-large"></i>
      </a>
    </span>
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
    <c:set var="r" value="${ebookVO.r }"/>
      <tr>
        <td class="td_bs">
          <input type="checkbox" id="chk_eb_no" name="chk_eb_no" value="${eb_no }">
        </td>
        <td class="td_bs">${eb_no }</td>
        <td class="td_bs_left">
          <a href="./read.do?eb_no=${eb_no }&nowPage=${nowPage }">${ebookVO.eb_title }</a>
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
          <fmt:formatNumber value = "${ebookVO.eb_price}" pattern="#,### 원"/>
        </td>
      </tr>
  </c:forEach>
  </tbody> 
</TABLE>

<div class='ebook_paging my-4'>${paging }</div>

<script type="text/javascript">
  $("#chk_all").click( function () {
    $( "input:checkbox[name='chk_eb_no']" ).prop( "checked", function () {
      return !$(this).prop( "checked" );
    } );
  });
</script>
<jsp:include page="/adm/menu/bottom.jsp" flush='false' />