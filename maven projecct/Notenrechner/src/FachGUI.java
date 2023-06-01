import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import all the things
public class FachGUI extends JFrame {
    // alle GUIs in diesem Projekt erweitern die Library JFrame
    
    
    //initialisieren von privaten Variablen zur Nutzung innerhalb von diesem Fenster"Objekt"
    private JTextField fachTextfeld;
    private Notenlist    mynotenList;
    private JTextArea FaecherTextArea;
    public FachGUI(Notenlist list){
        //importiert die "Backend-Database" in Form des Notenlist Objekts
        mynotenList=list;
    //definiert die Parameter des Fensters    genauso wie in GUI2
    setTitle("Fachuebersicht");
    setSize(400,600);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);

    //nutzt das gleiche Layout fuer das gesamte Fenster
    setLayout(new BorderLayout());
        //erstellt ein Panel mit Textfeld zur Fachauswahl
        JPanel interPanel =createinterPanel();
        add(interPanel, BorderLayout.NORTH);
        //erstellt eine Textarea in der die bereits angelegten Faecher aufgelistet werden
        FaecherTextArea = new JTextArea();
        add(new JScrollPane(FaecherTextArea),BorderLayout.CENTER);
        //befuellt die Fachliste mit allen bereits angelegten Faechern
        FaecherTextArea.setText(mynotenList.getBestandFaecher());
    


    }
    private JPanel createinterPanel(){

        // Konstuktor fuer das eingabePanel
        //nutzung des GridLayouts zur einfachen gleichmaessigen anordnung der einzelnen Elemente
        JPanel interPanel = new JPanel(new GridLayout( 2, 2));
        // erstellen von zwei Labels die nur Text darstellen
        JLabel wahlLabel = new JLabel("Fach auswaehlen");
        JLabel wahl2Label = new JLabel(":");
        //JTextfield wird zur Nutzereingabe genutzt
        fachTextfeld = new JTextField();
        interPanel.add(wahlLabel);
        interPanel.add(wahl2Label);
        interPanel.add(fachTextfeld);
        //Button zum Auswaehlen/erstellen des Fachs
        JButton wahlButton = new JButton("Go!");
        wahlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //buttonpress fuehrt nur eine Methode aus
                onButtonpress();
            }
        });
        interPanel.add(wahlButton);

        return interPanel;
    }
    private void onButtonpress(){
        //Methode die ausgefuehrt wird wenn der GO Button gepresst wurde
        
        String Fachname = fachTextfeld.getText();
                // prueft ob der Nutzer eine Eingabe gemacht hat
                if(!Fachname.isEmpty()){
                    //wenn eine Eingabe vorhanden ist wird die Funktion setCurrentFach des Notenlist Objekts ausgefuehrt ( erstellt oder waehlt ein Fach mit dem String Parameter aus)
                    mynotenList.setCurrentFach(Fachname);
                }
                //wenn keine Eingabe erfolgt ist wird ein Error Fenster geoeffnet
                else{
                    JOptionPane.showMessageDialog(this, "Bitte trage ein Fach ein!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                //resetten des Textfelds und ausgabe der neu angelegten Faecher(zeigt der einfachheit halber die ganze liste an)
        fachTextfeld.setText("");
        FaecherTextArea.setText(mynotenList.getBestandFaecher());
        
    }
    
}
