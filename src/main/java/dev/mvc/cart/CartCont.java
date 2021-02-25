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

  // ��� ���̺� ����
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc") 
  private MemberProcInter memberProc;
  
  // �̺� ���̺� ����
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
  * ������ ��ǰ �ߺ� üũ, JSON ���
  * @return
  */
  @ResponseBody
  @RequestMapping(value="/cart/checkEBNO.do", method=RequestMethod.GET ,
                         produces = "text/plain;charset=UTF-8" )
  public String checkEBNO(CartVO cartVO, HttpSession session) {
    String user_id =(String)session.getAttribute("id");  // ���̵� ����
    int cnt =0;
    
    if(user_id != null) { // �α��� ������̸� ��� ����
    cartVO.setUser_id(user_id);
    cnt = this.cartProc.checkEBNO(cartVO);  
    } else {
      cnt = -1;   
    }

    JSONObject json = new JSONObject();
    json.put("cnt", cnt);  // ��� ����
    
    return json.toString();  // {"cnt":-1}
  }  
  
  /**
   *                           http://localhost:9090/team3_testgit/ebook/read.do?eb_no=1
   * Ajax ��� ��� ó�� http://localhost:9090/team3_testgit/cart/create_ajax.do?eb_no=5&user_id=crm
   * @param cartVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/cart/create_ajax.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public String create_ajax(CartVO cartVO, HttpSession session)  {
    String user_id = (String)session.getAttribute("id");
    
    cartVO.setUser_id(user_id);  // id ����
    int cnt = this.cartProc.create(cartVO);  // ���

    JSONObject json = new JSONObject();       
    json.put("cnt", cnt); // ��� ����
    
    return json.toString();
  }
  
  // ���
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
   * Ajax ��� ���� ó�� 
   * @param cartVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/cart/delete_ajax.do", method = RequestMethod.POST)
  public int delete_ajax(HttpSession session, CartVO cartVO, 
                               @RequestParam(value="cartno_list[]") List<String> cartno_list)  {
    String user_id = (String)session.getAttribute("id");  // ���̵�
    
    int result = 0;      // ���
    int cart_no = 0;   // īƮ ��ȣ
    
    if(user_id != null) {   // �α��� ���� üũ
      cartVO.setUser_id(user_id);  // ���̵� ����

      for(String cartno : cartno_list) { 
        cart_no = Integer.parseInt(cartno);  // ����ȯ
        cartVO.setCart_no(cart_no);  // cart_no ����
        result = this.cartProc.delete(cartVO);  // ���� ó��
      } 
    }
    
    return result;
  }  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  

}
