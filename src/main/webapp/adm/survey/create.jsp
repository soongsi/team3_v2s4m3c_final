<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />



<DIV class='title_line'>설문조사 > 설문 등록</DIV>

<div> 
  <form name='frm' method='post' action='./create.do' class="form-horizontal">
    <div class="form-group col-md-4 float-left">
       <label class="control-label">제목</label>
       <div>
         <input type='text' name='title' id='title' value='' required="required" 
                    autofocus="autofocus" class="form-control">
       </div>
    </div>
    <div class="form-group col-md-1 float-left">
       <label class="control-label">순서</label>
       <div>
         <input type='text' name='seqno' id='seqno' value='' required="required" 
                    autofocus="autofocus" class="form-control">
       </div>
    </div>
    <div class="form-group col-md-2 float-left">
       <label class="control-label">시작일</label>
       <div>
         <input type='date' name='startdate' id='startdate' OnClick="todayIs();" value='' required="required" 
                   placeholder=""  class="form-control" >
       </div>
    </div>  
    <div class="form-group col-md-2 float-left">
       <label class="control-label">종료일</label>
       <div>
         <input type='date' name='enddate' id='enddate' value='' required="required" 
                   placeholder="종료일"  class="form-control"  >
       </div>
    </div>   
    <div class="form-group col-md-1 float-left">
       <label class="control-label">상태</label>
       <div>
          <select name='continueyn' id='continueyn' class="form-control">
            <option value='Y' selected="selected">Y</option>
            <option value='N'>N</option>
          </select>
       </div>
    </div>   
  
    <div class="col-md-2 float-left" style="line-height: 30px;">
      <label class="control-label"></label>
      <div>
        <button type="submit" class="btn btn-primary">등록</button>
        <button type="button" onclick="location.href='../../survey/list.do'" class="btn" style="border: 1px solid #ccc;">목록</button>
      </div>
    </div>
  </form>
</div>
<jsp:include page="/adm/menu/bottom.jsp" flush='false' />