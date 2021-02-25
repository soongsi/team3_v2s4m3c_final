package dev.mvc.cart;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.ebook.EbookProcInter;
import dev.mvc.ebook.EbookVO;
import dev.mvc.member.MemberProcInter;

@Controller
public class CartCont {

  // 멤버 테이블 참조
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc") 
  private MemberProcInter memberProc;
  
  // 이북 테이블 참조
  @Autowired
  @Qualifier("dev.mvc.ebook.EbookProc") 
  private EbookProcInter ebookProc;
  
  @Autowired
  @Qualifier("dev.mvc.cart.CartProc")
  private CartProcInter cartProc;
  
  public CartCont() {
    System.out.println("--> CartCont created.");
  }
  
  // http://localhost:9090/team3_testgit/cart/checkEBNO.do?user_id=crm&eb_no=4
  /**
  * 동일한 상품 중복 체크, JSON 출력
  * @return
  */
  @ResponseBody
  @RequestMapping(value="/cart/checkEBNO.do", method=RequestMethod.GET ,
                         produces = "text/plain;charset=UTF-8" )
  public String checkEBNO(CartVO cartVO, HttpSession session) {
    String user_id =(String)session.getAttribute("id");  // 아이디 전달
    int cnt =0;
    
    if(user_id != null) { // 로그인 사용자이면 결과 전송
    cartVO.setUser_id(user_id);
    cnt = this.cartProc.checkEBNO(cartVO);  
    } else {
      cnt = -1;   
    }

    JSONObject json = new JSONObject();
    json.put("cnt", cnt);  // 결과 전송
    
    return json.toString();  // {"cnt":-1}
  }  
  
  /**
   *                           http://localhost:9090/team3_testgit/ebook/read.do?eb_no=1
   * Ajax 기반 등록 처리 http://localhost:9090/team3_testgit/cart/create_ajax.do?eb_no=5&user_id=crm
   * @param cartVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/cart/create_ajax.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public String create_ajax(CartVO cartVO, HttpSession session)  {
    String user_id = (String)session.getAttribute("id");
    
    cartVO.setUser_id(user_id);  // id 전달
    int cnt = this.cartProc.create(cartVO);  // 등록

    JSONObject json = new JSONObject();       
    json.put("cnt", cnt); // 결과 전달
    
    return json.toString();
  }
  
  // 목록
  @RequestMapping(value = "/cart/list.do", method = RequestMethod.GET)
  public ModelAndView list(String user_id, HttpSession session) {
    ModelAndView mav = new ModelAndView();

    user_id = (String)session.getAttribute("id");
    
    System.out.println("get user_id:"+user_id);
    if(user_id == null) {
      mav.setViewName("redirect:/member/login.do");
    } else {
      List<CartVO> list = this.cartProc.list(user_id);
      mav.addObject("list", list);
      
      mav.setViewName("/cart/list");
    }

    return mav;
  }
  
  /**
   * Ajax 기반 삭제 처리 
   * @param cartVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/cart/delete_ajax.do", method = RequestMethod.POST)
  public int delete_ajax(HttpSession session, CartVO cartVO, 
                               @RequestParam(value="cartno_list[]") List<String> cartno_list)  {
    String user_id = (String)session.getAttribute("id");  // 아이디
    
    int result = 0;      // 결과
    int cart_no = 0;   // 카트 번호
    
    if(user_id != null) {   // 로그인 상태 체크
      cartVO.setUser_id(user_id);  // 아이디 전달

      for(String cartno : cartno_list) { 
        cart_no = Integer.parseInt(cartno);  // 형변환
        cartVO.setCart_no(cart_no);  // cart_no 전달
        result = this.cartProc.delete(cartVO);  // 삭제 처리
      } 
    }
    
    return result;
  }  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  

}
