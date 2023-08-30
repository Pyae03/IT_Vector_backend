package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Date;
import java.sql.Date;

public class Event {
    private int eventID;
    private String eventTitle;
    private Date eventDate;
    private String eventType;
    private String eventDescription;

    public Event() {
        
    }

    public Event(String eventTitle, Date eventDate, String eventType, String eventDescription) {
        
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.eventType = eventType;
        this.eventDescription = eventDescription;
    }
    // for delete
    public Event(int eventID, String eventTitle, Date eventDate, String eventType, String eventDescription) {
    	this.eventID = eventID;
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.eventType = eventType;
        this.eventDescription = eventDescription;
    }
    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
    
    public Date formatDate(Date date) throws ParseException {

//		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formatedDate = sdf.format(date);
		Date convertedDate = (Date) sdf.parse(formatedDate);

		return convertedDate;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventID=" + eventID +
                ", eventTitle='" + eventTitle + '\'' +
                ", eventDate=" + eventDate +
                ", eventType='" + eventType + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                '}';
    }
}

