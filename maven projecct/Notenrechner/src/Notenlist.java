import java.util.HashMap;
import java.util.Map;

public class Notenlist {
    private Map<String, Map<String, Double>> FachMap;

    public Notenlist() {
        FachMap = new HashMap<>();
    }

    public void addFach(String Fachname) {
        FachMap.put(Fachname, new HashMap<>());
    }

    public void addNote(String Fachname, String Schuelername, double Note) {
        Map<String, Double> NotenMap = FachMap.get(Fachname);
        if (NotenMap == null) {
            NotenMap = new HashMap<>();
            FachMap.put(Fachname, NotenMap);
        }
    
        NotenMap.put(Schuelername, Note);
    }
    

    public void displayNotenliste() {
        System.out.println("\nNotenliste:");

        for (Map.Entry<String, Map<String, Double>> fachEintrag : FachMap.entrySet()) {
            String Fachname = fachEintrag.getKey();
            Map<String, Double> NotenMap = fachEintrag.getValue();

            System.out.println("\nFach: " + Fachname);
            System.out.println("Schueler\t\tNote");

            for (Map.Entry<String, Double> notenEintrag : NotenMap.entrySet()) {
                String studentName = notenEintrag.getKey();
                double grade = notenEintrag.getValue();
                System.out.println(studentName + "\t\t" + grade);
            }

            double classAverage = calculateNotenschnitt(NotenMap);
            System.out.println("Notenschnitt: " + classAverage);
        }
    }

    public void displayShueleruebersicht(String SchuelerName) {
        System.out.println("\nNotenuebersich fuer: " + SchuelerName);

        for (Map.Entry<String, Map<String, Double>> fachEintrag : FachMap.entrySet()) {
            String Fachname = fachEintrag.getKey();
            Map<String, Double> NotenMap = fachEintrag.getValue();

            if (NotenMap.containsKey(SchuelerName)) {
                double Note = NotenMap.get(SchuelerName);
                System.out.println(Fachname + ": " + Note);
            }
        }
    }

    public double calculateNotenschnitt(Map<String, Double> NotenMap) {
        double gesamtNote = 0;
        for (double Note : NotenMap.values()) {
            gesamtNote += Note;
        }

        return gesamtNote / NotenMap.size();
    }
    public Map<String, Map<String, Double>> getFachMap() {
        return FachMap;
    }
    
}
