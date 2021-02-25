<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />

<DIV class='title_line'><a href="../survey/list.do">${survey_title} </a> - ${title} 생성</DIV>
 
<FORM name='frm' method='POST' action='./create.do' class="form-horizontal">
  <input type="hidden" name="survey_no" value="${param.survey_no}">
  <div class="form-group col-md-2 float-left">
     <label class="control-label">설문 조사 번호</label>
       <input type='text' name='survey_no' value='${param.survey_no}' required="required" 
                 class="form-control">
  </div>
  <div class="form-group col-md-1 float-left">
     <label class="control-label">순서</label>
       <input type='text' name='seqno' value='' required="required" 
                  autofocus="autofocus" class="form-control">
  </div>
  <div class="form-group col-md-7 float-left">
     <label class="control-label">설문 항목 이름</label>
       <input type='text' name='item_title' value='' required="required" 
                  autofocus="autofocus" class="form-control">
  </div>

  <div class="col-md-2 float-left" style="line-height: 30px;">
    <label class="control-label"></label>
    <div>
      <button type="submit" class="btn btn-primary">등록</button>
      <button type="button" onclick="location.href='../survey/list.do'" class="btn" style="border: 1px solid #ccc;">취소</button>
    </div>
  </div>
</FORM>
 
<jsp:include page="/adm/menu/bottom.jsp" flush='false' />