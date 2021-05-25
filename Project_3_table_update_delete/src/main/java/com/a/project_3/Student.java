package com.a.project_3;

public class Student {
	
	int idx;
	String name;
	double midScore;
	double finScore;
	double totalScore;
	String create_date;
	
	Student(){
		 
	}
	
	// DBCommon에서 idx 자동증가하도록 처리해놓음~
	public Student(String name, double mid, double fin, String create_date) {
		super();
		this.name = name;
		this.midScore = mid;
		this.finScore = fin;
		this.totalScore = midScore + finScore;
		this.create_date = create_date;
	}
	
	public Student(int idx, String name, double mid, double fin, String create_date) {
		this.idx = idx;
		this.name = name;
		this.midScore = mid;
		this.finScore = fin;
		this.totalScore = midScore + finScore;
		this.create_date = create_date;
	}

	
	@Override
	public String toString() {
		return "idx: " + idx + ", 이름: " + name + ", "
				+ "중간고사: " + midScore + ", 기말고사: " + finScore + ", 총점: " + totalScore + ", 생성일: " + create_date;
	}

}
