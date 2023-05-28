import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GradeBook {
    public static void main(String[] args) {
        Map<String, Double> gradeMap = new HashMap<>(); // Map to store grades for each student

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter student name (or 'quit' to exit): ");
            String name = scanner.nextLine();

            if (name.equalsIgnoreCase("quit")) {
                break;
            }

            System.out.println("Enter student grade: ");
            double grade = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character

            gradeMap.put(name, grade);
        }

        System.out.println("\nGrade Book:");

        for (Map.Entry<String, Double> entry : gradeMap.entrySet()) {
            String name = entry.getKey();
            double grade = entry.getValue();
            System.out.println(name + ": " + grade);
        }

        scanner.close();
    }
}