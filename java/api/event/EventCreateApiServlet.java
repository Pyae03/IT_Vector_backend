package api.event;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import dao.EventDao;
import models.Event;

@WebServlet("/EventCreateApiServlet")
public class EventCreateApiServlet extends HttpServlet {
    private EventDao eventDao = new EventDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
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
        
        try {
            Gson gson = new Gson();
            Event event = gson.fromJson(jsonInput.toString(), Event.class);
            System.out.println(event.toString());
           
            boolean success = eventDao.createEvent(event);
            // create event work but fix the error
            if(success) {
            	System.out.println("success");
            	//RequestDispatcher rd = request.getRequestDispatcher("admin-event");
            	//response.sendRedirect("admin-evenet.html");
            }
            
            response.getWriter().println("Event created successfully.");
            
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error occurred while creating the event.");
        }
    }
}
