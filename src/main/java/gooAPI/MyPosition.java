package gooAPI;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.google.gson.Gson;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import java.io.IOException;
import javax.servlet.ServletException;

import db.GetDistance;

public class MyPosition {
	
	
	// get-mypo.jsp에서 호출된다.
    public static locationDTO getMyLocation() throws Exception {
        try {
            OkHttpClient client = new OkHttpClient();

            // Geolocation API 엔드포인트 URL
            String apiUrl = "https://www.googleapis.com/geolocation/v1/geolocate?key=AIzaSyCADbz_8EFSmX6c1D8M_MZDQl4xO0mG3TI";

            // POST 요청 본문 데이터
            String postData = "{\"considerIp\": \"true\"}";

            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), postData);

            Request request = new Request.Builder()
                    .url(apiUrl)
                    .post(requestBody)
                    .build();

            Call call = client.newCall(request);

            // 동기적으로 요청 실행
            Response response = call.execute();

            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                
                //System.out.println(responseBody);
                
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonStr = (JSONObject)jsonParser.parse(responseBody);
                JSONObject location = (JSONObject)jsonStr.get("location");
               // System.out.println(jsonStr.toString());
                //System.out.println(location.toString());
                
                // JSONArray는 
               // JSONArray locationArray = (JSONArray)location.get("lat");
               // System.out.println(locationArray.toString());
                
                
                //json을 분해하면서 출력해보고 출력에 맞는 내용을 검색 ㄱㄱ
                Gson gson = new Gson();
                locationDTO myLocation = gson.fromJson(location.toString(), locationDTO.class);
                 
                
               // System.out.println(myLocation.getlat());
               // System.out.println(myLocation.getlng());
                
                // 내 위치를 토대로 open api의 lat lnt까지의 거리를 계산하는 함수
                GetDistance.getLatLnt(myLocation);
                
                // 현재 위치를 db에 저장
                LocationHistory.locationHistorySave(myLocation);
                
                return myLocation;
               
            } else {
                System.out.println("POST request failed with response code: " + response.code());
            }
        	}   catch (IOException e) {
	            e.printStackTrace();
	        }
		return null;
        }
}
    
        
    














