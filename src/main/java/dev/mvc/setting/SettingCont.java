package dev.mvc.setting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/adm")
public class SettingCont {
  
  public SettingCont() {
    System.out.println( "--> SettingCont created" );
  }
  
  @RequestMapping(value="/ebook/*.do", method=RequestMethod.GET)
  public ModelAndView adm() {
    System.out.println("¿©±â");
    
    ModelAndView mav = new ModelAndView();
    
    return mav;
  }
}
