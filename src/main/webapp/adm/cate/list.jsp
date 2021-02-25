<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />

<DIV class='title_line'>
  <a href="../categrp/list.do">카테고리 그룹</a> 
  > ${title }
</DIV>
<div>
  <form name='frm' method='post' action='./create.do'
    class="form-horizontal">
    <div class="form-group col-md-2 float-left">
      <label class="control-label">그룹 번호</label>
      <div>
        <input type='number' name='cg_no' value='${param.cg_no }' required="required" 
                min='1' max='99999' step='1' class="form-control">
      </div>
    </div>
    <div class="form-group col-md-3 float-left">
      <label class="control-label">카테고리</label>
      <div>
        <input type='text' name='name' value='' required="required" class="form-control">
      </div>
    </div>
    
    <div class="form-group col-md-2 float-left">
      <label class="control-label">출력 순서</label>
      <div>
        <input type='number' name='seqno' value='1'
          required="required" placeholder="" min="1" max="1000" step="1"
          class="form-control">
      </div>
    </div>
    <div class="form-group col-md-2 float-left">
      <label class="control-label">출력 여부</label>
      <div>
        <select name='visible' class="form-control">
          <option value='Y' selected="selected">Y</option>
          <option value='N'>N</option>
        </select>
      </div>
    </div>

    <div class="col-md-3 float-left" style="line-height: 4;">
      <label class="control-label"></label>
      <div class="cate_reg_div my-3">
        <button type="submit" class="btn btn-primary">등록</button>
        <button type="button" onclick="location.href='./list.do'"
          class="btn" style="border: 1px solid #ccc;">목록</button>
      </div>
    </div>
  </form>
</div>

  
<TABLE class='adm_cate table table-striped'>
  <colgroup>
    <col style='width: 5%;'/>
    <col style='width: 35%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>           
    <col style='width: 15%;'/>
  </colgroup>
 
  <thead>  
  <TR>
    <TH class="th_bs">순서</TH>
    <TH class="th_bs">카테고리</TH>
    <TH class="th_bs">등록일</TH>
    <TH class="th_bs">출력</TH>
    <TH class="th_bs">기타</TH>
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