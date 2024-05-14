package services;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import classes.*;
import java.util.Scanner;

public class TicketManager {

    public static Map<Event, SimpleEntry<User, Ticket>> attendeesList;

    public TicketManager() {
        attendeesList = new HashMap<Event, SimpleEntry<User, Ticket>>();
    }

    public void buyTicket(Scanner scanner, String email, String eventName) {

        User user = UserManager.getUserByEmail(email);
        Event event = EventManager.getEventByName(eventName);
        
        System.out.println("Input the ticket type:[Silver / Gold / VIP]");

        String type = scanner.nextLine();

        if(type.equals("Silver")){

            SilverTicket emptyTicket = new SilverTicket(0, "", 0, false);
            SilverTicket ticket = new SilverTicket(event.getBasicTicketPrice()*emptyTicket.getTicketPrice(), event.getEventName(), user.getId(), false);

            SimpleEntry<User, Ticket> entry = new SimpleEntry<User, Ticket>(user, ticket);
            attendeesList.put(event, entry);

        } else if(type.equals("Gold")){
            GoldTicket emptyTicket = new GoldTicket(0, "", 0, false);
            GoldTicket ticket = new GoldTicket(event.getBasicTicketPrice() * emptyTicket.getTicketPrice(), event.getEventName(), user.getId(), false);
            SimpleEntry<User, Ticket> entry = new SimpleEntry<User, Ticket>(user, ticket);
            attendeesList.put(event, entry);
  
        } else if(type.equals("VIP")){
            VIPTicket emptyTicket = new VIPTicket(0, "", 0, false);
            Ticket ticket = new VIPTicket(event.getBasicTicketPrice() * emptyTicket.getTicketPrice(), event.getEventName(), user.getId(), false);
            SimpleEntry<User, Ticket> entry = new SimpleEntry<User, Ticket>(user, ticket);
            attendeesList.put(event, entry);

        }

    }

    public void showAttendees() {
        for (Event event : attendeesList.keySet()) {
            SimpleEntry<User, Ticket> entry = attendeesList.get(event);
            System.out.println("Event: " + event.getEventName() + " User: " + entry.getKey().getEmail() + " Ticket: " + entry.getValue().getClass().getSimpleName());
        }
    }

    public void changeTicketBearer(String email, String eventName) {

        User user = UserManager.getUserByEmail(email);
        Event event = EventManager.getEventByName(eventName);

        SimpleEntry<User, Ticket> entry = attendeesList.get(event);
        entry.setValue(new SilverTicket(event.getBasicTicketPrice(), event.getEventName(), user.getId(), false));
    }
     
    
}
