package com.home.test;

public class Person {
	
	int idx;
	String name;
	int age;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public Person() {
	}
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
