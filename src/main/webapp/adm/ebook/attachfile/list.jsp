<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />

<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>

  <DIV class='title_line'>
    ${title } > 첨부파일 관리
  </DIV>
  <div class='menu_line'></div>
  <DIV class="ebook_form" style='width: 100%;margin-bottom: 20px;display: inline-block;'>
    <FORM name='frm' method='POST' action='./create.do' 
                enctype="multipart/form-data" class="form-horizontal">
               
      <!-- FK cateno 지정 -->
      <%-- <input type='hidden' name='cateno' id='cateno' value="${param.cateno }"> --%>
      <input type='hidden' name='eb_no' id='eb_no' value="${param.eb_no }">
      
      <div class="form-group">   
        <div class="col-md-12">
          <input type='file' class="form-control" name='fnamesMF'  
                     value='' placeholder="파일 선택" multiple="multiple">
        </div>
      </div>

      <DIV class="float-right">
        <button type="submit" class="btn btn-info">파일 전송</button>
        <button type="button" 
                    onclick="location.href='../list.do?cateno=${param.cateno}'" 
                    class="btn btn-info">취소[목록]</button>
      </DIV>
       
    </FORM>
  </DIV>
  
  <!-- 선택 삭제 -->
  <form id="choice_frm" method="post" class="form-horizontal" action="./chk_delete.do">
    <input type="hidden" id="chk_del_val" name="chk_del_val" value="">
    <input type="hidden" name="rurl" id="rurl" value="./list.do?eb_no=${param.eb_no }">
    <button type="button" id="ch_del_btn" class="btn btn-danger">선택 삭제</button>
  </form>
  
  <!-- 개별 삭제 -->
  <form name="frm" id="frm" method="post" action="./delete.do">
    <input type="hidden" name="attachfileno" id="attachfileno" value="">
    <input type="hidden" name="rurl" id="rurl" value="./list.do?eb_no=${param.eb_no }">
  </form>
  
  <!-- Modal 알림창 시작 -->
  <div id="modal_panel" class="modal fade"  role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title" id='modal_title'></h4><!-- 제목 -->
          <button type="button" class="close" data-dismiss="modal">×</button>
        </div>
        <div class="modal-body">
          <p id='modal_content'></p>  <!-- 내용 -->
        </div>
        <div class="modal-footer">
          <button type="button" id="btn_delete" data-focus="" data-content="" class="btn btn-danger" data-dismiss="modal">삭제 진행</button>
          <button type="button" id="btn_close" data-focus="" class="btn btn-default" data-dismiss="modal" style="border: 1px solid #ccc;">취소</button>
        </div>
      </div>
    </div>
  </div> <!-- Modal 알림창 종료 -->
  
  <!-- Modal 알림창 시작 (선택 삭제) -->
  <div id="modal_panel_choice" class="modal fade"  role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title" id='modal_title'>선택 파일 삭제</h4><!-- 제목 -->
          <button type="button" class="close" data-dismiss="modal">×</button>
        </div>
        <div class="modal-body">
          <p id='modal_content' class="alert alert-danger">
                      선택한 파일을 삭제하시겠습니까? <br>파일을 삭제하면 복구할 수 없습니다.<br>
          </p>  <!-- 내용 -->
        </div>
        <div class="modal-footer">
          <button type="button" id="btn_choice" data-focus="" data-content="" class="btn btn-danger" data-dismiss="modal">삭제 진행</button>
          <button type="button" id="btn_close" data-focus="" class="btn btn-default" data-dismiss="modal" style="border: 1px solid #ccc;">취소</button>
        </div>
      </div>
    </div>
  </div> <!-- Modal 알림창 종료 -->
  
  <!-- Modal 알림창 시작 (선택 삭제) -->
  <div id="modal_panel_alert" class="modal fade"  role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title" id='modal_title'>알림</h4><!-- 제목 -->
          <button type="button" class="close" data-dismiss="modal">×</button>
        </div>
        <div class="modal-body">
          <p id='modal_content' class="alert alert-danger">
                        삭제할 파일을 선택해 주세요.
          </p>  <!-- 내용 -->
        </div>
        <div class="modal-footer">
          <button type="button" id="btn_close" data-focus="" class="btn btn-default" data-dismiss="modal" style="border: 1px solid #ccc;">취소</button>
        </div>
      </div>
    </div>
  </div> <!-- Modal 알림창 종료 -->
  
  <TABLE class='adm_ebook table table-striped'>
  <colgroup>
    <col style="width: 5%;"/>
    <col style='width: 5%;'/>
    <col style='width: 15%;'/>
    <col style='width: 15%;'/>
    <col style='width: 15%;'/> 
    <col style='width: 20%;'/> 
    <col style='width: 10%;'/>               
    <col style='width: 30%;'/>
  </colgroup>
  <thead>  
  <TR>
    <th class="th_bs">
      <input type="checkbox" id="chk_all" name="chk_all" value="1">
    </th>
    <TH class="th_bs">번호</TH>
    <TH class="th_bs">파일</TH>
    <TH class="th_bs">원본<br>파일명</TH>
    <TH class="th_bs">저장<br>파일명</TH>
    <TH class="th_bs">Preview<br>파일명</TH>
    <TH class="th_bs">등록일</TH>
    <TH class="th_bs">기타</TH>
  </TR>
  </thead>
  
  <tbody>
  
  <c:forEach var="attachfileVO" items="${list }">
    <c:set var="fname" value="${attachfileVO.fname.toLowerCase() }" />
    <tr>
      <td class="td_bs">
        <input type="checkbox" id="chk_file_no" name="chk_file_no" value="${attachfileVO.attachfileno }">
      </td>
      <td class="td_bs">${attachfileVO.attachfileno }</td>
      <td class="td_bs">
        <c:choose>
          <c:when test="${fname.endsWith('jpg') || fname.endsWith('png') || fname.endsWith('gif')}">
            <IMG src="${pageContext.request.contextPath}/ebook/attachfile/storage/${attachfileVO.thumb }"> 
          </c:when>
          <c:otherwise> <!-- 이미지가 아닌 일반 파일 -->
            ${attachfileVO.fname}
          </c:otherwise>
        </c:choose>
      </td>
      <td class="td_bs">${attachfileVO.fname}</td>
      <td class="td_bs">${attachfileVO.fupname}</td>
      <td class="td_bs">${attachfileVO.thumb}</td>      
      <td class="td_bs">${attachfileVO.rdate.substring(0, 10)}</td>
      <td style='text-align: center; vertical-align: middle;'>
        <a href="javascript:delete_form(${attachfileVO.attachfileno})">
          <i class="far fa-trash-alt"></i>
        </a>
      </td>
    </tr>
  </c:forEach>
  </tbody> 
