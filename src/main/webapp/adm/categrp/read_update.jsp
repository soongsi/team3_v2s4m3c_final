<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />

<DIV class='title_line'>${title }</DIV>
  
  <div> 
    <form name='frm' method='post' action='./update.do' class="form-horizontal">
      <div class="form-group col-md-5 float-left">
        <input type="hidden" name="cg_no" value="${categrpVO.cg_no }">
        <label class="control-label">카테고리 그룹 이름</label>
        <div>
         <input type='text' name='cg_name' value="${categrpVO.cg_name }" required="required" 
                    autofocus="autofocus" class="form-control">
        </div>
      </div>
      <div class="form-group col-md-2 float-left">
        <label class="control-label">출력 순서</label>
        <div>
         <input type='number' name='cg_seqno' value="${categrpVO.cg_seqno }" required="required" 
                   placeholder="" min="1" max="1000" step="1" 
                   class="form-control" >
        </div>
      </div>  
      <div class="form-group col-md-2 float-left">
        <label class="control-label">출력 형식</label>
        <div>
          <select name='cg_visible' class="form-control">
            <option value='Y' ${categrpVO.cg_visible == 'Y' ? "selected" : "" }>Y</option>
            <option value='N' ${categrpVO.cg_visible == 'N' ? "selected" : "" }>N</option>
          </select>
        </div>
      </div>   
    
      <div class="col-md-3 float-left" style="line-height: 30px;">
        <label class="control-label"></label>
        <div>
          <button type="submit" class="btn btn-primary">수정</button>
          <button type="button" onclick="location.href='./list.do'" class="btn" style="border: 1px solid #ccc;">목록</button>
        </div>
      </div>
    </form>
  </div>
 
  <TABLE class='table table-striped'>
    <colgroup>
      <col style='width: 5%;' />
      <col style='width: 35%;' />
      <col style='width: 15%;' />
      <col style='width: 10%;' />
      <col style='width: 20%;' />
    </colgroup>
    <thead>
      <tr>
        <th class="th_bs">순서</th>
        <th class="th_bs">대분류명</th>
        <th class="th_bs">등록일</th>
        <th class="th_bs">출력</th>
        <th class="th_bs">기타</th>
      </tr>
    </thead>
    
    <tbody>
      <c:forEach var="categrpVO" items="${list}" varStatus="status">
        <c:set var="cg_no" value="${categrpVO.cg_no }" />
        <tr>
          <td class="td_bs">${categrpVO.cg_seqno }</td>
          <td><a href="../cate/list.do?cg_no=${cg_no }">${categrpVO.cg_name }</a></td>
          <td class="td_bs">${categrpVO.cg_rdate.substring(0, 10) }</td>
          <td class="td_bs">
          <c:choose>
            <c:when test="${categrpVO.cg_visible == 'Y'}">
              <A href="./update_visible.do?cg_no=${cg_no }&cg_visible=N">
                <i class="fas fa-eye"></i>
              </A>
            </c:when>
            <c:otherwise>
              <A href="./update_visible.do?cg_no=${cg_no }&cg_visible=Y">
                <i class="fas fa-eye-slash"></i>
              </A>
            </c:otherwise>
          </c:choose>
          </td>
          <td class="td_bs">
            <a href="./read_update.do?cg_no=${cg_no }">
              <i class="fas fa-pencil-alt"></i>
            </a>
            <a href="./read_delete.do?cg_no=${cg_no }">
              <i class="fas fa-trash-alt"></i>
            </a> 
            <a href="./update_seqno_up.do?cg_no=${cg_no }">
              <i class="fas fa-arrow-up"></i>
            </a>
            <a href="./update_seqno_down.do?cg_no=${cg_no }">
              <i class="fas fa-arrow-down"></i>
            </a>
          </td>
        </tr>
      </c:forEach>
    </tbody>
   
  </TABLE>
 
<jsp:include page="/adm/menu/bottom.jsp" flush='false' />