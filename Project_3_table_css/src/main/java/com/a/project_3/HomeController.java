package com.a.project_3;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
			@RequestParam("score") String scoreStr, Model model) {
		int score = Integer.parseInt(scoreStr);  // 서버는 String으로 값을 받아온다. 그래야 오류가 안난다고 하심
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sdf.format(Calendar.getInstance().getTime());
		DBCommon<Student> db = new DBCommon<Student>("c:/tomcat/0520.db", "student");
		db.insertData(new Student(name, score, now));
		model.addAttribute("message",  "데이터가 정상 입력됨@@@ ^_^");
		return "message";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String create(Locale locale, Model model) {  
		DBCommon<Student> db = new DBCommon<Student>("c:/tomcat/0520.db", "student");
		ArrayList<Student> students =  db.selectArrayList(new Student());
		
		String htmlString = "";
		for (int i=0; i<students.size(); i++) {
			htmlString += "<tr>";
			htmlString += "<td>";
			htmlString += students.get(i).idx;
			htmlString += "</td>";
			htmlString += "<td>";
			htmlString += students.get(i).name;
			htmlString += "</td>";
			htmlString += "<td>";
			htmlString += students.get(i).score;
			htmlString += "</td>";
			htmlString += "<td>";
			htmlString += students.get(i).create_date;
			htmlString += "</td>";
			htmlString += "</tr>";
		}
		model.addAttribute("list", htmlString);
		return "list";
	}
	

	
}
