import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;





public class GUI2 extends JFrame {
    public Notenlist notenListe;
    
   

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
    public Object getNotenlist(){
        return notenListe;
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




    
   

    
    

    
    
}
