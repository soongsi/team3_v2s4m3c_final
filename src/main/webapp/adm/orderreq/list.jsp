<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/adm/menu/left.jsp" flush='false' />
<jsp:include page="/adm/menu/top.jsp" flush='false' />


<DIV class='title_line'>주문요청</DIV>


<TABLE class='table table-hover'>
  <colgroup>
      <col style='width: 15%;'/>
      <col style='width: 20%;'/>
      <col style='width: 10%;'/>
      <col style="width: 25%;"/>
      <col style="width: 10%;"/>
      <col style="width: 20%;"/>
  </colgroup>
  <thead>
    <tr>
      <TH class="th_bs">
        <input type="checkbox" id="check_all" name="check_all" data-orderno="${orderreqVO.order_no}">
        <button type="button" id="btn_select_delete" class="btn btn-danger" style="color: #fff; text-align: right; padding: 4px 7px;">삭제</button>
      </TH>    
      <TH class="th_bs" style="background-color: #f0f0f5;">결제 번호</TH>    
      <TH class="th_bs">결제 아이디</TH>
      <TH class="th_bs">상품</TH>
      <TH class="th_bs">가격</TH>
      <TH class="th_bs">주문 일시</TH>
    </tr>
  </thead>

  <tbody>
    <c:forEach var="orderreqVO"  items="${list}" >  <!-- request 객체에 접근 -->
      <c:set var="order_no" value="${orderreqVO.order_no}" />
      <c:set var="payid" value="${orderreqVO.payid}" />
      <c:set var="user_id" value="${orderreqVO.user_id}" />
      <c:set var="eb_no" value="${orderreqVO.eb_no}" />
      <c:set var="price" value="${orderreqVO.price}" />
      <c:set var="rdate" value="${orderreqVO.rdate}" />
      <tr>
        <td class="td_bs">
          <input type="checkbox" id="chk_orderno" name="chk_orderno" class="chk_orderno" data-orderno="${order_no }">
        </td>
        <td class="td_bs">${payid}</td>
        <td class="td_bs">${user_id }</td>
        <td class="td_bs">
          <a href="../ebook/read.do?eb_no=${eb_no }">${eb_no}</a>
        </td>
        <td class="td_bs">
          <fmt:formatNumber value = "${price}" pattern="#,### 원" />
        </td>
        <td class="td_bs">${rdate }</td>
      </tr>                                                        
    </c:forEach>                      
  </tbody>
</TABLE>

<script>
     
    $(function(){ 
      $('#check_all').on('click', select_all_checkbox);                      //  체크박스 전체 선택/전체 해제
      $(".chk_orderno").on('click', dismiss_checkall_checkbox);         //  전체 체크박스 해제
      // $('#btn_select_delete').on('click', delete_selected_list_proc);      // 선택된 레코드 삭제
    }); 
    

    /*  선택 삭제   
    function delete_selected_list_proc(){
      var double_check = confirm("선택하신 상품이 삭제됩니다.");
      if(double_check) {
           var orderno_list = [];
           
           $("input[name='order_no']:checked").each(function() {
             orderno_list.push($(this).attr("data-orderno"));  
            }); // 체크된 항목을 배열에 저장

            var param = { 
                "orderno_list" : orderno_list
            };  // 카트번호 배열 저장
            
           $.ajax({
               url: "./delete_ajax.do",
               dataType: "json",
               type: "post",
               data: param,
               success: function(rdata){
                    if(rdata.result !=  0) {
                      location.href="./list.do";
                    } else {
                      alert("ajax error!");
                    }
                  }, 
                  error: function(request, status, error) {
                    var msg = 'ERROR request.status: '+request.status + '/ ' + error;
                    console.log(msg); // Chrome console 출력
                  }  // Ajax 통신 error, 응답 코드가 200이 아닌경우, dataType이 다른경우 

            });  //   ajax 호출
      } // if end 
    
    } // end
   
    */
   /*     체크박스 전체 선택/전체 해제      */
   function select_all_checkbox() {
      if ($('#check_all').prop("checked")) { 
         $(".chk_orderno").prop("checked", true);
         price_sum();  
      } else {
        $(".chk_orderno").prop("checked", false);
        price_sum();
      }       
    }

     /*   전체 선택 체크박스 체크 해제  */
    function dismiss_checkall_checkbox() {
      $("#check_all").prop("checked", false);  
    }

    

</script>

<jsp:include page="/adm/menu/bottom.jsp" flush='false' />


