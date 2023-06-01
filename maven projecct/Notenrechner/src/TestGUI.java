import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import required libraries
public class TestGUI extends JFrame {

    //initialisieren von im Objekt genutzten Variablen
    private Notenlist notenListe;
    private JTextField testnTextfeld;
    private JTextField schuelernTextfeld;
    private JTextField notenTextfeld;
    private JTextArea Noten1TextArea;
    private String Testname;
    private StringBuilder Liste;
    private String textfeldFach;
    private String textfeldSchueler;
    private String textfeldPruef;
    private String textfeldNote;
    private boolean testbuttonIspressed;
   
    public TestGUI(Notenlist list){

        //stellt das Notenlist Objekt in diesem Objekt als notenListe zur verfuegung

        notenListe = list;
        //testbuttonIspressed wird genutzt um sicherzustellen das der Test fuer den eine Note eingetragen werden soll bereits existiert
        testbuttonIspressed=false;
        //erstellt einen String welcher zur anzeige der im aktuellen Test eingetragenen Noten genutzt wird
        Liste =new StringBuilder();
        //bestimme die Parameter des Fensters
        setTitle("Test Eintragen");
        setSize(600,600);
        //weil das Gesamt Programm nicht beendet werden soll wenn dieses Fenster geschlossen wird nutzen wir hier Dispose on Close um nur das hier konstruierte JFrame Objekt zu verwerfen und nicht JFrame ganz zu beenden
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        //erstellen von einem Panel mit eingabeFeldern und Buttons
        JPanel interPanel=createInterPanel();
        add(interPanel, BorderLayout.NORTH);
        //TextArea zur Anzeige der aktuell eingetragenen Noten
        Noten1TextArea = new JTextArea();
        add(new JScrollPane(Noten1TextArea), BorderLayout.CENTER);


    }

    private JPanel createInterPanel(){

        //erstellen von eingabefeldern und Buttons
        JPanel interPanel = new JPanel(new GridLayout( 3, 3));
        JLabel TestLabel = new JLabel("Testname:");
        JLabel SchuelerLabel = new JLabel("Schuelername");
        JLabel NotenLabel =new JLabel("Note");
        JButton TestinitButton = new JButton("Test anlegen");
        testnTextfeld=new JTextField();
        schuelernTextfeld=new JTextField();
        notenTextfeld=new JTextField();
        TestinitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //druecken der Buttons in diesem Fenster called immer die Methode onButtonpress und die eigentliche Funktion des Buttons
                onButtonpress();
                Testnameselect();
                
            }
        });
        JButton DataentryButton = new JButton("Note Eintragen");
        DataentryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onButtonpress();
                Dataentry();
                
            }
        });
        JButton SaveButton = new JButton("Test abschliessen");
        SaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onButtonpress();
                Abschluss();
                
            }
        });
        //adden der erstellten Buttons
    interPanel.add(TestLabel);
    interPanel.add(testnTextfeld);
    interPanel.add(TestinitButton);
    interPanel.add(SchuelerLabel);
    interPanel.add(NotenLabel);
    interPanel.add(DataentryButton);
    interPanel.add(schuelernTextfeld);
    interPanel.add(notenTextfeld);
    interPanel.add(SaveButton);
    //gibt das erstellte Panel zurueck ans JFrame
    return interPanel;
    }
    private void onButtonpress(){
        //auslesen der Textfelder
        textfeldFach=notenListe.getcurrentFach();
        textfeldPruef=testnTextfeld.getText();
        textfeldSchueler=schuelernTextfeld.getText();
        textfeldNote=notenTextfeld.getText();
        //ueberprueft ob ein Fach ausgewaehlt ist in dem dieser Test angelegt werden soll und erstellt ein error feld wenn das nicht der Fall ist
        if(textfeldFach=="Fach erstellen"||textfeldFach==null){
            JOptionPane.showMessageDialog(this, "Bitte erstelle zuerst ein Fach ein!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void Testnameselect(){
        //prueft ob eine Eingabe gemacht wurde und zeigt ggf einen Errorscreen
        if(textfeldPruef.isEmpty()){
            JOptionPane.showMessageDialog(this, "Bitte trage einen Namen fuer die Leistungsueberpruefung ein!", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            //wird der Test der Testliste hinzugefuegt und im Textfeld der bereits eingetragenen Noten als ueberschrift notiert
        Testname= testnTextfeld.getText();
        Liste.append(Testname).append("\n");
        Noten1TextArea.setText(Liste.toString());
        
        testbuttonIspressed=true;
    }}
    private void Dataentry(){
       //prueft die Eingaben auf Validitaet
        if(!textfeldSchueler.isEmpty()&&!textfeldNote.isEmpty()&&!textfeldPruef.isEmpty()&&testbuttonIspressed){
            //traegt die eingegebenen Daten im notenListe Objekt (Datenbasisobjekt )ein

            notenListe.addNote(notenListe.getcurrentFach(), schuelernTextfeld.getText(), Testname, Double.parseDouble(textfeldNote));
        //notenListe.addNote(notenListe.getcurrentFach(),schuelernTextfeld.getText(),Testname,Double.parseDouble(notenTextfeld.getText()));
        Liste.append(schuelernTextfeld.getText()).append("    ").append(notenTextfeld.getText()).append("\n");
        //Stringbuilder Text wird um Schuelername und Note erweitert und ausgegeben
        Noten1TextArea.setText(Liste.toString());
        schuelernTextfeld.setText("");
        notenTextfeld.setText("");
        //resetten der Textfelder
    }else{
        // wenn eine der Eingaben einen Nichterwarteten Wert beinhaltet wird ein Errorfeld erstellt
        JOptionPane.showMessageDialog(this, "Bitte trage den Schuelernamen, den Pruefungsnamen und die Note ein!", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    private void Abschluss(){
        //schliesst das Fenster
        
        this.setVisible(false);
    }
}
