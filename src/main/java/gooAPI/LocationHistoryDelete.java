package gooAPI;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/delete")
public class LocationHistoryDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DB_url = "jdbc:mysql://localhost:3306/seoulwifi_db";
	private static final String DB_username = "testuser5";
	private static final String DB_password = "zerobase";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String message = "";
		
		try(Connection connection = DriverManager.getConnection(DB_url, DB_username, DB_password)){
			String query = "DELETE FROM Location_History Where id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, id);
			int rowsAffected = statement.executeUpdate();
			
			if(rowsAffected > 0) {
				message = "Data deleted successfully.";
			} else {
				message = "No data found for deletion.";
			}
			
			statement.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			message = "Error occurred during deletion.";
		}
		
		//삭제 완료 메시지 출력
		//request.setAttribute("message", message);
		request.getRequestDispatcher("location-history.jsp").forward(request, response);
	}
	

}
