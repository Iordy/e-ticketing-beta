package org.example;

import services.*;
import java.util.Scanner;
import java.sql.Date;
import java.sql.SQLException;



public final class Menu {


    private static EventManager eventManager = new EventManager();
    private static UserManager userManager = new UserManager();
    private static TicketManager ticketManager = new TicketManager();


    private static Menu instance = null;

    private Menu() {
    }

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    public void printMenu() throws SQLException {

        printOptions(); 

        String option = "0";
        Scanner scanner = new Scanner(System.in); 

       
      
        while(!(option.equals( "12"))){

            printOptions();

            try {
                option = scanner.nextLine().trim();
                System.out.println("Option: " + option);
            } catch (Exception e) {
                System.out.println(e);
            }
        
            
            switch (option) {
                 case "1":
                    System.out.println("Enter the event name:");
                    String eventName = scanner.nextLine();

                    System.out.println("Enter the event date:");
                    Date eventDate = new Date(0);
                     try {
                         eventDate = Date.valueOf(scanner.nextLine());
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        System.out.println("Please enter a valid date");
                        break;
                    }

                    System.out.println("Enter the event location:");
                    String eventLocation = scanner.nextLine();


                    System.out.println("Enter the basic ticket price:");

                    double basicTicketPrice = 0;

                    try{
                         String basicTicketPriceString = scanner.nextLine();
                         basicTicketPrice = Double.parseDouble(basicTicketPriceString);
                    }

                    catch (Exception e){
                        e.printStackTrace();
                        System.out.println("Please enter a valid ticket price");
                        break;
                    }


                    System.out.println("Enter the number of tickets:");
                    String numberOfTickets = scanner.nextLine();
                    int intNumberOfTickets = Integer.parseInt(numberOfTickets);

                    eventManager.registerEvent(scanner, eventName, eventDate, eventLocation, basicTicketPrice, intNumberOfTickets);
                    break;
                case "2":
                    System.out.println("Enter the user name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter the user email:");
                    String email = scanner.nextLine();
                    System.out.println("Enter the user password:");
                    String password = scanner.nextLine();
                    System.out.println("Enter the user age:");
                    String age = scanner.nextLine();
                    System.out.println("Enter the user phone number:");
                    String phoneNumber = scanner.nextLine();
                    System.out.println("Is the user premium? (yes/no)");
                    String isPremium = scanner.nextLine();
                    userManager.registerUser(name, email, password, age, phoneNumber, isPremium);
                    break;
                case "3":
                    System.out.println("Enter the user email:");
                    String userEmail = scanner.nextLine();
                    System.out.println("Enter the event name:");
                    String event = scanner.nextLine();
                    ticketManager.buyTicket(scanner, userEmail, event);
                    break;
                case "4":
                    ticketManager.showAttendees();
                    break;
                case "5":
                    System.out.println("Enter the user email:");
                    email = scanner.nextLine();
                    userManager.deleteUser(email);
                    break;
                case "6":
                    System.out.println("Enter the user email:");
                    userEmail = scanner.nextLine();
                    System.out.println("Enter the user name:");
                    name = scanner.nextLine();
                    System.out.println("Enter the user password:");
                    password = scanner.nextLine();
                    System.out.println("Enter the user age:");
                    age = scanner.nextLine();
                    System.out.println("Enter the user phone number:");
                    phoneNumber = scanner.nextLine();
                    userManager.updateUser(userEmail, name, password, age, phoneNumber);
                    break;
                case "7":
                    System.out.println("Enter the user email:");
                    email = scanner.nextLine();
                    userManager.upgradeUser(email);
                    break;
                case "8":
                    System.out.println("Enter the event name:");
                    eventName = scanner.nextLine();
                    eventManager.deleteEvent(eventName);
                    break;
                case "9":
                    System.out.println("Enter the event name:");
                    eventName = scanner.nextLine();
                    System.out.println("Enter the new event name:");
                    String newEventName = scanner.nextLine();
                    System.out.println("Enter the new event date:");
                    Date date = Date.valueOf(scanner.nextLine());
                    System.out.println("Enter the new event location:");
                    String new_eventLocation = scanner.nextLine();
                    System.out.println("Enter the new basic ticket price:");
                    String new_basicTicketPrice = scanner.nextLine();
                    double intNewBasicTicketPrice = Double.parseDouble(new_basicTicketPrice);
                    System.out.println("Enter the new number of tickets:");
                    eventManager.updateEvent(newEventName, date, new_eventLocation, intNewBasicTicketPrice);
                    break;
                case "10":
                    eventManager.getEventsEnrolled();
                    break;
                case "11":
                    System.out.println("Enter the user email:");
                    email = scanner.nextLine();
                    System.out.println("Enter the second user name:");
                    String email2 = scanner.nextLine();
                    System.out.println("Enter the event name:");
                    String eventName2 = scanner.nextLine();

                    ticketManager.changeTicketBearer(email2, eventName2);
                
                    break;
                case "12":
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }

           
            System.out.println("Am iesit din switch");
     
     
        }




  
}

public static void printOptions() {
    System.out.println("1. Register event");
    System.out.println("2. Register user");
    System.out.println("3. Buy ticket");
    System.out.println("4. Show attendees");
    System.out.println("5. Delete user");
    System.out.println("6. Update user");
    System.out.println("7. Upgrade user");
    System.out.println("8. Delete event");
    System.out.println("9. Update event");
    System.out.println("10. Show events");
    System.out.println("11. Change ticket bearer");
    System.out.println("12. Exit");
    System.out.println("Choose an option:");
    
}

}


