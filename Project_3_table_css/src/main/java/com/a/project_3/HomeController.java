package com.a.project_3;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		return "main";
	}
	
	@RequestMapping(value = "/create_table", method = RequestMethod.GET)
	public String createTable(Locale locale, Model model) {
		DBCommon<Student> db = new DBCommon<Student>("c:/tomcat/0520.db", "student");
		db.createTable(new Student());
		model.addAttribute("message", "테이블이 생성됨!!");
		return "message";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(Locale locale, Model model) {

		return "insert";
	}
	
	@RequestMapping(value = "/insert_action", method = RequestMethod.GET)
	public String insertAct(@RequestParam("student_name") String name,
			@RequestParam("score") int score, Model model) {
		DBCommon<Student> db = new DBCommon<Student>("c:/tomcat/0520.db", "student");
		db.insertData(new Student(name, score, "2021-05-20"));
		model.addAttribute("message",  "데이터가 정상 입력됨@@@ ^_^");
		return "message";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String create(Locale locale, Model model) {  
		return "list";
	}
	

	
}
