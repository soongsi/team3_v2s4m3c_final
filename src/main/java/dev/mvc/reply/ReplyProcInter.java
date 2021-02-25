package dev.mvc.reply;

import java.util.List;

public interface ReplyProcInter {
  /**
   * ´ñ±Û µî·Ï
   * @param replyVO
   * @return
   */
  public int create(ReplyVO replyVO);
  
  /**
   * ´ñ±Û ¸ñ·Ï
   * @return
   */
  public List<ReplyVO> list_replyno_desc();
  
  /**
   * ´ñ±Û »èÁ¦
   * @param reply_no
   * @return
   */
  public int delete(int reply_no);
 

}
