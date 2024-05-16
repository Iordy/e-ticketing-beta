package services;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserManager {

   public static Connection connection = services.dbService.getConnection();

    public void registerUser(String name, String email, String password, String age, String phoneNumber, String isPremium) {
        if (isPremium.equals("yes")) {
            double fee = 100;
            String subscribedSince = "2024-04-4";
          //  User user = new PremiumUser(name, email, password, age, phoneNumber, fee, java.sql.Date.valueOf(subscribedSince));
          
            try{
                connection.createStatement().executeUpdate("INSERT INTO User (name, email, hashedPassword, age, phoneNumber, userType) VALUES ('"+name+"', '"+email+"', '"+password+"', '"+age+"', '"+phoneNumber+"', 'Premium')");
                int id = connection.createStatement().executeUpdate("SELECT id FROM User WHERE email = '"+email+"'");
                connection.createStatement().executeUpdate("INSERT INTO PremiumUser (id, subscriptionFee, subscribedSince) VALUES ('"+id+"', '"+fee+"', '"+subscribedSince+"')");
            }
            catch(Exception e){
                System.out.println(e);
            }
            
        } else {
           // User user = new RegularUser(name, email, password, age, phoneNumber, 0);
           
              try{
                connection.createStatement().executeUpdate("INSERT INTO User (name, email, hashedPassword, age, phoneNumber, userType) VALUES ('"+name+"', '"+email+"', '"+password+"', '"+age+"', '"+phoneNumber+"', 'Regular')");
                int id = connection.createStatement().executeUpdate("SELECT id FROM User WHERE email = '"+email+"'");
                connection.createStatement().executeUpdate("INSERT INTO RegularUser (id, likelinessToSubscribe) VALUES ('"+id+"', '0')");

              } catch(SQLException e){
                System.out.println(e);
              }
        }
    }

    public void deleteUser(String email) {
       
        String query = "DELETE FROM User WHERE email = '"+email+"'";
        try{
            connection.createStatement().executeUpdate(query);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void updateUser(String email, String name, String password, String age, String phoneNumber) {
       
        String query = "UPDATE User SET name = '"+name+"', hashedPassword = '"+password+"', age = '"+age+"', phoneNumber = '"+phoneNumber+"' WHERE email = '"+email+"'";
        try{
            connection.createStatement().executeUpdate(query);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public void upgradeUser(String email) {
       
        String query = "UPDATE User SET userType = 'Premium' WHERE email = '"+email+"'";
        try{
            connection.createStatement().executeUpdate(query);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }


    public ResultSet getUser(String email) {
       
        String query = "SELECT * FROM User WHERE email = '"+email+"'";
        ResultSet result = null;

        try{
            result = connection.createStatement().executeQuery(query);
        }
        catch(Exception e){
            System.out.println(e);
        }

        return result;
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
