package com.home.test;

import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/")
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/ajax")
	public String ajax() {
		return "ajax/ajax";	
	}
	
	// ajax 1
    @ResponseBody
	@RequestMapping(value="/requestObject", method=RequestMethod.POST)
    public String simpleWithObject(Person person, @RequestParam("test") String test) {
        return person.getName() + " " + person.getAge() + " / test: " + test ;
    }
/*	@RequestMapping과 @ResponseBody	
	@RequestMapping으로 String type을 반환해주면, ViewResolver에서 정의한 prefix와 suffix가 return값에 추가되어 view로 이동이 된다.
	@ResponseBody를 사용해주면 view를 생성해주는것이 아니라, JSON 혹은 Object 형태 또는 String 그대로 데이터를 넘겨준다. */
	
    // ajax 1-1
    @RequestMapping(value = "/hello_api")
    @ResponseBody
    public HashMap<String, String> helloApi() {
    	HashMap<String, String> resultMap = new HashMap<String, String>();
    	resultMap.put("name", "순이");
    	resultMap.put("version", "1.2");
    	return resultMap;
    }
    // http://localhost:8787/test/hello_api로 찍어보면 {"name":"순이","version":"1.2"}가 나온다.
    
    // ajax 2
    @ResponseBody
	@RequestMapping(value="/serialize", method=RequestMethod.POST, 
					produces = "application/text; charset=UTF-8")	
    public String serialize(Person person, HttpServletResponse response) {
        return person.getName() + " -> " + person.getAge();
    }
    // ajax로의 리턴값에서 한글이 ???로 깨지는 경우
    // RequestMapping의 produces속성을 사용해 UTF-8 인코딩을 해서 다시 클라이언트로 내보내주면 한글이 정상적으로 나오게 된다.
    
    // ajax 3
	@RequestMapping(value = "/sqliteList")
	public String sqliteList() {
		return "ajax/sqliteList";	
	}
	
	@RequestMapping(value = "/createPersonDB")
	public String create(Locale locale, Model model) {
		DBCommon db = new DBCommon<Person>("c:/users/go_go/desktop/person.db", "tb_person");
		boolean success = db.createTable(new Person());
		
		if (success) {			
			model.addAttribute("msg", "테이블이 생성되었습니다.");
		} else {
			model.addAttribute("msg", "DB에러");
		}
		return "message";
	}
	// model을 사용해 메시지를 message.jsp페이지에 전달하여 출력함
	
	@RequestMapping(value = "/insertPersonDB_Page")
	public String insertPersonDB_Page() {
		return "ajax/sqliteInsert";	
	}
	
	@ResponseBody
	@RequestMapping(value = "/insertPersonDB")
	public HashMap<String, String> insertPersonDB(@RequestParam("name") String name, 
												  @RequestParam("age") int age) {
		DBCommon db = new DBCommon<Person>("c:/users/go_go/desktop/person.db", "tb_person");
		boolean success = db.insertData(new Person(name, age));
		HashMap<String, String> result = new HashMap<String, String>();
		
		if (success) {			
			result.put("msg", "입력성공!");
		} else {
			result.put("msg", "입력실패ㅠ");
		}
		return result;
	}
	// HashMap을 사용하여 메시지를 ajax로 전달해서 alert창을 띄움
	
	
}
