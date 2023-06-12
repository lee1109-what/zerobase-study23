package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import openapi.OpenApiDTO;
import openapi.OpenApiDTO.MyData;

public class OpenApiDB {

	public static void saveDB(List<MyData> myDataList) throws Exception {
			
			String url = "jdbc:mysql://localhost:3306/seoulwifi_db";
			String username = "testuser5";
			String password = "zerobase";
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection(url, username, password);
				
				// 기존 데이터가 있는지 확인
				
				String countSql = "SELECT COUNT(*) FROM MyData_Table";
				PreparedStatement countStatement = connection.prepareStatement(countSql);
				ResultSet resultSet = countStatement.executeQuery();
				resultSet.next();
				int count = resultSet.getInt(1);
				resultSet.close();
				countStatement.close();
				
				//데이터 삭제
				if(count > 0) {
					String deleteSql = "DELETE FROM MyData_Table";
					PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
					deleteStatement.executeUpdate();
					deleteStatement.close();
					System.out.println("기존의 데이터를 삭제했습니다.");
					
					String resetAutoIncrementSql = "ALTER TABLE MyData_Table AUTO_INCREMENT = 1";
					Statement resetAutoIncrementStatement = connection.createStatement();
					resetAutoIncrementStatement.execute(resetAutoIncrementSql);
					resetAutoIncrementStatement.close();
					System.out.println("id를 0으로 만듭니다.");
				}
				
				for(MyData data: myDataList) {
					
				
				// INSERT문 실행
					String sql = "INSERT INTO Mydata_Table (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2,"
							+ "X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MB, X_SWIFI_SVC_SE,"
							+ "X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM)"
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					
					
					PreparedStatement statement = connection.prepareStatement(sql);
					
					
					statement.setString(1, data.getX_SWIFI_MGR_NO());
					statement.setString(2, data.getX_SWIFI_WRDOFC());
					statement.setString(3, data.getX_SWIFI_MAIN_NM());
					statement.setString(4, data.getX_SWIFI_ADRES1());
					statement.setString(5, data.getX_SWIFI_ADRES2());
					statement.setString(6, data.getX_SWIFI_INSTL_FLOOR());
					statement.setString(7, data.getX_SWIFI_INSTL_TY());
					statement.setString(8, data.getX_SWIFI_INSTL_MB());
					statement.setString(9, data.getX_SWIFI_SVC_SE());
					statement.setString(10, data.getX_SWIFI_CMCWR());
					statement.setString(11, data.getX_SWIFI_CNSTC_YEAR());
					statement.setString(12, data.getX_SWIFI_INOUT_DOOR());
					statement.setString(13, data.getX_SWIFI_REMARS3());
					statement.setString(14, data.getLAT());
					statement.setString(15, data.getLNT());
					statement.setString(16, data.getWORK_DTTM());
					
					
					statement.executeUpdate();
					
					// 연결과 자원 해제
					statement.close();
				}
				connection.close();
				
				System.out.println("데이터가 mysql에 저장되었습니다.");
	
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

}
