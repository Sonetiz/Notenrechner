
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.border.Border;
//import all the things
public class TabelleGUI extends JFrame {
    // alle GUIs in diesem Projekt erweitern die Library JFrame
    
    
    //initialisieren von privaten Variablen zur Nutzung innerhalb von diesem Fenster"Objekt"
    
    private Notenlist   mynotenList;
    
    public TabelleGUI(Notenlist list){
        //importiert die "Backend-Database" in Form des Notenlist Objekts
        mynotenList=list;
    //definiert die Parameter des Fensters    genauso wie in GUI2
    setTitle("Notentabelle : "+mynotenList.getcurrentFach());
    setSize(400,600);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);

    //nutzt das gleiche Layout fuer das gesamte Fenster
    setLayout(new BorderLayout());
        //erstellt ein Panel mit Textfeld zur Fachauswahl
        JPanel Table =createTable();
        add(Table, BorderLayout.NORTH);
        //erstellt eine Textarea in der die bereits angelegten Faecher aufgelistet werden
        
    //updateTable();


    }
    private JPanel createTable(){
        Border blackline = BorderFactory.createLineBorder(Color.black);

        JPanel tablePanel=new JPanel();
       if(mynotenList.getTestliste()!=null){

        int rows=mynotenList.getSchuelerzahl()+1;
        int cols=mynotenList.getTestliste().length+7;
        
        
        if(rows!=0&&cols!=0){
        setLayout(new GridLayout(rows,cols));

        JLabel FachLabel= new JLabel(mynotenList.getcurrentFach());
        FachLabel.setBorder(blackline);
        add(FachLabel);
        String[] Testlistelocal=mynotenList.getTestliste();
        
        for(int i=0;i<Testlistelocal.length;i++){
            JLabel pruefLabel= new JLabel(Testlistelocal[i]);
            pruefLabel.setBorder(blackline);
            add (pruefLabel);
        }
        JLabel ZeugnisLabel=new JLabel("Zeugnissnote");
        ZeugnisLabel.setBorder(blackline);
        add (ZeugnisLabel);
        Map<String, Map<String, Double>> FachEintrag=mynotenList.getFachMap().get(mynotenList.getcurrentFach());
        
        for (Entry<String, Map<String, Double>> SchuelerEintrag  : FachEintrag.entrySet()) {
            String studentName = SchuelerEintrag.getKey();
            JLabel NamensLabel= new JLabel(studentName);
            NamensLabel.setBorder(blackline);
            add(NamensLabel);
            int studentaverage=0;
           
            for(int j =0;j<Testlistelocal.length;j++){
                if(mynotenList.getNote(mynotenList.getcurrentFach(),studentName,Testlistelocal[j])==null){
                    JOptionPane.showMessageDialog(this, "eine Note fehlt setzt die Note als 0", "Error", JOptionPane.ERROR_MESSAGE);
                    JLabel NotenLabel=new JLabel("0");
                NotenLabel.setBorder(blackline);
                add (NotenLabel);
                }
                else{
                JLabel NotenLabel=new JLabel(mynotenList.getNote(mynotenList.getcurrentFach(),studentName,Testlistelocal[j]).toString());
                NotenLabel.setBorder(blackline);
                add (NotenLabel);
                studentaverage+=mynotenList.getNote(mynotenList.getcurrentFach(),studentName,Testlistelocal[j]);
                }
            }
            int averageaverage=studentaverage/Testlistelocal.length+1;
            JLabel AverageLabel=new JLabel(String.valueOf(averageaverage));
            AverageLabel.setBorder(blackline);
            add(AverageLabel);
        }
        //revalidate();
        //repaint();
        }else{
            JOptionPane.showMessageDialog(this, "Keine Pruefung hinterlegt!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
        return tablePanel;

    }
   public void updateTable(){
        removeAll();
        
        JPanel tablePanel = createTable();
        add(tablePanel, BorderLayout.SOUTH);
        
    }
    
}
