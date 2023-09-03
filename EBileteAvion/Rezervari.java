package EBileteAvion;


import java.io.File;
import java.io.IOException;
import java.util.*;
public class Rezervari {
	String nume,prenume,parola;  // creare lista rezervari
	File fisier1=new File("src//EBileteAvion//Rezervari.txt");
	 ArrayList<Zbor> Rezervari=new ArrayList<Zbor>();
      public ArrayList<Zbor> getRezervari() {
	
    		 try {
    			   Scanner f1=new Scanner(fisier1);
    			   while(f1.hasNextLine()==true) {
    				   Zbor  cursa=new Zbor();
    				   String sir1=f1.nextLine();
    				   String[] sir=new String[30];
    				   sir=sir1.split(",");
    				   
    				   cursa.orasePlecare=sir[0];
    				  cursa.oraseDestinatie=sir[1];
    				   cursa.dataPleacare=sir[2];
    				   cursa.dataIntoarcere=sir[3];
    				   cursa.cod=Integer.parseInt(sir[5]);
    			   	Rezervari.add(cursa);
    			   }
    		   }
    		 catch(IOException a) {
    			   System.out.println("Eroare Rezervari");
    		 }
    		
    		return Rezervari;
    	     }
}