package dev.mvc.review;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReviewCont {

  @Autowired
  @Qualifier("dev.mvc.review.ReviewProc")
  private ReviewProcInter reviewProc;

  public ReviewCont() {
    System.out.println("--> ReviewCont created.");
  }

  /**
   * 등록폼
   * @return
   */
  @RequestMapping(value = "/review/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/review/create"); // /review/create.jsp

    return mav;
  }

  /**
   * 등록처리
   * @param reviewVO
   * @return
   */
  @RequestMapping(value = "/review/create.do", method = RequestMethod.POST)
  public ModelAndView create(ReviewVO reviewVO) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/review/create_msg");

    int cnt = this.reviewProc.create(reviewVO);
    mav.addObject("cnt", cnt);

    return mav;
  }

  /**
   * 목록
   * @return
   */
  @RequestMapping(value = "/review/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/review/list"); // /review/list.jsp

    List<ReviewVO> list = this.reviewProc.list_reviewno_asc();
    mav.addObject("list", list);

    return mav; // forward
  }

  /**
   * 전체 목록
   * @return
   */
  @RequestMapping(value = "/review/read.do", method = RequestMethod.GET)
  public ModelAndView read(int review_no) {
    ModelAndView mav = new ModelAndView();

    ReviewVO reviewVO = this.reviewProc.read(review_no);
    mav.addObject("reviewVO", reviewVO);
    mav.setViewName("/review/read"); 
    
    return mav;
  }
  
  /**
   * 수정폼
   * @return
   */
  @RequestMapping(value = "/review/update.do", method = RequestMethod.GET)
  public ModelAndView update(int review_no) {

    ModelAndView mav = new ModelAndView();
    
    ReviewVO reviewVO = this.reviewProc.read_update(review_no);
    mav.addObject("reviewVO", reviewVO); // request에 저장

    mav.setViewName("/review/update");

    return mav;
  }
  
  /**
   * 수정 처리 
   * @param reviewVO
   * @return
   */
  @RequestMapping(value = "/review/update.do", method = RequestMethod.POST)
  public ModelAndView update(ReviewVO reviewVO) {
    ModelAndView mav = new ModelAndView();

    mav.addObject("review_no", reviewVO.getReview_no());

    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("review_no", reviewVO.getReview_no());
    hashMap.put("review_pw", reviewVO.getReview_pw());

    int passwd_cnt = 0; // 패스워드 일치 레코드 갯수
    int cnt = 0; // 수정된 레코드 갯수

    passwd_cnt = this.reviewProc.passwd_check(hashMap);

    if (passwd_cnt == 1) { // 패스워드가 일치할 경우 글 수정
      cnt = this.reviewProc.update(reviewVO);
    }

    mav.addObject("cnt", cnt); // request에 저장
    mav.addObject("passwd_cnt", passwd_cnt); // request에 저장
    mav.setViewName("/review/update_msg");

    return mav;
  }
  
  /**
   * 삭제폼
   * @return
   */
  @RequestMapping(value = "/review/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int review_no) {
    ModelAndView mav = new ModelAndView();

    ReviewVO reviewVO = this.reviewProc.read_update(review_no);
    mav.addObject("reviewVO", reviewVO); 

    List<ReviewVO> list = this.reviewProc.list_reviewno_asc();
    mav.setViewName("/review/delete");

    return mav;
  }

  /**
   * 삭제 처리 
   * @param reviewVO
   * @return
   */
  @RequestMapping(value = "/review/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(int review_no, String review_pw) {
    ModelAndView mav = new ModelAndView();
    
    ReviewVO reviewVO = this.reviewProc.read(review_no);
    String rcontent = reviewVO.getReview_content();
    mav.addObject("rcontent", rcontent);
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("review_no", reviewVO.getReview_no());
    hashMap.put("review_pw", reviewVO.getReview_pw());

    int passwd_cnt = 0; // 패스워드 일치 레코드 갯수
    int cnt = 0; // 수정된 레코드 갯수

    passwd_cnt = this.reviewProc.passwd_check(hashMap);

    if (passwd_cnt == 1) { // 패스워드가 일치할 경우 글 수정
      cnt = this.reviewProc.delete(review_no);
    }
    mav.addObject("cnt", cnt); // request에 저장
    mav.addObject("passwd_cnt", passwd_cnt); // request에 저장
    mav.setViewName("/review/delete_msg");

    return mav;
  }
  
}