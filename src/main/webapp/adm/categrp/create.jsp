<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />

<DIV class='title_line'>${title }</DIV>

<div> 
  <form name='frm' method='post' action='./create.do' class="form-horizontal">
    <div class="form-group col-md-5 float-left">
       <label class="control-label">카테고리 그룹 이름</label>
       <div>
         <input type='text' name='cg_name' value='' required="required" 
                    autofocus="autofocus" class="form-control">
       </div>
    </div>
    <div class="form-group col-md-2 float-left">
       <label class="control-label">출력 순서</label>
       <div>
         <input type='number' name='cg_seqno' value='1' required="required" 
                   placeholder="" min="1" max="1000" step="1" 
                   class="form-control" >
       </div>
    </div>  
    <div class="form-group col-md-2 float-left">
       <label class="control-label">출력 형식</label>
       <div>
          <select name='cg_visible' class="form-control">
            <option value='Y' selected="selected">Y</option>
            <option value='N'>N</option>
          </select>
       </div>
    </div>   
  
    <div class="col-md-3 float-left" style="line-height: 30px;">
      <label class="control-label"></label>
      <div>
        <button type="submit" class="btn btn-primary">등록</button>
        <button type="button" onclick="location.href='./list.do'" class="btn" style="border: 1px solid #ccc;">목록</button>
      </div>
    </div>
  </form>
</div>
<jsp:include page="/adm/menu/bottom.jsp" flush='false' />