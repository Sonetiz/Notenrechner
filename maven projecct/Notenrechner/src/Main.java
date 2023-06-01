import javax.swing.*;


public class Main {
    //Initialisierung von Swing und erstellung eines neuen Anzeigefensters mit dem Konstuktor der GUI2 Methode
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //erstellt anhand des Konstruktors der Klasse GUI2 ein neues Objekt mit dem Namen Anzeigefenster
                GUI2 Anzeigefenster = new GUI2();
                //Anzeigefenster wird Sichtbar gemacht
                Anzeigefenster.setVisible(true);
                //Anzeigefenster.updateTable();
            }
        });
    }
}
