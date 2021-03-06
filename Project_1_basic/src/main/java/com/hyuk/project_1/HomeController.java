package com.hyuk.project_1;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {	// 이게 WEB에서 server.js 같은 놈임
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date); 
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("test", "문자열로만 매핑됨");
		
		return "home";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String page(Locale locale, Model model) {

		return "test";	// views의 test.jsp파일이 실행됨 
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Locale locale, Model model) {
		DBCommon<Stu> db = new DBCommon<Stu>("c:/tomcat/student.db", "student");	// "student"가 테이블명이 됨
		db.createTable(new Stu());		// 위 경로에 db파일이 생성됨 (컬럼명 포함) 
		return "pageCreate";			// view에 pageCreate.jsp파일이 실행됨.
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(Locale locale, Model model) {
		DBCommon<Stu> db = new DBCommon<Stu>("c:/tomcat/student.db", "student");
		db.insertData(new Stu("송1", 100, 32));	// 위 경로의 db파일에 데이터가 입력됨
		db.insertData(new Stu("송2", 80, 30));	// 만일 위 경로의 student.db파일이 없을경우 만들어지긴하나 안에 테이블이 없음.
		return "pageInsert";
	}
	
}
