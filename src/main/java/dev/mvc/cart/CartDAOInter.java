package dev.mvc.cart;

import java.util.List;

public interface CartDAOInter {

  // ������ ��ǰ ���ڵ� �˻�
  public int checkEBNO(CartVO cartVO);
  
  // īƮ ���
  public int create(CartVO cartVO);
  
  // ���
  public List<CartVO> list(String user_id);
  
  // ����
  public int delete(CartVO cartVO);
  
  
}
