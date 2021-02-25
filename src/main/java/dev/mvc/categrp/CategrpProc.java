package dev.mvc.categrp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.categrp.CategrpProc")
public class CategrpProc implements CategrpProcInter {
  
  @Autowired
  private CategrpDAOInter categrpDAO;
  
  public CategrpProc() {
    System.out.println( "--> CategrpProc created." );
  }
  
  @Override
  public int create(CategrpVO categrpVO) {
    int cnt = 0;
    cnt = this.categrpDAO.create(categrpVO);
    
    return cnt;
  }

  @Override
  public List<CategrpVO> list_cgno_asc() {
    List<CategrpVO> list = null;
    list = this.categrpDAO.list_cgno_asc();
    
    return list;
  }

  @Override
  public List<CategrpVO> list_cgseqno_asc() {
    List<CategrpVO> list = null;
    list = this.categrpDAO.list_cgseqno_asc();
    
    return list;
  }

  @Override
  public CategrpVO read(int cg_no) {
    CategrpVO vo = null;
    vo = this.categrpDAO.read(cg_no);
    
    return vo;
  }

  @Override
  public int update(CategrpVO categrpVO) {
    int cnt = 0;
    cnt = this.categrpDAO.update(categrpVO);
    
    return cnt;
  }

  @Override
  public int delete(int cg_no) {
    int cnt = 0;
    cnt = this.categrpDAO.delete(cg_no);
    
    return cnt;
  }

  @Override
  public int update_seqno_up(int cg_no) {
    int cnt = 0;
    cnt = this.categrpDAO.update_seqno_up(cg_no);
    
    return cnt;
  }

  @Override
  public int update_seqno_down(int cg_no) {
    int cnt = 0;
    cnt = this.categrpDAO.update_seqno_down(cg_no);
    
    return cnt;
  }

  @Override
  public int update_visible(CategrpVO categrpVO) {
    int cnt = 0;
    cnt = this.categrpDAO.update_visible(categrpVO);
    
    return cnt;
  }

}
