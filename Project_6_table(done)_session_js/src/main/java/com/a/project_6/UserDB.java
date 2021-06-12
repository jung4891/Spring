package com.a.project_6;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

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
			stmt.executeUpdate(query);					// 실행되면 0이 반환됨 (DDL - CREATE)
			// System.out.println(result);				// 이미 테이블 존재하변 바로 catch로 빠짐
			stmt.close();							
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
	// 패스워드 해쉬화
	// password hash(단방향) -> md5(옛날), sha256(이거를 주로 사용)
	public String sha256(String msg) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			// System.out.println(md);				SHA-256 Message Digest from SUN, <initialized>
			md.update(msg.getBytes());
			// System.out.println(md);				SHA-256 Message Digest from SUN, <in progress>
			// System.out.println(md.digest());		[B@c39f790
			StringBuilder sb = new StringBuilder();
			for (byte b: md.digest()) {
				// System.out.println(b);							107
				// System.out.println(String.format("%02x", b));	6b
				sb.append(String.format("%02x", b));
			}
			return sb.toString();		// 6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918
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
			
			// 아이디 중복 여부 검사
			String query = " SELECT * FROM Member " 
						   + "WHERE id=?";				
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1, m.id);
			ResultSet resSet = psmt.executeQuery();
			
			
			// stmt로 할경우 아래 애러가남. prepared로 하면 애러가 안남. (내 생각엔 utf-8로 인코딩을 해서 prepared만 가능한듯)
			// org.sqlite.SQLiteException: [SQLITE_ERROR] SQL error or missing database (no such column: a)
//			String query = " SELECT * FROM Member " 
//							+ "WHERE id=" + m.id;				
//			Statement stmt = con.createStatement();
//			ResultSet resSet = stmt.executeQuery(query);
			
			
			if (resSet.next()) {
				psmt.close();							
				con.close();	
				return false;
			} 
			psmt.close();	
	
			m.pwd = sha256(m.pwd);
			query = " INSERT INTO Member (id, pwd, name, birthday, address, created, updated) " 
					   + " VALUES (?, ?, ?, ?, ?, ?, ?)";	
			psmt = con.prepareStatement(query);	
			psmt.setString(1, m.id);
			psmt.setString(2, m.pwd);
			psmt.setString(3, m.name);
			psmt.setString(4, m.birthday);
			psmt.setString(5, m.address);
			psmt.setString(6, m.created);
			psmt.setString(7, m.updated);
			int result = psmt.executeUpdate();						// 실행되면 적용된 row수(1) 반환됨			
			// System.out.println("result: " +result);			   (DML - INSERT, UPDATE, DELETE)
			if (result < 1) {
				psmt.close();							
				con.close();	
				return false;
			}
			psmt.close();							
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public int login(String id, String pwd) {
		try { 
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/Member.db", config.toProperties());
			
			pwd = this.sha256(pwd);
			String query = "SELECT * FROM Member WHERE id=? AND pwd=?";
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			psmt.setString(2, pwd);
			ResultSet res = psmt.executeQuery();
			if (res.next()) {
				int idx = res.getInt("idx");
				psmt.close();
				con.close();
				return idx;
			} else {
				psmt.close();
				con.close();
				return -1;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			return -2;
		}
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
							"<td>" + pwd + "</td>" + 
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
				oneData.idx = resSet.getInt("idx");
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
	
	public boolean updateData(Member m) {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/Member.db", config.toProperties());
			
			m.pwd = sha256(m.pwd);
			String query = " UPDATE Member SET id=?, pwd=?, name=?, birthday=?, address=?, updated=? WHERE idx=? ";
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1, m.id);		
			psmt.setString(2, m.pwd);		
			psmt.setString(3, m.name);		
			psmt.setString(4, m.birthday);		
			psmt.setString(5, m.address);			
			psmt.setString(6, m.updated);		
			psmt.setInt(7, m.idx);		
			// 추가할때 번호순서 무조건 체크하도록!!!!
			System.out.println("[UPDATE query]");
			System.out.println(psmt);
			int result = psmt.executeUpdate();
			System.out.printf("\nquery실행후 결과값: %d", result);
			if (result < 1) {
				psmt.close();							
				con.close();
				return false;
			} else {
				psmt.close();							
				con.close();							
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteData(int idx) {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/Member.db", config.toProperties());
			String query = " DELETE FROM Member WHERE idx=? ";
			
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setInt(1, idx);		
			int result = psmt.executeUpdate();
			if (result < 1) {
				return false;
			}
			psmt.close();							
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String searchData(String nameStr) {
		String resStr = "";
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/Member.db", config.toProperties());
			
			String query = "SELECT * FROM Member WHERE name LIKE ?";
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1, "%"+nameStr+"%");
			ResultSet resSet = psmt.executeQuery();
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
							"<td>" + pwd + "</td>" + 
							"<td>" + name + "</td>" + 
							"<td>" + birthday + "</td>" + 
							"<td>" + address + "</td>" + 
							"<td>" + created + "</td>" + 
							"<td>" + updated + "</td>" + 
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
