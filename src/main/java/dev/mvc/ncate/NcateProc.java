package dev.mvc.ncate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("dev.mvc.ncate.NcateProc")
public class NcateProc implements NcateProcInter {

  @Autowired
  private NcateDAOInter ncateDAO;
 
  public NcateProc() {
    System.out.println("--> NcateProc created");
  }
  
  @Override
  public int create(NcateVO ncateVO) {
    int ncate_cnt = this.ncateDAO.create(ncateVO);
    return ncate_cnt;
  }

  @Override
  public List<NcateVO> list_ncateno_asc(){
    List<NcateVO> list = this.ncateDAO.list_ncateno_asc();
    return list;
  }

  @Override
  public List<NcateVO> list_nseqno_asc() {
    List<NcateVO> list = this.ncateDAO.list_nseqno_asc();
    return list;
  }
  
  @Override
  public NcateVO read(int ncate_no) {
    NcateVO ncateVO = this.ncateDAO.read(ncate_no);
    return ncateVO;
  }

  @Override
  public int update(NcateVO ncateVO) {
    int ncate_cnt = this.ncateDAO.update(ncateVO);
    return ncate_cnt;
  }

  @Override
  public int delete(int ncate_no) {
    int ncate_cnt = this.ncateDAO.delete(ncate_no);
    return ncate_cnt;
  }
  
  @Override
  public int update_nseqno_up(int ncate_no) {
    int ncate_cnt = this.ncateDAO.update_nseqno_up(ncate_no);
    return ncate_cnt;
  }

  @Override
  public int update_nseqno_down(int ncate_no) {
    int ncate_cnt = this.ncateDAO.update_nseqno_down(ncate_no);
    return ncate_cnt;
  }

  @Override
  public int update_nvisible(NcateVO ncateVO) {
    if (ncateVO.getNvisible().equalsIgnoreCase("Y")) {
      ncateVO.setNvisible("N");
    } else {
      ncateVO.setNvisible("Y");
    }
    
    int ncate_cnt = this.ncateDAO.update_nvisible(ncateVO);
    return ncate_cnt;
  }

  @Override
  public int increaseCnt(int ncate_no) {
    int ncate_cnt = this.ncateDAO.increaseCnt(ncate_no);
    return ncate_cnt;
  }

  @Override
  public int decreaseCnt(int ncate_no) {
    int ncate_cnt = this.ncateDAO.decreaseCnt(ncate_no);
    return ncate_cnt;
  }
  
}






