package dev.mvc.order_req;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.order_req.OrderreqProc")
public class OrderreqProc implements OrderreqProcInter {

  @Autowired
  private OrderreqDAOInter orderreqDAO;
  
  
  public OrderreqProc() {
    System.out.println("--> OrderreqProc created");
  }

  // 등록 처리
  @Override
  public int create(OrderreqVO orderreqVO) {
    int cnt = this.orderreqDAO.create(orderreqVO);
    return cnt;
  }

  // 관리자페이지: 목록
  @Override
  public List<OrderreqVO> list_all() {
    List<OrderreqVO> list = this.orderreqDAO.list_all();
    return list;
  }

   //회원페이지: 목록
   @Override
   public List<OrderreqVO> list_by_userid(String user_id) {
     List<OrderreqVO> list = this.orderreqDAO.list_by_userid(user_id);
     return list;
   }

   // 조회
  @Override
  public OrderreqVO read(int order_no) {
    OrderreqVO orderreqVO = this.orderreqDAO.read(order_no);
    return orderreqVO;
  }
  
  // 삭제
  @Override
  public int delete(int order_no) {
    int cnt = this.orderreqDAO.delete(order_no);
    return cnt;
  }

  
  
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}
