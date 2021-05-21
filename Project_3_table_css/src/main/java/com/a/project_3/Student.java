package com.a.project_3;

public class Student {
	
	int idx;
	String name;
	int score;
	String create_date;
	
	Student(){
		
	}
	
	public Student(String name, int score, String create_date) {
		super();
		this.name = name;
		this.score = score;
		this.create_date = create_date;
	}

}
