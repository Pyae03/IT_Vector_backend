package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// You may need to import the Gson library
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dao.UserDao;
import models.User;

@WebServlet("/UserReadApiServlet")
public class UserReadApiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Set CORS headers
        response.setHeader("Access-Control-Allow-Origin", "*"); // Allow requests from any origin (you can restrict it to specific origins)
        response.setHeader("Access-Control-Allow-Methods", "GET"); // Allow only GET requests
        response.setHeader("Access-Control-Allow-Headers", "Content-Type"); // Allow specified headers

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();


        List<User> users = UserDao.getAllUsers();

        // Convert user data to JSON using Gson
        Gson gson = new Gson();
        String json = gson.toJson(users);

        out.print(json);
        out.flush();
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	
    }
    
    
}


