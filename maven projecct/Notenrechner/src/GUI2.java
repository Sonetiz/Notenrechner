import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
//import all needed libraries





public class GUI2 extends JFrame {
    //initialisiert eine Variable vom Typ Objekt der Klasse Notenlist
    private Notenlist notenListe;
    
    
    
    public GUI2() {
        //Konstruktor des GUIs
        //erstellt ein neues Notenlist Objekt mit dem Namen  notenListe nach dem Konstruktor der Notenlist Klasse
        notenListe = new Notenlist();

        //Ueberschrift
        setTitle("Noten Manager by Simon & Maximilian & Jonas");
        //legt Parameter des Anzeigefensters fest:
        //Groesse
        setSize(600, 200);
        //setzt die Kondition das wenn das Fenster geschlossen wird JFrame beendet wird
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //resettet das relative Koordinatensystem
        setLocationRelativeTo(null);
        //bestimmt die Art des Layouts nach dem die einzelnen Panels im Fenster angeordnet werden
        setLayout(new BorderLayout());
        
        //befuellen des Anzeigefensters mit JPanel Objekte die in den folgenden methoden definiert werden
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);
        

        //NotenRechnerTextArea = new JTextArea();
        //add(new JScrollPane(NotenRechnerTextArea), BorderLayout.CENTER);
      
        
    }
   

    private JPanel createInputPanel() {
        //Anordnung Buttons im Header nach einem anderen Layout wie im gesamten Fenster
        JPanel inputPanel = new JPanel(new GridLayout( 1, 3));
        //erstellen eines neuen Buttons mit Anzeigetext == dem aktuell ausgewaehlten Fach ( initialisiert mit "bitte Fach auswaehlen")
        JButton fachButton = new JButton(notenListe.getcurrentFach());
        //Button benoetigen jeweils einen ActionListener um die Nutzereingaben (in diesem Fall Mausklicks abzufangen)
        fachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //wenn der Button gedrueckt wird eine methode fachFenster() ausgefuehrt
                //oeffnet ein neues Fenster zur Auswahl des Fachs
                fachFenster();
            }
        });
        //fuegt den erstellten button dem Layout hinzu
        inputPanel.add(fachButton);

       //erstellt neuen Button zum oeffnen des Test-eintrags-Fenster
        JButton testButton = new JButton("Leistung Eintragen");
        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                testFenster();
            }
        });
        inputPanel.add(testButton);
        //erstellt neuen Button zum oeffnen der Tabellen ansicht
        JButton updateButton = new JButton("Tabelle Öffnen");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTable();

            }
        });
        inputPanel.add(updateButton);

        return inputPanel;
    }
    private void showTable(){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                TabelleGUI Tabellewindow = new TabelleGUI(notenListe);
                Tabellewindow.setVisible(true);
            }
        });
    }
    
    private void fachFenster(){
        //Nutzt Swing zum erstellen eines neuen Fensters nach dem Konstruktor FachGUI
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                FachGUI Fachwindow = new FachGUI(notenListe);
                Fachwindow.setVisible(true);
            }
        });

    }
    private void testFenster(){
        //Nutzt Swing zum erstellen eines neuen Fensters nach dem Konstruktor TestGUI
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                TestGUI Testwindow = new TestGUI(notenListe);
                Testwindow.setVisible(true);
            }
        });

    }
    




    
   

    
    

    
    
}
