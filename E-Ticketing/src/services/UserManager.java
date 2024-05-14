package services;
import java.util.ArrayList;
import java.util.List;
import classes.PremiumUser;
import classes.RegularUser;
import classes.User;


public class UserManager {

    public static List <User> usersEnrolled;

    public UserManager() {
        usersEnrolled = new ArrayList<User>();
    }

    public void registerUser(String name, String email, String password, String age, String phoneNumber, String isPremium) {
        if (isPremium.equals("yes")) {
            double fee = 100;
            String subscribedSince = "2024-04-4";
            User user = new PremiumUser(name, email, password, age, phoneNumber, fee, java.sql.Date.valueOf(subscribedSince));
            usersEnrolled.add(user);
        } else {
            User user = new RegularUser(name, email, password, age, phoneNumber, 0);
            usersEnrolled.add(user);
        }
    }

    public void deleteUser(String email) {
        for (User user : usersEnrolled) {
            if (user.getEmail().equals(email)) {
                usersEnrolled.remove(user);
                break;
            }
        }
    }

    public void updateUser(String email, String name, String password, String age, String phoneNumber) {
        for (User user : usersEnrolled) {
            if (user.getEmail().equals(email)) {
                user.setName(name);
                user.setHashedPassword(password);
                user.setAge(age);
                user.setPhoneNumber(phoneNumber);
                break;
            }
        }
    }

    public void upgradeUser(String email) {
        for (User user : usersEnrolled) {
            if (user.getEmail().equals(email)) {
                PremiumUser premiumUser = ((RegularUser) user).UpgradeUser();
                usersEnrolled.remove(user);
                usersEnrolled.add(premiumUser);
                break;
            }
        }
    }


    public User getUser(String email) {
        for (User user : usersEnrolled) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }


    public void printUsers() {
        for (User user : usersEnrolled) {
            System.out.println(user);
        }
    }

    public List<User> getusersEnrolled() {
        return usersEnrolled;
    }

    public void changePassword(String email, String password) {
        for (User user : usersEnrolled) {
            if (user.getEmail().equals(email)) {
                user.setHashedPassword(password);
                break;
            }
        }
    }

    public static User getUserByEmail(String email) {
        for (User user : usersEnrolled) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    

    
}
