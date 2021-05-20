package com.kopo.firstproject;

public class Student {
	int idx;
	String name;
	int middleScore;
	int finalScore;
	
	Student(){
		
	}
	
	Student(String name, int middleScore, int finalScore) {
		this.name = name;
		this.middleScore = middleScore;
		this.finalScore = finalScore;
	};
}
