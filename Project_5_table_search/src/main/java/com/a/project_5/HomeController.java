package com.a.project_5;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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
	
	myDB db = new myDB();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main() {
		return "main";
	}
	
	@RequestMapping(value = "/create_table", method = RequestMethod.GET)
	public String createTable(Model model) {
		db.createTable();
		model.addAttribute("msg", "테이블 생성완료!");
		return "message";
	}
	
	@RequestMapping(value = "/insert_data", method = RequestMethod.GET)
	public String insertData(Model model) {
		return "insert";
	}
	
	@RequestMapping(value = "/insert_action", method = RequestMethod.GET)
	public String insert_data_action(Model model,
			@RequestParam("name") String name,
			@RequestParam("gender") String gender,
			@RequestParam("address") String address,
			@RequestParam("team") String team) {
		db.insertData(name, gender, address, team);
		model.addAttribute("msg", name + " 님의 데이터가 정상입력 되었습니다. ^^");
		return "message";
	}
	
	@RequestMapping(value = "/show_list", method = RequestMethod.GET)
	public String show_list(Model model) {
		String htmlStr = db.selectAllData();
		model.addAttribute("htmlList", htmlStr);
		return "list";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Model model,
			@RequestParam("idx") int idx) {
		Employee emp = db.selectOneData(idx);
		
		if (emp != null) {			
		model.addAttribute("idx", idx);
		model.addAttribute("name", emp.name);
		model.addAttribute("gender", emp.gender);
		model.addAttribute("address", emp.address);
		model.addAttribute("team", emp.team);
		}
		return "update";
	}
	
	@RequestMapping(value = "/update_action", method = RequestMethod.GET)
	public String update_action(Model model,
			@RequestParam("idx") int idx,
			@RequestParam("name") String name,
			@RequestParam("gender") String gender,
			@RequestParam("address") String address,
			@RequestParam("team") String team) {	
		db.updateData(idx, name, gender, address, team);
		model.addAttribute("msg", name + " 님의 데이터 정상 수정되었습니다. ^^");
		return "message";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Locale locale, Model model,
		@RequestParam("idx") int idx) {
		db.deleteData(idx);
		model.addAttribute("msg", "데이터가 정상 삭제되었습니다.");
		return "message";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Model model) {
		return "search";
	}
	
	@RequestMapping(value = "/search_action", method = RequestMethod.GET)
	public String update_action(Model model,
			@RequestParam("name") String name) {	
		String htmlStr = db.searchData(name);
		model.addAttribute("htmlList", htmlStr);
		return "list";
	}
	
}
