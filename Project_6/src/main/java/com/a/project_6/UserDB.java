package com.a.project_6;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.sqlite.SQLiteConfig;

public class UserDB {

	public boolean createTable() {
		try {
			// open
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/Member.db", config.toProperties());
						
			String query = "CREATE TABLE Member (idx INTEGER PRIMARY KEY AUTOINCREMENT, " +
							"id TEXT, pwd TEXT,name TEXT, birthday TEXT, address TEXT, created TEXT, updated TEXT)";
			Statement stmt = con.createStatement();	
			int result = stmt.executeUpdate(query);	// 실행되면 0이 반환됨 (DDL - CREATE)
			System.out.println(result);				// 이미 테이블 존재하변 바로 catch로 빠짐
			stmt.close();							
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
	public String sha256(String msg) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(msg.getBytes());
			
			StringBuilder builder = new StringBuilder();
			for (byte b: md.digest()) {
				builder.append(String.format("%02x", b));
			}
			return builder.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}				
	}
	
	public boolean insertData(Member m) {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/Member.db", config.toProperties());
			
			// password hash(단방향) -> md5(옛날), sha256(이거를 주로 사용)
			m.pwd = sha256(m.pwd);
			String query = " INSERT INTO Member (id, pwd, name, birthday, address, created, updated) " + 
						   " VALUES ('" + m.id + "', '" + m.pwd + "', '" + m.name + "', '" + m.birthday + 
						   "', '" + m.address + "', '"  + m.created + "', '"  + m.updated + "') ";					
			
			Statement stmt = con.createStatement();	
			int result = stmt.executeUpdate(query);		// 실행되면 적용된 row수(1) 반환됨			
			// System.out.println("result: " +result);		// (DML - INSERT, UPDATE, DELETE)
			if (result < 1) {
				return false;
			}
			stmt.close();							
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String selectAllData() {
		String resStr = "";
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/Member.db", config.toProperties());
			
			String query = " SELECT * FROM Member ";				
			Statement stmt = con.createStatement();	
			ResultSet resSet = stmt.executeQuery(query);				
			while(resSet.next()) {
				int idx = resSet.getInt("idx");
				String id = resSet.getString("id");
				String pwd = resSet.getString("pwd");
				String name = resSet.getString("name");
				String birthday = resSet.getString("birthday");
				String address = resSet.getString("address");
				String created = resSet.getString("created");
				String updated = resSet.getString("updated");
				
				resStr += "<tr>";
				resStr += "<td>" + idx + "</td>" + 
							"<td>" + id + "</td>" + 
							"<td width=\"10%\">" + pwd + "</td>" + 
							"<td>" + name + "</td>" + 
							"<td>" + birthday + "</td>" + 
							"<td>" + address + "</td>" + 
							"<td>" + created + "</td>" + 
							"<td>" + updated + "</td>" + 
							"<td><a href='update?idx=" + idx + "'>수정</a></td>" + 
							"<td><a href='delete?idx=" + idx + "'>삭제</a></td>"; 
				resStr += "</tr>";
			}
			stmt.close();							
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resStr;
	}
	
	public Member selectOneData(int idx) {
		Member oneData = new Member();
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/Member.db", config.toProperties());
			
			String query = "SELECT * FROM Member WHERE idx=?";
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setInt(1, idx);
			ResultSet resSet = psmt.executeQuery();
			if(resSet.next()) {
				oneData.id = resSet.getString("id");
				oneData.pwd = resSet.getString("pwd");
				oneData.name = resSet.getString("name");
				oneData.birthday = resSet.getString("birthday");
				oneData.address = resSet.getString("address");
			}	
			psmt.close();							
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oneData;
	}
	
	public boolean insertData(Member m) {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/Member.db", config.toProperties());
			
			// password hash(단방향) -> md5(옛날), sha256(이거를 주로 사용)
			m.pwd = sha256(m.pwd);
			String query = " INSERT INTO Member (id, pwd, name, birthday, address, created, updated) " + 
						   " VALUES ('" + m.id + "', '" + m.pwd + "', '" + m.name + "', '" + m.birthday + 
						   "', '" + m.address + "', '"  + m.created + "', '"  + m.updated + "') ";					
			
			Statement stmt = con.createStatement();	
			int result = stmt.executeUpdate(query);		// 실행되면 적용된 row수(1) 반환됨			
			// System.out.println("result: " +result);		// (DML - INSERT, UPDATE, DELETE)
			if (result < 1) {
				return false;
			}
			stmt.close();							
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void updateData(int idx, String name, String gender, String address, String team) {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/staff.db", config.toProperties());
			
			String query = " UPDATE staffTable SET name=?, gender=?, address=?, team=? WHERE idx=? ";
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1, name);		
			psmt.setString(2, gender);		
			psmt.setString(3, address);		
			psmt.setString(4, team);		
			psmt.setInt(5, idx);		
			psmt.executeUpdate();
			
			psmt.close();							
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteData(int idx) {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/staff.db", config.toProperties());
			String query = " DELETE FROM staffTable WHERE idx=? ";
			
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setInt(1, idx);		
			psmt.executeUpdate();
			psmt.close();							
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public String searchData(String nameStr) {
		String resStr = "";
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/staff.db", config.toProperties());
			
			String query = "SELECT * FROM staffTable WHERE name LIKE ?";
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1, "%"+nameStr+"%");
			ResultSet resSet = psmt.executeQuery();
			while(resSet.next()) {
				int idx = resSet.getInt("idx");
				String name = resSet.getString("name");
				String gender = resSet.getString("gender");
				String address = resSet.getString("address");
				String team = resSet.getString("team");
				resStr += "<tr>";
				resStr += "<td>" + idx + "</td>" + 
							"<td>" + name + "</td>" + 
							"<td>" + gender + "</td>" + 
							"<td>" + address + "</td>" + 
							"<td>" + team + "</td>" + 
							"<td><a href='update?idx=" + idx + "'>수정</a></td>" + 
							"<td><a href='delete?idx=" + idx + "'>삭제</a></td>"; 
				resStr += "</tr>";
			}
			psmt.close();							
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resStr;
	}
	
	
	
}
