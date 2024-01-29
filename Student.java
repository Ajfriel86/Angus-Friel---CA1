public class Student {
    // Add attributes for the class
    private String firstName;
    private String secondName;
    private int numClasses;
    private String studentNumber;

    // Constructor to create the Student Object
    public Student(String firstName, String secondName, int numClasses, String studentNumber){
        this.firstName = firstName;
        this.secondName = secondName;
        this.numClasses = numClasses;
        this.studentNumber = studentNumber;
    }

    // Parse a string of data to create a Student object
    public static Student parseStudent(String data){
        
        // Split the input data into parts using space as a delimiter
        String[] parts = data.split(" ");
        // Check if the data contains exactly three parts
        if (parts.length == 3){
            // Extracting individual parts
            String firstName = parts[0];
            String secondName = parts[1];
            int numClasses;
            // Parse the number of classes as an integer
            try{
                numClasses = Integer.parseInt(parts[2]);
            }
            // Catch any invalid data
            catch (NumberFormatException e){
                return null;
            }
            // check validation of individual parts and create a Student object if valid
            if (isValidFirstName(firstName) && isValidSecondName(secondName) && isValidNumClasses(numClasses)){
                return new Student(firstName, secondName, numClasses, parts[2]); 
            } 
        }
        return null;
    }

    // Method to validate the first name
    private static boolean isValidFirstName(String firstName){
        return firstName.matches("[a-zA-Z]+");
    }

    // Method to validate second name
    private static boolean isValidSecondName(String secondName){
        return secondName.matches("[a-zA-Z)-9]+");
    }

}
