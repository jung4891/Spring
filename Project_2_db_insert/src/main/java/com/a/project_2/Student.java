package com.a.project_2;

public class Student {
	int idx;
	String name;
	int middleScore;
	int finalScore;
	int totalScore;
	
	Student(){
		
	}
	
	Student(String name, int middleScore, int finalScore) {
		// idx는 DBCommon에서 자동 입력되도록 설정해놨기에 생성자에 넣지 않아도 된다.
		this.name = name;
		this.middleScore = middleScore;
		this.finalScore = finalScore;
		this.totalScore = middleScore + finalScore;
	};
}
