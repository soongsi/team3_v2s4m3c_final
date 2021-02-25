package dev.mvc.notice;

import java.util.HashMap;
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

import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;


@Controller
public class NoticeCont {
  @Autowired
  @Qualifier("dev.mvc.notice.NoticeProc")
  private NoticeProcInter noticeProc;

  public NoticeCont() {
    System.out.println("--> NoticeCont created.");
  }

  /**
   * �����
   * @return
   */
  @RequestMapping(value = "/notice/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/notice/create");

    return mav;
  }
 
  /**
   * ���ó��
   * @param noticeVO
   * @return
   */
  @RequestMapping(value = "notice/create.do", method = RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, NoticeVO noticeVO) {
    noticeVO.setIp(request.getRemoteAddr()); // IP ����
    ModelAndView mav = new ModelAndView();
    
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------
    String nfile1 = "";     // main image
    String nthumb1 = ""; // preview image
        
    String upDir = Tool.getRealPath(request, "/notice/storage/main_images"); // ���� ���
    
    // ���� ������ ����� fnamesMF ��ü�� ������.
    // <input type='file' class="form-control" name='file1MF' id='file1MF' 
    //           value='' placeholder="���� ����" multiple="multiple">
    MultipartFile mf = noticeVO.getNfile1MF();
    
    long nsize1 = mf.getSize();  // ���� ũ��
    if (nsize1 > 0) { // ���� ũ�� üũ
      // mp3 = mf.getOriginalFilename(); // ���� ���ϸ�, spring.jpg
      // ���� ���� �� ���ε�� ���ϸ��� ���ϵ�, spring.jsp, spring_1.jpg...
      nfile1 = Upload.saveFileSpring(mf, upDir); 
      
      if (Tool.isImage(nfile1)) { // �̹������� �˻�
        // thumb �̹��� ������ ���ϸ� ���ϵ�, width: 200, height: 150
        nthumb1 = Tool.preview(upDir, nfile1, 200, 150); 
      }    
    }    
    
    noticeVO.setNfile1(nfile1);
    noticeVO.setNthumb1(nthumb1);
    noticeVO.setNsize1(nsize1);
    // -------------------------------------------------------------------
    // ���� ���� �ڵ� ����
    // -------------------------------------------------------------------

    int cnt = this.noticeProc.create(noticeVO);
    mav.addObject("cnt", cnt);
    mav.setViewName("/notice/create_msg");

    return mav;
  }

  /**
   * ���
   * @return
   */
  @RequestMapping(value = "/notice/list.do", method = RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/notice/list");

    List<NoticeVO> list = this.noticeProc.list_noticeno_asc();
    mav.addObject("list", list);

    return mav; // forward
  }

  /**
   * ��ü ���
   * @return
   */
  @RequestMapping(value = "/notice/read.do", method = RequestMethod.GET)
  public ModelAndView read(int noticeno) {
    ModelAndView mav = new ModelAndView();

    NoticeVO noticeVO = this.noticeProc.read(noticeno);
    mav.addObject("noticeVO", noticeVO);
    mav.setViewName("/notice/read"); 
    //mav.setViewName("/notice/read_img"); 
    return mav;
  }
  
  /**
   * ������
   * @return
   */
  @RequestMapping(value = "/notice/update.do", method = RequestMethod.GET)
  public ModelAndView update(int noticeno) {

    ModelAndView mav = new ModelAndView();
    
    NoticeVO noticeVO = this.noticeProc.read_update(noticeno);
    mav.addObject("noticeVO", noticeVO); // request�� ����

    mav.setViewName("/notice/update");

    return mav;
  }
  
  /**
   * ���� ó�� 
   * @param noticeVO
   * @return
   */
  @RequestMapping(value = "/notice/update.do", method = RequestMethod.POST)
  public ModelAndView update(NoticeVO noticeVO) {
    ModelAndView mav = new ModelAndView();

    mav.addObject("noticeno", noticeVO.getNoticeno());

    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("noticeno", noticeVO.getNoticeno());
    hashMap.put("notice_pw", noticeVO.getNotice_pw());

    int passwd_cnt = 0; // �н����� ��ġ ���ڵ� ����
    int cnt = 0; // ������ ���ڵ� ����

    passwd_cnt = this.noticeProc.passwd_check(hashMap);

    if (passwd_cnt == 1) { // �н����尡 ��ġ�� ��� �� ����
      cnt = this.noticeProc.update(noticeVO);
    }

    mav.addObject("cnt", cnt); // request�� ����
    mav.addObject("passwd_cnt", passwd_cnt); // request�� ����
    mav.setViewName("/notice/update_msg");

    return mav;
  }
  
  /**
   * ���� �̹��� ��� �� http://localhost:9090/resort/contents/img_create.do
   * 
   * @return
   */
  @RequestMapping(value = "/notice/img_create.do", method = RequestMethod.GET)
  public ModelAndView img_create(int noticeno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/notice/img_create"); // /webapp/notice/img_create.jsp

    NoticeVO noticeVO = this.noticeProc.read(noticeno);
    mav.addObject("noticeVO", noticeVO);
    
    return mav; // forward
  }

  /**
   * ���� �̹��� ��� ó�� http://localhost:9090/resort/notice/create.do
   * 
   * @return
   */
  @RequestMapping(value = "/notice/img_create.do", method = RequestMethod.POST)
  public ModelAndView img_create(HttpServletRequest request, NoticeVO noticeVO) {
    ModelAndView mav = new ModelAndView();
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("noticeno", noticeVO.getNoticeno());
    hashMap.put("notice_pw", noticeVO.getNotice_pw());
    
    int passwd_cnt = 0; // �н����� ��ġ ���ڵ� ����
    int cnt = 0;             // ������ ���ڵ� ���� 
    
    passwd_cnt = this.noticeProc.passwd_check(hashMap);
    
    if (passwd_cnt == 1) { // �н����尡 ��ġ�� ��� ���� ���ε�
      // -------------------------------------------------------------------
      // ���� ���� �ڵ� ����
      // -------------------------------------------------------------------
      String nfile1 = "";     // main image
      String nthumb1 = ""; // preview image
          
      String upDir = Tool.getRealPath(request, "/notice/storage/main_images"); // ���� ���
      
      MultipartFile mf = noticeVO.getNfile1MF();
      long nsize1 = mf.getSize();  // ���� ũ��
      if (nsize1 > 0) { // ���� ũ�� üũ
        // mp3 = mf.getOriginalFilename(); // ���� ���ϸ�, spring.jpg
        // ���� ���� �� ���ε�� ���ϸ��� ���ϵ�, spring.jsp, spring_1.jpg...
        nfile1 = Upload.saveFileSpring(mf, upDir); 
        
        if (Tool.isImage(nfile1)) { // �̹������� �˻�
          // thumb �̹��� ������ ���ϸ� ���ϵ�, width: 200, height: 150
          nthumb1 = Tool.preview(upDir, nfile1, 200, 150); 
        }
      }    
      
      noticeVO.setNfile1(nfile1);
      noticeVO.setNthumb1(nthumb1);
      noticeVO.setNsize1(nsize1);
      // -------------------------------------------------------------------
      // ���� ���� �ڵ� ����
      // -------------------------------------------------------------------
      
      mav.setViewName("redirect:/notice/read.do?noticeno=" + noticeVO.getNoticeno());
      
      cnt = this.noticeProc.img_create(noticeVO);
      
    } else {
      mav.setViewName("/notice/update_msg"); // webapp/contents/update_msg.jsp
      
    }

    mav.addObject("cnt", cnt); // request�� ����
    mav.addObject("passwd_cnt", passwd_cnt); // request�� ����
            
    return mav;    
  }
  
  /**
   * ���� �̹��� ����/���� �� http://localhost:9090/resort/notice/img_update.do
   * 
   * @return
   */
  @RequestMapping(value = "/notice/img_update.do", method = RequestMethod.GET)
  public ModelAndView img_update(int noticeno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/notice/img_update"); // /webapp/notice/img_update.jsp

    NoticeVO noticeVO = this.noticeProc.read(noticeno);
    mav.addObject("noticeVO", noticeVO);
    
    return mav; // forward
  }
  
  /**
   * ���� �̹��� ���� ó�� http://localhost:9090/resort/notice/img_delete.do
   * 
   * @return
   */
  @RequestMapping(value = "/notice/img_delete.do", method = RequestMethod.POST)
  public ModelAndView img_delete(HttpServletRequest request,
                                       int noticeno, 
                                       String notice_pw,
                                       @RequestParam(value="nowPage", defaultValue="1") int nowPage) {
    ModelAndView mav = new ModelAndView();
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("noticeno", noticeno);
    hashMap.put("notice_pw", notice_pw);
    
    int passwd_cnt = 0; // �н����� ��ġ ���ڵ� ����
    int cnt = 0;             // ������ ���ڵ� ���� 
    
    passwd_cnt = this.noticeProc.passwd_check(hashMap);
    
    if (passwd_cnt == 1) { // �н����尡 ��ġ�� ��� ���� ���ε�
      // -------------------------------------------------------------------
      // ���� ���� �ڵ� ����
      // -------------------------------------------------------------------
      // ������ ���� ������ �о��.
      NoticeVO noticeVO = noticeProc.read(noticeno);
      
      String nfile1 = noticeVO.getNfile1().trim();
      String nthumb1 = noticeVO.getNthumb1().trim();
      long nsize1 = noticeVO.getNsize1();
      boolean sw = false;
      
      String upDir = Tool.getRealPath(request, "/notice/storage/main_images"); // ���� ���
      sw = Tool.deleteFile(upDir, noticeVO.getNfile1());  // Folder���� 1���� ���� ����
      sw = Tool.deleteFile(upDir, noticeVO.getNthumb1());  // Folder���� 1���� ���� ����
      // System.out.println("sw: " + sw);
      
      nfile1 = "";
      nthumb1 = "";
      nsize1 = 0;
      
      noticeVO.setNfile1(nfile1);
      noticeVO.setNthumb1(nthumb1);
      noticeVO.setNsize1(nsize1);
      // -------------------------------------------------------------------
      // ���� ���� ���� ����
      // -------------------------------------------------------------------      
      mav.addObject("nowPage", nowPage);  // redirect�ÿ� get parameter�� ���۵�
      mav.addObject("noticeno", noticeno);
      mav.setViewName("redirect:/notice/read.do");
      
      cnt = this.noticeProc.img_delete(noticeVO);    
      
    } else {
      mav.setViewName("/notice/update_msg"); // webapp/notice/update_msg.jsp
      
    }
    mav.addObject("cnt", cnt); // request�� ����
    mav.addObject("passwd_cnt", passwd_cnt); // request�� ����
            
    return mav;    
  }
  
  /**
   * ���� �̹��� ���� ó�� http://localhost:9090/resort/notice/img_update.do
   * ���� �̹��� ������ ���ο� �̹��� ���(���� ó��)
   * @return
   */
  @RequestMapping(value = "/notice/img_update.do", method = RequestMethod.POST)
  public ModelAndView img_update(HttpServletRequest request, 
                                     NoticeVO noticeVO,
                                     @RequestParam(value="nowPage", defaultValue="1") int nowPage) {
    ModelAndView mav = new ModelAndView();
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("noticeno", noticeVO.getNoticeno());
    hashMap.put("notice_pw", noticeVO.getNotice_pw());
    
    int passwd_cnt = 0; // �н����� ��ġ ���ڵ� ����
    int cnt = 0;             // ������ ���ڵ� ���� 
    
    passwd_cnt = this.noticeProc.passwd_check(hashMap);
    
    if (passwd_cnt == 1) { // �н����尡 ��ġ�� ��� ���� ���ε�
      // -------------------------------------------------------------------
      // ���� ���� �ڵ� ����
      // -------------------------------------------------------------------
      // ������ ���� ������ �о��.
      NoticeVO vo = noticeProc.read(noticeVO.getNoticeno());
      // System.out.println("file1: " + noticeVO.getFile1());
      
      String nfile1 = vo.getNfile1().trim();
      String nthumb1 = vo.getNthumb1().trim();
      long nsize1 = 0;
      boolean sw = false;
      
      String upDir = Tool.getRealPath(request, "/notice/storage/main_images"); // ���� ���
      sw = Tool.deleteFile(upDir, noticeVO.getNfile1());  // Folder���� 1���� ���� ����
      sw = Tool.deleteFile(upDir, noticeVO.getNthumb1());  // Folder���� 1���� ���� ����
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
      MultipartFile mf = noticeVO.getNfile1MF();
      nsize1 = mf.getSize();  // ���� ũ��
      if (nsize1 > 0) { // ���� ũ�� üũ
        // mp3 = mf.getOriginalFilename(); // ���� ���ϸ�, spring.jpg
        // ���� ���� �� ���ε�� ���ϸ��� ���ϵ�, spring.jsp, spring_1.jpg...
        nfile1 = Upload.saveFileSpring(mf, upDir); 
        
        if (Tool.isImage(nfile1)) { // �̹������� �˻�
          // thumb �̹��� ������ ���ϸ� ���ϵ�, width: 200, height: 150
          nthumb1 = Tool.preview(upDir, nfile1, 200, 150); 
        }
      }    
      
      noticeVO.setNfile1(nfile1);
      noticeVO.setNthumb1(nthumb1);
      noticeVO.setNsize1(nsize1);
      // -------------------------------------------------------------------
      // ���� ���� �ڵ� ����
      // -------------------------------------------------------------------

      mav.addObject("nowPage", nowPage);
      mav.addObject("noticeno", noticeVO.getNoticeno());
      mav.setViewName("redirect:/notice/read.do");
      
      
      cnt = this.noticeProc.img_create(noticeVO);
      // mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)
      
    } else {
      mav.setViewName("/notice/update_msg"); // webapp/notice/update_msg.jsp   
    }

    mav.addObject("cnt", cnt); // request�� ����
    mav.addObject("passwd_cnt", passwd_cnt); // request�� ����
            
    return mav;    
  }
  
  
  /**
   * ��¸�� ����
   * 
   * @param noticeVO
   * @return
   */
  @RequestMapping(value = "/notice/update_visible.do", method = RequestMethod.GET)
  public ModelAndView update_visible(NoticeVO noticeVO) {
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.noticeProc.update_visible(noticeVO);
    mav.addObject("cnt", cnt); // request�� ����

    mav.setViewName("redirect:/notice/list.do"); // request ��ü�� ������ �ȵ�. 

    return mav;
  }
  
  /**
   * ������
   * @return
   */
  @RequestMapping(value = "/notice/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int noticeno) {
    ModelAndView mav = new ModelAndView();

    NoticeVO noticeVO = this.noticeProc.read_update(noticeno);
    mav.addObject("noticeVO", noticeVO); // request.setAttribute("noticeVO", noticeVO);

    List<NoticeVO> list = this.noticeProc.list_noticeno_asc();
    mav.setViewName("/notice/delete"); // webapp/notice/delete.jsp

    return mav;
  }

  /**
   * ���� ó�� 
   * @param noticeVO
   * @return
   */
  @RequestMapping(value = "/notice/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(int noticeno, String notice_pw) {
    ModelAndView mav = new ModelAndView();
    
    NoticeVO noticeVO = this.noticeProc.read(noticeno);
    String title = noticeVO.getTitle();
    mav.addObject("title", title);
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("noticeno", noticeVO.getNoticeno());
    hashMap.put("notice_pw", noticeVO.getNotice_pw());

    int passwd_cnt = 0; // �н����� ��ġ ���ڵ� ����
    int cnt = 0; // ������ ���ڵ� ����

    passwd_cnt = this.noticeProc.passwd_check(hashMap);
    
    if (passwd_cnt == 1) { // �н����尡 ��ġ�� ��� �� ����
      cnt = this.noticeProc.delete(noticeno);
    }

    mav.addObject("cnt", cnt); // request�� ����
    mav.addObject("passwd_cnt", passwd_cnt); // request�� ����
    mav.setViewName("/notice/delete_msg");

    return mav;
  }
}
