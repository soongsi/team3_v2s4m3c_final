<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />

<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
<script type="text/javascript">
  $(function() {
    CKEDITOR.replace('eb_infor');  // <TEXTAREA>태그 id 값
  });
</script>

<DIV class='title_line'>
  <A href='../cate/list.do?cg_no=${categrpVO.cg_no }'>${categrpVO.cg_name }</A> >
  <A href='../ebook/list.do?cateno=${cateVO.cateno }'>${cateVO.name }</A>  
    > ${ebookVO.eb_title } 조회
</DIV>
 
  <div class='menu_line'></div>
  <DIV class="ebook_form" style='width: 100%;'>
    <FORM name='frm' id="frm" method='POST' action='./update.do' class="form-horizontal" enctype="multipart/form-data">
               
      <!-- FK categrpno 지정 -->
      <input type='hidden' name="eb_no" id="eb_no" value="${ebookVO.eb_no }">
      <input type="hidden" name="cg_no" id="cg_no" value="${cg_no }">
      
      <div class="form-group">
        <div class="col-md-6 col-sm-12 mb-3 float-left" style="display: inline-block;">
          <label>카테고리 그룹</label>
          <select class="form-control" id="cglist" required>
            <option value="">그룹 선택</option>
            <c:forEach var="cglist" items="${cglist }">
              <option value="${cglist.cg_no }" ${cg_no == cglist.cg_no ? "selected" : "" }>${cglist.cg_name }</option>
            </c:forEach>
          </select>
        </div>
        <div class="col-md-6 col-sm-12" style="display: inline-block;">
          <label>카테고리</label>
          <select class="form-control" id="catelist" name="cateno" required>
            <option value="">카테고리 선택</option>
          </select>
        </div>
      </div>
      
      
      <div class="form-group"> 
        <div class="col-md-6">
          <label>제목</label>  
          <input type='text' class="form-control" name="eb_title" value="${ebookVO.eb_title }" placeholder="제목" required="required">
        </div>
      </div>
            
      <div class="form-group">
        <div class="col-md-6 col-sm-12 mb-3 float-left" style="display: inline-block;">
          <label>저자</label>
          <input type='text' class="form-control" name='eb_author' value="${ebookVO.eb_author }" placeholder="저자" required="required">
        </div>
        <div class="col-md-6 col-sm-12" style="display: inline-block;">
          <label>출판사</label>
          <input type='text' class="form-control" name='eb_publisher' value="${ebookVO.eb_publisher }" placeholder="출판사" required="required">
        </div>
      </div>
      
      <div class="form-group">
        <div class="col-md-5 col-sm-12 mb-3 float-left" style="display: inline-block;">
          <label>가격</label>  
          <input type="number" class="form-control" name="eb_price" value="${ebookVO.eb_price }" placeholder="가격" required="required">
        </div>
        <div class="col-md-5 col-sm-12 float-left" style="display: inline-block;">
          <label>출간일</label>  
          <input type="text" class="form-control" name="eb_pdate" value="${ebookVO.eb_pdate }" placeholder="출간일" required="required">
        </div>
        <div class="col-md-2 col-sm-12" style="display: inline-block;">
          <label>페이지 수</label>
          <input type="number" class="form-control" name="eb_page" value="${ebookVO.eb_page }" placeholder="페이지 수" required="required">
        </div>
      </div>
      
      <div class="form-group">
        <div class="col-md-12">
          <textarea class="form-control" name="eb_useinfo" id="eb_useinfo" rows="5" placeholder="이용안내">${ebookVO.eb_useinfo }</textarea>
        </div>
      </div>
      
      <div class="form-group ebook_device">
        <label style="padding: 0 12px;">지원기기</label>  
        <div class="col-md-12">
          <label for="eb_device1">
            <input type="checkbox" id="eb_device1" name="eb_device" class="chk_device" value="PC (윈도우/MAC)" ${ebookVO.eb_device.indexOf("PC (윈도우/MAC)") > -1 ? "checked" : "" }> PC (윈도우/MAC)
          </label>
          <label for="eb_device2">
            <input type="checkbox" id="eb_device2" name="eb_device" class="chk_device" value="아이폰" ${ebookVO.eb_device.indexOf("아이폰") > -1 ? "checked" : "" }> 아이폰
          </label>
          <label for="eb_device3">
            <input type="checkbox" id="eb_device3" name="eb_device" class="chk_device" value="아이패드" ${ebookVO.eb_device.indexOf("아이패드") > -1 ? "checked" : "" }> 아이패드
          </label>
          <label for="eb_device4">
            <input type="checkbox" id="eb_device4" name="eb_device" class="chk_device" value="안드로이드폰" ${ebookVO.eb_device.indexOf("안드로이드폰") > -1 ? "checked" : "" }> 안드로이드폰
          </label>
          <label for="eb_device5">
            <input type="checkbox" id="eb_device5" name="eb_device" class="chk_device" value="안드로이드 패드" ${ebookVO.eb_device.indexOf("안드로이드 패드") > -1 ? "checked" : "" }> 안드로이드 패드
          </label>
          <label for="eb_device6">
            <input type="checkbox" id="eb_device6" name="eb_device" class="chk_device" value="전자책 단말기" ${ebookVO.eb_device.indexOf("전자책 단말기") > -1 ? "checked" : "" }> 전자책 단말기
          </label>
        </div>
      </div>
      
      <div class="form-group">   
        <div class="col-md-12">
          <label>이북 파일</label>
          <%-- 실제 컬럼명: file1, Spring File 객체 대응: file1MF --%>
          <c:if test="${ebookVO.eb_file1.length() > 0 }">
          <div id="eb_file1_div" class="my-1">
            <span>현재 등록된 파일 : </span>
              <span style="color: #3e64d3;font-weight: bold;">${ebookVO.eb_file1 }</span>
              <a href="javascript:file_delete_modal(1)" data-type="1" class="text-danger file_delete">삭제</a>
          </div>
          </c:if>
          <input type="hidden" name="eb_file1" value="${ebookVO.eb_file1 }">
          <input type="hidden" name="eb_size1" value="${ebookVO.eb_size1 }">
          
          <input type='file' class="form-control" name='file1MF' id='file1MF' 
                    value='' placeholder="파일 선택">
        </div>
      </div>
      
      <div class="form-group">   
        <div class="col-md-12">
          <label>메인 이미지</label>
          <%-- 실제 컬럼명: file2, Spring File 객체 대응: file2MF --%>
          <c:if test="${ebookVO.eb_file2.length() > 0 }">
          <div id="eb_file2_div" class="my-1">
            <span>현재 등록된 메인 이미지 : </span>
              <span style="color: #3e64d3;font-weight: bold;">${ebookVO.eb_file2 }</span>
              <a href="javascript:file_delete_modal(2)" data-type="2" class="text-danger file_delete">삭제</a>
          </div>
          </c:if>
          <input type="hidden" name="eb_file2" value="${ebookVO.eb_file2 }">
          <input type="hidden" name="eb_size2" value="${ebookVO.eb_size2 }">
          
          <input type='file' class="form-control" name='file2MF' id='file2MF' 
                    value='' placeholder="파일 선택">
        </div>
      </div>
      
      <div class="form-group">   
        <div class="col-md-12">
          <label>상세 정보</label>
          <textarea class="form-control" name="eb_infor" id="eb_infor" rows="10" placeholder="요약 및 정보(에디터 삽입)">${ebookVO.eb_infor }</textarea>
        </div>
      </div>
      
      <DIV class="float-right">
        <button type="submit" class="btn btn-success">수정</button>
        <button type="button" onclick="location.href='./list.do?cateno=${ebookVO.cateno }'" class="btn btn-default" style="border: 1px solid #ccc;">목록</button>
      </DIV>
      
    </FORM>
    <div class="float-right mx-1">
      <form id="delete_frm" name="delete_frm" method="post" action="./delete.do" class="form-horizontal">
        <input type='hidden' name="eb_no" id="eb_no" value="${ebookVO.eb_no }">
        <button type="button" class="btn btn-danger" id="delete_btn">삭제</button>
      </form>
    </div>
    <div class="float-left">
      <a href="./attachfile/list.do?cateno=${ebookVO.cateno }&eb_no=${ebookVO.eb_no }" class="btn btn-default" style="border: 1px solid #ccc;" id="attach_btn">다중파일 관리</a>
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
          [ ${ebookVO.eb_title } ] 을(를) 삭제하시겠습니까?
          <form name='frm_delete' id='frm_delete' action='./delete.do'
                      method='POST'>                    
              <input type='hidden' name='eb_no' id='eb_no' value='${param.eb_no }'>
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
    
    $('#cglist').on( 'change', getCatelist );

    var frm = $('#frm');
    var cg_no = $('#cg_no', frm);
    var cateno = ${ebookVO.cateno };

    getCatelist();
    
  });

  function delete_modal() {
    $('#modal_panel_delete').modal();
  }
  

  function file_delete_modal(type) {
    var title = '';
    var content = '';

    if ( type == 1 ) {
      title = '이북 파일 삭제';
      content = '이북 파일을 삭제하시겠습니까?';
    } else {
      title = '메인 이미지 삭제';
      content = '메인 이미지를 삭제하시겠습니까?';
    }

    $('.modal-title').html(title);
    $('.modal-body').html(content);
    $('.modal-body').attr('data-type', type);
    
    $('#modal_panel_file').modal();
  }

  function delete_proc() {
    $('#frm_delete').submit();
  }

   
  function getCatelist() {
    
    var frm = $('#frm');
    
    var cg_no = $('#cglist > option:selected').val();
    var params = 'cg_no=' + cg_no;
    var cateno = ${ebookVO.cateno };

    
    $.ajax({
      url: '../cate/cate_list_by_ajax.do', // spring execute
      type: 'get',  // get
      cache: false, // 응답 결과 임시 저장 취소
      async: true,  // true: 비동기 통신
      dataType: 'json', // 응답 형식: json, html, xml...
      data: params,      // 데이터
      success: function(rdata) { // 서버로부터 성공적으로 응답이 온경우
        var selected = "";
        var option = "";
        //console.log(rdata.list);
        if ( cg_no != 0 ) {
          for ( var i = 0; i < rdata.list.length; i++ ) {
            if ( cateno == rdata.list[i].cateno ) {
              selected = "selected";              
            } 
            option += "<option value='"+ rdata.list[i].cateno +"' " + selected + " >"+ rdata.list[i].name +"</option>";
          }
        } else {
          option += "<option value=''>카테고리 선택</option>";
        }
        $('#catelist').html(option);
      },
      // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
      error: function(request, status, error) { // callback 함수
        var msg = 'ERROR\n';
        msg += 'request.status: '+request.status + '\n';
        msg += 'message: '+error;
        console.log(msg);
      }
    });

  }

  // 파일 삭제 처리
  function file_delete_proc() {

    var type = $('.modal-body').attr('data-type');
    var params = "type="+type+"&eb_no="+${param.eb_no}
    
    $.ajax({
      url: './file_delete.do', // spring execute
      type: 'post',  // post
      cache: false, // 응답 결과 임시 저장 취소
      async: true,  // true: 비동기 통신
      dataType: 'json', // 응답 형식: json, html, xml...
      data: params,      // 데이터
      success: function(rdata) { // 서버로부터 성공적으로 응답이 온경우
        if ( rdata.cnt == 1 ) {
          $('#modal_panel .modal-body').addClass('alert alert-success');
          $('#modal_panel .modal-body').html('파일 삭제가 완료되었습니다.');
          
          if ( type == 1 ) {
            $('#eb_file1_div').remove();
          } else {
            $('#eb_file2_div').remove();
          }
          
        } else {
          $('#modal_panel .modal-body').addClass('alert alert-danger');
          $('#modal_panel .modal-body').html('파일 삭제에 실패했습니다..');
        }
        
        $('#modal_panel').modal();
      },
      // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
      error: function(request, status, error) { // callback 함수
        var msg = 'ERROR\n';
        msg += 'request.status: '+request.status + '\n';
        msg += 'message: '+error;
        console.log(msg);
      }
    });
  }
  
</script>
<jsp:include page="/adm/menu/bottom.jsp" flush='false' />