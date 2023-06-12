package openapi;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import openapi.OpenApiDTO;
import openapi.OpenApiDTO.MyData;
import db.OpenApiDB;



public class OpenApi {
	
	/*
	// 내부에서만 사용가능
	private static List<MyData> myDataList = new ArrayList<>();
	// 외부에서 선언하기 위해 필요
	public static List<MyData> getMyDataList() throws Exception{
		
		return myDataList;
	}*/

	
	public static int getListTotalCount() throws Exception {
		
		int totalSize = (Integer) getData().size();
		
		// load-wifi로 리턴
		return totalSize;
		
	}
	
	
	public static String getJson(String line) throws Exception{
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonStr = (JSONObject)jsonParser.parse(line);
		JSONObject TbPublicWifiInfo = (JSONObject)jsonStr.get("TbPublicWifiInfo");
		JSONArray rowArray = (JSONArray)TbPublicWifiInfo.get("row");
		
		String rowString = rowArray.toString();
		
		return rowString;
	}
	
		
	public static List<MyData> getData() throws Exception {

		
		List<MyData> myDataList = new ArrayList<>();
		
		int i=0;
		while(true) {
			int tmpCnt = 0;
			
			URL url = new URL("http://openapi.seoul.go.kr:8088/"
					+ "6e676a6556616c673937524a447054/json/TbPublicWifiInfo/" + (i+1) + "/" + (i+1000));
			
			
	
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
			
			try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream())))
			{
				String line = in.readLine();
				
				//DB에 저장
				
				
				in.close();
				
			
				
				String rowString = getJson(line);
				try {
					
					//System.out.println(rowString);
					Gson gson = new Gson();
					Type listType = new TypeToken<List<MyData>>() {}.getType();
					List<MyData> newDataList = gson.fromJson(rowString, listType);
					
					myDataList.addAll(newDataList);
					// newDataList에는 한 번에 최대 1000개가 저장된다
					tmpCnt = newDataList.size();
					
				} catch (Exception e) {
			        throw new RuntimeException("Failed to parse JSON string.", e);
			    }
 
				
			}
			
			
			// tmpcnt가 1000개보다 작으면 더이상 데이터가 없으므로 중지 
			if(tmpCnt == 1000) {
				i+=1000;
			} else {
				break;
			}
			
			
		}
		OpenApiDB.saveDB(myDataList);
		
		
		// getListTotalCount로 리턴
		return myDataList;
		
	}
	
	
}