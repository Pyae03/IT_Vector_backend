package api.event;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.EventDao;
import models.Event;

@WebServlet("/EventReadApiServlet")
public class EventReadApiServlet extends HttpServlet {
    private EventDao eventDao = new EventDao(); // Create an instance of EventDao

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    	// Set CORS headers
        response.setHeader("Access-Control-Allow-Origin", "*"); // Allow requests from any origin (you can restrict it to specific origins)
        response.setHeader("Access-Control-Allow-Methods", "GET"); // Allow only GET requests
        response.setHeader("Access-Control-Allow-Headers", "Content-Type"); // Allow specified headers
        response.setContentType("application/json");

        try (PrintWriter out = response.getWriter()) {
            // Retrieve all events from the database 
            List<Event> events = EventDao.getAllEvents();


            Gson gson = new Gson();

            // events to JSON
            String jsonEvents = gson.toJson(events);

            // to client
            out.println(jsonEvents);
        } catch (IOException e) {
            e.printStackTrace();
            
            response.getWriter().println("Error occurred while retrieving events.");
        }
    }
}
