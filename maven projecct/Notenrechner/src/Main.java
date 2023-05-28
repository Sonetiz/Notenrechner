import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GradeBookGUI gradeBookGUI = new GradeBookGUI();
                gradeBookGUI.setVisible(true);
            }
        });
    }
}
