<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />

<DIV class='title_line'>${title }</DIV>

<div>
  <form name='frm' method='post' action='./delete.do'
    class="form-horizontal">
    <div class="msg_warning">카테고리를 삭제하면 복구 할 수 없습니다.</div>
    <input type="hidden" name="cateno" value="${cateVO.cateno }">
    <div style="width: 80%;margin: 10px auto;">
    
      <div class="form-group col-md-2 float-left text-center">
        <label class="control-label">그룹 번호</label>
        <div>${cateVO.cg_no }</div>
      </div>
      
      <div class="form-group col-md-3 float-left text-center">
        <label class="control-label">카테고리</label>
        <div>
          ${cateVO.name }
        </div>
      </div>
      
      <div class="form-group col-md-2 float-left text-center">
        <label class="control-label">출력 순서</label>
        <div>
          ${cateVO.seqno }
        </div>
      </div>
      <div class="form-group col-md-2 float-left text-center">
        <label class="control-label">출력 여부</label>
        <div>
          ${cateVO.visible }
        </div>
      </div>
  
      <div class="col-md-3 float-left text-center" style="height: 50px;display: table;">
        <div style="display: table-cell;vertical-align: middle;">
          <div style="margin: 0 auto;">
          <button type="submit" class="btn btn-primary">삭제</button>
          <button type="button" onclick="location.href='./list.do?cg_no=${cateVO.cg_no }'"
            class="btn" style="border: 1px solid #ccc;">목록</button>
          </div>
        </div>
      </div>
    </div>
        
  </form>
</div>
 
  
<TABLE class='table table-striped'>
  <colgroup>
    <col style='width: 5%;'/>
    <col style='width: 35%;'/>
    <col style='width: 20%;'/>
    <col style='width: 10%;'/>    
    <col style='width: 20%;'/>
  </colgroup>
 
  <thead>  
  <TR>
    <TH class='th_bs'>순서</TH>
    <TH class='th_bs'>카테고리</TH>
    <TH class='th_bs'>등록일</TH>
    <TH class='th_bs'>출력</TH>
    <TH class='th_bs'>기타</TH>
  </TR>
  </thead>
  
  <tbody>
  <c:forEach var="cateVO" items="${list}" varStatus="status">
    <c:set var="cateno" value="${cateVO.cateno }"/>
    <tr>
      <td class="td_bs">${cateVO.seqno }</td>
      <td class="td_bs_left"><a href="../ebook/list.do?cateno=${cateno }">${cateVO.name }</a></td>
      <td class="td_bs">${cateVO.rdate.substring(0, 10) }</td>
      <td class="td_bs">
        <c:choose>
          <c:when test="${cateVO.visible == 'Y'}">
            <A href="./update_visible.do?cateno=${cateno }&visible=N&cg_no=${cateVO.cg_no}">
              <i class="fas fa-eye"></i>
            </A>
          </c:when>
          <c:otherwise>
            <A href="./update_visible.do?cateno=${cateno }&visible=Y&cg_no=${cateVO.cg_no}">
              <i class="fas fa-eye-slash"></i>
            </A>
          </c:otherwise>
        </c:choose>
      </td>
      <td class="td_bs">
        <a href="./read_update.do?cateno=${cateno }&cg_no=${cateVO.cg_no}">
          <i class="fas fa-pencil-alt"></i>
        </a>
        <a href="./read_delete.do?cateno=${cateno }&cg_no=${cateVO.cg_no}">
          <i class="fas fa-trash-alt"></i>
        </a> 
        <a href="./update_seqno_up.do?cateno=${cateno }&cg_no=${cateVO.cg_no}">
          <i class="fas fa-arrow-up"></i>
        </a>
        <a href="./update_seqno_down.do?cateno=${cateno }&cg_no=${cateVO.cg_no}">
          <i class="fas fa-arrow-down"></i>
        </a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
 
</TABLE>
 
<jsp:include page="/adm/menu/bottom.jsp" flush='false' />