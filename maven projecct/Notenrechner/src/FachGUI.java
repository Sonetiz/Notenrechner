import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class FachGUI extends JFrame {
    
    
    private JTextField fachTextfeld;
    private Notenlist    mynotenList;
    private JTextArea FaecherTextArea;
    public FachGUI(Notenlist list){
        mynotenList=list;
    
    setTitle("Fachuebersicht");
    setSize(400,600);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());
    
        JPanel interPanel =createinterPanel();
        add(interPanel, BorderLayout.NORTH);
        FaecherTextArea = new JTextArea();
        add(new JScrollPane(FaecherTextArea),BorderLayout.CENTER);
        FaecherTextArea.setText(mynotenList.getBestandFaecher());
    


    }
    private JPanel createinterPanel(){


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
                onButtonpress();
            }
        });
        interPanel.add(wahlButton);

        return interPanel;
    }
    private void onButtonpress(){
        
        String Fachname = fachTextfeld.getText();
                if(!Fachname.isEmpty()){
                    mynotenList.setCurrentFach(Fachname);
                }
                else{
                    JOptionPane.showMessageDialog(this, "Bitte trage ein Fach ein!", "Error", JOptionPane.ERROR_MESSAGE);
                }
        fachTextfeld.setText("");
        FaecherTextArea.setText(mynotenList.getBestandFaecher());
    }
    
}
