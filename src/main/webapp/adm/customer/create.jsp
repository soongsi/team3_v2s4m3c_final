<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.ArrayList" %> 
<%@ page import="dev.mvc.pay.TypeCode" %>
<%@ page import="dev.mvc.pay.CodeTable" %>

<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />
 
<DIV class='title_line'>문의 등록</DIV>

<FORM name='frm' method='POST' action='./create.do' class="form-horizontal" enctype="multipart/form-data">
  <%-- FK 전달 --%>
  <input type='hidden' name='m_no' id='m_no' value="${param.m_no}">
  
  <div class="form-group">
     <label class="control-label col-md-3" for="cs_type">문의유형 선택</label>
     <div class="col-md-9">
      <select id="cs_type" name='cs_type' class="form-control" style='width: 25%;'>
      <c:forEach var="code" items="${type_list}" varStatus="info"> <!-- code:  -->
          <option value="${code.value }">${code.pay }</option>
      </c:forEach>
      </select>
     </div>
  </div>
  <div class="form-group">
     <label class="control-label col-md-3">제목</label>
     <div class="col-md-9">
       <input type='text' name='cs_title' value='' required="required" 
                 placeholder="" style='width: 45%;' class="form-control" >
     </div>
  </div>
  <div class="form-group">
     <label class="control-label col-md-3">내용</label>
     <div class="col-md-9">
       <textarea name='cs_contents' required="required" 
                 placeholder="" rows='10' class="form-control" ></textarea>
     </div>
  </div>   
  <div class="form-group">
     <label class="control-label col-md-3">첨부파일</label>
     <div class="col-md-9">
          <input type='file' class="form-control" name='file1MF' id='file1MF' 
                    value='' placeholder="파일 선택">
     </div>
  </div> 

  <div class="form-group">
    <div class="col-md-9">
    <button type="button" onclick="location.href='./list_all.do'" class="btn" style="border: 1px solid #ccc;">취소</button>  
    <button type="submit" class="btn btn-primary">등록</button>
    </div>
  </div>

</FORM>
 
<jsp:include page="/adm/menu/bottom.jsp" flush='false' />