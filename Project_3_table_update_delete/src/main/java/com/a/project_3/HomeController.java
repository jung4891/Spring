package com.a.project_3;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
	SimpleDateFormat sdfDate = new SimpleDateFormat("MMdd");
	String date = sdfDate.format(Calendar.getInstance().getTime());
	DBCommon<Student> db = new DBCommon<Student>("c:/tomcat/"+date+".db", "student");	
	UserDB db2 = new UserDB();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Locale locale, Model model) {
		// System.out.println(db);  	com.a.project_3.DBCommon@1e460fbd
		return "main";
	}
	
	@RequestMapping(value = "/create_table", method = RequestMethod.GET)
	public String createTable(Locale locale, Model model) {
//			db.createTable(new Student());
			db2.createTable();
			model.addAttribute("msg", "테이블이 생성됨!!");
			return "message";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(Locale locale, Model model) {
		return "insert";
	}
	
	@RequestMapping(value = "/insert_action", method = RequestMethod.GET)
	public String insertAct(Model model, 
			@RequestParam("student_name") String name,
			@RequestParam("midScore") String midStr,			 // 근데 애초에 score input의 type을 number로 받으면 아얘 숫자말고는 입력이 안되게 됨.
			@RequestParam("finScore") String finStr ) {			 // score에 문자 들어올때 (String으로 받아서 parseInt하도록!)
		try {													 // (int score로 받을시) 클라 오류(400-잘못된 요청) 뜨면서 터짐. (애러처리 못함..)
			int midScore = Integer.parseInt(midStr);  	  		 // (String으로 받아 parseInt시) 서버 오류(500)뜨면서 터짐. (try로 애러쳐리)
			int finScore = Integer.parseInt(finStr); 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = sdf.format(Calendar.getInstance().getTime());
			
//			db2.insertData(new Student(name, midScore, finScore, now));
	
			double middleScore = Double.parseDouble(midStr);
			double finalScore = Double.parseDouble(finStr);
			db2.insertData2(name, middleScore, finalScore);
			model.addAttribute("msg",  "데이터가 정상 입력됨@@@ ^_^");
			return "message_insert";
		} catch (Exception e) {		
			// e.printStackTrace();	
			model.addAttribute("msg", "점수란에 입력을 하지 않았음!");
			return "message_insert_err";
			/*
			 * 이거 창 이동안하고 ajax맹키로 이벤트창만 띄우는 식으로는 어케하지?
			 * 더블어 아예 그냥 점수란에 문자 들어가면 이벤트창 띄우는건? -> 아얘 input type을 number로 잡으면 됨.
			 */
		}
	}
	 
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listMethod(Locale locale, Model model) {  
//		ArrayList<Student> students = db.selectArrayList(new Student());
		
//		for (Student stu : students) {
//			System.out.println(stu);
//		}
//		idx: 1, 이름: song1, 성적: 11, 생성일: 2021-05-22 12:09:21
//		idx: 2, 이름: song2, 성적: 22, 생성일: 2021-05-22 12:49:27
//		idx: 3, 이름: song3, 성적: 33, 생성일: 2021-05-22 12:49:57
//		idx: 4, 이름: song4, 성적: 44, 생성일: 2021-05-22 19:50:10
		
//        <tr>
//        <td>1</td><td>송혁중</td><td>100</td><td>2021-05-18</td>
//        </tr>
//		  http://localhost:8787/project_3/update?idx=1		
		
		
//		String htmlStr = "";
//		for (Student stu : students) {
//			htmlStr += "<tr>";
//			htmlStr += "<td>" + stu.idx + "</td>";
//			htmlStr += "<td>" + stu.name + "</td>";
//			htmlStr += "<td>" + stu.midScore + "</td>";
//			htmlStr += "<td>" + stu.finScore + "</td>";
//			htmlStr += "<td>" + stu.totalScore + "</td>";
//			htmlStr += "<td>" + stu.create_date + "</td>";
//			htmlStr += "<td><a href='update?idx=" + stu.idx + "'>수정</a></td>";	// 와우..!!!
//			htmlStr += "<td><a href='delete?idx=" + stu.idx + "'>삭제</a></td>";	
//			htmlStr += "</tr>";
//		}

//		for (int i=0; i<students.size(); i++) {
//			htmlString += "<tr>";
//			htmlString += "<td> + students.get(i).idx + </td>";
//			...
//		}
		UserDB db2 = new UserDB();
		String htmlStr= db2.selectData();
		
		model.addAttribute("htmlList", htmlStr);
		return "list";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateMethod(Locale locale, Model model, 
			@RequestParam("idx") int idx) {
		DBCommon<Student> db = new DBCommon<Student>("c:/tomcat/" + date +".db", "student");
		Student theStu = db.detailsData(new Student(), idx);
//		System.out.println(selectStudent);
//		idx: 1, 이름: s, 중간고사: 1, 기말고사: 1, 총점: 2, 생성일: 2021-05-23 00:28:05
		if (theStu != null) {
			model.addAttribute("idx", theStu.idx);
			model.addAttribute("name", theStu.name);
			model.addAttribute("midScore", theStu.midScore);
			model.addAttribute("finScore", theStu.finScore);
		}
		return "update";
	}
	
	@RequestMapping(value = "/update_action", method = RequestMethod.GET)
	public String updateAction(Locale locale, Model model,
			@RequestParam("idx") int idx,
			@RequestParam("name") String name,
			@RequestParam("midScore") String midStr,
			@RequestParam("finScore") String finStr) {
		try {
			int midScore = Integer.parseInt(midStr);
			int finScore = Integer.parseInt(finStr);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = sdf.format(Calendar.getInstance().getTime());
			
			DBCommon<Student> db = new DBCommon<Student>("c:tomcat/"+date+".db", "student");
			db.updateData(new Student(idx, name, midScore, finScore, now));
			model.addAttribute("msg", "데이터가 수정되었습니다.");
			return "message";
		} catch (Exception e) {
			model.addAttribute("msg", "점수란에 입력을 하지 않았음!");
			model.addAttribute("idx", idx);
			return "message_update_err";
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String updateAction(Locale locale, Model model,
		@RequestParam("idx") int idx) {
	
		DBCommon<Student> db = new DBCommon<Student>("c:tomcat/"+date+".db", "student");
		Student theStu = db.detailsData(new Student(), idx);
		db.deleteData(theStu);
		model.addAttribute("msg", "데이터가 삭제되었습니다.");
		return "message_delete";
		
		/*
		 * 여기서 '정말 삭제하시겠습니까?' 이벤트창 띄워서 한번더 확인받기.
		 */

	}
	

	
}
