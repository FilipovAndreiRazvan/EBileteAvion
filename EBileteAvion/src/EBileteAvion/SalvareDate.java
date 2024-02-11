package EBileteAvion;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SalvareDate {
	
	SalvareDate(Rezervare rezervare,ArrayList<Zbor> listaZboruri,ArrayList<Utilizator> listaUtilizatori,ArrayList<Card> listaCarduri) 
	{
	      String rezervari = "src\\EBileteAvion\\Rezervari.txt";
	      String zboruri = "src\\EBileteAvion\\Zboruri.txt";
	      String utilizatori = "src\\EBileteAvion\\Utilizatori.txt";
	      String carduri = "src\\EBileteAvion\\Carduri.txt";
	      Rezervare varRezervare = VariabileGlobale.getRezervare(false);
	      try {			 
	    	  FileWriter file = new FileWriter(rezervari,true);
	            BufferedWriter scriere = new BufferedWriter(file);
	    	  scriere.write("\n"+rezervare.getNume()+"/"+rezervare.getPrenume()+"/"+  rezervare.getOrasPlecare()+"/"+rezervare.getOrasDestinatie()+"/"+rezervare.getDataPlecare()+
	    			  "/"+rezervare.getDataIntoarcere()+"/"+rezervare.getPret()+"/"+rezervare.getCodRezervare()+"/"+rezervare.getTipBilet()+"/");
	    	  if(VariabileGlobale.getActiune() == "rezerva")
	    	  {
	    		  scriere.write(rezervare.getEmail()+"/"+rezervare.getMetodaPlata());
	    	  }
	    	  else
	    	  {
	    		  scriere.write(varRezervare.getEmail()+"/"+rezervare.getMetodaPlata());
	    	  }
			 scriere.close();
			 }
			  catch (IOException e1) 
	      		{ 
				  System.out.println( "Eroare scriere rezervare!");
	      		}
	      
	      try {
	            FileWriter fileWriter = new FileWriter(zboruri);

	            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	            bufferedWriter.write("Nume/Prenume/TipCard/NrCard/DataExpirare/Sold");
	            	for(int i= 0;i< listaZboruri.size();i++) 
	            	{
	            		bufferedWriter.write(
	            	"\n"+listaZboruri.get(i).getOrasPlecare()+"/"+listaZboruri.get(i).getOrasDestinatie()+"/"
	            	+listaZboruri.get(i).getDataPlecare()+"/"+listaZboruri.get(i).getDataIntoarcere()+"/"
	            	+listaZboruri.get(i).getPiloti()+"/"+listaZboruri.get(i).getInsotitori()+"/"
	            	+listaZboruri.get(i).getEscale()+"/"+listaZboruri.get(i).getNrLocuri()[0]+"/"
	            	+listaZboruri.get(i).getNrLocuri()[1]+"/"+listaZboruri.get(i).getNrLocuri()[2]+"/"
	            	+listaZboruri.get(i).getPret()+"/"+listaZboruri.get(i).getZborDeschis());
	            	}

	            bufferedWriter.close();
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	      
	      try {
	            FileWriter fileWriter = new FileWriter(utilizatori);

	            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	            bufferedWriter.write("UserName/Parola/Sold");
	            	for(int i= 0;i< listaUtilizatori.size();i++) 
	            	{
	            		bufferedWriter.write("\n"+listaUtilizatori.get(i).getNume()+"/"+listaUtilizatori.get(i).getParola()+"/"+listaUtilizatori.get(i).getSuma());
	            	}

	            bufferedWriter.close();
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	      try {
	            FileWriter fileWriter = new FileWriter(carduri);

	            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	            bufferedWriter.write("Nume/Prenume/TipCard/NrCard/DataExpirare/CVV/Sold");
	            	for(int i= 0;i< listaCarduri.size();i++) 
	            	{
	            		bufferedWriter.write("\n"+listaCarduri.get(i).getNume()+"/"+listaCarduri.get(i).getPrenume()+"/"
	            	+listaCarduri.get(i).getTipCard()+"/"+listaCarduri.get(i).getNrCard()+"/"+listaCarduri.get(i).getDataExpirare()+"/"
	            	+listaCarduri.get(i).getCVC()+"/"+listaCarduri.get(i).getSuma());
	            	}

	            bufferedWriter.close();
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
}
