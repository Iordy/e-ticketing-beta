package classes;


public final class GoldTicket extends Ticket {

    private int[] seatRange;

    public GoldTicket(double price, String eventName, int userId, boolean isPaid) {
        super(price, eventName, userId, isPaid);
        this.seatRange = new int[2];
        for (int i = 0; i < 2; i++) {
            this.seatRange[i] = (int) (Math.random() * 100);
        }
        this.numberOfBenefits = 5;
    }

    @Override
    public double getTicketPrice() {
        return price * 1.2;
    }

    public int[] getSeatRange() {
        return seatRange;
    }

    public void setSeatRange(int[] seatRange) {
        this.seatRange = seatRange;
    }
    
}
