import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Map.Entry;




public class GUI2 extends JFrame {
    private Notenlist notenListe;
    private JTextField fachTextfeld;
    private JTextField schuelerTextfeld;
    private JTextField pruefungsfeld;
    private JTextField notenTextfeld;
    private JTextArea NotenRechnerTextArea;
   

    public GUI2() {
        //Konstruktor des GUIs
        notenListe = new Notenlist();

        //Ueberschrift
        setTitle("Noten Manager by Simon & Maximilian & Jonas");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        

        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);
        JPanel tablePanel = createTable();
        add(tablePanel, BorderLayout.SOUTH);

        //NotenRechnerTextArea = new JTextArea();
        //add(new JScrollPane(NotenRechnerTextArea), BorderLayout.CENTER);
      
        
    }

    private JPanel createInputPanel() {
        //Anordnung Buttons im Header
        JPanel inputPanel = new JPanel(new GridLayout( 1, 3));
        //erstellen der einzelnen Buttons
        JButton fachButton = new JButton(notenListe.getcurrentFach());
        fachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fachFenster();
            }
        });
        inputPanel.add(fachButton);

       
        JButton testButton = new JButton("Leistung Eintragen");
        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                testFenster();
            }
        });
        inputPanel.add(testButton);
        JButton updateButton = new JButton("Tabelle Refreshen");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable();
            }
        });
        inputPanel.add(updateButton);

        return inputPanel;
    }
    private void fachFenster(){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                FachGUI Fachwindow = new FachGUI(notenListe);
                Fachwindow.setVisible(true);
            }
        });

    }
    private void testFenster(){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                TestGUI Testwindow = new TestGUI(notenListe);
                Testwindow.setVisible(true);
            }
        });

    }
    private JPanel createTable(){
        JPanel tablePanel=new JPanel();
       if(notenListe.getTestliste()!=null){

        int rows=notenListe.getSchuelerzahl()+1;
        int cols=notenListe.getTestliste().length+2;
        
        if(rows!=0&&cols!=0){
        setLayout(new GridLayout(rows,cols));

        JLabel FachLabel= new JLabel(notenListe.getcurrentFach());
        add(FachLabel);
        String[] Testlistelocal=notenListe.getTestliste();
        
        for(int i=0;i<Testlistelocal.length;i++){
            JLabel pruefLabel= new JLabel(Testlistelocal[i]);
            add (pruefLabel);
        }
        JLabel ZeugnisLabel=new JLabel("Zeugnissnote");
        add (ZeugnisLabel);
        for (Map.Entry<String, Map<String, Map<String, Double>>> SchuelerEintrag : notenListe.getFachMap().entrySet()) {
            String studentName = SchuelerEintrag.getKey();
            JLabel NamensLabel= new JLabel(studentName);
            add(NamensLabel);
            int studentaverage=0;
            for(int j =0;j<Testlistelocal.length;j++){
                JLabel NotenLabel=new JLabel(notenListe.getNote(notenListe.getcurrentFach(),studentName,Testlistelocal[j]).toString());
                add (NotenLabel);
                studentaverage+=notenListe.getNote(notenListe.getcurrentFach(),studentName,Testlistelocal[j]);
            }
            int averageaverage=studentaverage/Testlistelocal.length+1;
            JLabel AverageLabel=new JLabel(String.valueOf(averageaverage));
            add(AverageLabel);
        }
        revalidate();
        repaint();
        }else{
            JOptionPane.showMessageDialog(this, "Keine Pruefung hinterlegt!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
        return tablePanel;

    }
   public void updateTable(){
        removeAll();
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);
        JPanel tablePanel = createTable();
        add(tablePanel, BorderLayout.SOUTH);
        
    }




    
   

    
    private void addNote() {
        //Eintragen der Eingabewerte in die Hashmap

        //Eingegebene Werte in Variablen Speichern
        String FachName = fachTextfeld.getText();
        String SchuelerName = schuelerTextfeld.getText();
        String Pruefungsname = pruefungsfeld.getText();
        String Notenwert = notenTextfeld.getText();

        // wenn Alle Werte eingetragen wurden Daten im Backend in die Notenlisten HashMap eintragen
        if (!FachName.isEmpty() && !SchuelerName.isEmpty() && !Pruefungsname.isEmpty() && !Notenwert.isEmpty()) {
            double Note = Double.parseDouble(Notenwert);
            notenListe.addNote(FachName, SchuelerName,Pruefungsname, Note);
            fachTextfeld.setText("");
            schuelerTextfeld.setText("");
            pruefungsfeld.setText("");
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
        for (Map.Entry<String, Map<String, Map<String, Double>>> fachEintrag : notenListe.getFachMap().entrySet()) {
            //lokale Variable Fachname fuer den Key des Wertepaars in der Hashmap ( immer Fach ist der Key und der Wert ist eine Hashmap mit schuelername als key und note als Wert)
            String Fachname = fachEintrag.getKey();
            Map<String, Map<String, Double>> PruefMap = fachEintrag.getValue();
            //Ausgabetext um um den errechneten Fachnamen und festen
            Ausgabetext.append("\nFach: ").append(Fachname).append("\n");
            Ausgabetext.append("Schueler\t\tNote\n");
            //itteriert durch die 2. Dimension Hashmap (key=Schuelername value=Note)und fuegt die Ausgabewerte dem Ausgabestring an
            for (Map.Entry<String, Map<String, Double>> Pruefungseintrag : PruefMap.entrySet()) {
                String studentName = Pruefungseintrag.getKey();
                Map<String,Double> NotenMap = Pruefungseintrag.getValue();
                for(Map.Entry<String,Double>Noteneintrag : NotenMap.entrySet()){

                    double note = Noteneintrag.getValue();
                    Ausgabetext.append(studentName).append("\t\t").append(note).append("\n");
                }
                // called die backend funktion zum errechnen des Notenschnitts und fuegt den Wert dem String an
                double fachSchnitt = notenListe.calculateNotenschnitt(NotenMap);
                Ausgabetext.append("Notenschnitt: ").append(fachSchnitt).append("\n");
            }
        }
        //Gesamtes String element wird in die GUI gedruckt
        NotenRechnerTextArea.setText(Ausgabetext.toString());
    }
    
}
