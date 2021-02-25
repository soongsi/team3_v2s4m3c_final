<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/menu/top.jsp" flush='false' />
<link href="./css/admin.css" rel="Stylesheet" type="text/css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js" integrity="sha512-3j3VU6WC5rPQB4Ld1jnLV7Kd5xr+cq9avvhwqzbH/taCRNURoeEpoPBK9pDyeukwSxwRPJ8fDgvYXd6SkaZ2TA==" crossorigin="anonymous"></script>
<script>
function loadDefault() {
  $('#id_admin').val('admin1');
  $('#passwd_admin').val('1234');
} 
</script>

    <section class="page-section cta">
      <div class="container">
        <div class="row">
          <div class="col-xl-10 mx-auto">
            <div class="cta-inner text-center rounded">
              <h2 class="section-heading mb-5">
                <span class="section-heading-lower">관리자 로그인</span>
              </h2>
              <form name="frm" id="frm" method="POST" action="./login.do" class="form-horizontal">
                  <div class="form-group">
                    <label for="id" class="offset-md-1 col-md-6 control-label" style='font-size: 0.9em;'>아이디</label>    
                    <div class="offset-md-1 col-md-6" style="display: inline-block;">
                      <input type='text' class="form-control" name='id_admin' id='id_admin' value='' required="required" placeholder="아이디" autofocus="autofocus">
                    </div>
                  </div>
                   
                  <div class="form-group">
                    <label for="passwd" class="offset-md-1 col-md-6 control-label" style='font-size: 0.9em;'>패스워드 </label>    
                    <div class="offset-md-1 col-md-6" style="display: inline-block;">
                      <input type='password' class="form-control" name='passwd_admin' id='passwd_admin' value='' required="required" placeholder="패스워드">        
                    </div>
                  </div>   
               
                  <div class="form-group">
                    <div class="offset-md-3 col-md-6">
                      <button type="submit" class="btn btn-primary btn-md">로그인</button>
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