</TABLE>

<script type="text/javascript">

  $(function() {
    $( '#btn_delete' ).on( 'click', delete_proc );
    $( '#btn_choice' ).on( 'click', chk_delete_proc );
  });
  
  function delete_form(attachfileno) {
    $('#btn_delete').attr('data-content', attachfileno); // 삭제할 파일 임시 저장
    
    var msg = '파일을 삭제하시겠습니까? <br>파일을 삭제하면 복구할 수 없습니다.<br>';
    msg += '삭제 진행시 삭제 진행 버튼을 클릭하세요.';
  
    $('#modal_content').attr('class', 'alert alert-danger');
    $('#modal_title').html('파일 삭제');
    $('#modal_content').html(msg);
    $('#modal_panel').modal();
  
    return false;
  }
  
  function delete_proc() {
    //alert('삭제 진행 버튼 누름');
    var attachfileno = $('#btn_delete').attr('data-content');
    var frm = $('#frm');
    
    $('#attachfileno', frm).val( attachfileno );
  
    frm.submit();
  }

  function chk_delete_proc() {
    //alert('삭제 진행 버튼 누름');
    var frm = $('#choice_frm');  
    frm.submit();
  }

  /* 전체 체크 */
  $("#chk_all").click( function () {
    $( "input:checkbox[name='chk_file_no']" ).prop( "checked", function () {
      return !$(this).prop( "checked" );
    } );
  });


  /* 선택 삭제 */
  $('#ch_del_btn').click( function () {
    var chk_val = '';
    var delchk = $("input[name=chk_file_no]:checked");
    for ( var i = 0; i < delchk.length; i++ ) {
      if ( i == delchk.length - 1 ) {
        chk_val += delchk[i].value;
      } else {
        chk_val += delchk[i].value + ",";
      }
    }

    if ( delchk.length > 0 ) {
      $('#modal_panel_choice').modal();
      $('#chk_del_val').val( chk_val );
    } else {
      $('#modal_panel_alert').modal();
    }
    

    // alert(chk_val);
  } );

  
</script>

<jsp:include page="/adm/menu/bottom.jsp" flush='false' />