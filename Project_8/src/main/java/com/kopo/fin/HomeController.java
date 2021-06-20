package com.kopo.fin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.sqlite.SQLiteConfig;

// 강의자료 210618 3번자료. ArrayList<Apart>랑 aptIdx 활용함. 땡길때 요 두개로 체인지 ㄱㄱ.

@Controller
public class HomeController {
	
	// 1. 입주자 기본정보 관리
	
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
	
	@RequestMapping(value = "/insert_member", method = RequestMethod.GET)
	public String insert_member(Model model) {
		return "insert";
	}
	
	@RequestMapping(value = "/insert_action", method = RequestMethod.POST)
	public String insert_action(HttpServletRequest request, Model model) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// utf-8(한글깨짐 방지)설정시 이렇게 받아야함.
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		Member m  = new Member(name, age, gender);
		
		boolean isSuccess =  db.insertData(m);
		if (isSuccess) {			
			model.addAttribute("msg", name + " 님의 데이터가 정상입력 되었습니다. ^^");
		} else {
			model.addAttribute("msg", "DB애러입니다.");
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
	public String update(Model model, @RequestParam("idx") int idx) {
		Member m = db.selectOneData(idx);
		if (m != null) {			
		model.addAttribute("idx", idx);
		model.addAttribute("name", m.name);
		model.addAttribute("age", m.age);
		model.addAttribute("gender", m.gender);
		String htmlStr = db.selectAptName();
		model.addAttribute("htmlList", htmlStr);
		} 
		return "update";
	}
	
	@RequestMapping(value = "/update_action", method = RequestMethod.GET)
	public String update_action(HttpServletRequest request, Model model) {	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		int idx = Integer.parseInt(request.getParameter("idx"));
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		String apt = request.getParameter("apt");
		Member m  = new Member(idx, name, age, gender, apt);

		boolean isSuccess =  db.updateData(m);
		if (isSuccess) {			
			model.addAttribute("msg", name + " 님의 데이터가 수정되었습니다~~");
		} else {
			model.addAttribute("msg", "DB Error");
		}
		return "message";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest req, Model model,
		@RequestParam("idx") int idx) {

		boolean isSuccess = db.deleteData(idx);
		if (isSuccess) {			
			model.addAttribute("msg", "데이터가 정상 삭제되었습니다.~~");
		} else {
			model.addAttribute("msg", "DB Error");
		}
		return "message";
	}
	
	
	// 2. 기본통계출력
	@RequestMapping(value = "/info_member", method = RequestMethod.GET)
	public String member_info(Model model) {
		String htmlStr = db.memberInfo();
		model.addAttribute("htmlList", htmlStr);
		return "infoMember";
	}
	
	// 3. 아파트 관리
	@RequestMapping(value = "/insert_apart", method = RequestMethod.GET)
	public String insert_apart(Model model) {
		return "insertApt";
	}
	
	@RequestMapping(value = "/insertApt_action", method = RequestMethod.POST)
	public String insertApt_action(HttpServletRequest request, Model model) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String name = request.getParameter("name");
		Apart a  = new Apart(name);
		
		boolean isSuccess =  db.insertApt(a);
		if (isSuccess) {			
			model.addAttribute("msg", name + " 아파트가 생성되었습니다.^^");
		} else {
			model.addAttribute("msg", "DB애러입니다.");
		}
		return "message";
	}
	
	@RequestMapping(value = "/apart_list", method = RequestMethod.GET)
	public String apart_list(Model model) {
		String htmlStr = db.selectAllApt();
		model.addAttribute("htmlList", htmlStr);
		return "listApt";
	}
	
	@RequestMapping(value = "/updateApt", method = RequestMethod.GET)
	public String updateApt(Model model, @RequestParam("idx") int idx) {
		Apart a = db.selectOneApt(idx);
		if (a != null) {			
			model.addAttribute("idx", idx);
			model.addAttribute("name", a.name);
		} 
		return "updateApt";
	}
	
	@RequestMapping(value = "/updateApt_action", method = RequestMethod.GET)
	public String updateApt_action(HttpServletRequest request, Model model) {	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		int idx = Integer.parseInt(request.getParameter("idx"));
		String name = request.getParameter("name");
		Apart a  = new Apart(idx, name);

		boolean isSuccess =  db.updateApt(a);
		if (isSuccess) {			
			model.addAttribute("msg", name + " 아파트로 이름이 수정되었습니다~~");
		} else {
			model.addAttribute("msg", "DB Error");
		}
		return "message";
	}
	
	@RequestMapping(value = "/deleteApt", method = RequestMethod.GET)
	public String deleteApt(HttpServletRequest req, Model model,
		@RequestParam("idx") int idx) {

		boolean isSuccess = db.deleteApt(idx);
		if (isSuccess) {			
			model.addAttribute("msg", "아파트가 정상 삭제되었습니다.~~");
		} else {
			model.addAttribute("msg", "DB Error");
		}
		return "message";
	}
	
	
	// 4. 아파트별 입주자의 수
	@RequestMapping(value = "/info_apart", method = RequestMethod.GET)
	public String info_apart(Model model) {
		String htmlStr1 = db.selectAptName2();
		ArrayList<String> aptNames = db.selectAptName3();
		String htmlStr2 = db.countAptMember(aptNames);
		
		model.addAttribute("htmlStr1", htmlStr1);
		model.addAttribute("htmlStr2", htmlStr2);
		return "infoApt";
	}
	
	
	
	
	
	
	
	

	
	
}
