<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />

  <DIV class="title_line">
    1:1 문의 내역
  </DIV>
  
  <ASIDE class="aside_left">
    <A href="../member/list_all.do">문의 리스트</A> >
     ${customerVO.m_no}번 님의 문의 삭제
  </ASIDE>
  <ASIDE class="aside_right">
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list_all.do"> 모든 1:1 문의</A>
  </ASIDE>
  
  <div class='menu_line'></div>
 
  <FORM id='frm' name='frm' method='POST' action='./delete.do'>
          <input type='hidden' name='cs_no' value='${param.cs_no}'>
          <input type="hidden" name="m_no" value="${customerVO.m_no }">
                
          <div class="form-group">   
            <div class="col-md-12" style='text-align: center; margin: 30px;'>
              삭제 되는글: ${customerVO.cs_title }<br><br>
              삭제하시겠습니까? 삭제하시면 복구 할 수 없습니다.<br><br>           
            <div class="form-group">   
            <div class="col-md-12">
              <input id='cs_passwd' type='password' class="form-control" name='cs_passwd'  value='' placeholder="패스워드" style='width: 20%; margin: 10px auto;'>
            </div>
          </div>
              
          <button id='btn_send' data-focus=''  type="submit" class="btn btn-info bottom_button">삭제 진행</button>
          <button type = "button" onclick = "history.back()" class="btn btn-info bottom_button">취소</button>
        </div>
      </div>   
  </FORM>
 
<jsp:include page="/adm/menu/bottom.jsp" flush='false' /> 