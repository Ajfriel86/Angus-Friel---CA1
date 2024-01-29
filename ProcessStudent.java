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

    private static void processStudentData(String fileName){
        try(BufferedReader read = new BufferedReader(new FileReader(fileName)));{
            String line;
            while((line = read.readLine()) !=null){
                Student student = Student.parseStudent(line);
                if (student != null){
                    String workload = student.determinedWorkLoad();
                    writeStatusToFile(student.getStudentNumber(), student.getSecondName(), workload); 
                }
                else {
                    System.out.println("Invlaid data: " + line);
                }
            }
        }
        catch (IOException | NumberFormatException e){
            System.out.println("Error while reading file: " + e.getMessage);
        }
    }

    private static void addDataViaConsole(){
        System.out.println("Enter student data in the following format: ");
        System.out.println("<First Name> <Second Name> <Number of Classes>");

        String userData = scan.nextLine();
        Student student = Student.parseStudent(userData);

        if (student != null){
            String workload = student.determineWorkLoad();
            writeStatusToFile(student.getStudentNumber(), student.getSecondName(), workload);
            System.out.println("Data has been saved successfully");
        }
        else {
            System.out.println("Invalid Data");
        }
    }

    private static void writeStatusToFile(String studentNumber, String secondName, String workload){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(STATUS_FILE, true))){
            writer.write(studentNumber+ " - " + secondName);
            writer.newLine();
            writer.write(workload);
            writer.newLine();
            writer.newLine();
        }
        catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}