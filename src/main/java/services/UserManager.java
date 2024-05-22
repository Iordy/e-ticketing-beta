package services;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import classes.*;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.result.Row;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class UserManager {

   public static Connection connection = services.dbService.getConnection();

   public static logService logService = new logService();

    public void registerUser(String name, String email, String password, String age, String phoneNumber, String isPremium) {

        String table = "User";
        String action = "Register User -> Insert";

        if (isPremium.equals("yes")) {
            double fee = 100;
            Date subscribedSince = new Date(System.currentTimeMillis());
          
            try{
                connection.createStatement().executeUpdate("INSERT INTO User (name, email, hashedPassword, age, phoneNumber, userType) VALUES ('"+name+"', '"+email+"', '"+password+"', '"+age+"', '"+phoneNumber+"', 'Premium')");
                ResultSet id_set = connection.createStatement().executeQuery("SELECT id FROM User WHERE email = '"+email+"'");
                id_set.next();
                int id = id_set.getInt("id");
                connection.createStatement().executeUpdate("INSERT INTO PremiumUser (id, subscriptionFee, subscribedSince) VALUES ('"+id+"', '"+fee+"', '"+subscribedSince+"')");
                logService.logDatabaseAction(action,table);
            }
            catch(Exception e){
                System.out.println(e);
            }
            
        } else {

              try{
                connection.createStatement().executeUpdate("INSERT INTO User (name, email, hashedPassword, age, phoneNumber, userType) VALUES ('"+name+"', '"+email+"', '"+password+"', '"+age+"', '"+phoneNumber+"', 'Regular')");
                ResultSet id_set = connection.createStatement().executeQuery("SELECT id FROM User WHERE email = '"+email+"'");
                id_set.next();
                int id = id_set.getInt("id");
                connection.createStatement().executeUpdate("INSERT INTO RegularUser (id, likelinessToSubscribe) VALUES ('"+id+"', '0')");
                logService.logDatabaseAction(action,table);
              } catch(SQLException e){
                System.out.println(e);
              }
        }
    }

    public void deleteUser(String email) throws SQLException {

        String userfind = "SELECT * FROM User WHERE email = '" + email + "'";
        ResultSet user = null;
        String userType = null;
        int id = 0;

        String action = "Delete User -> Delete";
        String table = "User";

        try {
            user = connection.createStatement().executeQuery(userfind);

        }
            catch(Exception e)
        {
            System.out.println(e);
        }

        if (user.next()){
            id = user.getInt("id");
            userType = user.getString("userType");
        }



        if(userType.equals( "Premium")){
            String sql = "DELETE FROM PremiumUser WHERE id = '"+id+"'";
            String query = "DELETE FROM User WHERE email = '"+email+"'";

            try{
                connection.createStatement().executeUpdate(sql);
                connection.createStatement().executeUpdate(query);
                logService.logDatabaseAction(action,table);
            }
            catch(Exception e){
                System.out.println(e);
            }
        }

        else if(userType.equals("Regular")){
            String sql = "DELETE FROM RegularUser WHERE id = '"+id+"'";
            String query = "DELETE FROM User WHERE email = '"+email+"'";

            try{
                connection.createStatement().executeUpdate(sql);
                connection.createStatement().executeUpdate(query);
                logService.logDatabaseAction(action,table);
            }
            catch(Exception e){
                System.out.println(e);
            }
        }

    }


    public void updateUser(String email, String name, String password, String age, String phoneNumber) {

        String action = "Update User -> Update";
        String table = "User";

        String query = "UPDATE User SET name = '"+name+"', hashedPassword = '"+password+"', age = '"+age+"', phoneNumber = '"+phoneNumber+"' WHERE email = '"+email+"'";
        try{
            connection.createStatement().executeUpdate(query);
            logService.logDatabaseAction(action,table);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void upgradeUser(String email) throws SQLException {

        Date todayDate = new Date(System.currentTimeMillis());

        String action = "Upgrade User -> Update, Delete, Insert";
        String table = "User";
       
        String query = "UPDATE User SET userType = 'Premium' WHERE email = '"+email+"'";
        ResultSet id_set = connection.createStatement().executeQuery("SELECT id FROM User WHERE email = '"+email+"' ");
        id_set.next();
        int id = id_set.getInt("id");
        String deleteOldEntry = "DELETE FROM RegularUser WHERE id = '"+id+"'";
        String createNewEntry = "INSERT INTO PremiumUser (id, subscriptionFee, subscribedSince) VALUES ('"+id+"', 100 , '"+todayDate+"')";
        try{
            connection.createStatement().executeUpdate(query);
            connection.createStatement().executeUpdate(deleteOldEntry);
            connection.createStatement().executeUpdate(createNewEntry);
            logService.logDatabaseAction(action,table);

        }
        catch(Exception e){
            System.out.println(e);
        }
    }


    public HashMap<User, Integer> getUser(String email) {

        String userQuery = "SELECT * FROM User WHERE email = ?";
        String premiumQuery = "SELECT * FROM PremiumUser WHERE id = ?";
        String regularQuery = "SELECT * FROM RegularUser WHERE id = ?";

        String action = "Get User -> Select";
        String table = "User";

        HashMap<User, Integer> returned = new HashMap<>();

        try (PreparedStatement psUser = connection.prepareStatement(userQuery)) {
            psUser.setString(1, email);
            ResultSet result = psUser.executeQuery();

            if (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String password = result.getString("hashedPassword");
                String age = result.getString("age");
                String phoneNumber = result.getString("phoneNumber");
                String userType = result.getString("userType");

                if (userType.equals("Premium")) {

                    try (PreparedStatement psPremium = connection.prepareStatement(premiumQuery)) {
                        psPremium.setInt(1, id);
                        ResultSet result2 = psPremium.executeQuery();

                        logService.logDatabaseAction(action,table);


                        if (result2.next()) {

                            double fee = result2.getDouble("subscriptionFee");
                            String subscribedSince = result2.getString("subscribedSince");
                            PremiumUser ret = new PremiumUser(name, email, password, age, phoneNumber, fee, java.sql.Date.valueOf(subscribedSince));

                            returned.put(ret, id);

                            return returned;

                        }
                    }
                } else {
                    try (PreparedStatement psRegular = connection.prepareStatement(regularQuery)) {
                        psRegular.setInt(1, id);
                        ResultSet result2 = psRegular.executeQuery();

                        logService.logDatabaseAction(action,table);

                        if (result2.next()) {
                            int likelinessToSubscribe = result2.getInt("likelinessToSubscribe");
                            RegularUser ret = new RegularUser(name, email, password, age, phoneNumber, likelinessToSubscribe);

                            returned.put(ret, id);
                            return returned;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public void printUsers() {
       
        String query = "SELECT * FROM User";
        ResultSet result = null;

        try{
            result = connection.createStatement().executeQuery(query);
            while(result.next()){
                System.out.println(result.getString("name") + " " + result.getString("email") + " " + result.getString("hashedPassword") + " " + result.getString("age") + " " + result.getString("phoneNumber") + " " + result.getString("userType"));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }


    public void changePassword(String email, String password) {
        
        String query = "UPDATE User SET hashedPassword = '"+password+"' WHERE email = '"+email+"'";
        try{
            connection.createStatement().executeUpdate(query);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    

    
}
