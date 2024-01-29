import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ProcessStudent{
    private static final Scanner scan = new Scanner(System.in);
    private static final String STUDENT_FILE = "students.txt";
    private static final String STATUS_FILE = "status.txt";

    public static void main(String[] args) throws Exception{
        while(true){
            MenuOptions.displayMenu();
            int choice = getUserChoice();

            switch(choice){
                case    MenuOptions.STANDARD_OPERATION:
                        ProcessStudentData(STUDENT_FILE);
                        break;
                case    MenuOptions.ADD_DATA:
                        addDataViaConsole();
                        break;
                case    MenuOptions.EXIT:
                        System.out.println("Goodbye!");
                        System.exit(0);
                default:
                        System.out.println("Invalid choice");        
            }
        }
    }

    private static int getUserChoice() {
        int choice = -1;
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
}