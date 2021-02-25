package dev.mvc.order_req;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.cart.CartProcInter;
import dev.mvc.cart.CartVO;
import dev.mvc.cs_attachfile.Cs_AttachfileVO;
import dev.mvc.ebook.EbookProcInter;
import dev.mvc.ebook.EbookVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.payway.CodeTable;
import dev.mvc.payway.PaywayCode;
import dev.mvc.tool.Tool;


@Controller
public class OrderreqCont {

  // 멤버 테이블 참조
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc") 
  private MemberProcInter memberProc;
  
  // 이북 테이블 참조
  @Autowired
  @Qualifier("dev.mvc.ebook.EbookProc") 
  private EbookProcInter ebookProc;
  
  // 장바구니 테이블 참조
  @Autowired
  @Qualifier("dev.mvc.cart.CartProc")
  private CartProcInter cartProc;
  
  // 테이블 참조
  @Autowired
  @Qualifier("dev.mvc.order_req.OrderreqProc") 
  private OrderreqProcInter orderreqProc;

  public OrderreqCont() {
    System.out.println("--> OrderreqCont created.");
  }
  
  
  /**
   *                            http://localhost:9090/team3_testgit/ebook/read.do?eb_no=1
   * Ajax 기반 등록 처리 http://localhost:9090/team3_testgit/orderreq/create_ajax.do?eb_no=5&user_id=crm&payway=card&price=8000
   * 
   * @param cartVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/orderreq/create_ajax.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public String create_ajax(OrderreqVO orderreqVO, HttpSession session, CartVO cartVO,
                                  @RequestParam(value="ebno_list[]") List<String> ebno_list,
                                  @RequestParam(value="payway") String payway, 
                                  @RequestParam(value="cartno_list[]") List<String> cartno_list) {
    
    String user_id = (String)session.getAttribute("id");
    JSONObject json = new JSONObject();   
    int cnt = 0;
    int order_no = 0;
    int eb_no = 0;
    int price =0;
    int cart_no = 0;
        
    if(user_id != null) { // 로그인 사용자이면 결과 전송
      orderreqVO.setUser_id(user_id);  // id 전달
      cartVO.setUser_id(user_id);

      // 결제번호(payid) 생성을 위한 로직
      Calendar cal = Calendar.getInstance();
      int year = cal.get(Calendar.YEAR);
      String ym = year + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
      String ymd = ym +  new DecimalFormat("00").format(cal.get(Calendar.DATE));
      String subNum = "";
      
      for(int i = 1; i <= 6; i ++) {
       subNum += (int)(Math.random() * 10);
      }
      
      String payid = ymd + "_" + subNum; //ex) 20210216_373063
      orderreqVO.setPayid(payid);   //  payid 저장
      orderreqVO.setUser_id(user_id);
        
      /*----------------------    주문 테이블 등록 시작 ----------------------  */
      for(String ebno : ebno_list) {    
        eb_no = Integer.parseInt(ebno);    // eb_no 저장
        orderreqVO.setEb_no(eb_no);
        orderreqVO.setPayway(payway);  // payway 저장     
        
        EbookVO ebookVO = this.ebookProc.read(eb_no);
        orderreqVO.setPrice(ebookVO.getEb_price());  // eb_price 저장
        
        cnt  = this.orderreqProc.create(orderreqVO); 
      } 
      /*----------------------    주문요청 테이블 등록 끝 ----------------------  */
        
        
      /*----------------------   선택된 카트번호 삭제 시작  ---------------------- */
      for(String cartno : cartno_list) { 
        cart_no = Integer.parseInt(cartno);
        System.out.println("cart -> checked cartno_list : "+cart_no);
        cartVO.setCart_no(cart_no); 
        this.cartProc.delete(cartVO);  // 체크되어 들어온 cart번호로 cart table delete 
      }
      /*----------------------  선택된 카트번호 삭제 끝  ----------------------  */  
    } // end if
    json.put("cnt", cnt); // 결과 전달 

    return json.toString();     // {"cnt":1}
  } 

  // 관리자페이지: 목록
  @RequestMapping(value = "/adm/orderreq/list_all.do", method = RequestMethod.GET)
  public ModelAndView list_all() {
    ModelAndView mav = new ModelAndView();
    
    List<OrderreqVO> list = this.orderreqProc.list_all();     
    // List<OrderreqVO> list = this.orderreqProc.list_join();     
    mav.addObject("list", list);
    
    mav.setViewName("/adm/orderreq/list");

    return mav;
  } 
  
  // 회원페이지: 목록
  @RequestMapping(value = "/orderreq/list.do", method = RequestMethod.GET)
  public ModelAndView list_by_userid(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    String user_id=(String)session.getAttribute("id");
    
    if(user_id == null) {
      mav.setViewName("/member/login_need");
    } else {
      List<OrderreqVO> list = this.orderreqProc.list_by_userid(user_id);
      mav.addObject("list", list);  
      mav.setViewName("/orderreq/list");
    }

    return mav;
  } 

  
  /**
   * Ajax 기반 삭제 처리 
   * @param orderno_list
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/orderreq/delete_ajax.do", method = RequestMethod.POST)
  public int delete_ajax(@RequestParam(value="orderno_list[]") List<String> orderno_list )  {
    int result = 0;        
    int order_no=0;
    for(String orderno: orderno_list) {
      System.out.println("-->read to delete order_no: " + orderno);
      order_no = Integer.parseInt(orderno);
      
      this.orderreqProc.read(order_no);
      result = this.orderreqProc.delete(order_no);
    }  
  
    return result;
  }  
    
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  

}
  
  

