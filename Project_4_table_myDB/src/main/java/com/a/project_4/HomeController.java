package com.a.project_4;

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
	
	@RequestMapping(value = "/insert_data_action", method = RequestMethod.GET)
	public String insert_data_action(Model model,
			@RequestParam("name") String name,
			@RequestParam("midScore") String midStr,
			@RequestParam("finScore") String finStr) {
		
		int midScore = Integer.parseInt(midStr);
		int finScore = Integer.parseInt(finStr);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String now = sdf.format(Calendar.getInstance().getTime());
		
		db.insertData(name, midScore, finScore, now);
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
		Student theStu = db.selectOneData(idx);
		
		if (theStu != null) {			
		model.addAttribute("idx", idx);
		model.addAttribute("name", theStu.name);
		model.addAttribute("midScore", theStu.midScore);
		model.addAttribute("finScore", theStu.finScore);
		}
		return "update";
	}
	
	
	@RequestMapping(value = "/update_action", method = RequestMethod.GET)
	public String update_action(Model model,
			@RequestParam("idx") int idx,
			@RequestParam("name") String name,
			@RequestParam("mid") String midStr,
			@RequestParam("fin") String finStr) {
		
		int midScore = Integer.parseInt(midStr);
		int finScore = Integer.parseInt(finStr);
	
		db.updateData(idx, name, midScore, finScore);
		model.addAttribute("msg", name + " 님의 데이터 정상 수정되었습니다. ^^");
		return "message";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Locale locale, Model model,
		@RequestParam("idx") int idx) {
	
		db.deleteData(idx);
		model.addAttribute("msg", "데이터가 정상 삭제되었습니다.");
		return "message";
		
		/*
		 * 여기서 '정말 삭제하시겠습니까?' 이벤트창 띄워서 한번더 확인받기.
		 */

	}
	
}
