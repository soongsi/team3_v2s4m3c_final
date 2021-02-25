<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.ArrayList" %> 
<%@ page import="dev.mvc.pay.TypeCode" %>
<%@ page import="dev.mvc.pay.CodeTable" %>

<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />
 
<DIV class='title_line'> 
 <A href='./list_all.do?nowPage=1'> 전체 문의</A> >
 <A href="./list_by_mno_paging.do?m_no=${m_no}&nowPage=${param.nowPage}">회원번호 : ${param.m_no}</A> - 
 ${customerVO.cs_title} > 수정 
</DIV>

<FORM name='frm' method='POST' action='./update.do' class="form-horizontal">
  <input type='hidden' name='cs_no' id='cs_no' value="${param.cs_no }">
  <input type='hidden' name='m_no' id='m_no' value="${member_Customer_join.m_no }">

  <div class="form-group">
     <label class="control-label col-md-3" for="cs_type">문의유형</label>
     <div class="col-md-9">
      <select id="cs_type" name='cs_type' class="form-control" style='width: 25%;'>
          <option value="${param.cs_type }" ${param.cs_type=='A03' ? "selected='selected'":"" }
                                                  ${param.cs_type=='A01' ? "selected='selected'":"" }
                                                  ${param.cs_type=='A02' ? "selected='selected'":"" }
                                                  ${param.cs_type=='A04' ? "selected='selected'":"" }
                                                  ${param.cs_type=='A99' ? "selected='selected'":"" }>${member_Customer_join.cs_type }</option>
      </select>
     </div>
  </div>
  
  <div class="form-group">
     <label class="control-label col-md-3">제목</label>
     <div class="col-md-9">
       <input type='text' name='cs_title' value='${member_Customer_join.cs_title }' required="required" 
                 placeholder="" style='width: 45%;' class="form-control" >
     </div>
  </div>
  <div class="form-group">
     <label class="control-label col-md-3">내용</label>
     <div class="col-md-9">
       <textarea name='cs_contents' required="required" 
                 placeholder="" rows='10' class="form-control" >${member_Customer_join.cs_contents }</textarea>
     </div>
  </div>   
  <div class="form-group">
     <label class="control-label col-md-3">답변받을 이메일</label>
     <div class="col-md-9">
          <input type='text' class="form-control" name='r_email' value='${member_Customer_join.r_email }' placeholder="문의 답변을 받으실 이메일" required="required" style='width: 45%;'>
     </div>
  </div>   
  <div class="form-group">
    <div class="col-md-9">
    <button type="button" onclick="location.href='./read.do?cs_no=${param.cs_no}&m_no=${member_Customer_join.m_no }'" class="btn" style="border: 1px solid #ccc;">취소</button>  
    <button type="submit" class="btn btn-primary">수정</button>
    </div>
  </div>

</FORM>
 
<jsp:include page="/adm/menu/bottom.jsp" flush='false' />