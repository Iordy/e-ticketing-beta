package services;
import classes.AeroEvent;
import classes.AutomotiveEvent;
import java.util.ArrayList;
import classes.Event;
import classes.MusicalEvent;
import classes.SpecialEvent;

import java.util.List;
import java.util.Scanner;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class EventManager {

    
    public static dbService dbService;
    public static Connection connection = services.dbService.getConnection();


    public void registerEvent(Scanner scanner, String name, Date date, String location, double price, int numberOfTickets)
    throws SQLException 
    {

        System.out.println("Input the event type:[Aero / Auto / Special / Concert]");

        String type = scanner.nextLine();

        if(type.equals("Aero")){
            System.out.println("Input the airline:");
            
            String airline = scanner.nextLine();

            System.out.println("Input the number of pilots:");

            String numberOfPilots = scanner.nextLine();
            int IntnumberOfPilots = Integer.parseInt(numberOfPilots);
            

            ArrayList <String> pilots = new ArrayList<String>();

            for(int i = 0; i < IntnumberOfPilots; i++){
                System.out.println("Input the name of pilot " + (i + 1) + ":");
                String pilot = scanner.nextLine();
                pilots.add(pilot);
            }

            System.out.println("Is the event international?[yes / no]");

            String isInternational = scanner.nextLine();

            boolean isInternationalBool = false;
            int isInternationalInt = 0;

            if(isInternational.equals("yes")){
                isInternationalBool = true;
                isInternationalInt = 1;
            }

//            AeroEvent event = new AeroEvent(name, date, location, price, numberOfTickets, airline, pilots, isInternationalBool);

            try{
                    connection.createStatement().executeUpdate("INSERT INTO Event (eventName, eventDate, eventLocation, basicTicketPrice, numberOfTickets, isSoldOut, ticketsSold, eventType) VALUES ('" + name + "', '" + date + "', '" + location + "', '" + price + "', '" + numberOfTickets + "', 0, '0', 'Aero')");
                    ResultSet eventIdResultSet = connection.createStatement().executeQuery("SELECT id FROM Event ORDER BY id DESC");
                    int eventId = 0;
                    if (eventIdResultSet.next()) {
                        eventId = eventIdResultSet.getInt("id");
                    }
                    connection.createStatement().executeUpdate("INSERT INTO AeroEvent (id, airline, isInternational) VALUES ('" + eventId + "', '" + airline + "', '" + isInternationalInt + "')");
                    connection.createStatement().executeUpdate("INSERT INTO AeroEventPilots (eventId, pilotName) VALUES ('" + eventId + "', '" + pilots + "')");
            }

            catch(SQLException e){
                System.out.println(e);
            }

        } else if(type.equals("Auto")){
           

            System.out.println("Input the car brand:");
            String carBrand = scanner.nextLine();
            System.out.println("Is the car electric?[yes / no]");
            String isElectric = scanner.nextLine();
            boolean isElectricBool = false;
            int isElectricInt = 0;

            if(isElectric.equals("yes")){
                isElectricBool = true;
                isElectricInt = 1;
            }

            System.out.println("Input the number of sponsors:");
            String numberOfSponsors = scanner.nextLine();
            int IntnumberOfSponsors = Integer.parseInt(numberOfSponsors);

            ArrayList <String> sponsors = new ArrayList<String>(IntnumberOfSponsors);

            for(int i = 0; i < IntnumberOfSponsors; i++){
                System.out.println("Input the name of sponsor " + (i + 1) + ":");
                String sponsor = scanner.nextLine();
                sponsors.add(sponsor);
            }

          //  AutomotiveEvent event = new AutomotiveEvent(name, date, location, price, numberOfTickets, carBrand, isElectricBool, sponsors);
           
            
          try{
            connection.createStatement().executeUpdate("INSERT INTO Event (eventName, eventDate, eventLocation, basicTicketPrice, numberOfTickets, isSoldOut, ticketsSold, eventType) VALUES ('" + name + "', '" + date + "', '" + location + "', '" + price + "', '" + numberOfTickets + "', 0, '0', 'Automotive')");

              int eventId = 0;
              ResultSet eventIdResultSet = connection.createStatement().executeQuery("SELECT id FROM Event ORDER BY id DESC");
              if (eventIdResultSet.next()) {
                  eventId = eventIdResultSet.getInt("id");
              }

            connection.createStatement().executeUpdate("INSERT INTO AutomotiveEvent (id, carBrand, isElectric) VALUES ('" + eventId + "', '" + carBrand + "', '" + isElectricInt + "')");
            connection.createStatement().executeUpdate("INSERT INTO AutomotiveEventSponsors (eventId, sponsorName) VALUES ('" + eventId + "', '" + sponsors + "')");
          }
          catch(SQLException e){
              System.out.println(e);
          }


        } else if(type.equals("Special")){
           
            System.out.println("Input the special event type:");
            String specialType = scanner.nextLine();

            System.out.println("Input the description:");
            String description = scanner.nextLine();

          //  SpecialEvent event = new SpecialEvent(name, date, location, price, numberOfTickets, specialType, description);
            
            try{
                connection.createStatement().executeUpdate("INSERT INTO Event (eventName, eventDate, eventLocation, basicTicketPrice, numberOfTickets, isSoldOut, ticketsSold, eventType) VALUES ('" + name + "', '" + date + "', '" + location + "', '" + price + "', '" + numberOfTickets + "', 0, '0', 'Special')");

                ResultSet eventIdResultSet = connection.createStatement().executeQuery("SELECT id FROM Event ORDER BY id DESC");
                int eventId = 0;
                if (eventIdResultSet.next()) {
                    eventId = eventIdResultSet.getInt("id");
                }

                connection.createStatement().executeUpdate("INSERT INTO SpecialEvent (id, specialType, specialDescription) VALUES ('" + eventId + "', '" + specialType + "', '" + description + "')");
            }
            catch(SQLException e){
                System.out.println(e);
            }


        } else if(type.equals("Concert")){
            System.out.println("Input the band name:");
            String bandName = scanner.nextLine();

            System.out.println("Input the number of songs:");
            String songs = scanner.nextLine();
            int Intsongs = Integer.parseInt(songs);
            

            System.out.println("What Genre is the band?");
            String genre = scanner.nextLine();

           // MusicalEvent event = new MusicalEvent(name, date, location, price, numberOfTickets, bandName, genre, Intsongs);
            
            try{
                connection.createStatement().executeUpdate("INSERT INTO Event (eventName, eventDate, eventLocation, basicTicketPrice, numberOfTickets, isSoldOut, ticketsSold, eventType) VALUES ('" + name + "', '" + date + "', '" + location + "', '" + price + "', '" + numberOfTickets + "', 0, '0', 'Musical')");

                int eventId = 0;

                ResultSet eventIdResultSet = connection.createStatement().executeQuery("SELECT id FROM Event ORDER BY id DESC");
                if (eventIdResultSet.next()) {
                    eventId = eventIdResultSet.getInt("id");
                }

                connection.createStatement().executeUpdate("INSERT INTO MusicalEvent (id, bandName, musicGenre, numberOfSongs) VALUES ('" + eventId + "', '" + bandName + "', '" + genre + "', '" + Intsongs + "')");
            }
            catch(SQLException e){
                System.out.println(e);
            }

        } else {
           
            System.out.println("Invalid event type");
        }

        
        
    }

    public void deleteEvent(String name) throws SQLException {

        String type = null;
        ResultSet rs = null;
        int id = 0;


        String event_find = "Select eventType , id FROM Event WHERE eventName = '" + name + "'";

        try{
            rs = connection.createStatement().executeQuery(event_find);
        }catch(SQLException e){
            System.out.println(e);
        }

        if(rs.next()){

            id = rs.getInt("id");
            type = rs.getString("eventType");
        }

        if(type.equals("Special")){

            String lowerQuery = "DELETE FROM SpecialEvent WHERE id = '" + id + "'";
            String query = "DELETE FROM Event WHERE id = '" + id + "'";

            try {
                connection.createStatement().executeUpdate(lowerQuery);
                connection.createStatement().executeUpdate(query);
            } catch (Exception e) {
                System.out.println(e);
            }

        }

        else if(type.equals("Musical")){
            String lowerQuery = "DELETE FROM MusicalEvent WHERE id = '" + id + "'";
            String query = "DELETE FROM Event WHERE id = '" + id + "'";

            try{
                connection.createStatement().executeUpdate(lowerQuery);
                connection.createStatement().executeUpdate(query);
            }
            catch(SQLException e){
                System.out.println(e);
            }
        }
        else if(type.equals("Automotive")){
            String lowerQuery = "DELETE FROM AutomotiveEvent WHERE id = '" + id + "'";
            String query = "DELETE FROM Event WHERE id = '" + id + "'";

            try{
                connection.createStatement().executeUpdate("DELETE FROM AutomotiveEventSponsors WHERE eventId = '" + id + "'");
                connection.createStatement().executeUpdate(lowerQuery);
                connection.createStatement().executeUpdate(query);
            }
            catch(SQLException e){
                System.out.println(e);
            }
        }
       else if (type.equals("Aero")){
           String lowerQuery = "DELETE FROM AeroEvent WHERE id = '" + id + "'";
           String query = "DELETE FROM Event WHERE id = '" + id + "'";

           try{
               connection.createStatement().executeUpdate("DELETE FROM AeroEventPilots WHERE eventId = '" + id + "'");
               connection.createStatement().executeUpdate(lowerQuery);
               connection.createStatement().executeUpdate(query);
           }
           catch(SQLException e){
               System.out.println(e);
           }
        }
    }

    public void updateEvent(String name, Date date, String location, double price) {
            
            String query = "UPDATE Event SET date = '" + date + "', location = '" + location + "', price = '" + price + "' WHERE name = '" + name + "'";
    
            try {
                connection.createStatement().executeUpdate(query);
            } catch (Exception e) {
                System.out.println(e);
            }
    }

    public List<Event> getEventsEnrolled() throws SQLException {
        String query = "SELECT * FROM Event";
        ResultSet result = null;
        List<Event> EventList = new ArrayList<>();

        try {
            result = connection.createStatement().executeQuery(query);

            while (result.next()) {
                String type = result.getString("eventType");
                int id_nxt = result.getInt("id");

                if (type.equals("Aero")) {
                    ResultSet pilots = null;
                    try {
                        pilots = connection.createStatement().executeQuery("SELECT * FROM AeroEventPilots WHERE eventId = " + id_nxt);
                    } catch (SQLException e) {
                        System.out.println(e);
                    }

                    Event event = null;
                    String joinQuery = "SELECT * FROM Event INNER JOIN AeroEvent ON Event.id = AeroEvent.id WHERE Event.id = " + id_nxt;
                    ResultSet result2 = null;

                    try {
                        result2 = connection.createStatement().executeQuery(joinQuery);
                    } catch (SQLException e) {
                        System.out.println(e);
                    }

                    ArrayList<String> pilotList = new ArrayList<>();
                    if (pilots != null) {
                        while (pilots.next()) {
                            pilotList.add(pilots.getString("pilotName"));
                        }
                        pilots.close();
                    }

                    if (result2 != null && result2.next()) {
                        String airline = result2.getString("airline");
                        boolean isInternational = result2.getInt("isInternational") == 1;
                        event = new AeroEvent(
                                result2.getString("eventName"),
                                result2.getDate("eventDate"),
                                result2.getString("eventLocation"),
                                result2.getDouble("basicTicketPrice"),
                                result2.getInt("numberOfTickets"),
                                airline,
                                pilotList,
                                isInternational
                        );
                        EventList.add(event);
                        System.out.println(event);
                        result2.close();
                    }

                } else if (type.equals("Automotive")) {
                    ResultSet sponsors = null;
                    try {
                        sponsors = connection.createStatement().executeQuery("SELECT * FROM AutomotiveEventSponsors WHERE eventId = " + id_nxt);
                    } catch (SQLException e) {
                        System.out.println(e);
                    }

                    Event event = null;
                    String joinQuery = "SELECT * FROM Event INNER JOIN AutomotiveEvent ON Event.id = AutomotiveEvent.id WHERE Event.id = " + id_nxt;
                    ResultSet result2 = null;

                    try {
                        result2 = connection.createStatement().executeQuery(joinQuery);
                    } catch (SQLException e) {
                        System.out.println(e);
                    }

                    ArrayList<String> sponsorList = new ArrayList<>();
                    if (sponsors != null) {
                        while (sponsors.next()) {
                            sponsorList.add(sponsors.getString("sponsorName"));
                        }
                        sponsors.close();
                    }

                    if (result2 != null && result2.next()) {
                        String carBrand = result2.getString("carBrand");
                        boolean isElectric = result2.getBoolean("isElectric");
                        event = new AutomotiveEvent(
                                result2.getString("eventName"),
                                result2.getDate("eventDate"),
                                result2.getString("eventLocation"),
                                result2.getDouble("basicTicketPrice"),
                                result2.getInt("numberOfTickets"),
                                carBrand,
                                isElectric,
                                sponsorList
                        );
                        EventList.add(event);
                        System.out.println(event);
                        result2.close();
                    }

                } else if (type.equals("Special")) {
                    Event event = null;
                    String joinQuery = "SELECT * FROM Event INNER JOIN SpecialEvent ON Event.id = SpecialEvent.id WHERE Event.id = " + id_nxt;
                    try {

                        ResultSet result2 = connection.createStatement().executeQuery(joinQuery);

                        while(result2.next()) {

                            String specialType = result2.getString("specialType");
                            String description = result2.getString("specialDescription");
                            event = new SpecialEvent(
                                    result.getString("eventName"),
                                    result.getDate("eventDate"),
                                    result.getString("eventLocation"),
                                    result.getDouble("basicTicketPrice"),
                                    result.getInt("numberOfTickets"),
                                    specialType,
                                    description
                            );
                            EventList.add(event);
                            System.out.println(event);

                        }

                    } catch (SQLException e) {
                        System.out.println(e);
                    }

                } else if (type.equals("Musical")) {
                    Event event = null;
                    String joinQuery = "SELECT * FROM Event INNER JOIN MusicalEvent ON Event.id = MusicalEvent.id WHERE Event.id = " + id_nxt;

                    try {

                        ResultSet result2 = connection.createStatement().executeQuery(joinQuery);

                        while(result2.next()) {

                            String bandName = result2.getString("bandName");
                            String genre = result2.getString("musicGenre");
                            int numberOfSongs = result2.getInt("numberOfSongs");
                            event = new MusicalEvent(
                                    result.getString("eventName"),
                                    result.getDate("eventDate"),
                                    result.getString("eventLocation"),
                                    result.getDouble("basicTicketPrice"),
                                    result.getInt("numberOfTickets"),
                                    bandName,
                                    genre,
                                    numberOfSongs
                            );
                            EventList.add(event);
                            System.out.println(event);

                        }


                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }

        for (Event event : EventList) {
            System.out.println(event);
        }

        return EventList;
    }


    public static Event getEventByName(String name) throws SQLException {
        String query = "SELECT * FROM Event WHERE eventName = '" + name + "'";
        ResultSet result = null;
        Event event = null;

        try {
            result = connection.createStatement().executeQuery(query);

            if (result.next()) {
                String type = result.getString("eventType");
                int id_nxt = result.getInt("id");

                if (type.equals("Aero")) {
                    ResultSet pilots = null;
                    try {
                        pilots = connection.createStatement().executeQuery("SELECT * FROM AeroEventPilots WHERE eventId = " + id_nxt);
                    } catch (SQLException e) {
                        System.out.println(e);
                    }

                    String joinQuery = "SELECT * FROM Event INNER JOIN AeroEvent ON Event.id = AeroEvent.id WHERE Event.id = " + id_nxt;
                    ResultSet result2 = null;

                    try {
                        result2 = connection.createStatement().executeQuery(joinQuery);
                    } catch (SQLException e) {
                        System.out.println(e);
                    }

                    ArrayList<String> pilotList = new ArrayList<>();
                    if (pilots != null) {
                        while (pilots.next()) {
                            pilotList.add(pilots.getString("pilotName"));
                        }
                        pilots.close();
                    }

                    if (result2 != null && result2.next()) {
                        String airline = result2.getString("airline");
                        boolean isInternational = result2.getInt("isInternational") == 1;
                        event = new AeroEvent(
                                result2.getString("eventName"),
                                result2.getDate("eventDate"),
                                result2.getString("eventLocation"),
                                result2.getDouble("basicTicketPrice"),
                                result2.getInt("numberOfTickets"),
                                airline,
                                pilotList,
                                isInternational
                        );
                        result2.close();
                    }

                } else if (type.equals("Automotive")) {
                    ResultSet sponsors = null;
                    try {
                        sponsors = connection.createStatement().executeQuery("SELECT * FROM AutomotiveEventSponsors WHERE eventId = " + id_nxt);
                    } catch (SQLException e) {
                        System.out.println(e);
                    }

                    String joinQuery = "SELECT * FROM Event INNER JOIN AutomotiveEvent ON Event.id = AutomotiveEvent.id WHERE Event.id = " + id_nxt;
                    ResultSet result2 = null;

                    try {
                        result2 = connection.createStatement().executeQuery(joinQuery);
                    } catch (SQLException e) {
                        System.out.println(e);
                    }

                    ArrayList<String> sponsorList = new ArrayList<>();
                    if (sponsors != null) {
                        while (sponsors.next()) {
                            sponsorList.add(sponsors.getString("sponsorName"));
                        }
                        sponsors.close();
                    }

                    if (result2 != null && result2.next()) {
                        String carBrand = result2.getString("carBrand");
                        boolean isElectric = result2.getBoolean("isElectric");
                        event = new AutomotiveEvent(
                                result2.getString("eventName"),
                                result2.getDate("eventDate"),
                                result2.getString("eventLocation"),
                                result2.getDouble("basicTicketPrice"),
                                result2.getInt("numberOfTickets"),
                                carBrand,
                                isElectric,
                                sponsorList
                        );
                        result2.close();
                    }

                } else if (type.equals("Special")) {
                    String joinQuery = "SELECT * FROM Event INNER JOIN SpecialEvent ON Event.id = SpecialEvent.id WHERE Event.id = " + id_nxt;

                    try {
                        ResultSet result2 = connection.createStatement().executeQuery(joinQuery);

                        if (result2.next()) {
                            String specialType = result2.getString("specialType");
                            String description = result2.getString("specialDescription");
                            event = new SpecialEvent(
                                    result2.getString("eventName"),
                                    result2.getDate("eventDate"),
                                    result2.getString("eventLocation"),
                                    result2.getDouble("basicTicketPrice"),
                                    result2.getInt("numberOfTickets"),
                                    specialType,
                                    description
                            );
                            result2.close();
                        }
                    } catch (SQLException e) {
                        System.out.println(e);
                    }

                } else if (type.equals("Musical")) {
                    String joinQuery = "SELECT * FROM Event INNER JOIN MusicalEvent ON Event.id = MusicalEvent.id WHERE Event.id = " + id_nxt;

                    try {
                        ResultSet result2 = connection.createStatement().executeQuery(joinQuery);

                        if (result2.next()) {
                            String bandName = result2.getString("bandName");
                            String genre = result2.getString("musicGenre");
                            int numberOfSongs = result2.getInt("numberOfSongs");
                            event = new MusicalEvent(
                                    result2.getString("eventName"),
                                    result2.getDate("eventDate"),
                                    result2.getString("eventLocation"),
                                    result2.getDouble("basicTicketPrice"),
                                    result2.getInt("numberOfTickets"),
                                    bandName,
                                    genre,
                                    numberOfSongs
                            );
                            result2.close();
                        }
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }

        return event;
    }




}