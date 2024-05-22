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

    public logService logService = new logService();


    public void buyTicket(Scanner scanner, String email, String eventName) throws SQLException {


        String action = "BuyTicket -> Insert";
        String table = "";

        User user = null;
        Event event = null;
        int id = 0;
        HashMap <User, Integer> map = new HashMap<User, Integer>();

        try {
            map = UserManager.getUser(email);
            Map.Entry<User, Integer> entry = map.entrySet().iterator().next();
            user = entry.getKey();
            id = entry.getValue();



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
                table = "SilverTicket";
                connection.createStatement().executeUpdate("INSERT INTO SilverTicket (id, LuckyUpgrade) VALUES ('" + Id + "', '" + prop + "')");

                logService.logDatabaseAction(action,table);

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
                table = "GoldTicket";
                connection.createStatement().executeUpdate("INSERT INTO GoldTicket (id, seatRangeStart, seatRangeEnd) VALUES ('" + Id + "', '" + ticket.getSeatRange()[0] + "', '" + ticket.getSeatRange()[1] + "')");

                logService.logDatabaseAction(action,table);

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

                table = "VIPTicket";

                connection.createStatement().executeUpdate("INSERT INTO VIPTicket (id, seatRangeStart, SeatRangeEnd, freeParking) VALUES ('" + Id + "', '" + ticket.getSeatRange()[0] + "', '" + ticket.getSeatRange()[1] + "', '" + prop + "')");
                logService.logDatabaseAction(action,table);
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }


    public void showAttendees() throws SQLException {

        ResultSet res = null;
        String action = "ShowAttendees -> Select";
        String table = "User, Ticket";

        try{
            res =  connection.createStatement().executeQuery("SELECT User.name, Event.eventName FROM User INNER JOIN Ticket ON User.id = Ticket.userId INNER JOIN Event ON Ticket.eventName = Event.eventName");

            logService.logDatabaseAction(action,table);
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

    public void changeTicketBearer(String oldMail, String newMail, String eventName) throws SQLException {

        User user = null;
        Event event = null;
        int newId = 0;
        int oldId = 0;

        HashMap<User, Integer> newUserMap = new HashMap<>();
        HashMap<User, Integer> oldUserMap = new HashMap<>();

        String action = "ChangeTicketBearer -> Update, Select";
        String table = "User, Ticket";



        try{

            newUserMap = UserManager.getUser(newMail);
            oldUserMap = UserManager.getUser(oldMail);

            System.out.println(newUserMap);
            System.out.println(oldUserMap);


            Map.Entry<User, Integer> newEntry = newUserMap.entrySet().iterator().next();
            user = newEntry.getKey();
            newId = newEntry.getValue();

             Map.Entry<User, Integer> oldEntry = oldUserMap.entrySet().iterator().next();
            oldId = oldEntry.getValue();



         event = EventManager.getEventByName(eventName);
        }
        catch(Exception e){
            System.out.println(e);
        }


        try{
            connection.createStatement().executeUpdate("UPDATE Ticket SET userId = '"+newId+"' WHERE eventName = '"+event.getEventName()+"' and userId = '"+oldId+"' ");
            logService.logDatabaseAction(action,table);
        }
        catch(Exception e){
            System.out.println(e);
        }




    }
     
    
}
