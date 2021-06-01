package com.a.project_6;

public class Member {

	int idx;
	String id;
	String pwd;
	String name;
	String birthday;
	String address;
	String created;
	String updated;
	
	Member() {}

	public Member(String id, String pwd, String name, String birthday, String address, String created, String updated) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.birthday = birthday;
		this.address = address;
		this.created = created;
		this.updated = updated;
	}

	@Override
	public String toString() {
		return "Member [idx=" + idx + ", id=" + id + ", pwd=" + pwd + ", name=" + name + ", birthday=" + birthday
				+ ", address=" + address + ", created=" + created + ", updated=" + updated + "]";
	}
	
	
	

}
