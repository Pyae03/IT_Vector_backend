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
import java.sql.Date;
import java.text.ParseException;

@WebServlet("/EventCreateApiServlet")
public class EventCreateApiServlet extends HttpServlet {
    private EventDao eventDao = new EventDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	String eventTitle = request.getParameter("event-title");
    	String eventDate = request.getParameter("event-date");
    	String eventType = request.getParameter("event-type");
    	String eventDesc = request.getParameter("event-desc");
    	
    	Date eventD;
		try {
			eventD = Event.formatDate(eventDate);
			Event event = new Event(eventTitle, eventD, eventType, eventDesc);
			EventDao.createEvent(event);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
            
       
    }
}
