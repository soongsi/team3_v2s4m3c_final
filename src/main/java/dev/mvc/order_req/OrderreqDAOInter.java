package dev.mvc.order_req;

import java.util.List;

public interface OrderreqDAOInter {

  // 등록
  public int create(OrderreqVO orderreqVO);
  
  // 관리자페이지: 목록
  public List<OrderreqVO> list_all();
  
  // 회원페이지: 목록
  public List<OrderreqVO> list_by_userid(String user_id);

  // 조회
  public OrderreqVO read(int order_no);
  
  // 삭제
  public int delete(int order_no);
  
  
  
  
  
}
