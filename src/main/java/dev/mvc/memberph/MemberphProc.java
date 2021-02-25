package dev.mvc.memberph;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("dev.mvc.memberph.MemberphProc")
public class MemberphProc implements MemberphProcInter{
  @Autowired
  private MemberphDAOInter memberphDAO; 
  
  public MemberphProc() {
    System.out.println("--> MemberphProc created.");
  }
  
  @Override
  public int create(MemberphVO memberphVO) {
    int cnt = this.memberphDAO.create(memberphVO);
    return cnt;
  }

  @Override
  public List<MemberphVO> list_orderno_asc() {
    List<MemberphVO> list = this.memberphDAO.list_orderno_asc();
    return list;
  }

  @Override
  public MemberphVO read(int orderno) {
    MemberphVO read = this.memberphDAO.read(orderno);
    return read;
  }

  @Override
  public MemberphVO read_update(int orderno) {
    MemberphVO memberphVO = this.memberphDAO.read(orderno);
    return memberphVO;
  }

  @Override
  public int update(MemberphVO memberphVO) {
    int cnt = this.memberphDAO.update(memberphVO);
    return cnt;
  }

  @Override
  public int delete(int orderno) {
    int cnt = this.memberphDAO.delete(orderno);
    return cnt;
  }

  @Override
  public int img_create(MemberphVO memberphVO) {
    int cnt = this.memberphDAO.update_img(memberphVO);
    return cnt;
  }

  @Override
  public int img_update(MemberphVO memberphVO) {
    int cnt = this.memberphDAO.update_img(memberphVO);
    return cnt;
  }

  @Override
  public int img_delete(MemberphVO memberphVO) {
    int cnt = this.memberphDAO.update_img(memberphVO);
    return cnt;
  }

}
