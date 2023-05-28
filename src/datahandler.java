package randomshieeet;
import java.io.*;
import java.util.*;

public class datahandler {

	public static void main(String[] args) {
//erstellen des Namens-Stack
		//push zum hinzufügen 
		//Name.get(iterator) zum aufrufen
		
		Stack<String> Name = new Stack<String>();
		
		
		//erstelle 2D ArrayList
		//1. Dimension = ID von Note( 1 HÜ, 2 EPO,3 Klassenarbeit)
		ArrayList<ArrayList<Integer>> Fach1 =new ArrayList<>();
		//2. Dimension = Notenwert
		// ausgabe Note --> Fach.get(schülerid).get(NotenID)
		Fach1.add(new ArrayList());
		
	
		Fach1.get(0).add(14);
		
		
		
		System.out.println("Welche Fächer sollen angelegt werden?");
		System.out.println("beende mit X");
		
		while(true) {
			Scanner scanner = new Scanner(System.in);
			String temp =scanner.next();
			if(temp=="X") {
				break;
			}else {
				ArrayList<ArrayList<Integer>> temp =new ArrayList<>();
			}
		}
		
		
		
		
		System.out.println(Fach1.get(0).get(0));
		
		
		
		
		Name.push("Anita Dickmann");
		Name.push("Halber Hans");
		Iterator<String> itr = Name.iterator();
		System.out.println(itr.next());
		System.out.println(Name.get(1));
		
	}

}
