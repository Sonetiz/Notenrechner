import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestGUI extends JFrame {
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
        notenListe = list;
        testbuttonIspressed=false;
        Liste =new StringBuilder();
        setTitle("Test Eintragen");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JPanel interPanel=createInterPanel();
        add(interPanel, BorderLayout.NORTH);

        Noten1TextArea = new JTextArea();
        add(new JScrollPane(Noten1TextArea), BorderLayout.CENTER);


    }

    private JPanel createInterPanel(){

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

    interPanel.add(TestLabel);
    interPanel.add(testnTextfeld);
    interPanel.add(TestinitButton);
    interPanel.add(SchuelerLabel);
    interPanel.add(NotenLabel);
    interPanel.add(DataentryButton);
    interPanel.add(schuelernTextfeld);
    interPanel.add(notenTextfeld);
    interPanel.add(SaveButton);
    return interPanel;
    }
    private void onButtonpress(){
        textfeldFach=notenListe.getcurrentFach();
        textfeldPruef=testnTextfeld.getText();
        textfeldSchueler=schuelernTextfeld.getText();
        textfeldNote=notenTextfeld.getText();
        if(textfeldFach=="Fach erstellen"||textfeldFach==null){
            JOptionPane.showMessageDialog(this, "Bitte erstelle zuerst ein Fach ein!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void Testnameselect(){
        if(textfeldPruef.isEmpty()){
            JOptionPane.showMessageDialog(this, "Bitte trage einen Namen fuer die Leistungsueberpruefung ein!", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
        Testname= testnTextfeld.getText();
        Liste.append(Testname).append("\n");
        Noten1TextArea.setText(Liste.toString());
        notenListe.addTest(Testname);
        testbuttonIspressed=true;
    }}
    private void Dataentry(){
       
        if(!textfeldSchueler.isEmpty()&&!textfeldNote.isEmpty()&&!textfeldPruef.isEmpty()&&testbuttonIspressed){
        notenListe.addNote(notenListe.getcurrentFach(),schuelernTextfeld.getText(),Testname,Double.parseDouble(notenTextfeld.getText()));
        Liste.append(schuelernTextfeld.getText()).append("    ").append(notenTextfeld.getText()).append("\n");
        Noten1TextArea.setText(Liste.toString());
        schuelernTextfeld.setText("");
        notenTextfeld.setText("");
    }else{
        JOptionPane.showMessageDialog(this, "Bitte trage den Schuelernamen, den Pruefungsnamen und die Note ein!", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    private void Abschluss(){
        //Anzeigefenster.updateTable();
        this.setVisible(false);
    }
}
