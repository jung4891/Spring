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
		this.name = name;
		this.middleScore = middleScore;
		this.finalScore = finalScore;
		this.totalScore = middleScore + finalScore;
	};
}
