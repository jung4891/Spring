package com.a.project_3;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import org.sqlite.SQLiteConfig;

public class UserDB {

	public void createTable() {
		
		try {
			// open
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/userDBTest1.db", config.toProperties());
			
			// use
			String query = "CREATE TABLE myTable (idx INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "name TEXT, midScore REAL, finScore REAL, totalScore REAL, create_date TEXT)";
			// 괄호 안하면 테이블 생성 안됨!!
			// sqlite는 정수는 INTEGER, 실수는 REAL, 문자열 TEXT
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate(query);
			statement.close();
			
			// close
			connection.close();
		} catch (Exception e) {

		}
	}
	public void insertData2(String name, double midScore, double finScore) {
		try {
			// open
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/userDBTest1.db", config.toProperties());			

			String query = "INSERT INTO myTable (name, midScore, finScore, totalScore, create_date) "
					+ "VALUES ('" + name + "', " + midScore + ", " + finScore+ ", " + (midScore+finScore) + ", datetime('now'))";
			// sqlite에서 현재날짜, 현재시간까지 출력하는 함수: date('now'), datetime('now') 
			// db가 더 빠르긴 하나 자바에서 하는게 낫다.
			// 문자열 입력시 일반 Statement는 "" 주의!! PreparedStatement는 ㄱㅊ. 깔끔. 둘다 할 줄 알아야.
			
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
			
			// close
			connection.close();
		} catch (Exception e) {
		}
	}
	
	
	public void insertData(Student stu) throws IllegalArgumentException, IllegalAccessException {
		
		Field[] fieldArr = stu.getClass().getDeclaredFields();
		System.out.println(Arrays.toString(fieldArr));
/* 		[int com.a.project_3.Student.idx, 
 		java.lang.String com.a.project_3.Student.name, 
		int com.a.project_3.Student.midScore, 
		int com.a.project_3.Student.finScore, 
		int com.a.project_3.Student.totalScore, 
		java.lang.String com.a.project_3.Student.create_date] 
*/
		String fieldString = "";
		String valueString = "";
		for (Field field : fieldArr) {
			if (!fieldString.isEmpty()) {
				fieldString = fieldString + ",";
			}
			System.out.println(field);		// int com.a.project_3.Student.idx		// java.lang.String com.a.project_3.Student.name
			String fieldType = field.getType().toString();
			System.out.println(fieldType);	// int									// class java.lang.String
			String fieldName = field.getName();
			System.out.println(fieldName);	// idx									// name
			if (fieldName.matches("idx")) {
				continue;
			}
			fieldString = fieldString + fieldName;
			if (!valueString.isEmpty()) {
				valueString = valueString + ",";
			}
			if (fieldType.matches(".*String")) {
				valueString = valueString + "'" + field.get(stu) + "'";
			} else {
				valueString = valueString + field.get(stu);
			}
		}
		try {
			// open
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + 
					"c:/tomcat/userDBTest1.db", config.toProperties());
			
			// use
			String query = "INSERT INTO myTable " + "(" + fieldString + ") " + 
								"VALUES(" + valueString + ");";
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
			
			// close
			connection.close();
		} catch (Exception e) {

		}
	}
	
	public String selectData() {
		String resultString = "";
		try {
			// open
			System.out.println("??");
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + 
					"c:/tomcat/userDBTest1.db", config.toProperties());			

			String query = "SELECT * FROM myTable";
			// String query = "SELECT * FROM myTable WHERE ?";
			// String query = "SELECT * FROM myTable WHERE name LIKE ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			// preparedStatement.setString(1, "idx=1");
			// preparedStatement.setString(1, "%길%");
			// prepared는 쿼리문에 ?를 넣고 setString을 넣어 1번째 ?에 "추가쿼리"를 넣는게 가능하다.
			ResultSet resultSet = preparedStatement.executeQuery();
			// executeQuery()는 결과값이 반환되기에 ResultSet으로 받고  (select)
			// executeUpdate(query)는 결과값이 없고 실행결과(T/F)만 반환됨. (create, insert, delete) 
			while(resultSet.next()) {
				int idx = resultSet.getInt("idx");
				String name = resultSet.getString("name");
				System.out.println(name);
				double middleScore = resultSet.getDouble("midScore");
				double finalScore = resultSet.getDouble("finScore");
				double totalScore = resultSet.getDouble("totalScore");
				String created = resultSet.getString("create_date");
				// 컬럼명 다르면 출력이 안됨....!!!!! 아래 e.printStactTrace() 찍어봐야 !!
				resultString = resultString + "<tr>";
				System.out.println(name + " " + resultString);
				resultString = resultString + "<td>" + idx + "</td><td>" + name + "</td><td>" + middleScore
						+ "</td><td>" + finalScore + "</td><td>" + totalScore + "</td><td>" + created + "</td><td></td><td></td>";
				resultString = resultString + "</tr>";
			}
			System.out.println(resultString);
			preparedStatement.close();
			
			// close
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(resultString);
		return resultString;
	}
	 
	// 업데이트부분 정리. 210525 > 4 > 
	// 컨트롤러
	// 유저DB(select 수정하기, details, update) 
	
	
	
}
