package dev.mvc.cs_attachfile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.cart.CartVO;
import dev.mvc.customer.CustomerProcInter;
import dev.mvc.customer.CustomerVO;
import dev.mvc.customer.Member_Customer_join;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class Cs_AttachfileCont {
  
  // �θ� ���μ��� ����Ʈ
  @Autowired
  @Qualifier("dev.mvc.customer.CustomerProc")
  private CustomerProcInter customerProc;
  
  @Autowired
  @Qualifier("dev.mvc.cs_attachfile.Cs_AttachfileProc")
  private Cs_AttachfileProcInter cs_attachfileProc;
  
  public Cs_AttachfileCont() {
    System.out.println("--> Cs_AttachfileCont created.");
  }
  
  
  /**
   * http://localhost:9090/team3_testgit/cs_attachfile/create.do?cs_no=4
   * ���� ��� ��
   * @param cs_no
   * @return
   */
  @RequestMapping(value = "/cs_attachfile/create.do", method = RequestMethod.GET)
  public ModelAndView create(int cs_no) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/cs_attachfile/create"); // webapp/cs_attachfile/create.jsp
    return mav;
  }
  
  
  /**
   * http://localhost:9090/team3_testgit/cs_attachfile/create.do?cs_no=4
   * ���� ��� ó��
   * @param cs_no
   * @return
   */
  @RequestMapping(value = "/cs_attachfile/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, 
                                        Cs_AttachfileVO cs_attachfileVO, 
                                        int cs_no) {
    ModelAndView mav = new ModelAndView();
    
    // ---------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // ---------------------------------------------------------------
    cs_no = cs_attachfileVO.getCs_no();        //  �θ�� ��ȣ
    String fname = "";                             //   ���� ���ϸ�
    String fupname = "";                          //  ���ε�� ���ϸ�
    long fsize = 0;                                  //  ���� ������
    String thumb = "";                             // Preview �̹���
    int upload_count = 0;                         // ����ó���� ���ڵ� ����

    String upDir = Tool.getRealPath(request, "/cs_attachfile/storage");

    // ���� ������ ����� fnamesMF ��ü�� ������.
    List<MultipartFile> fnamesMF = cs_attachfileVO.getFnamesMF();

    int count = fnamesMF.size(); // ���� ���� ����
    if (count > 0) {
      for (MultipartFile multipartFile : fnamesMF) { // ���� ����, 1���̻� ���� ó��
        fsize = multipartFile.getSize(); // ���� ũ��
        if (fsize > 0) { // ���� ũ�� üũ
          fname = multipartFile.getOriginalFilename(); // ���� ���ϸ�
          fupname = Upload.saveFileSpring(multipartFile, upDir); // ���� ����, ���ε�� ���ϸ�

          if (Tool.isImage(fname)) { // �̹������� �˻�
            thumb = Tool.preview(upDir, fupname, 200, 150); // thumb �̹��� ����
          }
        }
        Cs_AttachfileVO vo = new Cs_AttachfileVO();
        vo.setCs_no(cs_no);
        vo.setFname(fname);
        vo.setFupname(fupname);
        vo.setThumb(thumb);
        vo.setFsize(fsize);

        // ���� 1�� ��� ���� dbms ����, ������ 20���̸� 20���� record insert.
        upload_count = upload_count + this.cs_attachfileProc.create(vo);
      }
    }
    // -----------------------------------------------------
    // ���� ���� �ڵ� ����
    // -----------------------------------------------------
    mav.addObject("cs_no", cs_no);   // redirect: parameter ����
    mav.addObject("upload_count", upload_count);   // redirect: parameter ����
    mav.addObject("url", "create_msg");   // redirect: parameter ����
    
    
    mav.setViewName("redirect:/cs_attachfile/msg.do"); // webapp/cs_attachfile/create.jsp
    return mav;
  }
  
  
  /**
   * http://localhost:9090/team3_testgit/cs_attachfile/list.do
   * ���� ��� ��
   * @param cs_no
   * @return
   */
  @RequestMapping(value = "/cs_attachfile/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();

    List<Cs_AttachfileVO> list = this.cs_attachfileProc.list();
    mav.addObject("list", list);

    mav.setViewName("/cs_attachfile/list");

    return mav;
  }
  
  @RequestMapping(value = "/cs_attachfile/delete.do", method = RequestMethod.POST)
  public ModelAndView delete_proc(HttpServletRequest request, int attach_no,
      @RequestParam(value = "cs_no", defaultValue = "0") int cs_no, String rurl) {
    ModelAndView mav = new ModelAndView();
    
    Cs_AttachfileVO cs_attachfileVO = this.cs_attachfileProc.read(attach_no);
    
    String upDir = Tool.getRealPath(request, "/cs_attachfile/storage");  //  ���� ���
    Tool.deleteFile(upDir, cs_attachfileVO.getFupname()); //  Folder���� 1���� ���� ����
    Tool.deleteFile(upDir, cs_attachfileVO.getThumb());   //  1���� Thumb ���� ����

    this.cs_attachfileProc.delete(attach_no); // ����
    
    List<Cs_AttachfileVO> list = this.cs_attachfileProc.list();  // ��ü ��� ���ΰ�ħ
    mav.addObject("list", list);
    mav.addObject("cs_no", cs_no);
    
    mav.setViewName("redirect:/cs_attachfile/"+rurl);  // list.jsp���� �� rurl �ּҷ� redirect 
    
    return mav;
  }
   
  /**
   * Ajax ��� ���� ó�� 
   * @param cartVO
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/cs_attachfile/delete_ajax.do", method = RequestMethod.POST)
  public int delete_ajax(HttpServletRequest request, int[] attachno_list)  {
  
    int result = 0;        
    String upDir = Tool.getRealPath(request, "/cs_attachfile/storage");  
  
    for(int attach_no:attachno_list) {
      // System.out.println("--> attach_no: " + attach_no);
      Cs_AttachfileVO cs_attachfileVO= this.cs_attachfileProc.read(attach_no); 
  
      Tool.deleteFile(upDir, cs_attachfileVO.getFupname()); 
      Tool.deleteFile(upDir, cs_attachfileVO.getThumb());  
  
      result = this.cs_attachfileProc.delete(attach_no);
    
    }
    
    return result;
  }  
    
  
  @RequestMapping(value = "/cs_attachfile/msg.do", method = RequestMethod.GET) 
  public ModelAndView msg(String url) {
    ModelAndView mav = new ModelAndView();

    // ��� ó�� �޽���: create_msg --> /cs_attachfile/create_msg.jsp
    // ���� ó�� �޽���: update_msg --> /cs_attachfile/update_msg.jsp
    // ���� ó�� �޽���: delete_msg --> /cs_attachfile/delete_msg.jsp
    mav.setViewName("/cs_attachfile/" + url); // forward

    return mav; // forward
  }
  
  
  
}
