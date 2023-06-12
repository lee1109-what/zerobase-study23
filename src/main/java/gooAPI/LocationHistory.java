package gooAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LocationHistory {
	
	public static void locationHistorySave(locationDTO myLocation) throws Exception {
		
		System.out.println("LAT : " + myLocation.getlat());
		System.out.println("LNG : " + myLocation.getlng());
		
		String url = "jdbc:mysql://localhost:3306/seoulwifi_db";
		String username = "testuser5";
		String password = "zerobase";
		
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, username, password);
			
			
			String insertSql = "INSERT INTO Location_history (LAT, LNT)"
					+ "VALUES (?, ?)";
			
			
			PreparedStatement insertStatement = connection.prepareStatement(insertSql);
			
			insertStatement.setString(1, String.valueOf(myLocation.getlat()));
			insertStatement.setString(2, String.valueOf(myLocation.getlng()));
			
			insertStatement.executeUpdate();
			
			// 연결과 자원 해제
			insertStatement.close();
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		

	}

}
