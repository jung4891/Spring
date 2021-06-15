package com.kopo.fin;

public class Member {

	int idx;
	String name;
	int age;
	String gender;
	String apt;
	
	Member() {}
	
	public Member(int idx, String name, int age, String gender, String apt) {
		super();
		this.idx = idx;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.apt = apt;
	}
	
	public Member(int idx, String name, int age, String gender) {
		super();
		this.idx = idx;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public Member(String name, int age, String gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Member [idx=" + idx + ", name=" + name + ", age=" + age + ", gender=" + gender + "]";
	}


	
	
	

}
