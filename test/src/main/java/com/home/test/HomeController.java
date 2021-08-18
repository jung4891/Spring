package com.home.test;

import java.util.HashMap;
import java.util.Locale;

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
    
    // ajax 2
    @ResponseBody
	@RequestMapping(value="/serialize", method=RequestMethod.POST)
    public String serialize(Person person) {
        return person.getName() + " -> " + person.getAge();
    }
    
    // ajax 3
	@RequestMapping(value = "/sqliteList")
	public String sqliteList() {
		return "ajax/sqliteList";	
	}
	
	@RequestMapping(value = "/createPersonDB")
	public String create(Locale locale, Model model) {
		DBCommon db = new DBCommon<Person>("c:/users/go_go/desktop/student.db", "tb_stu");
		if (db.createTable(new Person())) {			
			model.addAttribute("msg", "테이블이 생성되었습니다.");
		} else {
			model.addAttribute("msg", "DB에러");
		}
		return "message";
	}
	
	
}
