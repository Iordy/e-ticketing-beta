package classes;

public abstract class Ticket {

    protected int id;
    protected double price;
    protected String eventName;
    protected int userId;
    protected boolean isPaid;
    protected int numberOfBenefits;

    public Ticket() {
    }

    public Ticket(double price, String eventName, int userId, boolean isPaid) {
        this.price = price;
        this.eventName = eventName;
        this.userId = userId;
        this.isPaid = isPaid;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getEventName() {
        return eventName;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void payTicket()
    {
        this.isPaid = true;
    }

    public void changeTicketBearer(User newUser)
    {
        this.userId = newUser.getId();
    }

    public String toString()
    {
        return "Ticket for event: " + this.eventName + " with price: " + this.price + " and number of benefits: " + this.numberOfBenefits;
    }

    public abstract double getTicketPrice();

    
}
