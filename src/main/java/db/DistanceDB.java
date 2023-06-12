package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

import openapi.OpenApiDTO.MyData;

// 거리를 저장하고 있는 배열 arr을 GetDistance에서 호출함.
// arr의 요소를 db table에 저장
public class DistanceDB {
	
	public static void saveDistance(double[] arr) throws Exception {
		
		String url = "jdbc:mysql://localhost:3306/seoulwifi_db";
		String username = "testuser5";
		String password = "zerobase";
		
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, username, password);
			
			List<MyData> list = new ArrayList<>();
			
			for(int i=0; i<arr.length; i++) {
				MyData dto = new MyData();
				dto.setX_SWIFI_DISTANCE(arr[i]);
				list.add(dto);
				
			}
			
			
			/*
			for(double element : arr) {
				MyData dto = new MyData();
				dto.setX_SWIFI_DISTANCE(element);
				list.add(dto);
			}*/
			
			
			int i = 1;
			for(MyData data: list) {
			
			// 거리 데이터를 MyData_table에 저장함.
			// INSERT문 실행
				String updateSql = "UPDATE MyData_Table SET X_SWIFI_DISTANCE = ? WHERE id = ?";

				PreparedStatement updateStatement = connection.prepareStatement(updateSql);

				updateStatement.setDouble(1, data.getX_SWIFI_DISTANCE());
				updateStatement.setInt(2, i);  // 해당 id(i)에 거리 데이터를 저장함

				updateStatement.executeUpdate();
				
				// 연결과 자원 해제
				updateStatement.close();
				i++;
			}
			
			
			System.out.println("데이터가 mysql에 저장되었습니다.");
			
			// MyData_soredTable에 데이터가 있으면 삭제
			String countSql = "SELECT COUNT(*) FROM MyData_sortedTable";
			PreparedStatement countStatement = connection.prepareStatement(countSql);
			ResultSet resultSet = countStatement.executeQuery();
			resultSet.next();
			int count = resultSet.getInt(1);
			resultSet.close();
			countStatement.close();
			
			//데이터 삭제
			if(count > 0) {
				String deleteSql = "DELETE FROM MyData_sortedTable";
				PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
				deleteStatement.executeUpdate();
				deleteStatement.close();
				System.out.println("MyData_sortedTable의 기존의 데이터를 삭제했습니다.");
				
				String resetAutoIncrementSql = "ALTER TABLE MyData_sortedTable AUTO_INCREMENT = 1";
				Statement resetAutoIncrementStatement = connection.createStatement();
				resetAutoIncrementStatement.execute(resetAutoIncrementSql);
				resetAutoIncrementStatement.close();
				System.out.println("id를 0으로 만듭니다.");
			}
						
			
			// DB에서 데이터를 꺼내와 정렬함 
			String selectSql = "SELECT * FROM MyData_Table ORDER BY X_SWIFI_DISTANCE ASC LIMIT 20";
			
			Statement selectStatement = connection.createStatement();
			ResultSet rs = selectStatement.executeQuery(selectSql);
			
			
			
			while(rs.next()) {
				
				// 정렬된 데이터를 변수에 저장
				//int id = rs.getInt("id");
				double X_SWIFI_DISTANCE = rs.getDouble("X_SWIFI_DISTANCE");
				String X_SWIFI_MGR_NO = rs.getString("X_SWIFI_MGR_NO");
				String X_SWIFI_WRDOFC = rs.getString("X_SWIFI_WRDOFC");
				String X_SWIFI_MAIN_NM = rs.getString("X_SWIFI_MAIN_NM");
				String X_SWIFI_ADRES1 = rs.getString("X_SWIFI_ADRES1");
				String X_SWIFI_ADRES2 = rs.getString("X_SWIFI_ADRES2");
				String X_SWIFI_INSTL_FLOOR = rs.getString("X_SWIFI_INSTL_FLOOR");
				String X_SWIFI_INSTL_TY = rs.getString("X_SWIFI_INSTL_TY");
				String X_SWIFI_INSTL_MB = rs.getString("X_SWIFI_INSTL_MB");
				String X_SWIFI_SVC_SE = rs.getString("X_SWIFI_SVC_SE");
				String X_SWIFI_CMCWR = rs.getString("X_SWIFI_CMCWR");
				String X_SWIFI_CNSTC_YEAR = rs.getString("X_SWIFI_CNSTC_YEAR");
				String X_SWIFI_INOUT_DOOR = rs.getString("X_SWIFI_INOUT_DOOR");
				String X_SWIFI_REMARS3 = rs.getString("X_SWIFI_REMARS3");
				String LAT = rs.getString("LAT");
				String LNT = rs.getString("LNT");
				String WORK_DTTM = rs.getString("WORK_DTTM");
				
				
				
				// 변수에 저장된 데이터를 다시 다른 DB에 저장함
				
				String insertSql = "INSERT INTO Mydata_sortedTable (X_SWIFI_DISTANCE, X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2,"
						+ "X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MB, X_SWIFI_SVC_SE,"
						+ "X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM)"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				
				PreparedStatement insertStatement = connection.prepareStatement(insertSql);
				
				insertStatement.setDouble(1, X_SWIFI_DISTANCE);
				insertStatement.setString(2, X_SWIFI_MGR_NO);
				insertStatement.setString(3, X_SWIFI_MAIN_NM);
				insertStatement.setString(4, X_SWIFI_ADRES1);
				insertStatement.setString(5, X_SWIFI_ADRES2);
				insertStatement.setString(6, X_SWIFI_ADRES2);
				insertStatement.setString(7, X_SWIFI_INSTL_FLOOR);
				insertStatement.setString(8, X_SWIFI_INSTL_TY);
				insertStatement.setString(9, X_SWIFI_INSTL_MB);
				insertStatement.setString(10, X_SWIFI_SVC_SE);
				insertStatement.setString(11, X_SWIFI_CMCWR);
				insertStatement.setString(12, X_SWIFI_CNSTC_YEAR);
				insertStatement.setString(13, X_SWIFI_INOUT_DOOR);
				insertStatement.setString(14, X_SWIFI_REMARS3);
				insertStatement.setString(15, LAT);
				insertStatement.setString(16, LNT);
				insertStatement.setString(17, WORK_DTTM);
				
				
				insertStatement.executeUpdate();
				
				// 연결과 자원 해제
				insertStatement.close();
				
				
				
				
			}
			
			
			rs.close();
			selectStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
