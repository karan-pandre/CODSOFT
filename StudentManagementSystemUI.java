import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentManagementSystemUI {
    private StudentManagementSystem sms;

    public StudentManagementSystemUI(String filename) {
        sms = new StudentManagementSystem(filename);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add a new student");
            System.out.println("2. Edit an existing student's information");
            System.out.println("3. Search for a student");
            System.out.println("4. Display all students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    editStudent(scanner);
                    break;
                case 3:
                    searchStudent(scanner);
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private void addStudent(Scanner scanner) {
    System.out.print("Enter student name: ");
    String name = scanner.next();
    int rollNumber;
    while (true) {
        try {
            System.out.print("Enter roll number: ");
            rollNumber = scanner.nextInt();
            break;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer value.");
            scanner.next(); 
        }
    }
    System.out.print("Enter grade: ");
    String grade = scanner.next();
    Student student = new Student(name, rollNumber, grade);
    sms.addStudent(student);
    System.out.println("Student added successfully!");
}

    private void editStudent(Scanner scanner) {
        System.out.print("Enter roll number of the student to edit: ");
        int rollNumber = scanner.nextInt();
        Student student = sms.searchStudent(rollNumber);
        if (student != null) {
            System.out.print("Enter new name: ");
            String name = scanner.next();
            System.out.print("Enter new grade: ");
            String grade = scanner.next();
            student.setName(name);
            student.setGrade(grade);
            sms.saveStudents();
            System.out.println("Student information updated successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    private void searchStudent(Scanner scanner) {
        System.out.print("Enter roll number of the student to search: ");
        int rollNumber = scanner.nextInt();
        Student student = sms.searchStudent(rollNumber);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private void displayAllStudents() {
        sms.displayAllStudents();
    }

    public static void main(String[] args) {
        StudentManagementSystemUI ui = new StudentManagementSystemUI("students.txt");
        ui.run();
    }
}