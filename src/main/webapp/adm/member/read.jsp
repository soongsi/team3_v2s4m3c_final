<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />

<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
<script type="text/javascript">
  $(function() {
    $('#btn_DaumPostcode').on('click', DaumPostcode); // 다음 우편 번호
  });
</script>

<DIV class='title_line'>
  ${memberVO.id} 회원 정보 조회
</DIV>
 
  <div class='menu_line'></div>
  <DIV class="ebook_form" style='width: 100%;'>
    <FORM name='frm' id="frm" method='POST' action='./update.do' class="form-horizontal" enctype="multipart/form-data">
               
      <!-- FK categrpno 지정 -->
      <input type='hidden' name="m_no" id="m_no" value="${memberVO.m_no }">
      
      
      <div class="form-group"> 
        <div class="col-md-4">
          <label>아이디</label>  
          <input type='text' class="form-control" name="id" value="${memberVO.id }" readonly="readonly">
        </div>
      </div>
            
      <div class="form-group">
        <div class="col-md-4 col-sm-12">
          <label>비밀번호</label>
          <input type='password' class="form-control" name='passwd' value="" placeholder="비밀번호">
        </div>
      </div>
      
      <div class="form-group">
        <div class="col-md-4 col-sm-12">
          <label>비밀번호 확인</label>
          <input type='password' class="form-control" name='passwd2' value="" placeholder="비밀번호 확인">
        </div>
      </div>
      
      <div class="form-group">
        <div class="col-md-4 col-sm-12">
          <label>성명</label>
          <input type='text' class="form-control" name='mname' value="${memberVO.mname }" placeholder="성명">
        </div>
      </div>
      
      <div class="form-group">
        <div class="col-md-4 col-sm-12">
          <label>전화번호</label>
          <input type='text' class="form-control" id="tel" name='tel' value="${memberVO.tel }" placeholder="010-0000-0000">
        </div>
      </div>
      
      <div class="form-group">
        <div class="col-md-4 col-sm-12">
          <label style="display: block;">우편번호</label>
          <input type='text' class="form-control mr-2" id="zipcode" name='zipcode' value="${memberVO.zipcode }" placeholder="우편번호" maxlength="5" style="float: left;width: 50%;">
          <button type="button" id="btn_DaumPostcode" class="btn btn-primary btn-md">우편번호 찾기</button>
        </div>
      </div>
      
      <div class="form-group">
        <div class="col-md-12">

          <!-- ---------- DAUM 우편번호 API 시작 ---------- -->
          <div id="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 110px;position:relative">
            <img src="//i1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
          </div>
          
          <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
          <script>
              // 우편번호 찾기 화면을 넣을 element
              var element_wrap = document.getElementById('wrap');
          
              function foldDaumPostcode() {
                  // iframe을 넣은 element를 안보이게 한다.
                  element_wrap.style.display = 'none';
              }
          
              function DaumPostcode() {
                  // 현재 scroll 위치를 저장해놓는다.
                  var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
                  new daum.Postcode({
                      oncomplete: function(data) {
                          // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
          
                          // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                          // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                          var fullAddr = data.address; // 최종 주소 변수
                          var extraAddr = ''; // 조합형 주소 변수
          
                          // 기본 주소가 도로명 타입일때 조합한다.
                          if(data.addressType === 'R'){
                              //법정동명이 있을 경우 추가한다.
                              if(data.bname !== ''){
                                  extraAddr += data.bname;
                              }
                              // 건물명이 있을 경우 추가한다.
                              if(data.buildingName !== ''){
                                  extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                              }
                              // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                              fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                          }
          
                          // 우편번호와 주소 정보를 해당 필드에 넣는다.
                          $('#zipcode').val(data.zonecode); //5자리 새우편번호 사용
                          $('#address1').val(fullAddr);
          
                          // iframe을 넣은 element를 안보이게 한다.
                          // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                          element_wrap.style.display = 'none';
          
                          // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
                          document.body.scrollTop = currentScroll;
                          
                          $('#address2').focus();
                      },
                      // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
                      onresize : function(size) {
                          element_wrap.style.height = size.height+'px';
                      },
                      width : '100%',
                      height : '100%'
                  }).embed(element_wrap);
          
                  // iframe을 넣은 element를 보이게 한다.
                  element_wrap.style.display = 'block';
              }
          </script>
        </div>
      </div>
      
      <div class="form-group">
        <div class="col-md-6 col-sm-12">
          <label>주소</label>
          <input type='text' class="form-control" id="address1" name='address1' value="${memberVO.address1 }" placeholder="주소">
        </div>
      </div>
      
      <div class="form-group">
        <div class="col-md-6 col-sm-12">
          <label>나머지 주소</label>
          <input type='text' class="form-control" id="address2" name='address2' value="${memberVO.address2 }" placeholder="나머지 주소">
        </div>
      </div>
  
      <DIV class="float-right">
        <button type="submit" class="btn btn-success">수정</button>
        <button type="button" onclick="location.href='./list.do'" class="btn btn-default" style="border: 1px solid #ccc;">목록</button>
      </DIV>
      
    </FORM>
    <div class="float-right mx-1">
      <form id="delete_frm" name="delete_frm" method="post" action="./delete.do" class="form-horizontal">
        <input type='hidden' name="m_no" value="${memberVO.m_no }">
        <button type="button" class="btn btn-danger" id="delete_btn">삭제</button>
      </form>
    </div>
  </DIV>
  

  <!-- 게시글 삭제 Modal 시작 -->
  <div class="modal fade" id="modal_panel_delete" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title" id='modal_title'>삭제</h4><!-- 제목 -->
          <button type="button" class="close" data-dismiss="modal">×</button>
        </div>
        <div class="modal-body alert alert-danger">
          [ ${memberVO.id } ] 을(를) 삭제하시겠습니까?
          <form name='frm_delete' id='frm_delete' action='./delete.do'
                      method='POST'>                    
              <input type='hidden' name='m_no' value='${param.m_no }'>
          </form>
        </div>
        <div class="modal-footer">        
          <button type="button" id="delete_proc" class="btn btn-danger" data-dismiss="modal">삭제</button>
          <button type="button" class="btn btn-default" data-dismiss="modal" style="border: 1px solid #ccc;">Close</button>
        </div>
      </div>
    </div>
  </div> <!-- 게시글 삭제 Modal 종료 -->
  
  
  <!-- 파일 삭제 Modal 시작 -->
  <div class="modal fade" id="modal_panel_file" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title" id='modal_title'></h4><!-- 제목 -->
          <button type="button" class="close" data-dismiss="modal">×</button>
        </div>
        <div class="modal-body alert alert-danger" id="modal-body" data-type="">
          <form name='frm_delete' id='frm_file_delete'>                    
            <input type='hidden' name='eb_no' id='eb_no' value='${param.eb_no }'>
          </form>
        </div>
        <div class="modal-footer">        
          <button type="button" id="file_delete_proc" class="btn btn-danger" data-dismiss="modal" onclick="javascript:file_delete_proc()">삭제</button>
          <button type="button" class="btn btn-default" data-dismiss="modal" style="border: 1px solid #ccc;">Close</button>
        </div>
      </div>
    </div>
  </div> <!-- 파일 삭제 Modal 종료 -->
  
  
  <!-- 파일 삭제 Modal 시작 -->
  <div class="modal fade" id="modal_panel" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title" id='modal_title'>파일 삭제</h4><!-- 제목 -->
          <button type="button" class="close" data-dismiss="modal">×</button>
        </div>
        <div class="modal-body" id="modal-body" data-type="">
          
        </div>
        <div class="modal-footer">        
          <button type="button" class="btn btn-default" data-dismiss="modal" style="border: 1px solid #ccc;">Close</button>
        </div>
      </div>
    </div>
  </div> <!-- 파일 삭제 Modal 종료 -->
  
<script type="text/javascript">
  $(function () {

    $('#delete_btn').on( 'click', delete_modal );
    $('#delete_proc').on( 'click', delete_proc );

    var frm = $('#frm');

  });

  function delete_modal() {
    $('#modal_panel_delete').modal();
  }
 

  function delete_proc() {
    $('#frm_delete').submit();
  }

</script>
<jsp:include page="/adm/menu/bottom.jsp" flush='false' />