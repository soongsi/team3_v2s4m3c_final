<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/menu/top.jsp" flush='false' />
<link href="./css/member.css" rel="Stylesheet" type="text/css">
<script type="text/javascript">
  $(function(){ 
    $('#btn_home').on('click', function() {
      location.href="${pageContext.request.contextPath}/";
    });
  });
</script>
    <section class="page-section cta">
      <div class="container">
        <div class="row">
          <div class="col-xl-10 mx-auto">
            <div class="cta-inner text-center rounded">
              <div class="section-heading mb-5">
                <span class="section-heading-lower">로그아웃</span>
              </div>
              <ul>
                <li class='li_none'>이용해 주셔서 감사합니다.</li>
                <li class='li_none'>
                  <button type="button" id="btn_home" class="btn btn-primary btn-md">확인</button>
                </li>
              </ul> 
            </div>
          </div>
        </div>
      </div>
    </section>
<jsp:include page="/menu/bottom.jsp" flush='false' />