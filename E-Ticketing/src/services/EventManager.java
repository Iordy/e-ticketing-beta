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

public class EventManager {

    public static List <Event> eventsEnrolled;

    public EventManager() {
        eventsEnrolled = new ArrayList<Event>();
    }

    public void registerEvent(Scanner scanner, String name, Date date, String location, double price, int numberOfTickets) {

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

            AeroEvent event = new AeroEvent(name, date, location, price, numberOfTickets, airline, pilots, isInternationalBool);

         
            eventsEnrolled.add(event);

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

            AutomotiveEvent event = new AutomotiveEvent(name, date, location, price, numberOfTickets, carBrand, isElectricBool, sponsors);
            eventsEnrolled.add(event);


        } else if(type.equals("Special")){
           
            System.out.println("Input the special event type:");
            String specialType = scanner.nextLine();

            System.out.println("Input the description:");
            String description = scanner.nextLine();

            SpecialEvent event = new SpecialEvent(name, date, location, price, numberOfTickets, specialType, description);
            eventsEnrolled.add(event);



        } else if(type.equals("Concert")){
            System.out.println("Input the band name:");
            String bandName = scanner.nextLine();

            System.out.println("Input the number of songs:");
            String songs = scanner.nextLine();
            int Intsongs = Integer.parseInt(songs);
            

            System.out.println("What Genre is the band?");
            String genre = scanner.nextLine();

            MusicalEvent event = new MusicalEvent(name, date, location, price, numberOfTickets, bandName, genre, Intsongs);
            eventsEnrolled.add(event);

        } else {
           
            System.out.println("Invalid event type");
        }

        
        
    }

    public void deleteEvent(String name) {
        for (Event event : eventsEnrolled) {
            if (event.getEventName().equals(name)) {
                eventsEnrolled.remove(event);
                break;
            }
        }
    }

    public void updateEvent(String name, Date date, String location, double price) {
        for (Event event : eventsEnrolled) {
            if (event.getEventName().equals(name)) {
                event.setEventDate(date);
                event.setEventLocation(location);
                event.setBasicTicketPrice(price);
                break;
            }
        }
    }

    public List <Event> geteventsEnrolled() {
        return eventsEnrolled;
    }

    public static Event getEventByName(String name) {
        for (Event event : eventsEnrolled) {
            if (event.getEventName().equals(name)) {
                return event;
            }
        }
        return null;
    }

    public void printEvents() {
        for (Event event : eventsEnrolled) {
            System.out.println(event);
        }
    }
    
}
