package db;

import gooAPI.MyPosition;
import gooAPI.locationDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DistanceDB;

import com.mysql.jdbc.Statement;

public class GetDistance {
	
	
	
	
	// 거리 계산하기
	private static final double EARTH_RADIUS = 6371; // 지구의 반지름 (단위: km)
	
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
    	
    	
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;

        return distance;
    }
    
    
    // 거리 배열을 인수로 받아 배열의 인덱스(db의 id와 동일)와 요소(거리)를 key와 value로 map에 저장
    // value(거리)를 기준으로 리스트에 저장하고 정렬
    /*
	public static void indexValueMap(double[] arr) {
	    	
	    	Map<Integer, Double> ivMap = new HashMap<>();
	    	for(int i=0; i<arr.length; i++) {
	    		ivMap.put(i+1, arr[i]);
	    	}
	    	
	    	List<Map.Entry<Integer, Double>> sortedEntries = new ArrayList<>(ivMap.entrySet());
	    	Collections.sort(sortedEntries, Comparator.comparing(Map.Entry::getValue));
	    	
	    	
	    	int i= 0;
	    	for(Map.Entry<Integer, Double> entry : sortedEntries) {
	    		int index = entry.getKey();
	    		double value = entry.getValue();
	    		System.out.println("Index: " + index + ", value: " + value);
	    		if(i == 19) {
	    			break;
	    		}
	    		i++;
	    	}
	}*/
	
	
	// LAT, LNT 데이터 가져오기
	public static void getLatLnt(locationDTO myLocation) throws Exception {
		
		
		String url = "jdbc:mysql://localhost:3306/seoulwifi_db";
		String username = "testuser5";
		String password = "zerobase";
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			// 데이터베이스 연결
			connection = DriverManager.getConnection(url, username, password);
			
			// 데이터 수 쿼리 실행
			String countSql = "SELECT COUNT(*) FROM MyData_Table";
			statement = (Statement) connection.createStatement();
			rs = statement.executeQuery(countSql);
			
			int count = 0;
			if(rs.next()) {
				count = rs.getInt(1);
				System.out.println("데이터 수: " + count);
			}
			
			double[] arr = new double[count];
			//SQL 쿼리 실행
			String dataSql = "SELECT * FROM MyData_Table";
			rs = statement.executeQuery(dataSql);
			
			
			double lat1 = myLocation.getlat();
			double lon1 = myLocation.getlng();
			//결과 처리
			
			
			//open api 데이터가 잘못된 듯
			// 우리나라의 위도 latitude 는 33~38도
			// 우리나라의 경도 longitude 는 125~131도
			
			int i=0;
			while(rs.next()) {
				
				int id = rs.getInt("id");
				String lat2 = rs.getString("LAT"); // 126.9167 openapi 저장된값
				String lon2 = rs.getString("LNT"); // 37.62364
				//System.out.println(id + "," + lat2 + ", " + lon2);
				
				double dlat = Double.parseDouble(lat2);
				double dlon = Double.parseDouble(lon2);
				
				arr[i] = calculateDistance(lat1, lon1, dlon, dlat);
				//System.out.println(i);
				i++;
			}
			
			
			
			
			// 여기서 arr[i]의 값을 db table distance 에 저장하는 함수를 호출한다.
			DistanceDB.saveDistance(arr);
			
			
			//indexValueMap(arr);
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 해제
			try {
				if(rs != null) {
					rs.close();
				}
				if(statement != null) {
					statement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

	}
	
}
