package EBileteAvion;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class InformatiiZboruri {

	 private static File zboruri = new File("src\\EBileteAvion\\Zboruri.txt");
	 private static File utilizatori = new File("src\\EBileteAvion\\Utilizatori.txt");
	 private static File carduri = new File("src\\EBileteAvion\\Carduri.txt");

public static  ArrayList<Zbor> getListaZboruriDisponibile(){  //Creare lista zboruri
	  
	ArrayList<Zbor> listaZboruri = new ArrayList<Zbor>();
	int nrLinie = 1;
	
	try (Scanner f1 = new Scanner(zboruri)) {
		   while(f1.hasNextLine()) {
			   
			   String detalii = f1.nextLine();
			   String[] separator = detalii.split("/");
			   Zbor  zbor = new Zbor();
			   
			   if(nrLinie == 1)
			   {
				   nrLinie = 0;
				   continue;
			   }
			   
			   if(Boolean.parseBoolean(separator[11]) == true)
			   {
				   zbor.setOrasPlecare(separator[0]);
				   zbor.setOrasDestinatie(separator[1]);
				   zbor.setDataPlecare(separator[2]);
				   zbor.setDataIntoarcere(separator[3]);
				   zbor.setPiloti(separator[4]);
				   zbor.setInsotitori(separator[5]);
				
				   if(separator[6].equals("-"))
				   {
					   zbor.setEscale("Nu exista escale!");
				   }
				   else
				   {
					   zbor.setEscale(separator[6]);
				   }
				   zbor.setNrLocuri(Integer.parseInt(separator[7]), Integer.parseInt(separator[8]), Integer.parseInt(separator[9]));
				   zbor.setPret(Float.parseFloat(separator[10]));
				   zbor.setZborDeschis(Boolean.parseBoolean(separator[11]));
				   zbor.setCod(Integer.parseInt(separator[12]));
			   }
			   else
			   {
				   continue;
			   }
			   listaZboruri.add(zbor);
		   }
		  
	   }
	 catch(IOException a) {
		   System.out.println(a);
	 }
	 return listaZboruri;
}

public static  ArrayList<Zbor> getListaCompletaZboruri(){  //Creare lista zboruri

	  ArrayList<Zbor> listaZboruri = new ArrayList<Zbor>();
	  int nrLinie = 1;
	  
	 try (Scanner f1 = new Scanner(zboruri)) {
		   while(f1.hasNextLine()) { 
			   
			   String zbor = f1.nextLine();
			   String[] separator = zbor.split("/");
			   Zbor  cursa = new Zbor();
			   
			   if(nrLinie == 1)
			   {
				 nrLinie = 0;
				 continue;
			   }
				 cursa.setOrasPlecare(separator[0]);
				 cursa.setOrasDestinatie(separator[1]);
				 cursa.setDataPlecare(separator[2]);
				 cursa.setDataIntoarcere(separator[3]);
				 cursa.setPiloti(separator[4]);
				 cursa.setInsotitori(separator[5]);
				 cursa.setEscale(separator[6]);
				 cursa.setNrLocuri(Integer.parseInt(separator[7]), Integer.parseInt(separator[8]), Integer.parseInt(separator[9]));
				 cursa.setPret(Float.parseFloat(separator[10]));
				 cursa.setZborDeschis(Boolean.parseBoolean(separator[11]));
				 cursa.setCod(Integer.parseInt(separator[12]));
				 
				 listaZboruri.add(cursa);
		   }
		  
	   }
	 catch(IOException a) 
	 {
		   System.out.println(a);
	 }
	 return listaZboruri;
}
public static ArrayList<Utilizator> getUtilizatori()
{
	ArrayList<Utilizator> listaUtilizatori = new ArrayList<Utilizator>();
	int nrLinie = 1;
	
	 try (Scanner f1 = new Scanner(utilizatori)) {
		   while(f1.hasNextLine()) { 
			   String user = f1.nextLine();
			   String[] separator = user.split("/");
			   Utilizator  utilizator = new Utilizator();
			   if(nrLinie == 1)
			   {
				   nrLinie = 0;
				   continue;
			   }
				   utilizator.setNume(separator[0]);
				   utilizator.setParola(separator[1]);
				   utilizator.setSuma(Float.parseFloat(separator[2]));
						   
				   listaUtilizatori.add(utilizator);
			
		   }
		  
	   }
	 catch(IOException a) 
	 {
		   System.out.println(a);
	 }
	 
	 return listaUtilizatori;
}


public static ArrayList<Card> getCarduri()
{
	ArrayList<Card> listaCarduri = new ArrayList<Card>();
	int nrLinie = 1;
	
	 try (Scanner f1 = new Scanner(carduri)) {
		   while(f1.hasNextLine()) { 
			   String user = f1.nextLine();
			   String[] separator=user.split("/");
			   Card  card = new Card();
			   if(nrLinie==1)
			   {
				   nrLinie=0;
				   continue;
			   }
				   card.setNume(separator[0]);
				   card.setPrenume(separator[1]);
				   card.setTipCard(separator[2]);
				   card.setNrCard(separator[3]);
				   card.setDataExpirare(separator[4]);
				   card.setCVC(Integer.parseInt(separator[5]));
				   card.setSuma(Float.parseFloat(separator[6]));
				   listaCarduri.add(card);
			
		   }
		  
	   }
	 catch(IOException a) 
	 {
		   System.out.println(a);
	 } 
	 return listaCarduri;
}

public static ArrayList<Rezervare> getRezervari() {
	
	  File rezervari = new File("src//EBileteAvion//Rezervari.txt");
	  ArrayList<Rezervare> listaRezervari = new ArrayList<Rezervare>();
	  int nrLinie = 1;
		 
	  try {
			   Scanner f1 = new Scanner(rezervari);
			   
			   while(f1.hasNextLine()) { 
				   
				   String zbor = f1.nextLine();
				   String[] sir = zbor.split("/");
				   
				   Rezervare  rezervare = new Rezervare();
				   
				   if(nrLinie == 1)
				   {
					   nrLinie = 0;
					   continue;
				   }
				   
				   rezervare.setNume(sir[0]);
				   rezervare.setPrenume(sir[1]);
				   rezervare.setOrasPlecare(sir[2]);
				   rezervare.setOrasDestinatie(sir[3]);
				   rezervare.setDataPlecare(sir[4]);
				   rezervare.setDataIntoarcere(sir[5]);
				   rezervare.setPret(Float.parseFloat(sir[6]));
				   rezervare.setCodRezervare(Integer.parseInt(sir[7]));
				   rezervare.setTipBilet(Integer.parseInt(sir[8]));
				   rezervare.setEmail(sir[9]);
				   rezervare.setMetodaPlata(sir[10]);
				   listaRezervari.add(rezervare);
			   }
		   }
		 catch(IOException a) {
			   System.out.println("Eroare Rezervari");
		 }
		
		return listaRezervari;
	     }
}