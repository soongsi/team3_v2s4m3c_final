<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/menu/top.jsp" flush='false' />
<link href="./css/member.css" rel="Stylesheet" type="text/css">
<script>
function loadDefault() {
  $('#id').val('user1');
  $('#passwd').val('1234');
} 
</script>

    <section class="page-section cta">
      <div class="container">
        <div class="row">
          <div class="col-xl-10 mx-auto">
            <div class="cta-inner text-center rounded">
              <h2 class="section-heading mb-5">
                <span class="section-heading-lower">로그인</span>
              </h2>
              <form name="frm" id="frm" method="POST" action="./login.do" class="form-horizontal">
                  <div class="form-group">
                    <label for="id" class="offset-md-1 col-md-6 control-label" style='font-size: 0.9em;'>아이디</label>    
                    <div class="offset-md-1 col-md-6" style="display: inline-block;">
                      <input type='text' class="form-control" name='id' id='id' value='${ck_id }' required="required" placeholder="아이디" autofocus="autofocus">
                    </div>
                    <label class="offset-md-1 col-md-6 control-label">   
                      <input type='checkbox' name='id_save' value='Y' 
                                ${ck_id_save == 'Y' ? "checked='checked'" : "" }> 아이디 저장
                    </label>
                  </div>
                   
                  <div class="form-group">
                    <label for="passwd" class="offset-md-1 col-md-6 control-label" style='font-size: 0.9em;'>패스워드 </label>    
                    <div class="offset-md-1 col-md-6" style="display: inline-block;">
                      <input type='password' class="form-control" name='passwd' id='passwd' value='${ck_passwd }' required="required" placeholder="패스워드">        
                    </div>
                    <label class="offset-md-1 col-md-6 control-label">
                      <input type='checkbox' name='passwd_save' value='Y' 
                                ${ck_passwd_save == 'Y' ? "checked='checked'" : "" }> 패스워드 저장
                    </label>
                  </div>   
               
                  <div class="form-group">
                    <div class="offset-md-3 col-md-6">
                      <button type="submit" class="btn btn-primary btn-md">로그인</button>
                      <button type='button' onclick="location.href='./create.do'" class="btn btn-primary btn-md">회원가입</button>
                      <button type='button' onclick="loadDefault();" class="btn btn-primary btn-md">테스트 계정</button>
                    </div>
                  </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </section>
<jsp:include page="/menu/bottom.jsp" flush='false' />