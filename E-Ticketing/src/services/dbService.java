package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class dbService {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://server-0396.whmpanels.com:3306/r116501soft_e-ticketing", " r116501soft_ticket_master", "ticketmaster");
                System.out.println("Connection Successful");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}
