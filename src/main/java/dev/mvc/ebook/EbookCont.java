package dev.mvc.ebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import dev.mvc.admin.AdminProcInter;
import dev.mvc.cate.CateProcInter;
import dev.mvc.cate.CateVO;
import dev.mvc.cate.Categrp_Cate_join;
import dev.mvc.categrp.CategrpProcInter;
import dev.mvc.categrp.CategrpVO;
import dev.mvc.ebook.attachfile.AttachfileProcInter;
import dev.mvc.ebook.attachfile.AttachfileVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class EbookCont {
  
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
  
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc") // �̸� ����
  private AdminProcInter adminProc;
  
  public EbookCont() { 
    System.out.println( "--> EbookCont created." );
  }
  
  @RequestMapping(value = { "/", "/index.do" }, method=RequestMethod.GET)
  public ModelAndView latest() {
    ModelAndView mav = new ModelAndView();
    List<EbookVO> list = this.ebookProc.list_ebno_desc_latest();
    
    mav.addObject("list", list);
    mav.setViewName("index");
    return mav;
  }

  /**
   * �̺� ��� ��
   * @return
   */
  @RequestMapping(value="/adm/ebook/create.do", method=RequestMethod.GET)
  public ModelAndView create(@RequestParam(value = "cateno", defaultValue = "0") int cateno, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    boolean sw = this.adminProc.isAdmin(session);
    
    if ( sw == true ) {
      List<CategrpVO> cglist = this.categrpProc.list_cgno_asc();    
      mav.addObject("cglist", cglist);
      
      // ī�װ� �׷� + ī�װ� ��ȣ �� �̸� ��������
      if ( cateno != 0 ) {
        CateVO cateVO = this.cateProc.read(cateno);
        int cg_no = cateVO.getCg_no();
        String cate_name = cateVO.getName();
        
        String title = cate_name + "�ű� ���";
        mav.addObject("title", title);
        
        CategrpVO categrpVO = this.categrpProc.read(cg_no);
        
        mav.addObject("categrpVO", categrpVO);
        mav.addObject("cateVO", cateVO);
      }
      
      mav.setViewName("/adm/ebook/create");
    } else {
      mav.setViewName("redirect:/admin/login.do");
    }
    
    return mav;
  }
  
  /**
   * �̺� ��� �� (��ü ��Ͽ��� ��� �Ҷ�)
   * @return
   */
  /*
   * @RequestMapping(value="/adm/ebook/create.do", method=RequestMethod.GET)
   * public ModelAndView create() { ModelAndView mav = new ModelAndView();
   * 
   * List<CategrpVO> cglist = this.categrpProc.list_cgno_asc();
   * mav.addObject("cglist", cglist);
   * 
   * mav.setViewName("/adm/ebook/create");
   * 
   * return mav; }
   */
  
  /**
   * �̺� ��� ó��
   * @return
   */
  @RequestMapping(value="/adm/ebook/create.do", method=RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, EbookVO ebookVO, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    boolean sw = this.adminProc.isAdmin(session);
    
    if ( sw == true ) {
      int cateno = ebookVO.getCateno();
      
      // -------------------------------------------------------------------
      // �̺� ���� ���� �ڵ� ����
      // -------------------------------------------------------------------
      String ebookFile = "";     // ebook file
      
      String ebookDir = Tool.getRealPath(request, "/ebook/storage/ebook_file"); // ���� ���
      // ���� ������ ����� fnamesMF ��ü�� ������.
      // <input type='file' class="form-control" name='file1MF' id='file1MF' 
      //        value='' placeholder="���� ����" multiple="multiple">
      MultipartFile mf1 = ebookVO.getFile1MF();
      long ebookSize = mf1.getSize();  // ���� ũ��
      if (ebookSize > 0) { // ���� ũ�� üũ
        ebookFile = Upload.saveFileSpring(mf1, ebookDir); 
      }
      
      ebookVO.setEb_file1(ebookFile);
      ebookVO.setEb_size1(ebookSize);
      
      // -------------------------------------------------------------------
      // �̺� ���� ���� �ڵ� ����
      // -------------------------------------------------------------------
      
      
      // -------------------------------------------------------------------
      // ���� �̹��� ���� ���� �ڵ� ����
      // -------------------------------------------------------------------
      String imgFile = "";     // main image
      String thumb = ""; // preview image
      
      String imageDir = Tool.getRealPath(request, "/ebook/storage/main_images"); // ���� ���
      // ���� ������ ����� fnamesMF ��ü�� ������.
      // <input type='file' class="form-control" name='file1MF' id='file1MF' 
      //        value='' placeholder="���� ����" multiple="multiple">
      MultipartFile mf2 = ebookVO.getFile2MF();
      long imgSize = mf2.getSize();  // ���� ũ��
      
      if (imgSize > 0) { // ���� ũ�� üũ
        // mp3 = mf.getOriginalFilename(); // ���� ���ϸ�, spring.jpg
        // ���� ���� �� ���ε�� ���ϸ��� ���ϵ�, spring.jsp, spring_1.jpg...
        imgFile = Upload.saveFileSpring(mf2, imageDir);
        
        if (Tool.isImage(imgFile)) { // �̹������� �˻�
          // thumb �̹��� ������ ���ϸ� ���ϵ�, width: 120, height: 80
          thumb = Tool.preview(imageDir, imgFile, 300, 400);
        }
        
      }
      
      ebookVO.setEb_file2(imgFile);
      ebookVO.setEb_size2(imgSize);
      ebookVO.setEb_thumb(thumb);
      
      // -------------------------------------------------------------------
      // ���� �̹��� ���� ���� �ڵ� ����
      // -------------------------------------------------------------------
      
      
      int cnt = this.ebookProc.create(ebookVO);
      
      if ( cnt == 1 ) {
        mav.setViewName("redirect:/adm/ebook/list.do?cateno=" + cateno);
      }
      mav.addObject("cnt", cnt);
    } else {
      mav.setViewName("redirect:/admin/login.do");
    }

    return mav;
  }
  
  /**
   * �̺� ��ü ���
   * @return
   */
  /*
   * @RequestMapping(value="/adm/ebook/list_all.do", method=RequestMethod.GET)
   * public ModelAndView list_all() { ModelAndView mav = new ModelAndView();
   * 
   * String title = "Ebook ��ü ���"; mav.addObject("title", title);
   * 
   * List<EbookVO> list = this.ebookProc.list_ebno_desc();
   * 
   * int count = list.size();
   * 
   * mav.setViewName("/adm/ebook/list_all"); mav.addObject("count", count);
   * mav.addObject("list", list);
   * 
   * return mav; }
   */
  
//  @RequestMapping(value="/adm/ebook/list.do", method=RequestMethod.GET)
//  public ModelAndView list(@RequestParam(value = "cateno", defaultValue = "0") int cateno) {
//    ModelAndView mav = new ModelAndView();
//
//    List<EbookVO> list = null;
//     
//    if ( cateno == 0 ) {
//      list  = this.ebookProc.list_ebno_desc();
//      
//      String title = "ebook ��ü ���";
//      mav.addObject("title", title);
//      
//    } else {
//      list = this.ebookProc.list_ebook_category(cateno); 
//      
//      String cate_name = this.cateProc.read(cateno).getName();
//      mav.addObject("cate_name", cate_name);
//      
//      String title = cate_name + " ���";
//      mav.addObject("title", title);
//      
//      CateVO cateVO = this.cateProc.read(cateno);
//      mav.addObject("cateVO", cateVO);
//      
//      CategrpVO categrpVO = this.categrpProc.read(cateVO.getCg_no());
//      mav.addObject("categrpVO", categrpVO);
//    }
//    
//    mav.setViewName("/adm/ebook/list");
//    mav.addObject("list", list);
//    
//    return mav;
//  }
  
  /**
   * ������� ����Ʈ ����
   * @param cateno
   * @return
   */
  @RequestMapping(value="/adm/ebook/list_thumb.do", method=RequestMethod.GET)
  public ModelAndView adm_list_thumb(@RequestParam(value = "cateno", defaultValue = "0") int cateno, HttpSession session) {
    
    ModelAndView mav = new ModelAndView();
    List<EbookVO> list = null;
    
    boolean sw = this.adminProc.isAdmin(session);
    
    if ( sw == true ) {
      if ( cateno == 0 ) {
        list  = this.ebookProc.list_ebno_desc();
        
        String title = "ebook ��ü ���";
        mav.addObject("title", title);
        
      } else {
        list = this.ebookProc.list_ebook_category(cateno); 
        
        String cate_name = this.cateProc.read(cateno).getName();
        mav.addObject("cate_name", cate_name);
        
        String title = cate_name + " ���";
        mav.addObject("title", title);
        
        CateVO cateVO = this.cateProc.read(cateno);
        mav.addObject("cateVO", cateVO);
        
        CategrpVO categrpVO = this.categrpProc.read(cateVO.getCg_no());
        mav.addObject("categrpVO", categrpVO);
      }
      
      mav.setViewName("/adm/ebook/list_thumb");    
      mav.addObject("list", list);      
      
    } else {
      mav.setViewName("redirect:/admin/login.do");
    }
    
    return mav;
  }
  
  /**
   * �̺� ���� ��
   * @return
   */
  @RequestMapping(value="/adm/ebook/read.do", method=RequestMethod.GET)
  public ModelAndView adm_read(int eb_no, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    boolean sw = this.adminProc.isAdmin(session);
    
    if ( sw == true ) {
      List<CategrpVO> cglist = this.categrpProc.list_cgno_asc();    
      mav.addObject("cglist", cglist);
      
      EbookVO ebookVO = this.ebookProc.read(eb_no);
      mav.addObject("ebookVO", ebookVO);
      
      int cateno = ebookVO.getCateno();
      int cg_no = this.cateProc.read(cateno).getCg_no();
      mav.addObject("cg_no", cg_no);
      
      CateVO cateVO = this.cateProc.read(cateno);
      mav.addObject("cateVO", cateVO);
      
      CategrpVO categrpVO = this.categrpProc.read(cateVO.getCg_no());
      mav.addObject("categrpVO", categrpVO);
      
      
      mav.setViewName("/adm/ebook/read");
      
    } else {
      mav.setViewName("redirect:/admin/login.do");
    }
    
    
    return mav;
  }
  
  /**
   * �̺� ���� ó��
   * @return
   */
  @RequestMapping(value="/adm/ebook/update.do", method=RequestMethod.POST)
  public ModelAndView update(HttpServletRequest request, EbookVO ebookVO, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    boolean sw = this.adminProc.isAdmin(session);
    
    if ( sw == true ) {
      int cateno = ebookVO.getCateno();
      
      // �̺� ���� ����üũ
      if ( ebookVO.getEb_file1() == null || ebookVO.getEb_file1().equals("") ) {
        // -------------------------------------------------------------------
        // �̺� ���� ���� �ڵ� ����
        // -------------------------------------------------------------------
        String ebookFile = "";     // main image
        
        String ebookDir = Tool.getRealPath(request, "/ebook/storage/ebook_file"); // ���� ���
        // ���� ������ ����� fnamesMF ��ü�� ������.
        // <input type='file' class="form-control" name='file1MF' id='file1MF' 
        //        value='' placeholder="���� ����" multiple="multiple">
        MultipartFile mf1 = ebookVO.getFile1MF();
        long ebookSize = mf1.getSize();  // ���� ũ��
        if (ebookSize > 0) { // ���� ũ�� üũ
          // mp3 = mf.getOriginalFilename(); // ���� ���ϸ�, spring.jpg
          // ���� ���� �� ���ε�� ���ϸ��� ���ϵ�, spring.jsp, spring_1.jpg...
          ebookFile = Upload.saveFileSpring(mf1, ebookDir); 
          
        }
        
        ebookVO.setEb_file1(ebookFile);
        ebookVO.setEb_size1(ebookSize);
        
        // -------------------------------------------------------------------
        // ���� ���� �ڵ� ����
        // -------------------------------------------------------------------  
      }
      
      // ���� �̹��� ���� ����üũ
      if ( ebookVO.getEb_file2() == null || ebookVO.getEb_file2().equals("") ) {
        // -------------------------------------------------------------------
        // ���� �̹��� ���� ���� �ڵ� ����
        // -------------------------------------------------------------------
        String imgFile = "";     // main image
        String thumb = ""; // preview image
        
        String imageDir = Tool.getRealPath(request, "/ebook/storage/main_images"); // ���� ���
        // ���� ������ ����� fnamesMF ��ü�� ������.
        // <input type='file' class="form-control" name='file1MF' id='file1MF' 
        //        value='' placeholder="���� ����" multiple="multiple">
        MultipartFile mf2 = ebookVO.getFile2MF();
        long imgSize = mf2.getSize();  // ���� ũ��
        
        if (imgSize > 0) { // ���� ũ�� üũ
          // mp3 = mf.getOriginalFilename(); // ���� ���ϸ�, spring.jpg
          // ���� ���� �� ���ε�� ���ϸ��� ���ϵ�, spring.jsp, spring_1.jpg...
          imgFile = Upload.saveFileSpring(mf2, imageDir);
          
          if (Tool.isImage(imgFile)) { // �̹������� �˻�
            // thumb �̹��� ������ ���ϸ� ���ϵ�, width: 120, height: 80
            thumb = Tool.preview(imageDir, imgFile, 300, 400);
          }
          
        }
        
        ebookVO.setEb_file2(imgFile);
        ebookVO.setEb_size2(imgSize);
        ebookVO.setEb_thumb(thumb);
        
        // -------------------------------------------------------------------
        // ���� �̹��� ���� ���� �ڵ� ����
        // ------------------------------------------------------------------- 
      }
      
      int cnt = this.ebookProc.update(ebookVO);
      
      if ( cnt == 1 ) {
        mav.setViewName("redirect:/adm/ebook/list.do?cateno=" + cateno);      
      }
    } else {
      mav.setViewName("redirect:/admin/login.do");
    }
    
    
    return mav;
  }
  
  /**
   * �̺� ����
   * @param eb_no
   * @return
   */
  @RequestMapping(value="/adm/ebook/delete.do", method=RequestMethod.POST) 
  public ModelAndView delete(HttpServletRequest request, int eb_no, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    boolean admin_sw = this.adminProc.isAdmin(session);
    
    if ( admin_sw == true ) {
      EbookVO ebookVO = this.ebookProc.read(eb_no);
      int cateno = ebookVO.getCateno();
      
      int cnt = this.ebookProc.delete(eb_no);
      boolean sw = false;
      
      String ebookDir = Tool.getRealPath(request, "/ebook/storage/ebook_file/"); // ���� ���
      sw = Tool.deleteFile(ebookDir, ebookVO.getEb_file1()); // Folder���� 1���� ���� ����
      
      String imgDir = Tool.getRealPath(request, "/ebook/storage/main_images/"); // ���� ���
      sw = Tool.deleteFile(imgDir, ebookVO.getEb_file2()); // Folder���� 1���� ���� ����
      
      sw = Tool.deleteFile(ebookDir, ebookVO.getEb_thumb()); // Folder���� 1���� ���� ����
      
      if ( cnt == 1 ) {
        mav.setViewName("redirect:/adm/ebook/list.do?cateno=" + cateno );
      }
      
    } else {
      mav.setViewName("redirect:/admin/login.do");
    }

    return mav;
  }
  

  /**
   * �̺��� ���ϸ� ������
   * @param request
   * @param eb_no
   * @param num  ������ ����
   * @return
   */
  @ResponseBody
  @RequestMapping(value="/adm/ebook/file_delete.do", method=RequestMethod.POST) 
  public String file_delete(HttpServletRequest request, int eb_no, int type, HttpSession session) {
    
    JSONObject json = new JSONObject();
    
    EbookVO ebookVO = this.ebookProc.read(eb_no);

    boolean sw = false;
    int cnt = 0;
    
    if ( type == 1 ) {
      String ebookDir = Tool.getRealPath(request, "/ebook/storage/ebook_file/"); // ���� ���
      sw = Tool.deleteFile(ebookDir, ebookVO.getEb_file1()); // Folder���� 1���� ���� ����    
      ebookVO.setEb_file1("");
      ebookVO.setEb_size1(0);
      
      cnt = 1;
      
    } else if ( type == 2 ) {      
      String imgDir = Tool.getRealPath(request, "/ebook/storage/main_images/"); // ���� ���
      sw = Tool.deleteFile(imgDir, ebookVO.getEb_file2()); // Folder���� 1���� ���� ����
      sw = Tool.deleteFile(imgDir, ebookVO.getEb_thumb()); // Folder���� 1���� ���� ����
      ebookVO.setEb_file2("");
      ebookVO.setEb_size2(0);
      
      ebookVO.setEb_thumb("");
      
      cnt = 1;
    }  
    
    this.ebookProc.update(ebookVO);
    
    json.put("cnt", cnt);

    return json.toString();
  }
  
  /**
   * ��� + �˻� + ����¡ ����
   * @param cateno
   * @param word
   * @param nowPage
   * @return
   */
  @RequestMapping(value = "/adm/ebook/list.do", 
                                       method = RequestMethod.GET)
  public ModelAndView adm_list_paging(
      @RequestParam(value="cateno", defaultValue="0") int cateno,
      @RequestParam(value="word", defaultValue="") String word,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage,
      @RequestParam(value="type", defaultValue="1") int type,
      HttpSession session
      ) { 
    //System.out.println("--> nowPage: " + nowPage);
    
    ModelAndView mav = new ModelAndView();
    List<EbookVO> list = null;
    
    boolean sw = this.adminProc.isAdmin(session);
    
    if ( sw == true ) {
      // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
      HashMap<String, Object> map = new HashMap<String, Object>();
      map.put("cateno", cateno); // #{cateno}
      map.put("word", word);     // #{word}
      map.put("nowPage", nowPage);  // �������� ����� ���ڵ��� ������ �����ϱ����� ���     
      
      if ( cateno < 1 ) {
        list = ebookProc.list_ebno_desc_paging(map);
        
        String title = "ebook ��ü ���";
        mav.addObject("title", title);
      } else {
        list = ebookProc.list_by_cateno_search_paging(map);
        
        String cate_name = this.cateProc.read(cateno).getName();
        mav.addObject("cate_name", cate_name);
        
        String title = cate_name + " ���";
        mav.addObject("title", title);
        
        CateVO cateVO = this.cateProc.read(cateno);
        mav.addObject("cateVO", cateVO);
        
        CategrpVO categrpVO = this.categrpProc.read(cateVO.getCg_no());
        mav.addObject("categrpVO", categrpVO);
      }
      
      // �˻� ���
      mav.addObject("list", list);
      
      // �˻��� ���ڵ� ����
      int search_count = ebookProc.search_count(map);
      mav.addObject("search_count", search_count);
      
      /*
       * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
       * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
       * 
       * @param listFile ��� ���ϸ� 
       * @param cateno ī�װ���ȣ 
       * @param search_count �˻�(��ü) ���ڵ�� 
       * @param nowPage     ���� ������
       * @param word �˻���
       * @return ����¡ ���� ���ڿ�
       */ 
      String paging = ebookProc.pagingBox("list.do", cateno, search_count, nowPage, word, type);
      mav.addObject("paging", paging);
      
      mav.addObject("nowPage", nowPage);
      
      
      if ( type == 1 ) {
        mav.setViewName("/adm/ebook/list");         
      } else {
        mav.setViewName("/adm/ebook/list_thumb");
      }
    } else {
      mav.setViewName("redirect:/admin/login.do");
    }
    
    return mav;
  }
  
  
  
  /*******************************/
  /*    �׽�Ʈ �۾� �� 2020-11-12    */
  /******************************/
  
  
  /**
   * ����� ������ �ӽ�
   */
  @RequestMapping(value="/ebook/list.do", method=RequestMethod.GET)
  public ModelAndView list( @RequestParam(value="cateno", defaultValue="0") int cateno,
      @RequestParam(value="word", defaultValue="") String word,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage,
      @RequestParam(value="type", defaultValue="1") int type ) {
    ModelAndView mav = new ModelAndView();
    
    // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("cateno", cateno); // #{cateno}
    map.put("word", word);     // #{word}
    map.put("nowPage", nowPage);  // �������� ����� ���ڵ��� ������ �����ϱ����� ���  
    
    List<EbookVO> list = null;
    
    if ( cateno < 1 ) {
      list = ebookProc.list_ebno_desc_paging(map);
      
      String title = "ebook ��ü ���";
      mav.addObject("title", title);
    } else {
      list = ebookProc.list_by_cateno_search_paging(map);
      
      String cate_name = this.cateProc.read(cateno).getName();
      mav.addObject("cate_name", cate_name);
      
      String title = cate_name + " ���";
      mav.addObject("title", title);
      
      CateVO cateVO = this.cateProc.read(cateno);
      mav.addObject("cateVO", cateVO);
      
      CategrpVO categrpVO = this.categrpProc.read(cateVO.getCg_no());
      mav.addObject("categrpVO", categrpVO);
    }
    
    // �˻� ���
    mav.addObject("list", list);
    
    // �˻��� ���ڵ� ����
    int search_count = ebookProc.search_count(map);
    mav.addObject("search_count", search_count);

    /*
     * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
     * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
     * 
     * @param listFile ��� ���ϸ� 
     * @param cateno ī�װ���ȣ 
     * @param search_count �˻�(��ü) ���ڵ�� 
     * @param nowPage     ���� ������
     * @param word �˻���
     * @return ����¡ ���� ���ڿ�
     */ 
    String paging = ebookProc.pagingBox("list.do", cateno, search_count, nowPage, word, type);
    mav.addObject("paging", paging);
  
    mav.addObject("nowPage", nowPage);
    
    int count = list.size();
    
    mav.setViewName("/ebook/list");
    mav.addObject("count", count);
    mav.addObject("list", list);
    
    return mav;
  }
  
  /**
   * ī�װ� left �޴� ���
   * @param request
   * @return
   */
  @RequestMapping(value="/ebook/menu.do", method=RequestMethod.GET)
  public ModelAndView ebook_top_navi(HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();  
            
    List<CategrpVO> cg_list = this.categrpProc.list_cgseqno_asc();

    // Categrp: name, Cate: name ���� ���
    ArrayList<String> name_title_list = new ArrayList<String>();

    StringBuffer url = new StringBuffer(); // ī�װ� ���� ��ũ ����

    // ī�װ� �׷� ���� ��ŭ ��ȯ
    for (int index = 0; index < cg_list.size(); index++) {
      CategrpVO categrpVO = cg_list.get(index); // �ϳ��� ī�װ� �׷� ����

      name_title_list.add("<li class='ebook_categrp'>");
      name_title_list.add("<a class='nav-link dropdown-toggle' data-cate-id='categrp_"+ categrpVO.getCg_no() 
                          +"' href='#' id='navbarDropdown' role='button' data-toggle='dropdown' "
                          + "aria-haspopup='true' aria-expanded='false'>" + categrpVO.getCg_name() + "</a>");
      // ī�װ� Join ���
      List<Categrp_Cate_join> cate_list = cateProc.list_join_by_cgno(categrpVO.getCg_no());

      name_title_list.add("<div class='dropdown-menu' aria-labelledby='navbarDropdown'>");
      // ī�װ� ������ŭ ��ȯ
      for (int j = 0; j < cate_list.size(); j++) {
        Categrp_Cate_join categrp_Cate_join = cate_list.get(j);

        String name = categrp_Cate_join.getName(); // ī�װ� �̸�
        //int cnt = categrp_Cate_join.getCnt();

 
        url.append("  <a class='dropdown-item' href='" + request.getContextPath() + "/ebook/list.do?cateno="
            + categrp_Cate_join.getCateno() + "'>");
        url.append(name);
        url.append("  </a>");
        //url.append("  <span style='font-size: 0.9em; color: #555555;'>(" + cnt + ")</span>");
        name_title_list.add(url.toString()); // ��� ��Ͽ� �ϳ��� cate �߰�

        url.delete(0, url.toString().length()); // ���ο� ī�װ� ����� �����ϱ����� StringBuffer ���ڿ� ����

      }
      name_title_list.add("</div>");
      name_title_list.add("</li>");
    }

    mav.addObject("name_title_list", name_title_list);
    mav.setViewName("/ebook/menu/top_nav");
    //mav.addObject("total_count", this.contentsProc.total_count());
    
    return mav;
  }
  
  /**
   * ����� ������ �ӽ�
   */
  @RequestMapping(value="/ebook/read.do", method=RequestMethod.GET)
  public ModelAndView read(int eb_no) {
    ModelAndView mav = new ModelAndView();
    
    EbookVO ebookVO = this.ebookProc.read(eb_no);
    mav.addObject("ebookVO", ebookVO);   
    
    List<AttachfileVO> attachlist = this.attachfileProc.list_by_ebno(eb_no);
    mav.addObject("attachlist", attachlist);
    
    mav.setViewName("/ebook/read");
    return mav;
  }
}
