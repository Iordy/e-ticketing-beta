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
import java.sql.ResultSet;

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

            if(isInternational.equals("yes")){
                isInternationalBool = true;
            }

//            AeroEvent event = new AeroEvent(name, date, location, price, numberOfTickets, airline, pilots, isInternationalBool);

            try{
                    connection.createStatement().executeUpdate("INSERT INTO EVENT (eventName, eventDate, eventLocation, basicTicketPrice, numberOfTickets, isSoldOut, ticketsSold, eventType) VALUES ('" + name + "', '" + date + "', '" + location + "', '" + price + "', '" + numberOfTickets + "', 'false', '0', 'Aero')");
                    int eventId = connection.createStatement().executeQuery("SELECT eventId FROM event WHERE eventName = '" + name + "'").getInt(1);
                    connection.createStatement().executeUpdate("INSERT INTO AeroEvent (id, airline, isInternational) VALUES ('" + eventId + "', '" + airline + "', '" + isInternationalBool + "')");
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

            if(isElectric.equals("yes")){
                isElectricBool = true;
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
            connection.createStatement().executeUpdate("INSERT INTO EVENT (eventName, eventDate, eventLocation, basicTicketPrice, numberOfTickets, isSoldOut, ticketsSold, eventType) VALUES ('" + name + "', '" + date + "', '" + location + "', '" + price + "', '" + numberOfTickets + "', 'false', '0', 'Auto')");
            int eventId = connection.createStatement().executeQuery("SELECT eventId FROM event WHERE eventName = '" + name + "'").getInt(1);
            connection.createStatement().executeUpdate("INSERT INTO AutomotiveEvent (id, carBrand, isElectric) VALUES ('" + eventId + "', '" + carBrand + "', '" + isElectricBool + "')");
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
                connection.createStatement().executeUpdate("INSERT INTO EVENT (eventName, eventDate, eventLocation, basicTicketPrice, numberOfTickets, isSoldOut, ticketsSold, eventType) VALUES ('" + name + "', '" + date + "', '" + location + "', '" + price + "', '" + numberOfTickets + "', 'false', '0', 'Special')");
                int eventId = connection.createStatement().executeQuery("SELECT eventId FROM event WHERE eventName = '" + name + "'").getInt(1);
                connection.createStatement().executeUpdate("INSERT INTO SpecialEvent (id, specialType, description) VALUES ('" + eventId + "', '" + specialType + "', '" + description + "')");
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
                connection.createStatement().executeUpdate("INSERT INTO EVENT (eventName, eventDate, eventLocation, basicTicketPrice, numberOfTickets, isSoldOut, ticketsSold, eventType) VALUES ('" + name + "', '" + date + "', '" + location + "', '" + price + "', '" + numberOfTickets + "', 'false', '0', 'Concert')");
                int eventId = connection.createStatement().executeQuery("SELECT eventId FROM event WHERE eventName = '" + name + "'").getInt(1);
                connection.createStatement().executeUpdate("INSERT INTO MusicalEvent (id, bandName, genre, numberOfSongs) VALUES ('" + eventId + "', '" + bandName + "', '" + genre + "', '" + Intsongs + "')");
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
            
            String query = "UPDATE events SET date = '" + date + "', location = '" + location + "', price = '" + price + "' WHERE name = '" + name + "'";
    
            try {
                connection.createStatement().executeUpdate(query);
            } catch (Exception e) {
                System.out.println(e);
            }
    }

    public ResultSet getEventsEnrolled() {
        
        String query = "SELECT * FROM events";
        ResultSet result = null;

        try {
             result = connection.createStatement().executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }

    public static ResultSet getEventByName(String name) {
        
        String query = "SELECT * FROM events WHERE name = '" + name + "'";
        ResultSet result = null;

        try{
             result= connection.createStatement().executeQuery(query);
        }
        catch(SQLException e){
            System.out.println(e);
        }

        //return the event

        return result;
    }

    
}
