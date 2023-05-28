import javax.swing.*;


public class Main {
    //Initialisierung von Swin und erstellung eines neuen Anzeigefensters mit dem Konstuktor der GUI Methode
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUI Anzeigefenster = new GUI();
                Anzeigefenster.setVisible(true);
            }
        });
    }
}
