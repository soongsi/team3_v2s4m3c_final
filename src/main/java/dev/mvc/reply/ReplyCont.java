package dev.mvc.reply;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class ReplyCont {
  @Autowired
  @Qualifier("dev.mvc.reply.ReplyProc")
  private ReplyProcInter replyProc;

  public ReplyCont() {
    System.out.println("--> ReplyCont created.");
  }
  
  /**
   *등록
   * @return
   */
  @RequestMapping(value = "/reply/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/reply/create"); // /review/create.jsp

    return mav;
  }

  /**
   * 등록처리
   * @param replyVO
   * @return
   */
  @RequestMapping(value = "/reply/create.do", method = RequestMethod.POST)
  public ModelAndView create(ReplyVO replyVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/reply/create_msg");

    int cnt = this.replyProc.create(replyVO);
    mav.addObject("cnt", cnt);

    return mav;
  }

  /**
   * 목록
   * @return
   */
  @RequestMapping(value = "/reply/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/reply/list"); // /reply/list.jsp

    List<ReplyVO> list = this.replyProc.list_replyno_desc();
    mav.addObject("list", list);

    return mav; // forward
  }
  
  

}
