package dev.mvc.reply;

import java.util.List;

public interface ReplyDAOInter {
  
  /**
   * ��� ���
   * @param replyVO
   * @return
   */
  public int create(ReplyVO replyVO);
  
  /**
   * ��� ���
   * @return
   */
  public List<ReplyVO> list_replyno_desc();
 
  /**
   * ��� ����
   * @param reply_no
   * @return
   */
  public int delete(int reply_no);
  

}
