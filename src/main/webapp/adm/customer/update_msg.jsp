<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />

<DIV class='title_line'> 1:1 문의 > 수정 </DIV>

<DIV class='message'>
  <fieldset class='fieldset_basic'>
    <UL>
      <c:choose>
        <c:when test="${cnt == 1}">
          <LI class='li_none'>
            <span class="span_success">고객님의 문의가 변경 되었습니다. </span>
          </LI>
        </c:when>
        <c:otherwise>
          <LI class='li_none_left'>
            <span class="span_fail">고객님의 문의 변경에 실패하였습니다.</span>
          </LI>
          <LI class='li_none_left'>
            <span class="span_fail">다시 시도해주세요.</span>
          </LI>
        </c:otherwise>
      </c:choose>
      <LI class='li_none'>
        <br>
        <button type='button' onclick="history.back()" class="btn btn-primary">다시 시도</button>
        <button type='button' onclick="location.href='./list_all.do'" class="btn" style="border: 1px solid #ccc;">취소</button>
      </LI>
    </UL>
  </fieldset>

</DIV>

<jsp:include page="/adm/menu/bottom.jsp" flush='false' />