<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/menu/top.jsp" flush='false' />
<link href="./css/member.css" rel="Stylesheet" type="text/css">

    <section class="page-section cta">
      <div class="container">
        <div class="row">
          <div class="col-xl-10 mx-auto">
            <div class="cta-inner text-center rounded">
              <div class="section-heading mb-5">
                <span class="section-heading-lower">회원가입 완료</span>
              </div>
              <ul>
              <c:choose>
                <c:when test="${param.cnt == 1 }">
                  <li class='li_none'>회원가입이 완료되었습니다.</li>
                </c:when>
                <c:otherwise>
                  <li class='li_none'>회원가입에 실패했습니다.</li>
                  <li class='li_none'>다시 한 번 시도해주세요.</li>
                  <li class='li_none'>계속 실패시 ☏ 000-0000-0000 문의해주세요.</li>
                </c:otherwise>
              </c:choose>
                <li class='li_none'>
                  <button type='button' class="btn btn-primary btn-md" onclick="location.href='${pageContext.request.contextPath}/'">홈으로</button>
                </li>
              </ul> 
            </div>
          </div>
        </div>
      </div>
    </section>
<jsp:include page="/menu/bottom.jsp" flush='false' />