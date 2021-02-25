package dev.mvc.order_req;

import java.util.List;

public interface OrderreqDAOInter {

  // ���
  public int create(OrderreqVO orderreqVO);
  
  // ������������: ���
  public List<OrderreqVO> list_all();
  
  // ȸ��������: ���
  public List<OrderreqVO> list_by_userid(String user_id);

  // ��ȸ
  public OrderreqVO read(int order_no);
  
  // ����
  public int delete(int order_no);
  
  
  
  
  
}
