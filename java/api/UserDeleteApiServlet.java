package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.UserDao;
import models.User;

@WebServlet("/UserDeleteApiServlet")
public class UserDeleteApiServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	 // Set CORS headers
        response.setHeader("Access-Control-Allow-Origin", "*"); // Allow requests from any origin (you can restrict it to specific origins)
        response.setHeader("Access-Control-Allow-Methods", "POST"); // Allow only GET requests
        response.setHeader("Access-Control-Allow-Headers", "Content-Type"); // Allow specified headers

        response.setContentType("application/json");
    	
        // get user id from js post
        StringBuilder requestBody = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        }
        // didn't work
//        String userId = request.getParameter("userID");
        
        String userId = requestBody.toString();
        System.out.println("user idZ: "+ userId);
        
        
		if (userId == null || userId.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("User ID is missing.");
            return;
        }


		
		// for user deletion
		boolean deleted = UserDao.deleteUser(userId);

		if (deleted) {
		    response.setStatus(HttpServletResponse.SC_OK);
		    response.getWriter().println("User deleted successfully.");
		} else {
		    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		    response.getWriter().println("User not found or couldn't be deleted.");
		}
    }
}
