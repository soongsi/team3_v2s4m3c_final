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
  @Qualifier("dev.mvc.admin.AdminProc") // 이름 지정
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
   * 이북 등록 폼
   * @return
   */
  @RequestMapping(value="/adm/ebook/create.do", method=RequestMethod.GET)
  public ModelAndView create(@RequestParam(value = "cateno", defaultValue = "0") int cateno, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    boolean sw = this.adminProc.isAdmin(session);
    
    if ( sw == true ) {
      List<CategrpVO> cglist = this.categrpProc.list_cgno_asc();    
      mav.addObject("cglist", cglist);
      
      // 카테고리 그룹 + 카테고리 번호 및 이름 가져오기
      if ( cateno != 0 ) {
        CateVO cateVO = this.cateProc.read(cateno);
        int cg_no = cateVO.getCg_no();
        String cate_name = cateVO.getName();
        
        String title = cate_name + "신규 등록";
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
   * 이북 등록 폼 (전체 목록에서 등록 할때)
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
   * 이북 등록 처리
   * @return
   */
  @RequestMapping(value="/adm/ebook/create.do", method=RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, EbookVO ebookVO, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    boolean sw = this.adminProc.isAdmin(session);
    
    if ( sw == true ) {
      int cateno = ebookVO.getCateno();
      
      // -------------------------------------------------------------------
      // 이북 파일 전송 코드 시작
      // -------------------------------------------------------------------
      String ebookFile = "";     // ebook file
      
      String ebookDir = Tool.getRealPath(request, "/ebook/storage/ebook_file"); // 절대 경로
      // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
      // <input type='file' class="form-control" name='file1MF' id='file1MF' 
      //        value='' placeholder="파일 선택" multiple="multiple">
      MultipartFile mf1 = ebookVO.getFile1MF();
      long ebookSize = mf1.getSize();  // 파일 크기
      if (ebookSize > 0) { // 파일 크기 체크
        ebookFile = Upload.saveFileSpring(mf1, ebookDir); 
      }
      
      ebookVO.setEb_file1(ebookFile);
      ebookVO.setEb_size1(ebookSize);
      
      // -------------------------------------------------------------------
      // 이북 파일 전송 코드 종료
      // -------------------------------------------------------------------
      
      
      // -------------------------------------------------------------------
      // 메인 이미지 파일 전송 코드 시작
      // -------------------------------------------------------------------
      String imgFile = "";     // main image
      String thumb = ""; // preview image
      
      String imageDir = Tool.getRealPath(request, "/ebook/storage/main_images"); // 절대 경로
      // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
      // <input type='file' class="form-control" name='file1MF' id='file1MF' 
      //        value='' placeholder="파일 선택" multiple="multiple">
      MultipartFile mf2 = ebookVO.getFile2MF();
      long imgSize = mf2.getSize();  // 파일 크기
      
      if (imgSize > 0) { // 파일 크기 체크
        // mp3 = mf.getOriginalFilename(); // 원본 파일명, spring.jpg
        // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
        imgFile = Upload.saveFileSpring(mf2, imageDir);
        
        if (Tool.isImage(imgFile)) { // 이미지인지 검사
          // thumb 이미지 생성후 파일명 리턴됨, width: 120, height: 80
          thumb = Tool.preview(imageDir, imgFile, 300, 400);
        }
        
      }
      
      ebookVO.setEb_file2(imgFile);
      ebookVO.setEb_size2(imgSize);
      ebookVO.setEb_thumb(thumb);
      
      // -------------------------------------------------------------------
      // 메인 이미지 파일 전송 코드 종료
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
   * 이북 전체 목록
   * @return
   */
  /*
   * @RequestMapping(value="/adm/ebook/list_all.do", method=RequestMethod.GET)
   * public ModelAndView list_all() { ModelAndView mav = new ModelAndView();
   * 
   * String title = "Ebook 전체 목록"; mav.addObject("title", title);
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
//      String title = "ebook 전체 목록";
//      mav.addObject("title", title);
//      
//    } else {
//      list = this.ebookProc.list_ebook_category(cateno); 
//      
//      String cate_name = this.cateProc.read(cateno).getName();
//      mav.addObject("cate_name", cate_name);
//      
//      String title = cate_name + " 목록";
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
   * 썸네일형 리스트 보기
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
        
        String title = "ebook 전체 목록";
        mav.addObject("title", title);
        
      } else {
        list = this.ebookProc.list_ebook_category(cateno); 
        
        String cate_name = this.cateProc.read(cateno).getName();
        mav.addObject("cate_name", cate_name);
        
        String title = cate_name + " 목록";
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
   * 이북 수정 폼
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
   * 이북 수정 처리
   * @return
   */
  @RequestMapping(value="/adm/ebook/update.do", method=RequestMethod.POST)
  public ModelAndView update(HttpServletRequest request, EbookVO ebookVO, HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    boolean sw = this.adminProc.isAdmin(session);
    
    if ( sw == true ) {
      int cateno = ebookVO.getCateno();
      
      // 이북 파일 유무체크
      if ( ebookVO.getEb_file1() == null || ebookVO.getEb_file1().equals("") ) {
        // -------------------------------------------------------------------
        // 이북 파일 전송 코드 시작
        // -------------------------------------------------------------------
        String ebookFile = "";     // main image
        
        String ebookDir = Tool.getRealPath(request, "/ebook/storage/ebook_file"); // 절대 경로
        // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
        // <input type='file' class="form-control" name='file1MF' id='file1MF' 
        //        value='' placeholder="파일 선택" multiple="multiple">
        MultipartFile mf1 = ebookVO.getFile1MF();
        long ebookSize = mf1.getSize();  // 파일 크기
        if (ebookSize > 0) { // 파일 크기 체크
          // mp3 = mf.getOriginalFilename(); // 원본 파일명, spring.jpg
          // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
          ebookFile = Upload.saveFileSpring(mf1, ebookDir); 
          
        }
        
        ebookVO.setEb_file1(ebookFile);
        ebookVO.setEb_size1(ebookSize);
        
        // -------------------------------------------------------------------
        // 파일 전송 코드 종료
        // -------------------------------------------------------------------  
      }
      
      // 메인 이미지 파일 유무체크
      if ( ebookVO.getEb_file2() == null || ebookVO.getEb_file2().equals("") ) {
        // -------------------------------------------------------------------
        // 메인 이미지 파일 전송 코드 시작
        // -------------------------------------------------------------------
        String imgFile = "";     // main image
        String thumb = ""; // preview image
        
        String imageDir = Tool.getRealPath(request, "/ebook/storage/main_images"); // 절대 경로
        // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
        // <input type='file' class="form-control" name='file1MF' id='file1MF' 
        //        value='' placeholder="파일 선택" multiple="multiple">
        MultipartFile mf2 = ebookVO.getFile2MF();
        long imgSize = mf2.getSize();  // 파일 크기
        
        if (imgSize > 0) { // 파일 크기 체크
          // mp3 = mf.getOriginalFilename(); // 원본 파일명, spring.jpg
          // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
          imgFile = Upload.saveFileSpring(mf2, imageDir);
          
          if (Tool.isImage(imgFile)) { // 이미지인지 검사
            // thumb 이미지 생성후 파일명 리턴됨, width: 120, height: 80
            thumb = Tool.preview(imageDir, imgFile, 300, 400);
          }
          
        }
        
        ebookVO.setEb_file2(imgFile);
        ebookVO.setEb_size2(imgSize);
        ebookVO.setEb_thumb(thumb);
        
        // -------------------------------------------------------------------
        // 메인 이미지 파일 전송 코드 종료
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
   * 이북 삭제
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
      
      String ebookDir = Tool.getRealPath(request, "/ebook/storage/ebook_file/"); // 절대 경로
      sw = Tool.deleteFile(ebookDir, ebookVO.getEb_file1()); // Folder에서 1건의 파일 삭제
      
      String imgDir = Tool.getRealPath(request, "/ebook/storage/main_images/"); // 절대 경로
      sw = Tool.deleteFile(imgDir, ebookVO.getEb_file2()); // Folder에서 1건의 파일 삭제
      
      sw = Tool.deleteFile(ebookDir, ebookVO.getEb_thumb()); // Folder에서 1건의 파일 삭제
      
      if ( cnt == 1 ) {
        mav.setViewName("redirect:/adm/ebook/list.do?cateno=" + cateno );
      }
      
    } else {
      mav.setViewName("redirect:/admin/login.do");
    }

    return mav;
  }
  

  /**
   * 이북의 파일만 삭제함
   * @param request
   * @param eb_no
   * @param num  삭제할 파일
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
      String ebookDir = Tool.getRealPath(request, "/ebook/storage/ebook_file/"); // 절대 경로
      sw = Tool.deleteFile(ebookDir, ebookVO.getEb_file1()); // Folder에서 1건의 파일 삭제    
      ebookVO.setEb_file1("");
      ebookVO.setEb_size1(0);
      
      cnt = 1;
      
    } else if ( type == 2 ) {      
      String imgDir = Tool.getRealPath(request, "/ebook/storage/main_images/"); // 절대 경로
      sw = Tool.deleteFile(imgDir, ebookVO.getEb_file2()); // Folder에서 1건의 파일 삭제
      sw = Tool.deleteFile(imgDir, ebookVO.getEb_thumb()); // Folder에서 1건의 파일 삭제
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
   * 목록 + 검색 + 페이징 지원
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
      // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
      HashMap<String, Object> map = new HashMap<String, Object>();
      map.put("cateno", cateno); // #{cateno}
      map.put("word", word);     // #{word}
      map.put("nowPage", nowPage);  // 페이지에 출력할 레코드의 범위를 산출하기위해 사용     
      
      if ( cateno < 1 ) {
        list = ebookProc.list_ebno_desc_paging(map);
        
        String title = "ebook 전체 목록";
        mav.addObject("title", title);
      } else {
        list = ebookProc.list_by_cateno_search_paging(map);
        
        String cate_name = this.cateProc.read(cateno).getName();
        mav.addObject("cate_name", cate_name);
        
        String title = cate_name + " 목록";
        mav.addObject("title", title);
        
        CateVO cateVO = this.cateProc.read(cateno);
        mav.addObject("cateVO", cateVO);
        
        CategrpVO categrpVO = this.categrpProc.read(cateVO.getCg_no());
        mav.addObject("categrpVO", categrpVO);
      }
      
      // 검색 목록
      mav.addObject("list", list);
      
      // 검색된 레코드 갯수
      int search_count = ebookProc.search_count(map);
      mav.addObject("search_count", search_count);
      
      /*
       * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
       * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
       * 
       * @param listFile 목록 파일명 
       * @param cateno 카테고리번호 
       * @param search_count 검색(전체) 레코드수 
       * @param nowPage     현재 페이지
       * @param word 검색어
       * @return 페이징 생성 문자열
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
  /*    테스트 작업 중 2020-11-12    */
  /******************************/
  
  
  /**
   * 사용자 페이지 임시
   */
  @RequestMapping(value="/ebook/list.do", method=RequestMethod.GET)
  public ModelAndView list( @RequestParam(value="cateno", defaultValue="0") int cateno,
      @RequestParam(value="word", defaultValue="") String word,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage,
      @RequestParam(value="type", defaultValue="1") int type ) {
    ModelAndView mav = new ModelAndView();
    
    // 숫자와 문자열 타입을 저장해야함으로 Obejct 사용
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("cateno", cateno); // #{cateno}
    map.put("word", word);     // #{word}
    map.put("nowPage", nowPage);  // 페이지에 출력할 레코드의 범위를 산출하기위해 사용  
    
    List<EbookVO> list = null;
    
    if ( cateno < 1 ) {
      list = ebookProc.list_ebno_desc_paging(map);
      
      String title = "ebook 전체 목록";
      mav.addObject("title", title);
    } else {
      list = ebookProc.list_by_cateno_search_paging(map);
      
      String cate_name = this.cateProc.read(cateno).getName();
      mav.addObject("cate_name", cate_name);
      
      String title = cate_name + " 목록";
      mav.addObject("title", title);
      
      CateVO cateVO = this.cateProc.read(cateno);
      mav.addObject("cateVO", cateVO);
      
      CategrpVO categrpVO = this.categrpProc.read(cateVO.getCg_no());
      mav.addObject("categrpVO", categrpVO);
    }
    
    // 검색 목록
    mav.addObject("list", list);
    
    // 검색된 레코드 갯수
    int search_count = ebookProc.search_count(map);
    mav.addObject("search_count", search_count);

    /*
     * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
     * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
     * 
     * @param listFile 목록 파일명 
     * @param cateno 카테고리번호 
     * @param search_count 검색(전체) 레코드수 
     * @param nowPage     현재 페이지
     * @param word 검색어
     * @return 페이징 생성 문자열
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
   * 카테고리 left 메뉴 출력
   * @param request
   * @return
   */
  @RequestMapping(value="/ebook/menu.do", method=RequestMethod.GET)
  public ModelAndView ebook_top_navi(HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();  
            
    List<CategrpVO> cg_list = this.categrpProc.list_cgseqno_asc();

    // Categrp: name, Cate: name 결합 목록
    ArrayList<String> name_title_list = new ArrayList<String>();

    StringBuffer url = new StringBuffer(); // 카테고리 제목 링크 조합

    // 카테고리 그룹 갯수 만큼 순환
    for (int index = 0; index < cg_list.size(); index++) {
      CategrpVO categrpVO = cg_list.get(index); // 하나의 카테고리 그룹 추출

      name_title_list.add("<li class='ebook_categrp'>");
      name_title_list.add("<a class='nav-link dropdown-toggle' data-cate-id='categrp_"+ categrpVO.getCg_no() 
                          +"' href='#' id='navbarDropdown' role='button' data-toggle='dropdown' "
                          + "aria-haspopup='true' aria-expanded='false'>" + categrpVO.getCg_name() + "</a>");
      // 카테고리 Join 목록
      List<Categrp_Cate_join> cate_list = cateProc.list_join_by_cgno(categrpVO.getCg_no());

      name_title_list.add("<div class='dropdown-menu' aria-labelledby='navbarDropdown'>");
      // 카테고리 갯수만큼 순환
      for (int j = 0; j < cate_list.size(); j++) {
        Categrp_Cate_join categrp_Cate_join = cate_list.get(j);

        String name = categrp_Cate_join.getName(); // 카테고리 이름
        //int cnt = categrp_Cate_join.getCnt();

 
        url.append("  <a class='dropdown-item' href='" + request.getContextPath() + "/ebook/list.do?cateno="
            + categrp_Cate_join.getCateno() + "'>");
        url.append(name);
        url.append("  </a>");
        //url.append("  <span style='font-size: 0.9em; color: #555555;'>(" + cnt + ")</span>");
        name_title_list.add(url.toString()); // 출력 목록에 하나의 cate 추가

        url.delete(0, url.toString().length()); // 새로운 카테고리 목록을 구성하기위해 StringBuffer 문자열 삭제

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
   * 사용자 페이지 임시
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
