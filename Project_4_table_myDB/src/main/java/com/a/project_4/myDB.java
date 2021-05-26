package com.a.project_4;

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
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/myDBTest.db", config.toProperties());
						
			// use
			// 괄호 안하면 테이블 생성 안됨!!
			// sqlite는 정수는 INTEGER, 실수는 REAL, 문자열 TEXT
			String query = "CREATE TABLE scoreTable (idx INTEGER PRIMARY KEY AUTOINCREMENT, " +
							"name TEXT, midScore INTEGER, finScore INTEGER, sum INTEGER, avg REAL, created_date TEXT)";
			Statement stmt = con.createStatement();	// sql로 치면 에디터 열고
			stmt.executeUpdate(query);				// 쿼리문 작성후 실행
			stmt.close();							// 에디터 닫고
			
			// close
			con.close();							// 토드 종료
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void insertData(String name, int mid, int fin, String now) {
		
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/myDBTest.db", config.toProperties());
			
			int sum = mid + fin;
			double avg = sum / 2.0;
			String query = " INSERT INTO scoreTable (name, midScore, finScore, sum, avg, created_date) " + 
							"VALUES ('" + name + "', " + mid + ", " + fin + ", " + sum + ", " + avg + ", '" + now + "')";
							
			Statement stmt = con.createStatement();	
			stmt.executeUpdate(query);				
			stmt.close();							
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
		}
		// sqlite에서 현재날짜, 현재시간까지 출력하는 함수: date('now'), datetime('now') 
		// db가 더 빠르긴 하나 자바에서 하는게 낫다.
		// 문자열 입력시 일반 Statement는 "" 주의!! PreparedStatement는 ㄱㅊ. 깔끔. 둘다 할 줄 알아야.
	}
	
	public String selectAllData() {
		
		String resStr = "";
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/myDBTest.db", config.toProperties());
			
			String query = " SELECT * FROM scoreTable ";				
			Statement stmt = con.createStatement();	
			ResultSet resSet = stmt.executeQuery(query);				
			
			while(resSet.next()) {
				int idx = resSet.getInt("idx");
				String name = resSet.getString("name");
				int mid = resSet.getInt("midScore");
				int fin = resSet.getInt("finScore");
				int sum = resSet.getInt("sum");
				double avg = resSet.getDouble("avg");
				String date = resSet.getString("created_date");
				// 컬럼명 다르면 출력이 안됨....!!!!! 아래 e.printStactTrace() 찍어봐야 !! crete_date...
				
				resStr += "<tr>";
				resStr += "<td>" + idx + "</td>" + 
							"<td>" + name + "</td>" + 
							"<td>" + mid + "</td>" + 
							"<td>" + fin + "</td>" + 
							"<td>" + sum + "</td>" + 
							"<td>" + avg + "</td>" + 
							"<td>" + date + "</td>" + 
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
		
		// PreparedStatement (에디터에 미리 쿼리문을 써놓는거임)
		// prepared는 쿼리문에 ?를 넣고 setString을 넣어 1번째 ?에 "추가쿼리"를 넣는게 가능하다.
		// String query = "SELECT * FROM myTable WHERE name LIKE ?";
		// preparedStatement.setString(1, "%길%");
		
		// executeQuery()와 executeUpdate()
		// executeQuery()는 결과값이 반환되기에 ResultSet으로 받고  (select)
		// executeUpdate()는 결과값이 없고 실행결과(T/F)만 반환됨. (create, insert, delete) 
		// Statement로 하면 (query), Prepared로 하면 미리 쿼리문을 작성하므로 ();
	}
	
	public Student selectOneData(int idx) {
		Student oneData = new Student();
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/myDBTest.db", config.toProperties());
			
			String query = "SELECT * FROM scoreTable WHERE idx=?";
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setInt(1, idx);
			ResultSet resSet = psmt.executeQuery();
			if(resSet.next()) {
				oneData.name = resSet.getString("name");
				oneData.midScore = resSet.getInt("midScore");
				oneData.finScore = resSet.getInt("finScore");
			}	
			
			psmt.close();							
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oneData;
	}
	
	public void updateData(int idx, String name, int mid, int fin) {
		
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/myDBTest.db", config.toProperties());
			
			int sum = mid + fin;
			double avg = sum / 2.0;
			String query = " UPDATE scoreTable SET name=?, midScore=?, finScore=?, sum=?, avg=?, created_date=datetime('now') WHERE idx=? ";
			
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1, name);		
			psmt.setInt(2, mid);		
			psmt.setInt(3, fin);		
			psmt.setInt(4, sum);		
			psmt.setDouble(5, avg);	
			psmt.setInt(6, idx);		
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
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/myDBTest.db", config.toProperties());
			
			String query = " DELETE FROM scoreTable WHERE idx=? ";
			
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setInt(1, idx);		
			psmt.executeUpdate();
			
			psmt.close();							
			con.close();							
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	
}
