import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Notenlist {
    private Map<String, Map<String, Map<String, Double>>> FachMap;
    private String currentFach="Fach erstellen";
    private Map<String,ArrayList<String>> Testliste;
    public Notenlist() {
        FachMap = new HashMap<>();
        Testliste= new HashMap<>();
    }

    public void addFach(String Fachname) {
        FachMap.put(Fachname, new HashMap<>());
        
        Testliste.put(Fachname,new ArrayList<String>());
    }
    

    public void addNote(String Fachname, String Schuelername, String Pruefungsname, double Note) {
        Map<String, Map<String, Double>> SchuelerMap = FachMap.get(Fachname);
        if (SchuelerMap == null) {
            SchuelerMap = new HashMap<>();
            FachMap.put(Fachname, SchuelerMap);
        }
        Map<String, Double> pruefMap = SchuelerMap.get(Schuelername);
        if(pruefMap==null){
            pruefMap= new HashMap<>();
            SchuelerMap.put(Schuelername, pruefMap);
        }
        pruefMap.put(Pruefungsname, Note);

        if(!Testliste.get(currentFach).contains(Pruefungsname)){
            Testliste.get(currentFach).add(Pruefungsname);
        }
    }
    public Double getNote(String Fachname,String Schuelername,String Pruefungsname){
        Map<String,Map<String,Double>> SchuelerMap = FachMap.get(Fachname);
        Map<String,Double>pruefMap=SchuelerMap.get(Schuelername);
        if(pruefMap!=null){
            return pruefMap.get(Schuelername);
            
        }
        else{
            return 0.0;
        }
    }
    public String getcurrentFach(){
        return currentFach;
    }

    
    public String getBestandFaecher(){
        StringBuilder AusgabeText= new StringBuilder();
        for (Map.Entry<String,Map<String,Map<String,Double>>> facheintrag:this.getFachMap().entrySet()){
            String Bestandsfaecher =facheintrag.getKey();
            AusgabeText.append(Bestandsfaecher).append("\n");
            
        }
        return AusgabeText.toString();
    }
    public void setCurrentFach(String selected){
        currentFach=selected;
        for (Map.Entry<String,Map<String,Map<String,Double>>> facheintrag:this.getFachMap().entrySet()){
            String Fachname =facheintrag.getKey();
            if (Fachname==selected){
                return;
            }
            
            
            
        }
        this.addFach(selected);
    }
   
    public String[] getTestliste(){
        if(Testliste.get(currentFach)==null){
            return null;
        }else{
        int size=Testliste.get(currentFach).size();
        String[] temp =new String[size];
        for(int i=0;i<size;i++){
            temp[i]=Testliste.get(currentFach).get(i);

        }
        return temp;
    }
       
    }
    public int getSchuelerzahl(){
        Map<String, Map<String, Double>> SchuelerMap = FachMap.get(currentFach);
        if(SchuelerMap==null){
        return 0;
    }else{
        return SchuelerMap.size();
    }
    }
    

    

    public double calculateNotenschnitt(Map<String, Double> NotenMap) {
        double gesamtNote = 0;
        for (double Note : NotenMap.values()) {
            gesamtNote += Note;
        }

        return gesamtNote / NotenMap.size();
    }
    public Map<String, Map<String, Map<String, Double>>> getFachMap() {
        return FachMap;
    }
    


    //public void displayNotenliste() {
    //    System.out.println("\nNotenliste:");
    //    
    //    for (Map.Entry<String, Map<String, Double>> fachEintrag : FachMap.entrySet()) {
    //        String Fachname = fachEintrag.getKey();
    //        Map<String, Double> NotenMap = fachEintrag.getValue();
    //    
    //        System.out.println("\nFach: " + Fachname);
    //        System.out.println("Schueler\t\tNote");
    //    
    //       for (Map.Entry<String, Double> notenEintrag : NotenMap.entrySet()) {
    //            String studentName = notenEintrag.getKey();
    //            double grade = notenEintrag.getValue();
    //            System.out.println(studentName + "\t\t" + grade);
    //        }
    //    
    //        double classAverage = calculateNotenschnitt(NotenMap);
    //        System.out.println("Notenschnitt: " + classAverage);
    //    }
    //}

    //public void displayShueleruebersicht(String SchuelerName) {
    //    System.out.println("\nNotenuebersich fuer: " + SchuelerName);
    //
    //    for (Map.Entry<String, Map<String, Double>> fachEintrag : FachMap.entrySet()) {
    //        String Fachname = fachEintrag.getKey();
    //        Map<String, Double> NotenMap = fachEintrag.getValue();
    //    
    //        if (NotenMap.containsKey(SchuelerName)) {
    //            double Note = NotenMap.get(SchuelerName);
    //            System.out.println(Fachname + ": " + Note);
    //        }
    //    }
    //}
    
}
