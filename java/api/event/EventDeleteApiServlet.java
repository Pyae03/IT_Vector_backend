package api.event;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.EventDao;
import models.Event;

@WebServlet("/EventDeleteApiServlet")
public class EventDeleteApiServlet extends HttpServlet {
    private EventDao eventDao = new EventDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Set CORS headers
        response.setHeader("Access-Control-Allow-Origin", "*"); // Allow requests from any origin (you can restrict it to specific origins)
        response.setHeader("Access-Control-Allow-Methods", "POST"); // Allow only POST requests
        response.setHeader("Access-Control-Allow-Headers", "Content-Type"); // Allow specified headers
        response.setContentType("application/json");

        // Read JSON data from the request
        try (BufferedReader reader = request.getReader()) {
            StringBuilder jsonInput = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonInput.append(line);
            }

            System.out.println("Received JSON data: " + jsonInput);

            Gson gson = new Gson();
            Event event = gson.fromJson(jsonInput.toString(), Event.class);

            boolean success = EventDao.deleteEvent(event.getEventID());

            if (success) {
                System.out.println("Event deleted successfully.");
                response.getWriter().println("Event deleted successfully.");
            } else {
                System.out.println("Event deletion failed.");
                response.getWriter().println("Event deletion failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error occurred while processing the request.");
        }
    }
}
