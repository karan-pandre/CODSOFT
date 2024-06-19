import java.util.Scanner;

public class studentGrade {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalMarks = 0;
        int totalSubjects = 0;

        System.out.println("Enter the marks obtained in each subject:");
        for (int i = 1; i <= 5; i++) {
            System.out.print("Subject " + i + ": ");
            int marks = scanner.nextInt();
            totalMarks += marks;
            totalSubjects++;
        }

        double averagePercentage = (double) totalMarks / totalSubjects * 100;

        String grade;
        if (averagePercentage >= 90) {
            grade = "A";
        } else if (averagePercentage >= 80) {
            grade = "B";
        } else if (averagePercentage >= 70) {
            grade = "C";
        } else if (averagePercentage >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);
    }
}