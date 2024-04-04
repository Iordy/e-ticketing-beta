package services;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import classes.*;
import java.util.Scanner;

public class TicketManager {

    public static HashMap<Event, SimpleEntry<User, Ticket>> AtendeesList;

    public TicketManager() {
        AtendeesList = new HashMap<Event, SimpleEntry<User, Ticket>>();
    }

    public void buyTicket(String email, String eventName) {

        User user = UserManager.getUserByEmail(email);
        Event event = EventManager.getEventByName(eventName);
        
        System.out.println("Input the ticket type:[Silver / Gold / VIP]");

        Scanner scanner = new Scanner(System.in);

        String type = scanner.nextLine();

        if(type.equals("Silver")){

            SilverTicket emptyTicket = new SilverTicket(0, "", 0, false);
            SilverTicket ticket = new SilverTicket(event.getBasicTicketPrice()*emptyTicket.getTicketPrice(), event.getEventName(), user.getId(), false);

            SimpleEntry<User, Ticket> entry = new SimpleEntry<User, Ticket>(user, ticket);
            AtendeesList.put(event, entry);
            scanner.close();
        } else if(type.equals("Gold")){
            GoldTicket emptyTicket = new GoldTicket(0, "", 0, false);
            GoldTicket ticket = new GoldTicket(event.getBasicTicketPrice() * emptyTicket.getTicketPrice(), event.getEventName(), user.getId(), false);
            SimpleEntry<User, Ticket> entry = new SimpleEntry<User, Ticket>(user, ticket);
            AtendeesList.put(event, entry);
            scanner.close();
        } else if(type.equals("VIP")){
            VIPTicket emptyTicket = new VIPTicket(0, "", 0, false);
            Ticket ticket = new VIPTicket(event.getBasicTicketPrice() * emptyTicket.getTicketPrice(), event.getEventName(), user.getId(), false);
            SimpleEntry<User, Ticket> entry = new SimpleEntry<User, Ticket>(user, ticket);
            AtendeesList.put(event, entry);
            scanner.close();
        }

       // scanner.close();
    }

    public void showAttendees() {
        for (Event event : AtendeesList.keySet()) {
            SimpleEntry<User, Ticket> entry = AtendeesList.get(event);
            System.out.println("Event: " + event.getEventName() + " User: " + entry.getKey().getEmail() + " Ticket: " + entry.getValue().getClass().getSimpleName());
        }
    }

    public void ChangeTicketBearer(String email, String eventName) {

        User user = UserManager.getUserByEmail(email);
        Event event = EventManager.getEventByName(eventName);

        SimpleEntry<User, Ticket> entry = AtendeesList.get(event);
        entry.setValue(new SilverTicket(event.getBasicTicketPrice(), event.getEventName(), user.getId(), false));
    }
     
    
}
