// Importing libraries
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// Main class for processing students data
public class ProcessStudent{
    // Scanner for user input
    private static final Scanner scan = new Scanner(System.in);
    //Declaring file names
    private static final String STUDENT_FILE = "students.txt";
    private static final String STATUS_FILE = "status.txt";

    // Main method to start the program
    public static void main(String[] args) throws Exception{
        // Main loop to displaying menu optins to user
        while(true){
            // Displaying the menu options
            MenuOptions.displayMenu();
            // Getting the users choice
            int choice = getUserChoice();

            // Switch statement to handle the user choice
            switch(choice){
                // Process student data from file 
                case    MenuOptions.STANDARD_OPERATION:
                        ProcessStudentData(STUDENT_FILE);
                        break;
                // Prompt user and add data via console        
                case    MenuOptions.ADD_DATA:
                        addDataViaConsole();
                        break;
                // Display a goodbye message and exit the program
                case    MenuOptions.EXIT:
                        System.out.println("Goodbye!");
                        System.exit(0);
                // Display error message     
                default:
                        System.out.println("Invalid choice");        
            }
        }
    }

    // Method to gather users choice from the menu
    private static int getUserChoice() {
        int choice = -1;
        // Loop used until a valid choice is selected
        while (choice < MenuOptions.STANDARD_OPERATION || choice > MenuOptions.EXIT) {
            System.out.println("Please enter a choice: ");
            try{
                choice = scan.nextInt();
                scan.nextLine();      
            }
            catch (Exception e){
                System.out.println("Invalid option");
                scan.nextLine();
            }
        }
        return choice;
    }

    // Method to process student data from the a file
    private static void processStudentData(String fileName){
        // Try used to initialize BufferReader
        try(BufferedReader read = new BufferedReader(new FileReader(fileName))){
            String line;
            // Loop to read each line of the file
            while((line = read.readLine()) !=null){
                // Parse line into Student object
                Student student = Student.parseStudent(line);
                // Determine workload and write to the status file
                if (student != null){
                    String workload = student.determinedWorkLoad();
                    writeStatusToFile(student.getStudentNumber(), student.getSecondName(), workload); 
                }
                // If the data is invalid, print an error message
                else {
                    System.out.println("Invalid data: " + line);
                }
            }
        }
        // Handle IOException (file not found, etc.) and NumberFormatException (parsing error)
        catch (IOException | NumberFormatException e){
            System.out.println("Error while reading file: " + e.getMessage);
        }
    }

    // Method to add data via console
    private static void addDataViaConsole(){
        // Display to user the valid format for data entry
        System.out.println("Enter student data in the following format: ");
        System.out.println("<First Name> <Second Name> <Number of Classes>");

        // Read user input
        String userData = scan.nextLine();
        // Parse user input into a Student object
        Student student = Student.parseStudent(userData);

        // If the input is valid, determine workload and write to the status file
        if (student != null){
            String workload = student.determineWorkLoad();
            writeStatusToFile(student.getStudentNumber(), student.getSecondName(), workload);
            System.out.println("Data has been saved successfully");
        }
        // If the input is invalid, print an error message
        else {
            System.out.println("Invalid Data");
        }
    }
    // Method to write student status to the status file
    private static void writeStatusToFile(String studentNumber, String secondName, String workload){
        // Try that initialize BufferedWriter
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(STATUS_FILE, true))){
            // Write student information and workload to the file
            writer.write(studentNumber+ " - " + secondName);
            writer.newLine();
            writer.write(workload);
            writer.newLine();
            writer.newLine();
        }
        // Handle IOException
        catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}