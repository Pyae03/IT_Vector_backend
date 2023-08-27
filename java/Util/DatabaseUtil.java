package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {


    public static Connection connection;
    // Use a ThreadLocal to manage connections per thread
    public static Connection getConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    return connection;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
        	String url = "jdbc:mysql://localhost:3306/it_vector";
            String username = "root";
            String password = "root";

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection started");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }


}
