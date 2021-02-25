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

  // 부모 Process 클래스가 위에 오도록 배치
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
   * 첨부파일 목록 및 등록 폼
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
   * 등록 처리
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
    // 파일 전송 코드 시작
    // ------------------------------------------------------------------
    int eb_no = attachfileVO.getEb_no(); // 부모글 번호
    String fname = ""; // 원본 파일명
    String fupname = ""; // 업로드된 파일명
    long fsize = 0; // 파일 사이즈
    String thumb = ""; // Preview 이미지
    int upload_count = 0; // 정상처리된 레코드 갯수
    
    
    String upDir = Tool.getRealPath(request, "/ebook/attachfile/storage");
    // 전송 파일이 없어서도 fnamesMF 객체가 생성됨.
    List<MultipartFile> fnamesMF = attachfileVO.getFnamesMF();
    int count = fnamesMF.size(); // 전송 파일 갯수
    if (count > 0) {
      for (MultipartFile multipartFile : fnamesMF) { // 파일 추출
        fsize = multipartFile.getSize(); // 파일 크기
        if (fsize > 0) { // 파일 크기 체크
          fname = multipartFile.getOriginalFilename(); // 원본 파일명
          fupname = Upload.saveFileSpring(multipartFile, upDir); // 파일 저장, 업로드된 파일

          if (Tool.isImage(fname)) { // 이미지인지 검사
            thumb = Tool.preview(upDir, fupname, 200, 150); // thumb 이미지 생성
          }
        }
        AttachfileVO vo = new AttachfileVO();
        vo.setEb_no(eb_no);
        vo.setFname(fname);
        vo.setFupname(fupname);
        vo.setThumb(thumb);
        vo.setFsize(fsize);

        // 파일 1건 등록 정보 dbms 저장, 파일이 20개이면 20개의 레코드 insert
        upload_count = upload_count + attachfileProc.create(vo); // 파일 1건 등록 정도 dbms 저장
      }
    }

    // -----------------------------------------------------
    // 파일 전송 코드 종료
    // -----------------------------------------------------

    mav.addObject("upload_count", upload_count); // redirect parameter 적용
    mav.addObject("eb_no", attachfileVO.getEb_no()); // redirect parameter 적용

    mav.setViewName("redirect:/adm/ebook/attachfile/list.do?eb_no=" + eb_no); // 새로고침 방지

    return mav;
  }

  /**
   * 새로 고침 방지 메시지 출력
   * 
   * @param url
   * @return
   */
  @RequestMapping(value = "/adm/ebook/attachfile/msg.do", method = RequestMethod.GET)
  public ModelAndView msg(String url) {
    ModelAndView mav = new ModelAndView();

    // 등록 처리 메시지: create_msg --> /attachfile/create_msg.jsp
    // 수정 처리 메시지: update_msg --> /attachfile/update_msg.jsp
    // 삭제 처리 메시지: delete_msg --> /attachfile/delete_msg.jsp
    mav.setViewName("/adm/ebook/attachfile/" + url); // forward

    return mav; // forward
  }


  /**
   * 첨부 파일 1건 삭제 처리
   * 
   * @return
   */
  @RequestMapping(value = "/adm/ebook/attachfile/delete.do", method = RequestMethod.POST)
  public ModelAndView delete_proc(HttpServletRequest request, int attachfileno, String rurl) {
    ModelAndView mav = new ModelAndView();

    // 삭제할 파일 정보를 읽어옴.
    AttachfileVO attachfileVO = attachfileProc.read(attachfileno);

    String upDir = Tool.getRealPath(request, "/ebook/attachfile/storage"); // 절대 경로
    Tool.deleteFile(upDir, attachfileVO.getFupname()); // Folder에서 1건의 파일 삭제
    Tool.deleteFile(upDir, attachfileVO.getThumb()); // 1건의 Thumb 파일 삭제

    // DBMS에서 1건의 파일 삭제
    attachfileProc.delete(attachfileno);

    List<AttachfileVO> list = attachfileProc.list(); // 목록 새로 고침
    mav.addObject("list", list);

    mav.setViewName("redirect:/adm/ebook/attachfile/" + rurl);

    return mav;
  }
  
  /**
   * 선택한 첨부 파일 삭제
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
      
      String upDir = Tool.getRealPath(request, "/ebook/attachfile/storage"); // 절대 경로
      Tool.deleteFile(upDir, attachfileVO.getFupname()); // Folder에서 1건의 파일 삭제
      Tool.deleteFile(upDir, attachfileVO.getThumb()); // 1건의 Thumb 파일 삭제
      
      attachfileProc.delete(del_num);
    }
    
    List<AttachfileVO> list = attachfileProc.list(); // 목록 새로 고침
    mav.addObject("list", list);
    mav.setViewName("redirect:/adm/ebook/attachfile/" + rurl);

    return mav;
  }

  // http://localhost:9090/team3/attachfile/count_by_ebno.do?eb_no=1
  /**
   * 부모키별 개수 산출
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
   * FK 부모키를 이용한 모든 레코드 삭제
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
    int cnt = 0; // 삭제된 레코드 개수

    String upDir = Tool.getRealPath(request, "/ebook/attachfile/storage"); // 절대 경로
    
    for (AttachfileVO attachfileVO : list) { // 파일 개수만큼 순환
      Tool.deleteFile(upDir, attachfileVO.getFupname()); // Folder에서 1건의 파일 삭제
      Tool.deleteFile(upDir, attachfileVO.getThumb()); // 1건의 Thumb 파일 삭제
      this.attachfileProc.delete(attachfileVO.getAttachfileno()); // DBMS에서 1건의 파일 삭제

      cnt = cnt + 1;
    }

    JSONObject json = new JSONObject();
    json.put("cnt", cnt);

    return json.toString();
  }

}
