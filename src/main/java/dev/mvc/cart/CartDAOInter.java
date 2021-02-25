package dev.mvc.cart;

import java.util.List;

public interface CartDAOInter {

  // 동일한 상품 레코드 검사
  public int checkEBNO(CartVO cartVO);
  
  // 카트 담기
  public int create(CartVO cartVO);
  
  // 목록
  public List<CartVO> list(String user_id);
  
  // 삭제
  public int delete(CartVO cartVO);
  
  
}
