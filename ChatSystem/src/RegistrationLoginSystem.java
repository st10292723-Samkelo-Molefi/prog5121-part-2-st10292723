import javax.swing.*;
import java.util.Scanner;

//CODE ATTRIBUTION
//This code was adapted from the source below

//Author: OpenAI
//Date: 2024
//Title: ChatGPT-4 (Apr 9 version) [Large Language Model].
//Source: https://openai.com/





public class RegistrationLoginSystem {
    static String registerUsername;
    static String registerPassword;
    static String registerCellPhoneNumber;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            
    // Registration for Account
            
            System.out.println("Registration Page");
            
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            
            System.out.print("Enter your cellphone number: ");
            String cellphoneNumber = scanner.nextLine();
    
    // Validate inputs
    
            if (checkUserName(username) && checkPasswordComplexity(password) && checkCellPhoneNumber(cellphoneNumber)) {
                registerUsername = username;
                registerPassword = password;
                registerCellPhoneNumber = cellphoneNumber;
                
                System.out.println(registerUser(true));
            } else {
                System.out.println(registerUser(false));
                return;
            }
            
    // Login to Account
            
            System.out.println(" Login Page ");
            
            System.out.print("Enter your username: ");
            String loginUsername = scanner.nextLine();
            
            System.out.print("Enter your password: ");
            String loginPassword = scanner.nextLine();
            
            if (loginUsername (loginUsername, loginPassword)) {
                System.out.println(returnLoginStatus(true));
                runMessageSystem();
            } else {
                System.out.println(returnLoginStatus(false));
            }
        }
    }
    
    //CODE ATTRIBUTION
    //This code was adapted from the source below
    
    //Author: Yoel Nunez
    //Author Profile: https://stackoverflow.com/users/4114033/yoel-nunez
    //Date: Oct 7, 2014
    //Title: java-public-static-boolean-method
    //Source: https://stackoverflow.com/questions/26240250/java-public-static-boolean-method

    //Validation methods for the RegistrationLoginSystem
    
       public static boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }
    
       public static boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 && //Eight characters long
               password.matches(".*[A-Z].*") && //Capital letters
               password.matches(".*[a-z].*") && //Small letters
               password.matches(".*[\\d].*") && //Number
               password.matches(".*[!@#$%^&*_+-?/;'].*"); //Special characters
    }
    
    public static boolean checkCellPhoneNumber(String cellphoneNumber) {
        return cellphoneNumber.matches("^\\+\\d{10,13}$");
}

    
    public static String registerUser(boolean success) {
        if (success) {
            return "Successfully captured Username\nSuccessfully captured Password\nSuccessfully added Cellphone Number";
        } else {
            return "Registration was unsuccessful. Please ensure that all requirement have been fullfilled.";
        }
    }
    
    public static boolean loginUsername(String username, String password) {
        return username.equals(registerUsername) && password.equals(registerPassword);
    }
    
    public static String returnLoginStatus(boolean success) {
        if (success) {
            return "Welcome " + registerUsername + " it is great to see you!";
        } else {
            return "Incorrect username or password , please try again.";
        }
    }

    
//Starts the interactive message system where you can send messages, check your sent messages, or exit. 
//It also takes care of what you type and shows you dialog boxes while keeping track of your messages.

    //CODE ATTRIBUTION
    //This code was adapted from the source below
    
    //Author: Oracle
    //Date: 2025
    //Title: javase tutorial uiswing components dialog
    //Source:https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
    
    public static void runMessageSystem() {
        Message msg = new Message("+27718693002", "Hey", "Send");
        boolean running = true;

        while (running) {
            String[] options = {"1. Send Message", "2. View Sent Messages", "3. Quit"};
            String choice = (String) JOptionPane.showInputDialog(
                    null,
                    "Choose an option:",
                    "Main Menu",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (choice == null) break;        

                        switch (choice.charAt(0)) {
                case '1' -> {
                    String id = JOptionPane.showInputDialog("Enter Message ID:");
                    String cell = JOptionPane.showInputDialog("Enter Recipient Cell:");
                    String text = JOptionPane.showInputDialog("Enter message (50-250 characters):");
                    String result = msg.sendMessage(id, cell, text);
                    JOptionPane.showMessageDialog(null, result);
                }
                case '2' -> {
                    JOptionPane.showMessageDialog(null, msg.printMessages());
                }
                case '3' -> {
                    running = false;
                }
            }
        }
    }
}




