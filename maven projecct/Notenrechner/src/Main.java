import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GradeBook gradeBook = new GradeBook();

        while (true) {
            System.out.println("Enter class name (or 'quit' to exit): ");
            String className = scanner.nextLine();

            if (className.equalsIgnoreCase("quit")) {
                break;
            }

            gradeBook.addClass(className);

            while (true) {
                System.out.println("Enter student name (or 'quit' to exit): ");
                String studentName = scanner.nextLine();

                if (studentName.equalsIgnoreCase("quit")) {
                    break;
                }

                System.out.println("Enter student grade: ");
                double grade = scanner.nextDouble();
                scanner.nextLine(); // Consume the newline character

                gradeBook.addGrade(className, studentName, grade);
            }
        }

        gradeBook.displayGradeBook();

        System.out.println("\nEnter student name for summary (or 'quit' to exit): ");
        String summaryStudentName = scanner.nextLine();
        if (!summaryStudentName.equalsIgnoreCase("quit")) {
            gradeBook.displayStudentSummary(summaryStudentName);
        }

        scanner.close();
    }
}
