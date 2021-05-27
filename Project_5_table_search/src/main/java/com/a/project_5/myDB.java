package com.a.project_5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.sqlite.SQLiteConfig;

public class myDB {

	public void createTable() {
		try {
			// open
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/staff.db", config.toProperties());
						
			String query = "CREATE TABLE staffTable (idx INTEGER PRIMARY KEY AUTOINCREMENT, " +
							"name TEXT, gender TEXT, address TEXT, team TEXT)";
			Statement stmt = con.createStatement();	
			stmt.executeUpdate(query);				
			stmt.close();							
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void insertData(String name, String gender, String address, String team) {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/staff.db", config.toProperties());
			
			String query = " INSERT INTO staffTable (name, gender, address, team) " + 
						   " VALUES ('" + name + "', '" + gender + "', '" + address + "', '" + team + "') ";					
			Statement stmt = con.createStatement();	
			stmt.executeUpdate(query);				
			stmt.close();							
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public String selectAllData() {
		String resStr = "";
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/staff.db", config.toProperties());
			
			String query = " SELECT * FROM staffTable ";				
			Statement stmt = con.createStatement();	
			ResultSet resSet = stmt.executeQuery(query);				
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
			stmt.close();							
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resStr;
	}
	
	public Employee selectOneData(int idx) {
		Employee oneData = new Employee();
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/staff.db", config.toProperties());
			
			String query = "SELECT * FROM staffTable WHERE idx=?";
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setInt(1, idx);
			ResultSet resSet = psmt.executeQuery();
			if(resSet.next()) {
				oneData.name = resSet.getString("name");
				oneData.gender = resSet.getString("gender");
				oneData.address = resSet.getString("address");
				oneData.team = resSet.getString("team");
			}	
			psmt.close();							
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oneData;
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
