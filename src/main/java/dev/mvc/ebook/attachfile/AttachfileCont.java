package dev.mvc.ebook.attachfile;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.cate.CateProcInter;
import dev.mvc.categrp.CategrpProcInter;
import dev.mvc.ebook.EbookProcInter;
import dev.mvc.ebook.EbookVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class AttachfileCont {

  // �θ� Process Ŭ������ ���� ������ ��ġ
  @Autowired
  @Qualifier("dev.mvc.categrp.CategrpProc")
  private CategrpProcInter categrpProc;

  @Autowired
  @Qualifier("dev.mvc.cate.CateProc")
  private CateProcInter cateProc;

  @Autowired
  @Qualifier("dev.mvc.ebook.EbookProc")
  private EbookProcInter ebookProc;

  @Autowired
  @Qualifier("dev.mvc.ebook.attachfile.AttachfileProc")
  private AttachfileProcInter attachfileProc;

  public AttachfileCont() {
    System.out.println("--> Ebook AttachfileCont created.");
  }

  /**
   * ÷������ ��� �� ��� ��
   * 
   * @return
   */
  @RequestMapping(value = "/adm/ebook/attachfile/list.do", method = RequestMethod.GET)
  public ModelAndView list(int eb_no) {
    ModelAndView mav = new ModelAndView();
    
    EbookVO ebookVO = this.ebookProc.read(eb_no);
    String title = ebookVO.getEb_title();
    
    List<AttachfileVO> list = attachfileProc.list_by_ebno(eb_no);
    mav.addObject("list", list);
    
    mav.addObject("title", title);
    mav.setViewName("/adm/ebook/attachfile/list"); // webapp/attachfile/create.jsp

    return mav;
  }

  /**
   * ��� ó��
   * 
   * @param request
   * @param attachfileVO
   * @return
   */
  @RequestMapping(value = "/adm/ebook/attachfile/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, AttachfileVO attachfileVO) {
    // System.out.println("--> categrpno: " + categrpno);

    ModelAndView mav = new ModelAndView();
    
    // ------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // ------------------------------------------------------------------
    int eb_no = attachfileVO.getEb_no(); // �θ�� ��ȣ
    String fname = ""; // ���� ���ϸ�
    String fupname = ""; // ���ε�� ���ϸ�
    long fsize = 0; // ���� ������
    String thumb = ""; // Preview �̹���
    int upload_count = 0; // ����ó���� ���ڵ� ����
    
    
    String upDir = Tool.getRealPath(request, "/ebook/attachfile/storage");
    // ���� ������ ����� fnamesMF ��ü�� ������.
    List<MultipartFile> fnamesMF = attachfileVO.getFnamesMF();
    int count = fnamesMF.size(); // ���� ���� ����
    if (count > 0) {
      for (MultipartFile multipartFile : fnamesMF) { // ���� ����
        fsize = multipartFile.getSize(); // ���� ũ��
        if (fsize > 0) { // ���� ũ�� üũ
          fname = multipartFile.getOriginalFilename(); // ���� ���ϸ�
          fupname = Upload.saveFileSpring(multipartFile, upDir); // ���� ����, ���ε�� ����

          if (Tool.isImage(fname)) { // �̹������� �˻�
            thumb = Tool.preview(upDir, fupname, 200, 150); // thumb �̹��� ����
          }
        }
        AttachfileVO vo = new AttachfileVO();
        vo.setEb_no(eb_no);
        vo.setFname(fname);
        vo.setFupname(fupname);
        vo.setThumb(thumb);
        vo.setFsize(fsize);

        // ���� 1�� ��� ���� dbms ����, ������ 20���̸� 20���� ���ڵ� insert
        upload_count = upload_count + attachfileProc.create(vo); // ���� 1�� ��� ���� dbms ����
      }
    }

    // -----------------------------------------------------
    // ���� ���� �ڵ� ����
    // -----------------------------------------------------

    mav.addObject("upload_count", upload_count); // redirect parameter ����
    mav.addObject("eb_no", attachfileVO.getEb_no()); // redirect parameter ����

    mav.setViewName("redirect:/adm/ebook/attachfile/list.do?eb_no=" + eb_no); // ���ΰ�ħ ����

    return mav;
  }

  /**
   * ���� ��ħ ���� �޽��� ���
   * 
   * @param url
   * @return
   */
  @RequestMapping(value = "/adm/ebook/attachfile/msg.do", method = RequestMethod.GET)
  public ModelAndView msg(String url) {
    ModelAndView mav = new ModelAndView();

    // ��� ó�� �޽���: create_msg --> /attachfile/create_msg.jsp
    // ���� ó�� �޽���: update_msg --> /attachfile/update_msg.jsp
    // ���� ó�� �޽���: delete_msg --> /attachfile/delete_msg.jsp
    mav.setViewName("/adm/ebook/attachfile/" + url); // forward

    return mav; // forward
  }


  /**
   * ÷�� ���� 1�� ���� ó��
   * 
   * @return
   */
  @RequestMapping(value = "/adm/ebook/attachfile/delete.do", method = RequestMethod.POST)
  public ModelAndView delete_proc(HttpServletRequest request, int attachfileno, String rurl) {
    ModelAndView mav = new ModelAndView();

    // ������ ���� ������ �о��.
    AttachfileVO attachfileVO = attachfileProc.read(attachfileno);

    String upDir = Tool.getRealPath(request, "/ebook/attachfile/storage"); // ���� ���
    Tool.deleteFile(upDir, attachfileVO.getFupname()); // Folder���� 1���� ���� ����
    Tool.deleteFile(upDir, attachfileVO.getThumb()); // 1���� Thumb ���� ����

    // DBMS���� 1���� ���� ����
    attachfileProc.delete(attachfileno);

    List<AttachfileVO> list = attachfileProc.list(); // ��� ���� ��ħ
    mav.addObject("list", list);

    mav.setViewName("redirect:/adm/ebook/attachfile/" + rurl);

    return mav;
  }
  
  /**
   * ������ ÷�� ���� ����
   * 
   * @return
   */
  @RequestMapping(value = "/adm/ebook/attachfile/chk_delete.do", method = RequestMethod.POST)
  public ModelAndView chk_delete_proc(HttpServletRequest request, String chk_del_val, String rurl) {
    ModelAndView mav = new ModelAndView();

    // System.out.println( chk_del_val );
    AttachfileVO attachfileVO = null;
    String[] chk_val = chk_del_val.split(",");
    
    for ( int i = 0; i < chk_val.length; i++ ) {
      int del_num = Integer.parseInt(chk_val[i]);
      //System.out.println( del_num );
      attachfileVO = attachfileProc.read(del_num);
      
      String upDir = Tool.getRealPath(request, "/ebook/attachfile/storage"); // ���� ���
      Tool.deleteFile(upDir, attachfileVO.getFupname()); // Folder���� 1���� ���� ����
      Tool.deleteFile(upDir, attachfileVO.getThumb()); // 1���� Thumb ���� ����
      
      attachfileProc.delete(del_num);
    }
    
    List<AttachfileVO> list = attachfileProc.list(); // ��� ���� ��ħ
    mav.addObject("list", list);
    mav.setViewName("redirect:/adm/ebook/attachfile/" + rurl);

    return mav;
  }

  // http://localhost:9090/team3/attachfile/count_by_ebno.do?eb_no=1
  /**
   * �θ�Ű�� ���� ����
   * 
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/adm/ebook/attachfile/count_by_ebno.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
  public String count_by_ebno(int eb_no) {
    
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    int cnt = this.attachfileProc.count_by_ebno(eb_no);

    JSONObject json = new JSONObject();
    json.put("cnt", cnt);

    return json.toString();
  }

  // http://localhost:9090/team/attachfile/delete_by_ebno.do?contentsno=1
  /**
   * FK �θ�Ű�� �̿��� ��� ���ڵ� ����
   * 
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/adm/ebook/attachfile/delete_by_ebno.do", method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
  public String delete_by_ebno(HttpServletRequest request,
      @RequestParam(value = "contentsno", defaultValue = "0") int eb_no) {
    
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    List<AttachfileVO> list = this.attachfileProc.list_by_ebno(eb_no);
    int cnt = 0; // ������ ���ڵ� ����

    String upDir = Tool.getRealPath(request, "/ebook/attachfile/storage"); // ���� ���
    
    for (AttachfileVO attachfileVO : list) { // ���� ������ŭ ��ȯ
      Tool.deleteFile(upDir, attachfileVO.getFupname()); // Folder���� 1���� ���� ����
      Tool.deleteFile(upDir, attachfileVO.getThumb()); // 1���� Thumb ���� ����
      this.attachfileProc.delete(attachfileVO.getAttachfileno()); // DBMS���� 1���� ���� ����

      cnt = cnt + 1;
    }

    JSONObject json = new JSONObject();
    json.put("cnt", cnt);

    return json.toString();
  }

}
