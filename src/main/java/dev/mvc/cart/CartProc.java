package dev.mvc.cart;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.cart.CartProc")
public class CartProc implements CartProcInter {

  @Autowired
  private CartDAOInter cartDAO;
  
  public CartProc() {
    System.out.println("--> CartProc created");
  }

  
  // ������ ��ǰ ���ڵ� �˻�
  @Override
  public int checkEBNO(CartVO cartVO) {
    int cnt = this.cartDAO.checkEBNO(cartVO);
    return cnt;
  }
  
  // īƮ ���
  @Override
  public int create(CartVO cartVO) {
    int cnt = this.cartDAO.create(cartVO);
    return cnt;
  }

  // īƮ ����Ʈ
  @Override
  public List<CartVO> list(String user_id) {
    List<CartVO> list = this.cartDAO.list(user_id);
    return list;
  }

  // īƮ ����
  @Override
  public int delete(CartVO cartVO) {
    int cnt = this.cartDAO.delete(cartVO);
    return cnt;
  }




  
  
  
  
  
  
  
  
  
  
  
  
  
  

  
  
}
