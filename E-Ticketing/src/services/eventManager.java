package services;
import classes.AeroEvent;
import classes.AutomotiveEvent;
import java.util.ArrayList;
import classes.Event;
import classes.MusicalEvent;
import classes.SpecialEvent;
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

    public void deleteEvent(String name) {
       
        String query = "DELETE FROM event WHERE name = '" + name + "'";

        try {
            connection.createStatement().executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
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

    public ArrayList<Event> getEventsEnrolled() throws SQLException {
        
        String query = "SELECT * FROM Event";
        ResultSet result = null;

        try {
             result = connection.createStatement().executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
        }

        ArrayList <Event> EventList = new ArrayList<Event>();


        while(result.next()){
            String type = result.getString("eventType");
            if(type.equals("Aero")){

                ResultSet pilots = null;

                try{
                        pilots = connection.createStatement().executeQuery("SELECT * FROM AeroEventPilots WHERE eventId = (SELECT eventId FROM event WHERE eventName = '" + result.getString("eventName") + "')");
                    }
                    catch(SQLException e){
                        System.out.println(e);
                }

            Event event = null;
                
                
                //we have to join the Events with the AeroEventsTable to get the info about the events

            
            String joinQuery = "SELECT * FROM Event INNER JOIN AeroEvent ON Event.id = AeroEvent.id WHERE Event.eventName = '" + result.getString("eventName") + "'";

            ResultSet result2 = null;

            try{
                result2 = connection.createStatement().executeQuery(joinQuery);
            }
            catch(SQLException e){
                System.out.println(e);
            }

        
            
     

            while (pilots.next()) {
                
                pilotList.add(pilots.getString("pilotName"));
            }

            event = new AeroEvent(result.getString("eventName"), result.getDate("eventDate"), result.getString("eventLocation"), result.getDouble("basicTicketPrice"), result.getInt("numberOfTickets"), airline, pilotList, isInternational);

            EventList.add(event);

            } else if(type.equals("Auto")){

                ResultSet sponsors = null;

                try{
                    sponsors = connection.createStatement().executeQuery("SELECT * FROM AutomotiveEventSponsors WHERE eventId = (SELECT eventId FROM event WHERE eventName = '" + result.getString("eventName") + "')");
                }
                catch(SQLException e){
                    System.out.println(e);
                }

                Event event = null;

                ArrayList<String> sponsorList = new ArrayList<String>();

                while (sponsors.next()) {

                    sponsorList.add(sponsors.getString("sponsorName"));
                    
                }

                try{

                String carBrand = result.getString("carBrand");

                boolean isElectric = result.getBoolean("isElectric");


                event = new AutomotiveEvent(result.getString("eventName"), result.getDate("eventDate"), result.getString("eventLocation"), result.getDouble("basicTicketPrice"), result.getInt("numberOfTickets"), carBrand, isElectric, sponsorList);

                EventList.add(event);
                }
                catch(SQLException e){
                    System.out.println(e);
                }


            } else if(type.equals("Special")){

                Event event = null;

                try{

                String specialType = result.getString("specialType");
                String description = result.getString("description");

                event = new SpecialEvent(result.getString("eventName"), result.getDate("eventDate"), result.getString("eventLocation"), result.getDouble("basicTicketPrice"), result.getInt("numberOfTickets"), specialType, description);

                EventList.add(event);

                }
                catch(SQLException e){
                    System.out.println(e);
                }



            } else if(type.equals("Concert")){

                Event event = null;

                try{

                String bandName = result.getString("bandName");
                String genre = result.getString("genre");
                int numberOfSongs = result.getInt("numberOfSongs");

                event = new MusicalEvent(result.getString("eventName"), result.getDate("eventDate"), result.getString("eventLocation"), result.getDouble("basicTicketPrice"), result.getInt("numberOfTickets"), bandName, genre, numberOfSongs);

                EventList.add(event);
            }
            catch(SQLException e){
                System.out.println(e);
            }

        }
    }
        return EventList;


    }

    public static Event getEventByName(String name) throws SQLException {
        
        String query = "SELECT * FROM events WHERE name = '" + name + "'";
        ResultSet result = null;
            

        try{
             result = connection.createStatement().executeQuery(query);
        }
        catch(SQLException e){
            System.out.println(e);
        }

        //return the event

        Event event = null;

        while (result.next()) {
            
            String type = result.getString("eventType");
            if(type.equals("Aero")){

                ResultSet pilots = null;

                try{
                        pilots = connection.createStatement().executeQuery("SELECT * FROM AeroEventPilots WHERE eventId = (SELECT eventId FROM event WHERE eventName = '" + name + "')");
                    }
                    catch(SQLException e){
                        System.out.println(e);
                }


                String airline = result.getString("airline");
                boolean isInternational = result.getBoolean("isInternational");
                ArrayList<String> pilotList = new ArrayList<String>();

                while (pilots.next()) {
                   
                    pilotList.add(pilots.getString("pilotName"));
                }

                event = new AeroEvent(result.getString("eventName"), result.getDate("eventDate"), result.getString("eventLocation"), result.getDouble("basicTicketPrice"), result.getInt("numberOfTickets"), airline, pilotList, isInternational);

                return event;

            } else if(type.equals("Auto")){

                ResultSet sponsors = null;


                try{
                    sponsors = connection.createStatement().executeQuery("SELECT * FROM AutomotiveEventSponsors WHERE eventId = (SELECT eventId FROM event WHERE eventName = '" + name + "')");
                }
                catch(SQLException e){
                    System.out.println(e);
                }

                ArrayList<String> sponsorList = new ArrayList<String>();

                while (sponsors.next()) {

                    sponsorList.add(sponsors.getString("sponsorName"));
                    
                }

                String carBrand = result.getString("carBrand");
                boolean isElectric = result.getBoolean("isElectric");
                
                event = new AutomotiveEvent(result.getString("eventName"), result.getDate("eventDate"), result.getString("eventLocation"), result.getDouble("basicTicketPrice"), result.getInt("numberOfTickets"), carBrand, isElectric, sponsorList);

                return event;

            } else if(type.equals("Special")){
                String specialType = result.getString("specialType");
                String description = result.getString("description");
                event = new SpecialEvent(result.getString("eventName"), result.getDate("eventDate"), result.getString("eventLocation"), result.getDouble("basicTicketPrice"), result.getInt("numberOfTickets"), specialType, description);

                return event;

            } else if(type.equals("Concert")){
                String bandName = result.getString("bandName");
                String genre = result.getString("genre");
                int numberOfSongs = result.getInt("numberOfSongs");
                event = new MusicalEvent(result.getString("eventName"), result.getDate("eventDate"), result.getString("eventLocation"), result.getDouble("basicTicketPrice"), result.getInt("numberOfTickets"), bandName, genre, numberOfSongs);

                return event;
            }
        }
        return event;
    }

    

}