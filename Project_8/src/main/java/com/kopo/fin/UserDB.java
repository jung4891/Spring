package com.kopo.fin;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.sqlite.SQLiteConfig;

public class UserDB {

	public boolean createTable() {
		try {
			// open
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/apartment.db", config.toProperties());
						
			String query = "CREATE TABLE Member (idx INTEGER PRIMARY KEY AUTOINCREMENT, " +
							"name TEXT, age INTEGER, gender TEXT, apt TEXT)";
			Statement stmt = con.createStatement();	
			stmt.executeUpdate(query);			
			
			String query2 = "CREATE TABLE Apart (idx INTEGER PRIMARY KEY AUTOINCREMENT, " +
							"name TEXT)";
			Statement stmt2 = con.createStatement();	
			stmt.executeUpdate(query2);		
			
			stmt.close();	
			stmt2.close();
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
	public boolean insertData(Member m) {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/apartment.db", config.toProperties());

			String query = " INSERT INTO Member (name, age, gender) " 
							+ " VALUES (?, ?, ?)";	
			PreparedStatement psmt = con.prepareStatement(query);	
			psmt.setString(1, m.name);
			psmt.setInt(2, m.age);
			psmt.setString(3, m.gender);
			int result = psmt.executeUpdate();							
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
	
	public String selectAllData() {
		String resStr = "";
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/apartment.db", config.toProperties());
			
			String query = " SELECT * FROM Member ";				
			Statement stmt = con.createStatement();	
			ResultSet resSet = stmt.executeQuery(query);				
			while(resSet.next()) {
				int idx = resSet.getInt("idx");
				String name = resSet.getString("name");
				int age = resSet.getInt("age");
				String gender = resSet.getString("gender");
				
				resStr += "<tr>";
				resStr += "<td>" + idx + "</td>" + 
							"<td>" + name + "</td>" + 
							"<td>" + age + "</td>" + 
							"<td>" + gender + "</td>" + 
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
		Member m = new Member();
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/apartment.db", config.toProperties());
			
			String query = "SELECT * FROM Member WHERE idx=?";
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setInt(1, idx);
			ResultSet resSet = psmt.executeQuery();
			if(resSet.next()) {
				m.idx = resSet.getInt("idx");
				m.name = resSet.getString("name");
				m.age = resSet.getInt("age");
				m.gender = resSet.getString("gender");
			}	
			psmt.close();							
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;
	}
	
	public boolean updateData(Member m) {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/apartment.db", config.toProperties());
			
			String query = " UPDATE Member SET name=?, age=?, gender=?, apt=? WHERE idx=? ";
			PreparedStatement psmt = con.prepareStatement(query);		
			psmt.setString(1, m.name);		
			psmt.setInt(2, m.age);		
			psmt.setString(3, m.gender);			
			psmt.setString(4, m.apt);			
			psmt.setInt(5, m.idx);		
			int result = psmt.executeUpdate();
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
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/apartment.db", config.toProperties());
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
	
	public String memberInfo() {
		String resStr = "";
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/apartment.db", config.toProperties());
			
			String query = "SELECT COUNT(*) FROM Member";				
			Statement stmt = con.createStatement();	
			ResultSet resSet = stmt.executeQuery(query);		
			int count = resSet.getInt("COUNT(*)");
			
			query = "SELECT AVG(age) FROM Member";	
			stmt = con.createStatement();	
			resSet = stmt.executeQuery(query);		
			double ageAvg = resSet.getDouble("AVG(age)");
			
			query = "SELECT COUNT(*) FROM Member WHERE gender='남'";	
			stmt = con.createStatement();	
			resSet = stmt.executeQuery(query);		
			int countMan = resSet.getInt("COUNT(*)");
			int ratioMan = countMan*10/count;
			int ratioWoman = (count-countMan)*10/count;
			
			resStr += "<tr>";
			resStr += "<td>" + count + "</td>" + 
						"<td>" + ageAvg + "</td>" + 
						"<td>" + ratioMan + ":" + ratioWoman + "</td>";
			resStr += "</tr>";
			
			stmt.close();							
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resStr;
	}
	
	public boolean insertApt(Apart m) {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/apartment.db", config.toProperties());

			String query = " INSERT INTO Apart (name) " 
							+ " VALUES (?)";	
			PreparedStatement psmt = con.prepareStatement(query);	
			psmt.setString(1, m.name);
			int result = psmt.executeUpdate();							
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
	
	public String selectAllApt() {
		String resStr = "";
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/apartment.db", config.toProperties());
			
			String query = " SELECT * FROM Apart ";				
			Statement stmt = con.createStatement();	
			ResultSet resSet = stmt.executeQuery(query);				
			while(resSet.next()) {
				int idx = resSet.getInt("idx");
				String name = resSet.getString("name");
				
				resStr += "<tr>";
				resStr += "<td>" + idx + "</td>" + 
							"<td>" + name + "</td>" + 
							"<td><a href='updateApt?idx=" + idx + "'>수정</a></td>" + 
							"<td><a href='deleteApt?idx=" + idx + "'>삭제</a></td>"; 
				resStr += "</tr>";
			}
			stmt.close();							
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resStr;
	}
	
	public Apart selectOneApt(int idx) {
		Apart a = new Apart();
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/apartment.db", config.toProperties());
			
			String query = "SELECT * FROM Apart WHERE idx=?";
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setInt(1, idx);
			ResultSet resSet = psmt.executeQuery();
			if(resSet.next()) {
				a.idx = resSet.getInt("idx");
				a.name = resSet.getString("name");
			}	
			psmt.close();							
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}
	
	public boolean updateApt(Apart a) {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/apartment.db", config.toProperties());
			
			String query = " UPDATE Apart SET name=? WHERE idx=? ";
			PreparedStatement psmt = con.prepareStatement(query);		
			psmt.setString(1, a.name);			
			psmt.setInt(2, a.idx);			
			int result = psmt.executeUpdate();
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
	
	public boolean deleteApt(int idx) {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/apartment.db", config.toProperties());
			String query = " DELETE FROM Apart WHERE idx=? ";
			
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
	
	public String selectAptName() {
		String resStr = "";
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/apartment.db", config.toProperties());
			
			String query = " SELECT name FROM Apart ";				
			Statement stmt = con.createStatement();	
			ResultSet resSet = stmt.executeQuery(query);
			resStr += "<label> 아파트:";
			resStr += 	"<select name=\"apt\">";
			while(resSet.next()) {
				String name = resSet.getString("name");
				resStr += "<option>" + name + "</option>";
			}
			resStr += 	"</select>";
			resStr += "</label>";
			stmt.close();							
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resStr;
	}
	
	public String selectAptName2() {
		String resStr = "";
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/apartment.db", config.toProperties());
			
			String query = " SELECT name FROM Apart ";				
			Statement stmt = con.createStatement();	
			ResultSet resSet = stmt.executeQuery(query);				
			while(resSet.next()) {
				String name = resSet.getString("name");
				resStr += "<th>" + name + "</th>";
			}
			stmt.close();							
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resStr;
	}
	
	

	public ArrayList<String> selectAptName3() {
		ArrayList<String> resArr = new ArrayList<String>();
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/apartment.db", config.toProperties());
			
			String query = " SELECT name FROM Apart ";				 
			Statement stmt = con.createStatement();	
			ResultSet resSet = stmt.executeQuery(query);				
			while(resSet.next()) {
				String name = resSet.getString("name");
				resArr.add(name);
			}
			stmt.close();							
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resArr;
	}
	
	public String countAptMember(ArrayList<String> aptNames) {
		String resStr = "";
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/apartment.db", config.toProperties());
			
	        for (String aptName : aptNames) {
				String query = "SELECT COUNT(*) FROM Member WHERE apt=" + "'"+ aptName + "'";	
				Statement stmt = con.createStatement();	
				ResultSet resSet = stmt.executeQuery(query);		
				int count = resSet.getInt("COUNT(*)");
				resStr += "<td>" + count + "</td>";
				stmt.close();							
	        }
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resStr;
	}
	
	
	
}
