import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.HashMap;


public class GradeBookGUI extends JFrame {
    private GradeBook gradeBook;
    private JTextField classNameField;
    private JTextField studentNameField;
    private JTextField gradeField;
    private JTextArea gradeBookTextArea;

    public GradeBookGUI() {
        gradeBook = new GradeBook();

        setTitle("Grade Book");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        gradeBookTextArea = new JTextArea();
        add(new JScrollPane(gradeBookTextArea), BorderLayout.CENTER);

        JButton displayButton = new JButton("Display Grade Book");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayGradeBook();
            }
        });
        add(displayButton, BorderLayout.SOUTH);
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));

        JLabel classNameLabel = new JLabel("Class Name:");
        classNameField = new JTextField();
        JLabel studentNameLabel = new JLabel("Student Name:");
        studentNameField = new JTextField();
        JLabel gradeLabel = new JLabel("Grade:");
        gradeField = new JTextField();

        inputPanel.add(classNameLabel);
        inputPanel.add(classNameField);
        inputPanel.add(studentNameLabel);
        inputPanel.add(studentNameField);
        inputPanel.add(gradeLabel);
        inputPanel.add(gradeField);

        JButton addButton = new JButton("Add Grade");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addGrade();
            }
        });
        inputPanel.add(addButton);

        return inputPanel;
    }

    private void addGrade() {
        String className = classNameField.getText();
        String studentName = studentNameField.getText();
        String gradeText = gradeField.getText();

        if (!className.isEmpty() && !studentName.isEmpty() && !gradeText.isEmpty()) {
            double grade = Double.parseDouble(gradeText);
            gradeBook.addGrade(className, studentName, grade);
            classNameField.setText("");
            studentNameField.setText("");
            gradeField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please enter all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayGradeBook() {
        gradeBookTextArea.setText("");
    
        StringBuilder gradeBookText = new StringBuilder();
    
        for (Map.Entry<String, Map<String, Double>> classEntry : gradeBook.getClassMap().entrySet()) {
            String className = classEntry.getKey();
            Map<String, Double> gradeMap = classEntry.getValue();
    
            gradeBookText.append("\nClass: ").append(className).append("\n");
            gradeBookText.append("Student\t\tGrade\n");
    
            for (Map.Entry<String, Double> gradeEntry : gradeMap.entrySet()) {
                String studentName = gradeEntry.getKey();
                double grade = gradeEntry.getValue();
                gradeBookText.append(studentName).append("\t\t").append(grade).append("\n");
            }
    
            double classAverage = gradeBook.calculateClassAverage(gradeMap);
            gradeBookText.append("Class Average: ").append(classAverage).append("\n");
        }
    
        gradeBookTextArea.setText(gradeBookText.toString());
    }
    
}
