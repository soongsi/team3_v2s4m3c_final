<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container ebook_menu">
  <div class="row">
    <ul class="ebook_nav">
        <c:forEach var="categrp_cate_name" items="${name_title_list}">
          ${categrp_cate_name}
        </c:forEach>
    </ul>
  </div>
</div>

