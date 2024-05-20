package org.example;

import java.sql.Connection;

import services.UserManager;
import services.dbService;
import classes.*;


public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("Hello, World!");


        UserManager userManager = new UserManager();

        System.out.println(userManager.getUser("901"));


         Menu menu = Menu.getInstance();
         menu.printMenu();


    }
}
