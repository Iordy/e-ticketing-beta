package classes;
import java.util.ArrayList;

import java.sql.Date;

public final class AeroEvent extends Event {

    private String airline;
    private ArrayList <String> pilots;
    private boolean isInternational;

    /*

     */

    public AeroEvent(String eventName, Date eventDate, String eventLocation, double BasicTicketPrice, int numberOfTickets, String airline, ArrayList<String> pilots, boolean isInternational) {
        super(eventName, eventDate, eventLocation, BasicTicketPrice, numberOfTickets);
        this.airline = airline;
        this.pilots = pilots;
        this.isInternational = isInternational;
    }

    public String getAirline() {
        return airline;
    }

    public ArrayList<String> getPilots() {
        return pilots;
    }

    public boolean isInternational() {
        return isInternational;
    }
    
}
