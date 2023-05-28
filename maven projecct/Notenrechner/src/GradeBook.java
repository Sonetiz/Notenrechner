import java.util.HashMap;
import java.util.Map;

public class GradeBook {
    private Map<String, Map<String, Double>> classMap;

    public GradeBook() {
        classMap = new HashMap<>();
    }

    public void addClass(String className) {
        classMap.put(className, new HashMap<>());
    }

    public void addGrade(String className, String studentName, double grade) {
        Map<String, Double> gradeMap = classMap.get(className);
        if (gradeMap != null) {
            gradeMap.put(studentName, grade);
        } else {
            System.out.println("Class not found.");
        }
    }

    public void displayGradeBook() {
        System.out.println("\nGrade Book:");

        for (Map.Entry<String, Map<String, Double>> classEntry : classMap.entrySet()) {
            String className = classEntry.getKey();
            Map<String, Double> gradeMap = classEntry.getValue();

            System.out.println("\nClass: " + className);
            System.out.println("Student\t\tGrade");

            for (Map.Entry<String, Double> gradeEntry : gradeMap.entrySet()) {
                String studentName = gradeEntry.getKey();
                double grade = gradeEntry.getValue();
                System.out.println(studentName + "\t\t" + grade);
            }

            double classAverage = calculateClassAverage(gradeMap);
            System.out.println("Class Average: " + classAverage);
        }
    }

    public void displayStudentSummary(String studentName) {
        System.out.println("\nStudent Summary for: " + studentName);

        for (Map.Entry<String, Map<String, Double>> classEntry : classMap.entrySet()) {
            String className = classEntry.getKey();
            Map<String, Double> gradeMap = classEntry.getValue();

            if (gradeMap.containsKey(studentName)) {
                double studentGrade = gradeMap.get(studentName);
                System.out.println(className + ": " + studentGrade);
            }
        }
    }

    private double calculateClassAverage(Map<String, Double> gradeMap) {
        double totalGrades = 0;
        for (double grade : gradeMap.values()) {
            totalGrades += grade;
        }

        return totalGrades / gradeMap.size();
    }
}
