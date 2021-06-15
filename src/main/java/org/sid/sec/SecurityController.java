package org.sid.sec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	
	 @RequestMapping(value="/login")
	  public String login() {
		  
		  return "login";
	  }
	 
	 //action par deffaut
	 @RequestMapping(value="/")
	  public String home() {
		  
		  return "redirect:/entreprises";
	  }
	 @RequestMapping(value="/403")
	  public String accessdenied() {
		  
		  return "403";
	  }
}
