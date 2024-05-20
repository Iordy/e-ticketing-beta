package classes;

public final class VIPTicket extends Ticket{
     
    private int[] seatRange;
    private boolean freeParking;
    
    public VIPTicket(double price, String eventName, int userId, boolean isPaid) {
        super(price, eventName, userId, isPaid);
        this.seatRange = new int[2];
        for (int i = 0; i < 2; i++) {
            this.seatRange[i] = (int) (Math.random() * 50);
        }
        this.freeParking = true;
        this.numberOfBenefits = 10;
    }

    @Override
    public double getTicketPrice() {
        return price * 1.8;
    }

    public int[] getSeatRange() {
        return seatRange;
    }

    public void setSeatRange(int[] seatRange) {
        this.seatRange = seatRange;
    }

    public boolean isFreeParking() {
        return freeParking;
    }

    public void setFreeParking(boolean freeParking) {
        this.freeParking = freeParking;
    }





    
}
