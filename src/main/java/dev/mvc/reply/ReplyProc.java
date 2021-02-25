package dev.mvc.reply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("dev.mvc.reply.ReplyProc")
public class ReplyProc implements ReplyProcInter{
  
  @Autowired
  private ReplyDAOInter replyDAO;
  
  public ReplyProc() {
    System.out.println("--> ReplyProc created."); 
  }

  @Override
  public int create(ReplyVO replyVO) {
    int cnt = this.replyDAO.create(replyVO);
    return cnt;
  }

  @Override
  public List<ReplyVO> list_replyno_desc() {
    List<ReplyVO> list = this.replyDAO.list_replyno_desc();
    return list;
  }

  @Override
  public int delete(int reply_no) {
    int cnt = this.replyDAO.delete(reply_no);
    return cnt;
  }

}


