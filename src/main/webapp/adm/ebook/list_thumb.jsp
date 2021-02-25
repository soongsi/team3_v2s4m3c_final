<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />

<DIV class='title_line'>
  <c:set var="cateno" value="${param.cateno }" />
  <c:choose>
    <c:when test="${empty cateno || cateno == 0 }">
      ${title }
    </c:when>
    <c:otherwise>
      <a href="../categrp/list.do">카테고리 그룹</a> >
      <a href="../cate/list.do?cg_no=${categrpVO.cg_no }">
        ${categrpVO.cg_name }
      </a> 
      > ${cateVO.name }
    </c:otherwise>
  </c:choose>
  
</DIV>

<div style="width: 100%;display: inline-block;margin-bottom: 20px;">
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

  <!-- Page Content -->
  <div class="container">
    <!-- Page Features -->
    <div class="row text-center">
      <fmt:setLocale value = "ko_KR"/>
      <c:forEach var="ebookVO" items="${list }" varStatus="status">
        <%-- 하나의 행에 이미지를 4개씩 출력 후 행 변경 --%>
        <c:if test="${status.index % 4 == 0 && status.index != 0 }">
          
        </c:if>
        <div class="col-sm-6 col-md-6 col-lg-3 mb-3">
          <div class="card h-100">
            <c:set var="thumb" value="${ebookVO.eb_thumb }"/>
            <c:choose>
                <c:when test="${thumb.endsWith('jpg') || thumb.endsWith('png') || thumb.endsWith('gif')}">
                  <img class="card-img-top" src="../../ebook/storage/main_images/${thumb }" alt="">
                </c:when>
                <c:otherwise>
                  <img class="card-img-top" src="http://placehold.it/300x400" alt="">              
                </c:otherwise>
            </c:choose>
            <div class="card-body">
              <h5 class="card-title" style="text-align: left;">
                <a href="./read.do?eb_no=${ebookVO.eb_no }&nowPage=${nowPage }">${ebookVO.eb_title }</a>
              </h5>
                <ul class="ebinfo card-text" style="text-align: left;list-style: none;">
                  <li class="author">
                  <c:choose>
                    <c:when test="${ebookVO.eb_author.length() > 20 }">
                      ${ebookVO.eb_author.substring(0, 20) } ...
                    </c:when>
                    <c:otherwise>
                      ${ebookVO.eb_author }
                    </c:otherwise>
                  </c:choose>
                  </li>
                  <li class="publisher">${ebookVO.eb_publisher }</li>
                  <li class="price">
                    <fmt:formatNumber value="${ebookVO.eb_price}" pattern="#,### 원"/>
                  </li>
                </ul>
            </div>
          </div>
        </div>
      </c:forEach>
    </div>
    <!-- /.row -->
    <div class='ebook_paging my-4'>${paging }</div>
  </div>
  <!-- /.container -->
<jsp:include page="/adm/menu/bottom.jsp" flush='false' />