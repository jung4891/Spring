package com.kopo.memo;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteConfig;

public class MemoDB {

	public boolean createDB() {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "C:/tomcat/memoDB.db", config.toProperties());
			
			String query = "CREATE TABLE memo(idx INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, writer TEXT, content TEXT, created TEXT, updated TEXT, userIdx INTEGER)";
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);

			String query2 = "CREATE TABLE memoUser(idx INTEGER PRIMARY KEY AUTOINCREMENT, id TEXT, pwd TEXT, name TEXT, birthday TEXT, address TEXT, joindate TEXT)";
			Statement stmt2 = connection.createStatement();
			stmt2.executeUpdate(query2);

			stmt.close();
			stmt2.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean insertDb(Memo m) {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "C:/tomcat/memoDB.db", config.toProperties());

			String fieldString = "title, writer, content, created, updated, userIdx";
			String query = "INSERT INTO memo (" + fieldString + ") VALUES (?, ?, ?, ?, ?, ?);";
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1, m.title);
			psmt.setString(2, m.writer);
			psmt.setString(3, m.content);
			psmt.setString(4, m.created);
			psmt.setString(5, m.updated);
			psmt.setInt(6, m.userIdx);

			int finalResult = psmt.executeUpdate();
			if (finalResult < 1) {
				psmt.close();
				con.close();
				return false;
			}
			psmt.close();
			con.close();
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public String selectData() {
		String resStr = "";
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "C:/tomcat/memoDB.db", config.toProperties());
			String query = "SELECT * FROM memo;";
			PreparedStatement psmt = con.prepareStatement(query);
			ResultSet resultSet = psmt.executeQuery();

			while (resultSet.next()) {
				int idx = resultSet.getInt("idx");
				String title = resultSet.getString("title");
				String writer = resultSet.getString("writer");
				String content = resultSet.getString("content");
				String created = resultSet.getString("created");
				String updated = resultSet.getString("updated");

				resStr += "<tr>";
				resStr += "<td>" + idx + "</td>" 
						+ "<td class='content_sort'>" + title + "</td>" 
						+ "<td class='content_sort'>" + content + "</td>"
						+ "<td>" + created + "</td>"
						+ "<td>" + updated + "</td>"
						+ "<td>" + writer + "</td>";
				resStr += "</tr>";
			}
			psmt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resStr;
	}
	
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
	
	public boolean signup(User user) {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "C:/tomcat/memoDB.db", config.toProperties());
			
			// 아이디 중복여부 검사
			String query2 = "SELECT * FROM memoUser WHERE id = ?";
			PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
			preparedStatement2.setString(1, user.id);
			ResultSet resultSet = preparedStatement2.executeQuery();
			if (resultSet.next()) {
				preparedStatement2.close();
				connection.close();
				return false;
			}
			preparedStatement2.close();
		
			user.pwd = sha256(user.pwd);
			String fieldString = "id, pwd, name, birthday, address, joindate";
			String query = "INSERT INTO memoUser (" + fieldString + ") VALUES (?, ?, ?, ?, ?, ?);";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user.id);
			preparedStatement.setString(2, user.pwd);
			preparedStatement.setString(3, user.name);
			preparedStatement.setString(4, user.birthday);
			preparedStatement.setString(5, user.address);
			preparedStatement.setString(6, user.joindate);

			int result = preparedStatement.executeUpdate();
			if (result < 1) {
				preparedStatement.close();
				connection.close();
				return false;
			}
			preparedStatement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public int loginDB(User user) {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection connection = DriverManager.getConnection("jdbc:sqlite:/" + "C:/tomcat/memoDB.db", config.toProperties());

			user.pwd = sha256(user.pwd);
			String query2 = "SELECT * FROM memoUser WHERE id = ? AND pwd = ?";
			PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
			preparedStatement2.setString(1, user.id);
			preparedStatement2.setString(2, user.pwd);
			
			ResultSet resultSet = preparedStatement2.executeQuery();
			if (resultSet.next()) {
				int userIdx = resultSet.getInt("idx");
				preparedStatement2.close();
				connection.close();
				return userIdx;
			} else {
				preparedStatement2.close();
				connection.close(); 
				return -1;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -2;
		} catch (SQLException e) {
			e.printStackTrace();
			return -2;
		}
	}
	
	public String selectMyMemo(int userIdx) {
		String resStr = "";
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "C:/tomcat/memoDB.db", config.toProperties());
			String query = "SELECT * FROM memo WHERE userIdx=?";
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setInt(1, userIdx);
			ResultSet resultSet = psmt.executeQuery();

			while (resultSet.next()) {
				int idx = resultSet.getInt("idx");
				String title = resultSet.getString("title");
				String writer = resultSet.getString("writer");
				String content = resultSet.getString("content");
				String created = resultSet.getString("created");
				String updated = resultSet.getString("updated");

				resStr += "<tr>";
				resStr += "<td>" + idx + "</td>" 
						+ "<td class='content_sort'>" + title + "</td>" 
						+ "<td class='content_sort'>" + content + "</td>"
						+ "<td>" + created + "</td>"
						+ "<td>" + updated + "</td>"
						+ "<td>" + writer + "</td>"
						+ "<td><a href='update?idx=" + idx + "'>수정</a></td>" 
						+ "<td><a href='delete?idx=" + idx + "'>삭제</a></td>"; 
				resStr += "</tr>";
			}
			psmt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resStr;
	}
	
	public Memo selectMemo(int idx) {
		Memo m = new Memo();
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "C:/tomcat/memoDB.db", config.toProperties());
			String query = "SELECT * FROM memo WHERE idx=?";
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setInt(1, idx);
			ResultSet resultSet = psmt.executeQuery();

			if (resultSet.next()) {
				m.idx = resultSet.getInt("idx");
				m.title = resultSet.getString("title");
				m.writer = resultSet.getString("writer");
				m.content = resultSet.getString("content");
			}
			psmt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}
	
	public boolean updateMemo(Memo m) {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/memoDB.db", config.toProperties());
			
			String query = " UPDATE memo SET title=?, content=?, updated=? WHERE idx=? ";	// (주의!) 컬럼추가시 , 꼭 넣기
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1, m.title);		
			psmt.setString(2, m.content);		
			psmt.setString(3, m.updated);		
			psmt.setInt(4, m.idx);			// (주의!) 숫자 중복없나 확인, 맞게 넣었는지도 확인!

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
	
	public boolean deleteMemo(int idx) {
		try {
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig();
			Connection con = DriverManager.getConnection("jdbc:sqlite:/" + "c:/tomcat/memoDB.db", config.toProperties());
			String query = " DELETE FROM memo WHERE idx=? ";
			
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
}
