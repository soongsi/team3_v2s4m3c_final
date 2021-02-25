<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>eBook Store > ${title }</title>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<link href="../css/custom.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    
<script type="text/javascript">

</script>
 
</head> 
 
<body>
<jsp:include page="/menu/top.jsp" />
 
  <DIV class='title_line'>카테고리 그룹 > ${categrpVO.cg_name } 조회(수정)</DIV>
 
  <DIV id='panel_create' style='padding: 10px 0px 10px 0px; background-color: #F9F9F9; width: 100%; text-align: center;'>
    <FORM name='frm_update' id='frm_update' method='POST' action='./update.do'>
      <!-- <input type='hidden' name='lang' id='lang' value='en'> --> <!-- ko, en -->
      <input type='hidden' name='cg_no' id='cg_no' value='${categrpVO.cg_no }'>
      
      <label>그룹 이름</label>
      <input type='text' name='cg_name' value="${categrpVO.cg_name }" required="required" style='width: 25%;'>
 
      <label>순서</label>
      <input type='number' name='cg_seqno' value="${categrpVO.cg_seqno }" required="required" 
                min='1' max='1000' step='1' style='width: 5%;'>
  
      <label>출력 여부</label>
      <select name='cg_visible' id="cg_visible">
        <option value='Y' ${categrpVO.cg_visible == 'Y' ? "selected" : "" }>Y</option>
        <option value='N' ${categrpVO.cg_visible == 'N' ? "selected" : "" }>N</option>
      </select>
       
      <button type="submit" id='submit'>수정</button>
      <button type="button" onclick="location.href='./list.do';">취소</button>
    </FORM>
  </DIV>
 
  
<TABLE class='table table-striped'>
  <colgroup>
    <col style='width: 10%;'/>
    <col style='width: 40%;'/>
    <col style='width: 20%;'/>
    <col style='width: 10%;'/>    
    <col style='width: 20%;'/>
  </colgroup>
 
  <thead>  
  <TR>
    <TH class='th_bs'>순서</TH>
    <TH class='th_bs'>대분류명</TH>
    <TH class='th_bs'>등록일</TH>
    <TH class='th_bs'>출력</TH>
    <TH class='th_bs'>기타</TH>
  </TR>
  </thead>
  
  <tbody>
  <c:forEach var="categrpVO" items="${list}">
    <c:set var="cg_no" value="${categrpVO.cg_no }"/>
    <tr>
      <td class="td_bs">${categrpVO.cg_seqno }</td>
      <td class="td_bs_left"><a href="../cate/list.do?cg_no=${categrpVO.cg_no}">${categrpVO.cg_name }</a></td>
      <td class="td_bs">${categrpVO.cg_rdate.substring(0, 10) }</td>
      <td class="td_bs">
        <c:choose>
          <c:when test="${categrpVO.cg_visible == 'Y'}">
            <A href="./update_visible.do?cg_no=${cg_no }&cg_visible=N">
              <span class="glyphicon glyphicon-ok" ></span>
            </A>
          </c:when>
          <c:otherwise>
            <A href="./update_visible.do?cg_no=${cg_no }&cg_visible=Y">
              <span class="glyphicon glyphicon-remove" ></span>
            </A>
          </c:otherwise>
        </c:choose>
      </td>    
      <td class="td_bs">
        <a href="./read_update.do?cg_no=${cg_no }"><span class="glyphicon glyphicon-pencil"></span></a>
        <a href="./read_delete.do?cg_no=${cg_no }"><span class="glyphicon glyphicon-trash"></span></a>
        <a href="./update_seqno_up.do?cg_no=${cg_no }"><span class="glyphicon glyphicon-arrow-up"></span></a>
        <a href="./update_seqno_down.do?cg_no=${cg_no }"><span class="glyphicon glyphicon-arrow-down"></span></a> 
      </td>
    </tr>
  </c:forEach>
  </tbody>
 
</TABLE>
 
 
<jsp:include page="/menu/bottom.jsp" />
</body>
 
</html>