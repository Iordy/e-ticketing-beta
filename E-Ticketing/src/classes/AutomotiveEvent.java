package classes;

import java.util.List;
import java.sql.Date;

public final class AutomotiveEvent extends Event {

    private String carBrand;
    private boolean isElectric;
    private List<String> sponsors;

    public AutomotiveEvent(String eventName, Date eventDate, String eventLocation, double BasicTicketPrice, int numberOfTickets, String carBrand, boolean isElectric, List<String> sponsors) {
        super(eventName, eventDate, eventLocation, BasicTicketPrice, numberOfTickets);
        this.carBrand = carBrand;
        this.isElectric = isElectric;
        this.sponsors = sponsors;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public List<String> getSponsors() {
        return sponsors;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }
    
}
