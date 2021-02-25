package dev.mvc.memberph;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class MemberphCont {
  @Autowired
  @Qualifier("dev.mvc.memberph.MemberphProc")
  private MemberphProcInter memberphProc; 
  
  public MemberphCont() {
    System.out.println("--> MemberphCont created.");
  }
  
  /**
   * �����
   * http://localhost:9090/team3/memberph/create.do
   * @return
   */
  @RequestMapping(value="/memberph/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/memberph/create"); // /webapp/memberph/create.jsp
    
    return mav;  // forward
  }
  
  /**
   * ��� ó��
   * http://localhost:9090/team3/memberph/create.do
   * @return
   */
  @RequestMapping(value="/memberph/create.do", method=RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, MemberphVO memberphVO) {
    ModelAndView mav = new ModelAndView();
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String file1 = "";     // main image
    String thumb1 = ""; // preview image
        
    String upDir = Tool.getRealPath(request, "/memberph/storage/main_images"); // ���� ���
    // ���� ������ ����� fnamesMF ��ü�� ������.
    MultipartFile mf = memberphVO.getFile1MF();
    System.out.println("mf: " + memberphVO.getFile1MF());
    long size1 = mf.getSize();  // ���� ũ��
    System.out.println("size1: " + size1);
    if (size1 > 0) { // ���� ũ�� üũ
      // mp3 = mf.getOriginalFilename(); // ���� ���ϸ�, spring.jpg
      // ���� ���� �� ���ε�� ���ϸ��� ���ϵ�, spring.jsp, spring_1.jpg...
      file1 = Upload.saveFileSpring(mf, upDir); 
      
      if (Tool.isImage(file1)) { // �̹������� �˻�
        // thumb �̹��� ������ ���ϸ� ���ϵ�, width: 120, height: 80
        thumb1 = Tool.preview(upDir, file1, 200, 150);
      }
      
    }    
    
    memberphVO.setFile1(file1);
    memberphVO.setThumb1(thumb1);
    memberphVO.setSize1(size1);
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    
    int cnt = this.memberphProc.create(memberphVO); // ��� ó��
    
    
    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
    mav.addObject("orderno", memberphVO.getOrderno());
    mav.addObject("url", "create_msg"); // // webapp/memberph/create_msg.jsp
    
    
    mav.setViewName("/memberph/create_msg"); // /webapp/memberph/create.jsp
    
    return mav;  // forward
  }
  
  /**
   * ���
   * http://localhost:9090/team3/memberph/list.do
   * @return
   */
  @RequestMapping(value="/memberph/list.do", method=RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/memberph/list"); // /webapp/memberph/create.jsp
    
    List<MemberphVO> list = this.memberphProc.list_orderno_asc();
    mav.addObject("list" ,list);
    
    return mav;  // forward
  }
  
  /**
   * ��ȸ
   * http://localhost:9090/team3/memberph/read.do
   * @return
   */
  @RequestMapping(value="/memberph/read.do", method=RequestMethod.GET)
  public ModelAndView read_update(int orderno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/memberph/read"); // /webapp/memberph/read_update.jsp
    
    MemberphVO memberphVO = this.memberphProc.read(orderno);
    mav.addObject("memberphVO" ,memberphVO);
    
    return mav;  // forward
  }
  
  /**
   * ���� ��
   * @return
   */
  @RequestMapping(value="/memberph/update.do", method=RequestMethod.GET )
  public ModelAndView update(int orderno) {
    ModelAndView mav = new ModelAndView();
    
    MemberphVO memberphVO = this.memberphProc.read_update(orderno); // ������ �б�
    mav.addObject("MemberphVO", memberphVO); // request.setAttribute("memberphVO", memberphVO);
    
    mav.setViewName("/memberph/update"); // webapp/memberph/update.jsp
    
    return mav;
  }
  
  /**
   * ���� ó��
   * @param memberphVO
   * @return
   */
  @RequestMapping(value="/memberph/update.do", method=RequestMethod.POST )
  public ModelAndView update(MemberphVO memberphVO) {
    
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.memberphProc.update(memberphVO);
    mav.addObject("cnt", cnt); // request�� ����
    
    mav.setViewName("/memberph/update_msg"); // webapp/memberph/update_msg.jsp
    
    return mav;
  }
  
  /**
   * ������
   * http://localhost:9090/resort/memberph/delete.do
   * @return
   */
  @RequestMapping(value="/memberph/delete.do", method=RequestMethod.GET)
  public ModelAndView read_delete(int orderno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/memberph/delete"); // /webapp/memberph/delete.jsp
    
    MemberphVO memberphVO = this.memberphProc.read(orderno);
    mav.addObject("memberphVO" ,memberphVO);
    
    return mav;  // forward
  }
   
  /**
   * ���� ó��
   * @param memberphno
   * @return
   */
  @RequestMapping(value="/memberph/delete.do", method=RequestMethod.POST )
  public ModelAndView delete(HttpServletRequest request, int orderno) {
    
    ModelAndView mav = new ModelAndView();
    MemberphVO memberphVO = this.memberphProc.read(orderno);
    
    int cnt = this.memberphProc.delete(orderno);
        
    String upDir = Tool.getRealPath(request, "/memberph/storage/main_images"); // ���� ���
    Tool.deleteFile(upDir, memberphVO.getFile1());  // Folder���� 1���� ���� ����
    Tool.deleteFile(upDir, memberphVO.getThumb1());  // Folder���� 1���� ���� ����
    
    mav.addObject("cnt", cnt); // request�� ����
    
    mav.setViewName("/memberph/delete_msg"); // webapp/memberph/update_msg.jsp
    
    return mav;
  }
  
  /**
   * ���� �̹��� ��� �� http://localhost:9090/resort/memberph/img_create.do
   * 
   * @return
   */
  @RequestMapping(value = "/memberph/img_create.do", method = RequestMethod.GET)
  public ModelAndView img_create(int orderno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/memberph/img_create"); // /webapp/memberph/img_create.jsp

    MemberphVO memberphVO = this.memberphProc.read(orderno);
    mav.addObject("memberphVO", memberphVO);
    
    return mav; // forward
  }
  
  /**
   * ���� �̹��� ��� ó�� http://localhost:9090/resort/memberph/img_create.do
   * 
   * @return
   */
  @RequestMapping(value = "/memberph/img_create.do", method = RequestMethod.POST)
  public ModelAndView img_create(HttpServletRequest request, MemberphVO memberphVO) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = 0;             // ������ ���ڵ� ���� 
    
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String file1 = "";     // main image
    String thumb1 = ""; // preview image
        
    String upDir = Tool.getRealPath(request, "/memberph/storage/main_images"); // ���� ���
    // ���� ������ ����� fnamesMF ��ü�� ������.
    // <input type='file' class="form-control" name='file1MF' id='file1MF' 
    //           value='' placeholder="���� ����" multiple="multiple">
    MultipartFile mf = memberphVO.getFile1MF();
    long size1 = mf.getSize();  // ���� ũ��
    if (size1 > 0) { // ���� ũ�� üũ
      // mp3 = mf.getOriginalFilename(); // ���� ���ϸ�, spring.jpg
      // ���� ���� �� ���ε�� ���ϸ��� ���ϵ�, spring.jsp, spring_1.jpg...
      file1 = Upload.saveFileSpring(mf, upDir); 
      
      if (Tool.isImage(file1)) { // �̹������� �˻�
        // thumb �̹��� ������ ���ϸ� ���ϵ�, width: 120, height: 80
        thumb1 = Tool.preview(upDir, file1, 200, 150);
      }
    }    
      
    memberphVO.setFile1(file1);
    memberphVO.setThumb1(thumb1);
    memberphVO.setSize1(size1);
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    
    mav.setViewName("redirect:/memberph/read.do?orderno=" + memberphVO.getOrderno());
    
    cnt = this.memberphProc.img_create(memberphVO);
    // mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
    

    mav.setViewName("/memberph/update_msg"); // webapp/memberph/update_msg.jsp
      

    mav.addObject("cnt", cnt); // request�� ����
            
    return mav;    
  }
  
  /**
   * ���� �̹��� ��� �� http://localhost:9090/resort/memberph/img_update.do
   * 
   * @return
   */
  @RequestMapping(value = "/memberph/img_update.do", method = RequestMethod.GET)
  public ModelAndView img_update(int orderno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/memberph/img_update"); // /webapp/memberph/img_update.jsp

    MemberphVO memberphVO = this.memberphProc.read(orderno);
    mav.addObject("memberphVO", memberphVO);
    
    return mav; // forward
  }
  
  /**
   * ���� �̹��� ���� ó�� http://localhost:9090/resort/memberph/img_delete.do
   * 
   * @return
   */
  @RequestMapping(value = "/memberph/img_delete.do", method = RequestMethod.POST)
  public ModelAndView img_delete(HttpServletRequest request, int orderno) {
    ModelAndView mav = new ModelAndView();

    int cnt = 0;             // ������ ���ڵ� ���� 
    
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    // ������ ���� ������ �о��.
    MemberphVO memberphVO = memberphProc.read(orderno);
    // System.out.println("file1: " + memberphVO.getFile1());
    
    String file1 = memberphVO.getFile1().trim();
    String thumb1 = memberphVO.getThumb1();
    long size1 = memberphVO.getSize1();
    
    String upDir = Tool.getRealPath(request, "/memberph/storage/main_images"); // ���� ���
    Tool.deleteFile(upDir, memberphVO.getFile1());  // Folder���� 1���� ���� ����
    Tool.deleteFile(upDir, memberphVO.getThumb1());  // Folder���� 1���� ���� ����
    
    file1 = "";
    thumb1 = "";
    size1 = 0;
    
    memberphVO.setFile1(file1);
    memberphVO.setThumb1(thumb1);
    memberphVO.setSize1(size1);
    
    // System.out.println("sw: " + sw);
    // -------------------------------------------------------------------
    // ���� ���� ���� ����
    // -------------------------------------------------------------------
    
    mav.setViewName("redirect:/memberph/read.do?orderno=" + orderno);
    
    cnt = this.memberphProc.img_delete(memberphVO);
    // mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
    
    mav.setViewName("/memberph/delete_msg"); // webapp/memberph/delete_msg.jsp
    mav.addObject("cnt", cnt); // request�� ����
            
    return mav;    
  }
  
  /**
   * ���� �̹��� ���� ó�� http://localhost:9090/resort/memberph/img_update.do
   * ���� �̹��� ������ ���ο� �̹��� ���(���� ó��)
   * @return
   */
  @RequestMapping(value = "/memberph/img_update.do", method = RequestMethod.POST)
  public ModelAndView img_update(HttpServletRequest request, MemberphVO memberphVO) {
    ModelAndView mav = new ModelAndView();

    int cnt = 0;             // ������ ���ڵ� ���� 
    

    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    // ������ ���� ������ �о��.
    MemberphVO vo = memberphProc.read(memberphVO.getOrderno());
    // System.out.println("file1: " + memberphVO.getFile1());
    
    String file1 = vo.getFile1().trim();
    String thumb1 = vo.getThumb1();
    long size1 = 0;
    
    String upDir = Tool.getRealPath(request, "/memberph/storage/main_images"); // ���� ���
    Tool.deleteFile(upDir, memberphVO.getFile1());  // Folder���� 1���� ���� ����
    Tool.deleteFile(upDir, memberphVO.getThumb1());  // Folder���� 1���� ���� ����
    
    // System.out.println("sw: " + sw);
    // -------------------------------------------------------------------
    // ���� ���� ���� ����
    // -------------------------------------------------------------------
    
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
 // ���� ������ ����� fnamesMF ��ü�� ������.
    // <input type='file' class="form-control" name='file1MF' id='file1MF' 
    //           value='' placeholder="���� ����" multiple="multiple">
    MultipartFile mf = memberphVO.getFile1MF();
    size1 = mf.getSize();  // ���� ũ��
    if (size1 > 0) { // ���� ũ�� üũ
      // mp3 = mf.getOriginalFilename(); // ���� ���ϸ�, spring.jpg
      // ���� ���� �� ���ε�� ���ϸ��� ���ϵ�, spring.jsp, spring_1.jpg...
      file1 = Upload.saveFileSpring(mf, upDir); 
      
      if (Tool.isImage(file1)) { // �̹������� �˻�
        // thumb �̹��� ������ ���ϸ� ���ϵ�, width: 120, height: 80
        thumb1 = Tool.preview(upDir, file1, 200, 150); 
      }
    }    
    
    memberphVO.setFile1(file1);
    memberphVO.setThumb1(thumb1);
    memberphVO.setSize1(size1);
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------

    mav.setViewName("redirect:/memberph/read.do?orderno=" + memberphVO.getOrderno());
    
    cnt = this.memberphProc.img_create(memberphVO);
    // mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
    mav.addObject("orderno", memberphVO.getOrderno());
    mav.addObject("cnt", cnt); // request�� ����
    mav.setViewName("/memberph/update_msg"); // webapp/memberph/update_msg.jsp

    
            
    return mav;    
  }

}
