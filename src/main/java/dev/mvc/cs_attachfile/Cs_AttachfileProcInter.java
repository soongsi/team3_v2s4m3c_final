package dev.mvc.cs_attachfile;

import java.util.List;

public interface Cs_AttachfileProcInter {

  // ���� ���
  public int create(Cs_AttachfileVO cs_attachfileVO);
  
  // ���ε� ���
  public List<Cs_AttachfileVO> list();
  
  // ��ȸ
  public Cs_AttachfileVO read(int attach_no);
  
  //  ����
  public int delete(int attach_no);
  
  
  
  
}
