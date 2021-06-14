package com.kopo.memo;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
//		model.addAttribute("msg", "Main Page");  이걸 살릴방법이 없나...
		
		try {
			boolean isLogin = (Boolean) session.getAttribute("is_login");
			if(isLogin) {
				return "mainLogin";	 	// 로그인시
			} else {	
				return "main";
			}
		} catch (Exception e) {			// 비로그인시 여기로 빠짐
			e.printStackTrace();
			return "main";
		}
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createTable(RedirectAttributes rttr, Model model) {
		MemoDB db = new MemoDB();
		boolean isSuccess = db.createDB();
		if (isSuccess) {
			rttr.addFlashAttribute("msg", "메모테이블이 생성되었습니다.");	 
//			model.addAttribute("msg", "메모테이블이 생성되었습니다.");
		} else {
			rttr.addFlashAttribute("msg", "DB ERROR");
//			model.addAttribute("msg", "DB ERROR");
		}
		return "redirect:/";
		
		// RedirectAttributes는 redirect되는 method의 jsp 파일에서 $('msg ')에 출력됨
	}
	
	// 로그인시 작성자에 아이디 입력되도록.
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		try {
			boolean isLogin = (Boolean) session.getAttribute("is_login");
			
			if(isLogin) {
				String login_id = (String) session.getAttribute("login_id");
				int userIdx = (Integer) session.getAttribute("userIdx");
				model.addAttribute("writer", login_id);
				model.addAttribute("userIdx", userIdx);
				return "insert";
			} else {	
				model.addAttribute("msg", "로그인이 필요합니다.~~");
				return "main";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인이 필요합니다.");
			return "mainLogin";
		}
	}
	
	@RequestMapping(value = "/insert_action", method = RequestMethod.POST)
	public String insertAction(HttpServletRequest request, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		int userIdx = Integer.parseInt(request.getParameter("userIdx"));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String now = sdf.format(Calendar.getInstance().getTime());
		Memo memo = new Memo(title, writer, content, now, now,  userIdx);
		
		MemoDB db = new MemoDB();
		boolean isSuccess = db.insertDb(memo);
		if (isSuccess) {
			model.addAttribute("msg", "메모가 등록되었습니다.");
		} else {
			model.addAttribute("msg", "메모가 등록되지 않았습니다.");
		}
		return "mainLogin";
	}
	
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public String select(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		try {
			boolean isLogin = (Boolean) session.getAttribute("is_login");
			if(isLogin) {
				MemoDB db = new MemoDB();
				String htmlStr = db.selectData();
				model.addAttribute("htmlStr", htmlStr);
				return "list";
			} else {	
				model.addAttribute("msg", "로그인이 필요합니다.~~");
				return "main";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인이 필요합니다.");
			return "main";
		}
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signUpMethod( Model model) {
		return "signup";
	}
	
	@RequestMapping(value = "/signup_action", method = RequestMethod.GET)
	public String signUpAction(HttpServletRequest request, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday");
		String address = request.getParameter("address");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String joindate = sdf.format(Calendar.getInstance().getTime());

		User user = new User(id, pwd, name, birthday, address, joindate);
		MemoDB db = new MemoDB();
		boolean isSuccess = db.signup(user);

		if (isSuccess) {
			model.addAttribute("msg", name+"님의 회원등록이 완료되었습니다.");
		} else {
			model.addAttribute("msg", "아이디가 중복되었거나 DB애러입니다.");
		}
		return "main";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login( Model model) {
		return "login";
	}

	@RequestMapping(value = "/login_action", method = RequestMethod.GET)
	public String loginAction(HttpServletRequest request, Model model) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		User user = new User(id, pwd);
		MemoDB db = new MemoDB();
		int userIdx = db.loginDB(user);

		if (userIdx > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("is_login", true);
			session.setAttribute("login_id", id);
			session.setAttribute("userIdx", userIdx);
			model.addAttribute("msg", "로그인 되었습니다.");
			return "mainLogin";
		} else if (userIdx == -1) {
			model.addAttribute("msg", "아이디 혹은 비밀번호가 틀렸습니다.");
			return "main";			
		} else {
			model.addAttribute("msg", "DB Error");
			return "main";
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		session.invalidate();
		model.addAttribute("msg", "로그아웃됨");
		return "main";
	}
	
	@RequestMapping(value = "/myMemo", method = RequestMethod.GET)
	public String selectMyData(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		int userIdx = (Integer) session.getAttribute("userIdx");
		MemoDB db = new MemoDB();
		String htmlStr = db.selectMyMemo(userIdx);
		model.addAttribute("htmlStr", htmlStr);
		return "myList";
		
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updatedata(Model model, @RequestParam("idx") int idx) {
		MemoDB db = new MemoDB();
		Memo m = db.selectMemo(idx);

		model.addAttribute("idx", m.idx);
		model.addAttribute("title", m.title);
		model.addAttribute("writer", m.writer);
		model.addAttribute("content", m.content);
		System.out.println(m);
		return "update";
	}
	
	@RequestMapping(value = "/update_action", method = RequestMethod.POST)
	public String update_action(HttpServletRequest request, Model model) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		int idx = Integer.parseInt(request.getParameter("idx"));
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sdf.format(Calendar.getInstance().getTime());
		
		MemoDB db = new MemoDB();
		Memo m  = new Memo(idx, title, content, now);
		boolean isSuccess =  db.updateMemo(m);
		if (isSuccess) {			
			model.addAttribute("msg", writer + " 님의 메모가 수정되었습니다~~");
		} else {
			model.addAttribute("msg", "DB Error");
		}
		return "mainLogin";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest req, Model model,
		@RequestParam("idx") int idx) {
		MemoDB db = new MemoDB();
		boolean isSuccess = db.deleteMemo(idx);
		if (isSuccess) {			
			model.addAttribute("msg", "데이터가 정상 삭제되었습니다.~~");
		} else {
			model.addAttribute("msg", "DB Error");
		}
		return "mainLogin";
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.GET)
	public String updateUser(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		int userIdx = (Integer) session.getAttribute("userIdx");
		
		MemoDB db = new MemoDB();
		User u = db.selectUser(userIdx);
		
		model.addAttribute("idx", u.idx);
		model.addAttribute("id", u.id);
		model.addAttribute("name", u.name);
		model.addAttribute("birthday", u.birthday);
		model.addAttribute("address", u.address);
		return "updateUser";
	}
	
	@RequestMapping(value = "/updateUser_action", method = RequestMethod.POST)
	public String updateUser_action(HttpServletRequest request, Model model) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		int idx = Integer.parseInt(request.getParameter("idx"));
		String pwd = request.getParameter("pwd1");
		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday");
		String address = request.getParameter("address");
		
		MemoDB db = new MemoDB();
		User u  = new User(idx, pwd, name, birthday, address);
		boolean isSuccess =  db.updateUser(u);
		if (isSuccess) {			
			model.addAttribute("msg", name + " 님의 데이터가 수정되었습니다~~");
		} else {
			model.addAttribute("msg", "DB Error");
		}
		return "mainLogin";
	}
	
	
	
	
	
}
