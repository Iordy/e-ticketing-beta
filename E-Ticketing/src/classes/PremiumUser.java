
package classes;
import java.sql.Date;
import java.time.LocalDate;

public final class PremiumUser extends User {

    private double subscriptionFee;
    private Date subscribedSince;


    public PremiumUser(String name, String email, String hashedPassword, String age, String phoneNumber, double subscriptionFee, Date subscribedSince) {
        super(name, email, hashedPassword, age, phoneNumber);
        this.subscriptionFee = subscriptionFee;
        this.subscribedSince = subscribedSince;
    }

    public double getSubscriptionFee() {
        return subscriptionFee;
    }

    public Date getSubscribedSince() {
        return subscribedSince;
    }

    @Override
    public double getTicketDiscount() {
        
        if (this.subscribedSince.toLocalDate().isBefore(LocalDate.now().minusYears(1)))
        {
            return 0.3;
        }
        else if (this.subscribedSince.toLocalDate().isBefore(LocalDate.now().minusYears(2))) 
        {
            return 0.5;
        }
        else
        {
            return 0.7;
        }
    }
}

