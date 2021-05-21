package com.kopo.project_0521;

public class Student {
	
	int idx;
	String name;
	int mid;
	int fin;
	String create_date;
	
	Student(){
		
	}
	
	public Student(String name, int mid, int fin, String date) {
		super();
		this.name = name;
		this.mid = mid;
		this.fin = fin;
		this.create_date = date;
	}
	
	public Student(int idx, String name, int mid, int fin, String date) {
		this.idx = idx;
		this.name = name;
		this.mid = mid;
		this.fin = fin;
		this.create_date = date;
	}

}
