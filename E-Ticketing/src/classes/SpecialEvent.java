package classes;

import java.sql.Date;

public final class SpecialEvent extends Event{

    private String specialType;
    private String specialDescription;

    public SpecialEvent(String eventName, Date eventDate, String eventLocation, double BasicTicketPrice, int numberOfTickets, String specialType, String specialDescription) {
        super(eventName, eventDate, eventLocation, BasicTicketPrice, numberOfTickets);
        this.specialType = specialType;
        this.specialDescription = specialDescription;
    }

    public String getSpecialType() {
        return specialType;
    }

    public String getSpecialDescription() {
        return specialDescription;
    }

    public void setSpecialType(String specialType) {
        this.specialType = specialType;
    }

    public void setSpecialDescription(String specialDescription) {
        this.specialDescription = specialDescription;
    }
    
}
