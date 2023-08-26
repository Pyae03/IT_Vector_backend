package api;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.List;
import com.google.gson.*; // You may need to import the Gson library
import dao.UserDao;
import models.User;

@WebServlet("/UserApiServlet")
public class UserApiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Set CORS headers
        response.setHeader("Access-Control-Allow-Origin", "*"); // Allow requests from any origin (you can restrict it to specific origins)
        response.setHeader("Access-Control-Allow-Methods", "GET"); // Allow only GET requests
        response.setHeader("Access-Control-Allow-Headers", "Content-Type"); // Allow specified headers

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // Fetch user data from your database using a UserDao (you need to implement UserDao)
        List<User> users = UserDao.getAllUsers(); // Example method to fetch all users

        // Convert user data to JSON using Gson
        Gson gson = new Gson();
        String json = gson.toJson(users);

        out.print(json);
        out.flush();
    }
}


