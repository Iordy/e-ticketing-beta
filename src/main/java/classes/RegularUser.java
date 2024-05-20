

package classes;

public final class RegularUser extends User {

    private double likelinessToSubscribe;

    public RegularUser(String name, String email, String hashedPassword, String age, String phoneNumber, double likelinessToSubscribe) {
        super(name, email, hashedPassword, age, phoneNumber);
        this.likelinessToSubscribe = likelinessToSubscribe;
    }

    public double getLikelinessToSubscribe() {
        return likelinessToSubscribe;
    }

    @Override
    public double getTicketDiscount() {
        return 1;
    }

    public void computeLikelinessToSubscribe()
    {
        //to do based on how many events the user has attended
    }

    public PremiumUser UpgradeUser()
    {
        PremiumUser premiumUser = new PremiumUser(this.name, this.email, this.hashedPassword, this.age, this.phoneNumber, 100, java.sql.Date.valueOf(java.time.LocalDate.now()));

        return premiumUser;

    }






}
