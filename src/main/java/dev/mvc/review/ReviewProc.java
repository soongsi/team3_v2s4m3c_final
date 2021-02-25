package dev.mvc.review;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Tool;

@Component("dev.mvc.review.ReviewProc")
public class ReviewProc implements ReviewProcInter {

  @Autowired
  private ReviewDAOInter reviewDAO;

  public ReviewProc() {
    System.out.println("--> ReviewProc created.");
  }

  @Override
  public int create(ReviewVO reviewVO) {
    int cnt = this.reviewDAO.create(reviewVO);
    return cnt;
  }

  @Override
  public List<ReviewVO> list_reviewno_asc() {
    List<ReviewVO> list = this.reviewDAO.list_reviewno_asc();
    return list;
  }

  @Override
  public ReviewVO read(int review_no) {
    ReviewVO reviewVO = this.reviewDAO.read(review_no);

    String rcontent = reviewVO.getReview_content();   
    rcontent = Tool.convertChar(rcontent);
    
    reviewVO.setReview_content(rcontent);
  
    return reviewVO;
  }
  
  @Override
  public ReviewVO read_update(int review_no) {
    ReviewVO reviewVO = this.reviewDAO.read(review_no);
    return reviewVO;
  }


  @Override
  public int update(ReviewVO reviewVO) {
    int cnt = this.reviewDAO.update(reviewVO);
    return cnt;
  }

  @Override
  public int passwd_check(HashMap hashMap) {
    int cnt = this.reviewDAO.passwd_check(hashMap);
    return cnt;
  }

  @Override
  public int delete(int review_no) {
    int passwd_cnt = this.reviewDAO.delete(review_no);
    return passwd_cnt;
  }

  
}
