package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Event;
import util.DatabaseUtil;

public class EventDao {

    // Insert 
    public static boolean createEvent(Event event) {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "INSERT INTO Event (eventTitle, eventDate, eventType, eventDescription) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, event.getEventTitle());
            preparedStatement.setDate(2, new java.sql.Date(event.getEventDate().getTime()));
            preparedStatement.setString(3, event.getEventType());
            preparedStatement.setString(4, event.getEventDescription());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve 
    public static List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM Event";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Event event = new Event();
                event.setEventID(resultSet.getInt("eventID"));
                event.setEventTitle(resultSet.getString("eventTitle"));
                event.setEventDate(resultSet.getDate("eventDate"));
                event.setEventType(resultSet.getString("eventType"));
                event.setEventDescription(resultSet.getString("eventDescription"));
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    // Retrieve 
    public Event getEventById(int eventId) {
        Event event = null;
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM Event WHERE eventID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, eventId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                event = new Event();
                event.setEventID( resultSet.getInt("eventID"));
                event.setEventTitle(resultSet.getString("eventTitle"));
                event.setEventDate(resultSet.getDate("eventDate"));
                event.setEventType(resultSet.getString("eventType"));
                event.setEventDescription(resultSet.getString("eventDescription"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return event;
    }

    // Update 
    public boolean updateEvent(Event event) {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "UPDATE Event SET eventTitle = ?, eventDate = ?, eventType = ?, eventDescription = ? WHERE eventID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, event.getEventTitle());
            preparedStatement.setDate(2, new java.sql.Date(event.getEventDate().getTime()));
            preparedStatement.setString(3, event.getEventType());
            preparedStatement.setString(4, event.getEventDescription());
            preparedStatement.setInt(5, event.getEventID());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete 
    public static boolean deleteEvent(int eventId) {
        try (Connection connection = DatabaseUtil.getConnection()) {
            String sql = "DELETE FROM Event WHERE eventID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, eventId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
