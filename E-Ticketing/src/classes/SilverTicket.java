package classes;

import java.util.Random;


public final class SilverTicket extends Ticket {

    boolean LuckyUpgrade;

    public SilverTicket(double price, String eventName, int userId, boolean isPaid) {
        super(price, eventName, userId, isPaid);
        long seed = System.nanoTime();
        this.LuckyUpgrade = new Random(seed).nextBoolean();
        this.numberOfBenefits = 3;
    }

    @Override
    public double getTicketPrice() {
        if (this.LuckyUpgrade)
        {
            return price;
        }
        else
        {
            return price * 0.9;
        }
    }

    
    
}
