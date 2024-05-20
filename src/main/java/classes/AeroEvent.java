package classes;
import java.util.ArrayList;
import java.util.List;

import java.sql.Date;

public final class AeroEvent extends Event {

    private String airline;
    private ArrayList <String> pilots;
    private boolean isInternational;

    /*

     */

    public AeroEvent(String eventName, Date eventDate, String eventLocation, double basicTicketPrice, int numberOfTickets, String airline, ArrayList<String> pilots, boolean isInternational) {
        super(eventName, eventDate, eventLocation, basicTicketPrice, numberOfTickets);
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


    @Override
    public String toString() {

        String superString = super.toString();
        superString += "Airline: " + airline + "\n";
        superString += "Pilots: " + pilots + "\n";
        return superString;



    }
}
