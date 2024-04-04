package services;
import java.util.ArrayList;

import classes.PremiumUser;
import classes.RegularUser;
import classes.User;


public class UserManager {


    public static ArrayList <User> UsersEnrolled;

    public UserManager() {
        UsersEnrolled = new ArrayList<User>();
    }

    public void registerUser(String name, String email, String password, String age, String phoneNumber, String isPremium) {
        if (isPremium.equals("yes")) {
            double fee = 100;
            String subscribedSince = "2024-04-4";
            User user = new PremiumUser(name, email, password, age, phoneNumber, fee, java.sql.Date.valueOf(subscribedSince));
            UsersEnrolled.add(user);
        } else {
            User user = new RegularUser(name, email, password, age, phoneNumber, 0);
            UsersEnrolled.add(user);
        }
    }

    public void deleteUser(String email) {
        for (User user : UsersEnrolled) {
            if (user.getEmail().equals(email)) {
                UsersEnrolled.remove(user);
                break;
            }
        }
    }

    public void updateUser(String email, String name, String password, String age, String phoneNumber) {
        for (User user : UsersEnrolled) {
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
        for (User user : UsersEnrolled) {
            if (user.getEmail().equals(email)) {
                PremiumUser premiumUser = ((RegularUser) user).UpgradeUser();
                UsersEnrolled.remove(user);
                UsersEnrolled.add(premiumUser);
                break;
            }
        }
    }


    public User getUser(String email) {
        for (User user : UsersEnrolled) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }


    public void printUsers() {
        for (User user : UsersEnrolled) {
            System.out.println(user);
        }
    }

    public ArrayList<User> getUsersEnrolled() {
        return UsersEnrolled;
    }

    public void changePassword(String email, String password) {
        for (User user : UsersEnrolled) {
            if (user.getEmail().equals(email)) {
                user.setHashedPassword(password);
                break;
            }
        }
    }

    public static User getUserByEmail(String email) {
        for (User user : UsersEnrolled) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    

    
}
