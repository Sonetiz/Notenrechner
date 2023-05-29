import java.util.HashMap;
import java.util.Map;

public class Notenlist {
    private Map<String, Map<String, Map<String, Double>>> FachMap;

    public Notenlist() {
        FachMap = new HashMap<>();
    }

    public void addFach(String Fachname) {
        FachMap.put(Fachname, new HashMap<>());
    }
    //public void addSchueler(String Schuelername,String Fachname){
    //    Map<String  
    //}

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
    
        SchuelerMap.put(Schuelername, new HashMap<>());
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
    //public Map<String, Map<String, Map<String, Souble>>> getPruefMap(){
    //    return SchuelerMap;
    //}


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
