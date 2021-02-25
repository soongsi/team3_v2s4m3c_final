<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/menu/top.jsp" flush='false' />

    <section class="page-section cta">
      <div class="container">
       <jsp:include page="./menu/top_nav.jsp" flush='false' />
        <!-- Page Features -->
        <div class="row text-center">
          <fmt:setLocale value = "ko_KR"/>
          <c:forEach var="ebookVO" items="${list }" varStatus="status">
            <%-- 하나의 행에 이미지를 4개씩 출력 후 행 변경 --%>
            <c:if test="${status.index % 4 == 0 && status.index != 0 }">
              <HR class='menu_line'>
            </c:if>
            <div class="ebook_list_thumb col-6 col-sm-6 col-md-4 col-lg-3 my-3">
              <div class="card h-100">
                <img class="card-img-top" src="http://placehold.it/300x400" alt="">
                <div class="card-body">
                  <h5 class="card-title" style="text-align: left;">
                    <a href="./read.do?eb_no=${ebookVO.eb_no }">${ebookVO.eb_title }</a>
                  </h5>
                    <ul class="ebinfo card-text" style="text-align: left;list-style: none;">
                      <li class="author">
                      <c:choose>
                        <c:when test="${ebookVO.eb_author.length() > 20 }">
                          ${ebookVO.eb_author.substring(0, 17) } ...
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
      </div>
    </section>
<jsp:include page="/menu/bottom.jsp" flush='false' />