<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />


<DIV class='title_line'>${title }</DIV>
  
  <div> 
    <form name='frm' method='post' action='./update.do' class="form-horizontal">
     <div class="form-group col-md-1 float-left">
         <label class="control-label">순서</label>
         <div>
           <input type='text' name='seqno' id='seqno' value="${surveyVO.seqno}" required="required" 
                      autofocus="autofocus" class="form-control">
         </div>
      </div>    
     <div class="form-group col-md-4 float-left">
        <input type="hidden" name="survey_no" value="${surveyVO.survey_no}">
         <label class="control-label">제목</label>
         <div>
           <input type='text' name='title' id='title' value="${surveyVO.title}" required="required" 
                      autofocus="autofocus" class="form-control">
         </div>
      </div>
      <div class="form-group col-md-2 float-left">
         <label class="control-label">시작일</label>
         <div>
           <input type='date' name='startdate' id='startdate' OnClick="todayIs();" value="${surveyVO.startdate}"  required="required" 
                     class="form-control" >
         </div>
      </div>  
      <div class="form-group col-md-2 float-left">
         <label class="control-label">종료일</label>
         <div>
           <input type='date' name='enddate' id='enddate' value="${surveyVO.enddate}"  required="required" 
                    class="form-control"  >
         </div>
      </div>   
      <div class="form-group col-md-1 float-left">
         <label class="control-label">상태</label>
         <div>
            <select name='continueyn' id='continueyn' class="form-control">
              <option value='Y' ${surveyVO.continueyn == 'Y' ? "selected='selected'" : "" }>진행</option>
              <option value='N' ${surveyVO.continueyn == 'N' ? "selected='selected'" : "" }>마감</option>
            </select>
         </div>
      </div>   
    
      <div class="col-md-2 float-left" style="line-height: 30px;">
        <label class="control-label"></label>
        <div>
          <button type="submit" class="btn btn-primary">수정</button>
          <button type="button" onclick="location.href='./list.do'" class="btn" style="border: 1px solid #ccc;">취소</button>
        </div>
      </div>
    </form>
  </div>
  
  <TABLE class='table table-striped'>
    <colgroup>
        <col style='width: 10%;'/>
        <col style='width: 30%;'/>
        <col style="width: 15%;"/>
        <col style="width: 15%;"/>
        <col style="width: 10%;"/>
        <col style="width: 10%;"/>
        <col style="width: 10%;"/>
    </colgroup>
    <thead>
      <tr>
        <TH class="th_bs">번호</TH>     
        <TH class="th_bs">제목</TH>
        <TH class="th_bs">시작일</TH>
        <TH class="th_bs">종료일</TH>
        <TH class="th_bs">상태</TH>
        <TH class="th_bs">설문결과</TH>
        <TH class="th_bs">기타</TH>
      </tr>
    </thead>
    
       <tbody>
      <c:forEach var="surveyVO" items="${list}" varStatus="status" begin='1'>  <!-- request 객체에 접근 -->
        <c:set var="survey_no" value="${surveyVO.survey_no}" />
        <c:set var="title" value="${surveyVO.title}" />
        <c:set var="startdate" value="${surveyVO.startdate}" />
        <c:set var="enddate" value="${surveyVO.enddate}" />
        <c:set var="continueyn" value="${surveyVO.continueyn}" />
        <c:set var="survey_result" value="${surveyVO.survey_result}" />
        <TR>
          <TD class="td_bs">${status.index}</TD>
          <TD class="td_bs_left" style="padding:6px 0 6px 0;">
            <A href="../survey_item/read.do?survey_no=${survey_no}">${title}</A>
          </TD>               
          <TD class="td_bs">${startdate}</TD>           
          <TD class="td_bs">${enddate}</TD> 
          <TD class="td_bs">
          <c:choose>
            <c:when test="${surveyVO.continueyn =='Y'}">
             <A href="./update_continueyn.do?survey_no=${survey_no}&continueyn=${continueyn} "><img src="./images/ongoing.png"></A>
            </c:when>
            <c:otherwise>
             <A href="./update_continueyn.do?survey_no=${survey_no}&continueyn=${continueyn} "><img src="./images/blocked.png"></A>
            </c:otherwise>
          </c:choose>
          </TD>  
          <TD class="td_bs"><a href="#">${survey_result}</a></TD>
          <TD class="td_bs">
            <a href="./read_update.do?survey_no=${survey_no }">
              <i class="fas fa-pencil-alt" title="수정"></i>
            </a>
            <a href="./read_delete.do?survey_no=${survey_no }">
              <i class="fas fa-trash-alt" title="삭제"></i>
            </a> 
          </TD>                              
      </c:forEach>                  
      </tbody>
    </TABLE>
 
<jsp:include page="/adm/menu/bottom.jsp" flush='false' />