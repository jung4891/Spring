package com.a.project_2;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome Song's home! The client locale is {}.", locale);
		return "home";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Locale locale, Model model) {  // 주소입력시 역슬래시는 두번써야.
		DBCommon<Student> db = new DBCommon<Student>("c:/tomcat/test.db", "student");  
		db.createTable(new Student());
		model.addAttribute("msg", "테이블이 생성되었습니다.");
		return "message";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(Locale locale, Model model) {
		return "insert";
	}
	
	@RequestMapping(value = "/insert_action", method = RequestMethod.GET)
	public String insertAct(@RequestParam("name") String name, 
			@RequestParam("middle") int mid, @RequestParam("final") int fin, Model model) {
	
		DBCommon<Student> db = new DBCommon<Student>("c:/tomcat/test.db", "student");  
		db.insertData(new Student(name, mid, fin));			// test는 db명, student는 테이블명
		model.addAttribute("msg", "테이블이 입력되었습니다.");
		return "message";
	}
	
}
