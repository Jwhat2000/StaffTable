/*
Jeffrey Janvier
COP2805 - Final Exam
4/26/2022
The program that views, inserts, and deletes staff information stored in the
Staff table of the Javabook database
 */

import java.util.*;
import java.sql.*;
        
public class StaffTable {
    public static void main(String[] args)
        throws SQLException, ClassNotFoundException {
         // Load the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");
        //System.out.println("Driver loaded");
        
        // Connect to a database
        Connection  connection = DriverManager.getConnection
        ("jdbc:mysql://localhost/javabook", "scott", "tiger");
        //System.out.println("Database connected");
        
        // Create a statement
        Statement statement = connection.createStatement();
        
        // Display the welcome greeting for the program
        System.out.println("Welcome to the Staff Management program!");
        System.out.println();
        
        
        // Display the options the user can chose for the program
        System.out.println("1. View");
        System.out.println("2. Insert");
        System.out.println("3. Delete");
        System.out.println("4. Exit");
        System.out.println("");
        
        // Prompt the user to choose an option
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a choice: ");
        int options = input.nextInt();
        System.out.println("");
        
        // Display the information of the option
        while(options != 4) {
            switch(options)  {
                case 1: viewTable(statement);
                        break;
                case 2: insertTable(statement);
                        break;
                default:deleteTable(statement);
                        break;
            }
            
        // Display the options the user can chose for the program
        System.out.println("1. View");
        System.out.println("2. Insert");
        System.out.println("3. Delete");
        System.out.println("4. Exit");
        System.out.println("");
        
        // Prompt the user to choose an option
        System.out.print("Enter a choice: ");
        options = input.nextInt();
        System.out.println("");
        }
        
        // Display end of program message
        System.out.println("Good bye!");
        
        // Close the connection
        connection.close();
    }
    
    /* Method to view a selected staff information in the table */
    private static void viewTable(Statement stmt) {
        // Prompt the user to enter staff ID to view their record
        Scanner input = new Scanner(System.in);
        System.out.print("Enter staff's ID to view: ");
        String staff = input.next();
        System.out.println();
            
        try {
            // Execute a statement
            ResultSet rSet = stmt.executeQuery
            ("select id, lastName, firstName, mi, address, city, state, "
             + "telephone, email from Staff where id = '" + staff + "'");
            
            if (rSet.next()) {           
                System.out.println("Record found!");    
                System.out.println("-------------");    
                System.out.println(rSet.getString(1) + "\t" + rSet.getString(2) 
                                + "\t" + rSet.getString(3) + "\t" + rSet.getString(4) 
                                + "\t" + rSet.getString(5) + "\t" + rSet.getString(6)     
                                + "\t" + rSet.getString(7) + "\t" + rSet.getString(8));
                System.out.println();
            }
            else {    
                System.out.println("No record found!");    
                System.out.println();    
            }
        }
        catch (SQLException ex) {
        }
    }
    
    /* Method to insert a selected staff information in the table */
    private static void insertTable(Statement stmt) {
        // Prompt the user to enter staff information to insert into table
        System.out.println("Enter the staff information to insert: ");
        Scanner input = new Scanner(System.in);
        System.out.print("ID: ");
        String staffID = input.nextLine();
        System.out.print("Last name: ");
        String staffLName = input.nextLine();
        System.out.print("First name: ");
        String staffFName = input.nextLine();
        System.out.print("Middle initial: ");
        String staffMI = input.nextLine();
        System.out.print("Address: ");
        String staffAdd = input.nextLine();
        System.out.print("City: ");
        String staffCity = input.nextLine();
        System.out.print("State (2 letters): ");
        String staffState = input.nextLine();
        System.out.print("Telephone: ");
        String staffPhone = input.nextLine();
        System.out.print("Email: ");
        String staffEmail = input.nextLine();
        System.out.println();
        
        try {
            // Execute a statement
            ResultSet rSet;
            rSet = stmt.executeQuery("select * from Staff where id = '"
                    + staffID + "'");
            
            // Check if record already exists
            if (rSet.next() && rSet.getString(1).equals(staffID)) {
                System.out.println("A record with this ID has already existed! "
                                   + "Record NOT inserted.");
                System.out.println();
            }
            else {
            // Inserting the staff information into the table
            int x = stmt.executeUpdate
                ("INSERT INTO Staff " + "VALUES ('" + staffID + "', '" + staffLName 
                + "', '" + staffFName + "', '" + staffMI + "', '" + staffAdd 
                + "', '" + staffCity + "', '" + staffState + "', '" + staffPhone 
                + "', '" + staffEmail + "')");
                System.out.println();
            
            // Determine if information was inserted
            if (x == 1)
                System.out.println("Record inserted. \n");
            }
        }
        catch (SQLException ex) {
        }
    }
    
    /* Method to delete a selected staff information in the table */
    private static void deleteTable(Statement stmt) {
        // Prompt the user to enter the staff ID to delete the record
        Scanner input = new Scanner(System.in);
        System.out.print("Enter staff's ID to delete: ");
        String  staff = input.next();
        System.out.println();
        
        try {
            // Delete a selected staff information record from the table
            stmt.executeUpdate("delete from Staff where id = '" + staff + "'");
            System.out.println("Record deleted.");
            System.out.println();
        
        }
        catch(SQLException ex) {
        }
    }
}