package classes;

import java.sql.Date;

public abstract class Event {

    private int id;
    private String eventName;
    private Date eventDate;
    private String eventLocation;
    private double BasicTicketPrice;
    private int numberOfTickets;
    private boolean isSoldOut;
    private int ticketsSold;

    public Event() {
    }

    public Event(String eventName, Date eventDate, String eventLocation, double BasicTicketPrice, int numberOfTickets) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.BasicTicketPrice = BasicTicketPrice;
        this.numberOfTickets = numberOfTickets;
        this.isSoldOut = false;
        this.ticketsSold = 0;
        
    }

    public int getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public double getBasicTicketPrice() {
        return BasicTicketPrice;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public boolean isSoldOut() {
        return isSoldOut;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public void incrementTicketsSold() {
        ticketsSold++;
        if (ticketsSold == numberOfTickets) {
            isSoldOut = true;
        }
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public void setBasicTicketPrice(double BasicTicketPrice) {
        this.BasicTicketPrice = BasicTicketPrice;

    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public void setSoldOut(boolean isSoldOut) {
        this.isSoldOut = isSoldOut;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String toString() {
        return "Event: " + this.eventName + " on " + this.eventDate + " at " + this.eventLocation + " with price: " + this.BasicTicketPrice + " and number of tickets: " + this.numberOfTickets;
    }


    
}
