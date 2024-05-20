package classes;

import java.sql.Date;

public abstract class Event {

    private int id;
    private String eventName;
    private Date eventDate;
    private String eventLocation;
    private double basicTicketPrice;
    private int numberOfTickets;
    private boolean isSoldOut;
    private int ticketsSold;

    public Event() {
    }

    public Event(String eventName, Date eventDate, String eventLocation, double basicTicketPrice, int numberOfTickets) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.basicTicketPrice = basicTicketPrice;
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
        return basicTicketPrice;
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
        this.basicTicketPrice = BasicTicketPrice;

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


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Event) {
            Event event = (Event) obj;
            return this.eventName.equals(event.eventName) && this.eventDate.equals(event.eventDate) && this.eventLocation.equals(event.eventLocation);
        }
        return false;
    }


    public String toString() {
        return "Event: " + this.eventName + " on " + this.eventDate + " at " + this.eventLocation + " with price: " + this.basicTicketPrice + " and number of tickets: " + this.numberOfTickets;
    }


    
}
