package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dao.UserDao;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/UserUpdateApiServlet")
public class UserUpdateApiServlet extends HttpServlet {
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	 // Set CORS headers
        response.setHeader("Access-Control-Allow-Origin", "*"); // Allow requests from any origin (you can restrict it to specific origins)
        response.setHeader("Access-Control-Allow-Methods", "GET"); // Allow only GET requests
        response.setHeader("Access-Control-Allow-Headers", "Content-Type"); // Allow specified headers

        response.setContentType("application/json");
    	// this is constant
        // Read JSON data from the request
        BufferedReader reader = request.getReader();
        StringBuilder jsonInput = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonInput.append(line);
        }

        System.out.println("enter suer: " + jsonInput);
        
        // parse JSON data into a User object
        Gson gson = new Gson();
        User user = gson.fromJson(jsonInput.toString(), User.class);

        // update user in the database
        boolean success = false;
		success = UserDao.updateUser(user);

        // prepare the JSON response
        JsonObject jsonResponse = new JsonObject(); // using gson
        if (success) {
            jsonResponse.addProperty("status", "success");
        } else {
            jsonResponse.addProperty("status", "error");
        }

        // Send JSON response
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse.toString());
        out.flush();
    }

    
}
