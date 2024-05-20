package services;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import classes.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

public class TicketManager {

    public static Connection connection = services.dbService.getConnection();
    public static dbService dbService = new dbService();

    public static UserManager UserManager = new UserManager();


    public void buyTicket(Scanner scanner, String email, String eventName) throws SQLException {

        User user = null;
        Event event = null;
        int id = 0;
        HashMap <User, Integer> map = new HashMap<User, Integer>();

        try {
            map = UserManager.getUser(email);
            Map.Entry<User, Integer> entry = map.entrySet().iterator().next();
            user = entry.getKey();
            id = entry.getValue();

            System.out.println(id + "Asta e id-ul returnat in hash");
            System.out.println(user.getEmail() + "asta e mail-ul userului");


        } catch (Exception e) {
            System.out.println(e);
            return;
        }

        try {
            event = EventManager.getEventByName(eventName);
            if (event == null) {
                System.out.println("Event not found!");
                return;
            }
        } catch (Exception e) {
            System.out.println(e);
            return;
        }

        System.out.println("Input the ticket type: [Silver / Gold / VIP]");
        String type = scanner.nextLine();

        if (type.equals("Silver")) {
            SilverTicket emptyTicket = new SilverTicket(0, "", 0, false);
            SilverTicket ticket = new SilverTicket(event.getBasicTicketPrice() * emptyTicket.getTicketPrice(), event.getEventName(), user.getId(), false);

            try {
                System.out.println(id);
                connection.createStatement().executeUpdate("INSERT INTO Ticket (price, eventName, userId, isPaid, numberOfBenefits, ticketType) VALUES ('" + ticket.getTicketPrice() + "', '" + ticket.getEventName() + "', '" + id + "', 0, '" + ticket.getNumberOfBenefits() + "', 'Silver')");
                ResultSet rs = connection.createStatement().executeQuery("SELECT id FROM Ticket WHERE eventName = '" + ticket.getEventName() + "' AND userId = '" + id + "' ORDER BY id DESC LIMIT 1");
                int Id = 0;
                if (rs.next()) {
                    Id = rs.getInt("id");
                }
                int prop = 1;
                if (!ticket.isLuckyUpgrade()) {
                    prop = 0;
                }
                System.out.println();
                connection.createStatement().executeUpdate("INSERT INTO SilverTicket (id, LuckyUpgrade) VALUES ('" + Id + "', '" + prop + "')");
            } catch (SQLException e) {
                System.out.println(e);
            }

        } else if (type.equals("Gold")) {
            GoldTicket emptyTicket = new GoldTicket(0, "", 0, false);
            GoldTicket ticket = new GoldTicket(event.getBasicTicketPrice() * emptyTicket.getTicketPrice(), event.getEventName(), user.getId(), false);

            try {
                connection.createStatement().executeUpdate("INSERT INTO Ticket (price, eventName, userId, isPaid, numberOfBenefits, ticketType) VALUES ('" + ticket.getTicketPrice() + "', '" + ticket.getEventName() + "', '" + id + "', 0, '" + ticket.getNumberOfBenefits() + "', 'Gold')");
                ResultSet rs = connection.createStatement().executeQuery("SELECT id FROM Ticket WHERE eventName = '" + ticket.getEventName() + "' AND userId = '" + id + "' ORDER BY id DESC LIMIT 1");
                int Id = 0;
                if (rs.next()) {
                    Id = rs.getInt("id");
                }
                connection.createStatement().executeUpdate("INSERT INTO GoldTicket (id, seatRangeStart, seatRangeEnd) VALUES ('" + Id + "', '" + ticket.getSeatRange()[0] + "', '" + ticket.getSeatRange()[1] + "')");
            } catch (SQLException e) {
                System.out.println(e);
            }

        } else if (type.equals("VIP")) {
            VIPTicket emptyTicket = new VIPTicket(0, "", 0, false);
            VIPTicket ticket = new VIPTicket(event.getBasicTicketPrice() * emptyTicket.getTicketPrice(), event.getEventName(), user.getId(), false);

            try {
                connection.createStatement().executeUpdate("INSERT INTO Ticket (price, eventName, userId, isPaid, numberOfBenefits, ticketType) VALUES ('" + ticket.getTicketPrice() + "', '" + ticket.getEventName() + "', '" + id + "', 0 , '" + ticket.getNumberOfBenefits() + "', 'VIP')");
                ResultSet rs = connection.createStatement().executeQuery("SELECT id FROM Ticket WHERE eventName = '" + ticket.getEventName() + "' AND userId = '" + id + "' ORDER BY id DESC LIMIT 1");
                int Id = 0;
                if (rs.next()) {
                    Id = rs.getInt("id");
                }
                int prop = 1;
                if(!ticket.isFreeParking()){
                    prop = 0;
                }


                connection.createStatement().executeUpdate("INSERT INTO VIPTicket (id, seatRangeStart, SeatRangeEnd, freeParking) VALUES ('" + Id + "', '" + ticket.getSeatRange()[0] + "', '" + ticket.getSeatRange()[1] + "', '" + prop + "')");
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }


    public void showAttendees() throws SQLException {

        ResultSet res = null;

        try{
            res =  connection.createStatement().executeQuery("SELECT User.name, Event.eventName FROM User INNER JOIN Ticket ON User.id = Ticket.userId INNER JOIN Event ON Ticket.eventName = Event.eventName");
        }
        catch(Exception e){
            System.out.println(e);
        }

        try{
            while(res.next()){
                System.out.println(res.getString("User.name") + " is attending " + res.getString("Event.eventName"));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
       

    }

    public void changeTicketBearer(String email, String eventName) throws SQLException {

        User user = null;
        Event event = null;
        int id = 0;

        HashMap<User, Integer> map = new HashMap<>();

        try{

            map = UserManager.getUser(email);

            Map.Entry<User, Integer> entry = map.entrySet().iterator().next();
            user = entry.getKey();
            id = entry.getValue();

            System.out.println(id + "Asta e id-ul bun");
            System.out.println(user.getId());

         event = EventManager.getEventByName(eventName);
        }
        catch(Exception e){
            System.out.println(e);
        }


        try{
            connection.createStatement().executeUpdate("UPDATE Ticket SET userId = '"+id+"' WHERE eventName = '"+event.getEventName()+"'");
        }
        catch(Exception e){
            System.out.println(e);
        }




    }
     
    
}
