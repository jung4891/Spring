package com.a.project_6;

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
	
	UserDB db = new UserDB();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main() {
		return "main";
	}
	
	@RequestMapping(value = "/create_table", method = RequestMethod.GET)
	public String createTable(Model model) {
		boolean isSuccess = db.createTable();
		if (isSuccess) model.addAttribute("msg", "테이블 생성완료!");
		else	model.addAttribute("msg", "DB Error");
		return "message";
	}
	
	@RequestMapping(value = "/insert_data", method = RequestMethod.GET)
	public String insertData(Model model) {
		return "insert";
	}
	
	@RequestMapping(value = "/insert_action", method = RequestMethod.GET)
	public String insert_data_action(Model model,
			@RequestParam("id") String id,
			@RequestParam("pwd") String pwd,
			@RequestParam("name") String name,
			@RequestParam("birthday") String birthday,
			@RequestParam("address") String address) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sdf.format(Calendar.getInstance().getTime());
		Member m  = new Member(id, pwd, name, birthday, address, now, now);
		boolean isSuccess =  db.insertData(m);
		if (isSuccess) {			
			model.addAttribute("msg", name + " 님의 데이터가 정상입력 되었습니다. ^^");
		} else {
			model.addAttribute("msg", "DB Error");
		}
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
		Member emp = db.selectOneData(idx);
		
		if (emp != null) {			
		model.addAttribute("idx", idx);
		model.addAttribute("id", emp.id);
		model.addAttribute("pwd", emp.pwd);
		model.addAttribute("name", emp.name);
		model.addAttribute("birthday", emp.birthday);
		model.addAttribute("address", emp.address);
		} 
		System.out.println(model.toString());
		return "update";
	}
	
	@RequestMapping(value = "/update_action", method = RequestMethod.GET)
	public String update_action(Model model,
			@RequestParam("idx") int idx,
			@RequestParam("id") String id,
			@RequestParam("pwd") String pwd,
			@RequestParam("name") String name,
			@RequestParam("birthday") String birthday,
			@RequestParam("address") String address) {	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sdf.format(Calendar.getInstance().getTime());
		Member m  = new Member(id, pwd, name, birthday, address, now, now);
		m.idx = idx;
		System.out.println(m.toString());
		boolean isSuccess =  db.updateData(m);
		System.out.println(isSuccess);
		if (isSuccess) {			
			model.addAttribute("msg", name + " 님의 데이터가 수정되었습니다~~");
		} else {
			model.addAttribute("msg", "DB Error");
		}
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
