import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;



public class GUI extends JFrame {
    private Notenlist notenListe;
    private JTextField fachTextfeld;
    private JTextField schuelerTextfeld;
    private JTextField notenTextfeld;
    private JTextArea NotenRechnerTextArea;

    public GUI() {
        //Konstruktor des GUIs
        notenListe = new Notenlist();

        //Ueberschrift
        setTitle("Noten Manager by JMS™");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        NotenRechnerTextArea = new JTextArea();
        add(new JScrollPane(NotenRechnerTextArea), BorderLayout.CENTER);
        //Ausgabebutton Notenuebersicht Fach
        JButton displayButton = new JButton("Fachzusammenfassung anzeigen");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zusammenfassungAnzeige();
            }
        });
        add(displayButton, BorderLayout.SOUTH);
    }

    private JPanel createInputPanel() {
        //Eingabefelder Anordnugn
        JPanel inputPanel = new JPanel(new GridLayout(2, 3));
        //erstellen der einzelnen Text&Eingabefelder
        JLabel fachLabel = new JLabel("Fach:");
        fachTextfeld = new JTextField();
        JLabel schuelerLabel = new JLabel("Schueler:");
        schuelerTextfeld = new JTextField();
        JLabel notenLabel = new JLabel("Note:");
        notenTextfeld = new JTextField();
        
        //felder in JPanel hinzufuegen
        inputPanel.add(fachLabel);
        inputPanel.add(fachTextfeld);
        inputPanel.add(schuelerLabel);
        inputPanel.add(schuelerTextfeld);
        inputPanel.add(notenLabel);
        inputPanel.add(notenTextfeld);
        
        //Eventlistener fuer den Eintrage button
        JButton addButton = new JButton("Note eintragen");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNote();
            }
        });
        inputPanel.add(addButton);

        return inputPanel;
    }

    private void addNote() {
        //Eintragen der Eingabewerte in die Hashmap

        //Eingegebene Werte in Variablen Speichern
        String FachName = fachTextfeld.getText();
        String SchuelerName = schuelerTextfeld.getText();
        String Notenwert = notenTextfeld.getText();

        // wenn Alle Werte eingetragen wurden Daten im Backend in die Notenlisten HashMap eintragen
        if (!FachName.isEmpty() && !SchuelerName.isEmpty() && !Notenwert.isEmpty()) {
            double grade = Double.parseDouble(Notenwert);
            notenListe.addNote(FachName, SchuelerName, grade);
            fachTextfeld.setText("");
            schuelerTextfeld.setText("");
            notenTextfeld.setText("");

            //Error Screen wenn Werte fehlen
        } else {
            JOptionPane.showMessageDialog(this, "Alle Felder muessen ausgefuellt sein", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void zusammenfassungAnzeige() {
        //Anzeige von Noten und Notenschnitt des Fachs
        NotenRechnerTextArea.setText("");
        //Stringbuilder um aus Verschiedenen Datenformen einen String zur Ausgabe zu generieren
        StringBuilder Ausgabetext = new StringBuilder();
        //Itteriert durch alle Faecher in der HashMap und setzt fachEintrag als lokale Variable fuer die Eintraege in der Fach-Hashmap im notenListe Objekt
        for (Map.Entry<String, Map<String, Double>> fachEintrag : notenListe.getFachMap().entrySet()) {
            //lokale Variable Fachname fuer den Key des Wertepaars in der Hashmap ( immer Fach ist der Key und der Wert ist eine Hashmap mit schuelername als key und note als Wert)
            String Fachname = fachEintrag.getKey();
            Map<String, Double> NotenMap = fachEintrag.getValue();
    
            Ausgabetext.append("\nFach: ").append(Fachname).append("\n");
            Ausgabetext.append("Schueler\t\tNote\n");
    
            for (Map.Entry<String, Double> NotenEingabe : NotenMap.entrySet()) {
                String studentName = NotenEingabe.getKey();
                double note = NotenEingabe.getValue();
                Ausgabetext.append(studentName).append("\t\t").append(note).append("\n");
            }
    
            double fachSchnitt = notenListe.calculateNotenschnitt(NotenMap);
            Ausgabetext.append("Notenschnitt: ").append(fachSchnitt).append("\n");
        }
    
        NotenRechnerTextArea.setText(Ausgabetext.toString());
    }
    
}
