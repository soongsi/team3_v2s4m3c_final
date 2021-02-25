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
    > 신규 등록
</DIV>
 
  <div class='menu_line'></div>
  <DIV class="ebook_form" style='width: 100%;'>
    <FORM name='frm' method='POST' action='./create.do' class="form-horizontal" enctype="multipart/form-data">
               
      <!-- FK categrpno 지정 -->
      <input type='hidden' name='cateno' id='cateno' value='${param.cateno }'>
      
      <div class="form-group"> 
        <div class="col-md-6">
          <label>제목</label>  
          <input type='text' class="form-control" name='eb_title' value='' placeholder="제목" required="required">
        </div>
      </div>
      
      <div class="form-group">
        <div class="col-md-6 col-sm-12 mb-3 float-left" style="display: inline-block;">
          <label>저자</label>
          <input type='text' class="form-control" name='eb_author' value="" placeholder="저자" required="required">
        </div>
        <div class="col-md-6 col-sm-12" style="display: inline-block;">
          <label>출판사</label>
          <input type='text' class="form-control" name='eb_publisher' value="" placeholder="출판사" required="required">
        </div>
      </div>
      
      <div class="form-group">
        <div class="col-md-5 col-sm-12 mb-3 float-left" style="display: inline-block;">
          <label>가격</label>  
          <input type="number" class="form-control" name="eb_price" value="" placeholder="가격" required="required">
        </div>
        <div class="col-md-5 col-sm-12 float-left" style="display: inline-block;">
          <label>출간일</label>  
          <input type="text" class="form-control" name="eb_pdate" value="" placeholder="ex) 2020-01-01" required="required">
        </div>
        <div class="col-md-2 col-sm-12" style="display: inline-block;">
          <label>페이지 수</label>
          <input type="number" class="form-control" name="eb_page" value="" placeholder="페이지 수" required="required">
        </div>
      </div>
      
      <div class="form-group">
        <div class="col-md-12">
          <textarea class="form-control" name="eb_useinfo" id="eb_useinfo" rows="5" placeholder="이용안내"></textarea>
        </div>
      </div>
      
      <div class="form-group ebook_device">
        <label style="padding: 0 12px;">지원기기</label>  
        <div class="col-md-12">
          <label for="eb_device1">
            <input type="checkbox" id="eb_device1" name="eb_device" class="chk_device" value="PC (윈도우/MAC)"> PC (윈도우/MAC)
          </label>
          <label for="eb_device2">
            <input type="checkbox" id="eb_device2" name="eb_device" class="chk_device" value="아이폰"> 아이폰
          </label>
          <label for="eb_device3">
            <input type="checkbox" id="eb_device3" name="eb_device" class="chk_device" value="아이패드"> 아이패드
          </label>
          <label for="eb_device4">
            <input type="checkbox" id="eb_device4" name="eb_device" class="chk_device" value="안드로이드폰"> 안드로이드폰
          </label>
          <label for="eb_device5">
            <input type="checkbox" id="eb_device5" name="eb_device" class="chk_device" value="안드로이드 패드"> 안드로이드 패드
          </label>
          <label for="eb_device6">
            <input type="checkbox" id="eb_device6" name="eb_device" class="chk_device" value="전자책 단말기"> 전자책 단말기
          </label>
        </div>
      </div>
      
      <div class="form-group">   
        <div class="col-md-12">
          <label>이북 파일</label>
          <%-- 실제 컬럼명: file1, Spring File 객체 대응: file1MF --%>
          <input type='file' class="form-control" name='file1MF' id='file1MF' 
                    value='' placeholder="파일 선택">
        </div>
      </div>
      
      <div class="form-group">   
        <div class="col-md-12">
          <label>상세 정보</label>
          <textarea class="form-control" name="eb_infor" id="eb_infor" rows="10" placeholder="요약 및 정보(에디터 삽입)"></textarea>
        </div>
      </div>
      
      <DIV class="float-right">
        <button type="submit" class="btn btn-primary">등록</button>
        <button type="button" 
                    onclick="javascript:reset()" 
                    class="btn" style="border: 1px solid #ccc;">취소</button>
      </DIV>
       
    </FORM>
  </DIV>

<jsp:include page="/adm/menu/bottom.jsp" flush='false' />