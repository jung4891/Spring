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

	// DBCommon에서 내용 가져와 수정한 부분.
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
	
	 
	// 업데이트부분 정리. 210525 > 4 > 
	// 컨트롤러
	// 유저DB(select 수정하기, details, update) 
	
	
	
}
