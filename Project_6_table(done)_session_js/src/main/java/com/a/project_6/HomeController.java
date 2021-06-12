package com.a.project_6;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	public String insert_data(Model model) {
		return "insert";
	}
	
	/* 
	 * Get방식으로 클라에서 요청할 경우 
	 * http://localhost:8787/project_5/insert_action?name=Hyuk+Jung&gender=Song&address=1111&team=22
	 * POST방식으로 요청할 경우
	 * http://localhost:8787/project_6/insert_action
	 */
	@RequestMapping(value = "/insert_action", method = RequestMethod.POST)
	public String insert_action(HttpServletRequest request, Model model
//			@RequestParam("id") String id,
//			@RequestParam("pwd") String pwd,
//			@RequestParam("name") String name,
//			@RequestParam("birthday") String birthday,
//			@RequestParam("address") String address
			) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// utf-8(한글깨짐 방지)설정시 이렇게 받아야함.
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday");
		String address = request.getParameter("address");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sdf.format(Calendar.getInstance().getTime());
		Member m  = new Member(id, pwd, name, birthday, address, now, now);
		System.out.println(m);
		System.out.println("뭐지?");

		boolean isSuccess =  db.insertData(m);
		if (isSuccess) {			
			model.addAttribute("msg", name + " 님의 데이터가 정상입력 되었습니다. ^^");
		} else {
			model.addAttribute("msg", "아이디가 중복되었거나 DB애러입니다.");
		}
		return "message";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginMethod(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();

		Object isLogin = session.getAttribute("is_login");
		if (isLogin != null) {
			model.addAttribute("msg", "이미 로그인 상태입니다.");
			return "message";
		} else {			
			return "login";
		}
	}
	
	@RequestMapping(value = "/login_action", method = RequestMethod.POST)
	public String login_action(HttpServletRequest request, Model model) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		// utf-8(한글깨짐 방지)설정시 이렇게 받아야함.
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		int userIdx = db.login(id, pwd);
		
		if (userIdx > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("is_login", true);
			session.setAttribute("user_idx", userIdx);
			return "redirect:/";
		} else if (userIdx == -1) {
			return "redirect:/login";
		} else {
			return "redirect:/login";
		}
		
		/*
		  	Session: 서버에 있는 각 사용자들의 공간.
		  	
		  	Session
		  	사용자의 정보가 서버에 저장됨. (보안성 높다) 
          	시간이 지나면 소멸됨
          	톰캣등의 소프트웨어(서버)가 각 세션을 관리하는 것
          	톰캣에 웹브라우저별로 각각의 세션이 생성되어(각 브라우저에는 쿠키가 저장되고) 
          	크롬에서 로그인 되어 있어도 엣지에서는 로그인이 안되어 있는 것임.
			
			Cookie
			사용자의 컴퓨터에 저장됨. (보안성 낮다)
          	시간이 지나도 남음(포맷하거나 하면 지워짐)

			return "viewName" -> viewName.jsp인 뷰를 보여줌
			return "redirect:/" -> /주소로 URL 요청을 다시함.
		 */
	}
	
	@RequestMapping(value = "/show_list", method = RequestMethod.GET)
	public String show_list(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		
		try {
			boolean isLogin = (Boolean) session.getAttribute("is_login");
			
			if(isLogin) {
				System.out.println("dd");
				String htmlStr = db.selectAllData();
				model.addAttribute("htmlList", htmlStr);
				return "list";
			} else {	// 여기로 빠질 수가 있나?  근데 else문 없으면 return error발생함
				model.addAttribute("msg", "로그인이 필요합니다.");
				return "message";
			}
		} catch (Exception e) {
			System.out.println("aaa");
			e.printStackTrace();
			model.addAttribute("msg", "로그인이 필요합니다.(비로그인상태, Null)");
			return "message";
		}
		
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(HttpServletRequest req, Model model,
			@RequestParam("idx") int idx) {
		HttpSession session = req.getSession();
		int loginIdx = (Integer) session.getAttribute("user_idx");
		
		if (loginIdx != idx) {
			model.addAttribute("msg", "로그인한 사용자의 데이터만 수정가능합니다.");
			return "message";
		} else {
			Member m = db.selectOneData(idx);
			
			if (m != null) {			
			model.addAttribute("idx", idx);
			model.addAttribute("id", m.id);
			model.addAttribute("pwd", m.pwd);
			model.addAttribute("name", m.name);
			model.addAttribute("birthday", m.birthday);
			model.addAttribute("address", m.address);
			} 
			System.out.println(model.toString());
			return "update";
		}
	}
	
	// PUT방식으로 하니까 잘 안됨.. 왜 안되지?
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
//		System.out.println(m.toString());
		boolean isSuccess =  db.updateData(m);
//		System.out.println(isSuccess);
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
		HttpSession session = req.getSession();
		int loginIdx = (Integer) session.getAttribute("user_idx");
		
		if (loginIdx != idx) {
			model.addAttribute("msg", "로그인한 사용자의 데이터만 삭제가능합니다.");
			return "message";
		} else { 
			boolean isSuccess = db.deleteData(idx);
			if (isSuccess) {			
				model.addAttribute("msg", "데이터가 정상 삭제되었습니다.~~");
			} else {
				model.addAttribute("msg", "DB Error");
			}
			return "message";
		}
	}
	
	@RequestMapping(value = "/update_mydata", method = RequestMethod.GET)
	public String update_mydata(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		try {
			boolean isLogin = (Boolean) session.getAttribute("is_login");
			
			if (isLogin) {
				int idx = (Integer) session.getAttribute("user_idx");
				Member m = db.selectOneData(idx);
				
				model.addAttribute("idx", m.idx);
				model.addAttribute("id", m.id);
				model.addAttribute("original_name", m.name);
				model.addAttribute("original_birthday", m.birthday);
				model.addAttribute("original_address", m.address);
				return "updateMydata";
			} else {
				model.addAttribute("msg", "로그인이 필요합니다.");
				return "message";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인이 필요합니다.(비로그인상태, Null)");
			return "message";
		}
	}
	
	@RequestMapping(value = "/updateMydata_action", method = RequestMethod.GET)
	public String updateMydata_action(HttpServletRequest req, Model model) {
		
		try {
			req.setCharacterEncoding("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int idx = Integer.parseInt(req.getParameter("idx"));
		String id = req.getParameter("id");
		String pwd = req.getParameter("new_pwd");
		String name = req.getParameter("new_name");
		String birthday = req.getParameter("new_birthday");
		String address = req.getParameter("new_address");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sdf.format(Calendar.getInstance().getTime());
		Member m  = new Member(pwd, name, birthday, address, now);
		m.idx = idx;
		m.id = id;

		boolean isSuccess =  db.updateData(m);
		if (isSuccess) {			
			model.addAttribute("msg", name + " 님의 데이터가 수정되었습니다~~");
		} else {
			model.addAttribute("msg", "DB Error");
		}
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
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		
		try {
			boolean isLogin = (Boolean) session.getAttribute("is_login");
			
			if (isLogin) {
				session.invalidate();	// 로그아웃(세션 날림)
				model.addAttribute("msg", "로그아웃됨");
				return "message";
			} else {
				model.addAttribute("msg", "로그인을 하지 않았습니다.");
				return "message";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인을 하지 않았습니다.");
			return "message";
		}

	}

	
	
}
