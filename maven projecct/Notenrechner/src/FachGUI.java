import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
public class FachGUI extends JFrame {
    private GUI2 notenListe;
    
    private JTextField fachTextfeld;
   
    private JTextArea NotenRechnerTextArea;
    public FachGUI(){
    setTitle("Fachuebersicht");
    setSize(400,600);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());
    JPanel interPanel = new JPanel(new GridLayout( 2, 2));
    JLabel wahlLabel = new JLabel("Fach auswaehlen");
    JLabel wahl2Label = new JLabel(":");
    fachTextfeld = new JTextField();
    interPanel.add(wahlLabel);
    interPanel.add(wahl2Label);
    interPanel.add(fachTextfeld);
    JButton wahlButton = new JButton("Go!");
        wahlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Fachname = fachTextfeld.getText();
                if(!Fachname.isEmpty()){
                    notenListe.setCurrentFach(Fachname);
                }
            }
        });
        interPanel.add(wahlButton);



    }
    
}
