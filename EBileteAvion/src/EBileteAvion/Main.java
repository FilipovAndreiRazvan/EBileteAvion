package EBileteAvion;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class Main {
	
	public static void main(String[] args) {
		
		initializare();
		
	}
	
	public static void initializare() {
		
		actualizareZboruri();
		JButton rezervaBtn,modificaBtn, anuleazaRezervareBtn,adminBtn,creazaZborBtn;
		
		rezervaBtn = new JButton("Fă o rezervare");
		modificaBtn = new JButton("Modifica rezervare");
		anuleazaRezervareBtn = new JButton ("Anuleaza rezervare");
		creazaZborBtn = new JButton ("Creaza zbor");
		adminBtn = new JButton("Administrare");
		
		
		JLabel paragraf1 = new JLabel("Orarul zborurilor este afișat în ora locală. Orele de deschidere și închidere ale biroului de check-in,");
		JLabel paragraf2 = new JLabel( "de predare a bagajelor și ale porții de îmbarcare pot fi afectate de modificările stării zborului.");
		JLabel paragraf3 = new JLabel( "Vă recomandăm să urmăriți cu atenție ecranele/anunțurile de la aeroport și informațiile care vă  ");
		JLabel paragraf4 = new JLabel( "sunt trimise direct de Serviciul de relații cu clienții EBileteAvion.");
		
		JLabel titlu = new JLabel();
		titlu.setText("EBileteAvion");
	    titlu.setFont(new Font("Time New Roman", Font.PLAIN, 40));
	    titlu.setBounds(225, 30, 300, 50);

	  
		JFrame frame = new JFrame ("Fereastra Principala - EBileteAvion");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(650,600);
		frame.setVisible(true);
		
	    frame.add(titlu);
	    frame.add(paragraf1);
	    frame.add(paragraf2);
	    frame.add(paragraf3);
	    frame.add(paragraf4);
	    frame.add(anuleazaRezervareBtn);
	    frame.add(modificaBtn);
	    frame.add(adminBtn);
	    frame.add(rezervaBtn);
	    frame.add(creazaZborBtn);
	    
	    creazaZborBtn.setBounds(235, 290, 200, 50);
	    adminBtn.setBounds(500, 10, 115, 25);
		rezervaBtn.setBounds(235, 110, 200, 50);
		modificaBtn.setBounds(235, 170, 200, 50);
		anuleazaRezervareBtn.setBounds(235, 230, 200, 50);
		
		paragraf1.setBounds(30, 235, 800, 150);
		paragraf2.setBounds(30, 250, 800, 150);
		paragraf3.setBounds(30, 275, 800, 150);
		paragraf4.setBounds(30, 290, 800, 150);
		
		if(VariabileGlobale.getAdmin() == true)
		{
			rezervaBtn.setEnabled(false);
			modificaBtn.setEnabled(false);
			anuleazaRezervareBtn.setEnabled(false);
			adminBtn.setText("Log out");
			creazaZborBtn.setVisible(true);
			paragraf1.setBounds(30, 285, 800, 150);
			paragraf2.setBounds(30, 300, 800, 150);
			paragraf3.setBounds(30, 325, 800, 150);
			paragraf4.setBounds(30, 340, 800, 150);
		}
		else
		{
			rezervaBtn.setEnabled(true);
			modificaBtn.setEnabled(true);
			rezervaBtn.setEnabled(true);
			creazaZborBtn.setVisible(false);
		}

		rezervaBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				VariabileGlobale.setActiune("rezerva");
				frame.setVisible(false);
				new DetaliiUtilizator();
			}
		});
		modificaBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				VariabileGlobale.setActiune("modifica");
				
				int cod = 0,zborGasit;
				String input = JOptionPane.showInputDialog("Introduceti codul rezervarii");
				if(input == null)
		        {
		        	return;
		        }
			    try {
			        cod = Integer.parseInt(input);
			    } catch (NumberFormatException e) {
			    	JOptionPane.showMessageDialog(null, "Introduceti o valoare numerica!");
			    	return;
			    }
				    
				zborGasit = verificaRezervare(cod);
				
				if(zborGasit == -1)
				{
					JOptionPane.showMessageDialog(null, "Rezervarea nu exista in sistem!");
				}
				else
				{
					frame.setVisible(false);
					ModificaRezervare.initializare(zborGasit);
				}
				
				
				
			}});
		
		
		anuleazaRezervareBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				VariabileGlobale.setActiune("anuleaza");
				int cod;
				String input = JOptionPane.showInputDialog("Introduceti codul rezervarii");
				if(input == null)
		        {
		        	return;
		        }
			    try {
			        cod = Integer.parseInt(input);
			    } catch (NumberFormatException e) {
			    	JOptionPane.showMessageDialog(null, "Introduceti o valoare numerica!");
			    	return;
			    }
				ArrayList<Rezervare> listaRezervari = VariabileGlobale.getRezervari();
				int rezervareGasita = -1;
				for(int i = 0;i < listaRezervari.size();i++) {
					if(listaRezervari.get(i).getCodRezervare() == cod)
					{
						rezervareGasita = 1;
						int raspuns = JOptionPane.showOptionDialog(null,"Rezervarea cu urmatoarele detalii va fi anulata:"
								+ "\nNume:"+listaRezervari.get(i).getNume()+"\nPrenume:"+listaRezervari.get(i).getPrenume()+"\nOras de plecare:"+listaRezervari.get(i).getOrasPlecare()+"\nOras de destinatie:"+listaRezervari.get(i).getOrasDestinatie()
								+"\nData de plecare:"+listaRezervari.get(i).getDataPlecare()+"\nData de intoarcere:"+listaRezervari.get(i).getDataIntoarcere()+"\nConfirmati?","Selecteaza o optiune!",
								   JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
						
						if(raspuns == JOptionPane.YES_OPTION)
						{
							modificaZboruri(listaRezervari.get(i).getOrasPlecare(),listaRezervari.get(i).getOrasDestinatie(),listaRezervari.get(i).getDataPlecare(),listaRezervari.get(i).getDataIntoarcere(),listaRezervari.get(i).getTipBilet(),"sterge");
							frame.setVisible(false);
							JOptionPane.showMessageDialog(null, "Rezervarea a fost stearsa din  sistem!");
							
							Main.initializare();
							Email.trimitere(listaRezervari.get(i).getEmail(),listaRezervari.get(i).getOrasPlecare(),listaRezervari.get(i).getOrasDestinatie(),listaRezervari.get(i).getDataPlecare(),listaRezervari.get(i).getDataIntoarcere(),listaRezervari.get(i).getTipBilet(),cod, listaRezervari.get(i).getPret());
							
							returneazaBani(listaRezervari.get(i).getMetodaPlata(),listaRezervari.get(i));
							listaRezervari.remove(i);
							VariabileGlobale.setRezervari(listaRezervari);
							actualizareRezervari();
							actualizareZboruri();
						}
					}
				}
				
				if(rezervareGasita == -1) 
					{
						JOptionPane.showMessageDialog(null, "Rezervarea nu exista in sistem!");
						frame.setVisible(false);
						initializare();
					}
			}
			public void returneazaBani(String cont,Rezervare rezervare) {
				String[] detalii = cont.split("-");
				int index = Integer.parseInt(detalii[1]);
				if(detalii[0].equals("card"))
				{
					float suma = VariabileGlobale.getCarduri().get(index).getSuma();
					VariabileGlobale.getCarduri().get(index).setSuma(suma + rezervare.getPret()); 
					actualizareCarduri();
				}
				else
				{
					float suma = VariabileGlobale.getUtilizatori().get(index).getSuma() + rezervare.getPret();
					VariabileGlobale.getUtilizatori().get(index).setSuma(suma);
					actualizareUtilizatori();
				}
			}
		});
		
		creazaZborBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				new CreareZbor();
				frame.setVisible(false);
			}
		});
		
		adminBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				
				if(VariabileGlobale.getAdmin() == false) 
				{
					new AdminLogin();
				}
				else
				{
					VariabileGlobale.setAdmin(false);
					 Main.initializare();
				}
			}
			
		});
	}
	
	public static boolean isValidEmail(String email) {
		String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
	
	private static void actualizareUtilizatori() {
		File numeFisier2 = new File("src\\EBileteAvion\\Utilizatori.txt");
		ArrayList<Utilizator> listaUtilizatori = VariabileGlobale.getUtilizatori();
		 try {
	            FileWriter fileWriter = new FileWriter(numeFisier2);

	            BufferedWriter writer = new BufferedWriter(fileWriter);
	            writer.write("UserName/Parola/Sold");
	            	
	            for(int i= 0;i<listaUtilizatori.size();i++) 
	            	{
	            	writer.write("\n"+listaUtilizatori.get(i).getNume()+"/"+listaUtilizatori.get(i).getParola()+"/"+listaUtilizatori.get(i).getSuma());
	            	}
	            writer.close();
	            	}
		 catch (IOException e) {
	            e.printStackTrace();
	        }
		
	}
	private static void actualizareCarduri() {
		File numeFisier2 = new File("src//EBileteAvion//Carduri.txt");
		ArrayList<Card> listaCarduri = VariabileGlobale.getCarduri();
		 try {
	            FileWriter fileWriter = new FileWriter(numeFisier2);

	            BufferedWriter writer = new BufferedWriter(fileWriter);
	            writer.write("UserName/Parola/Sold");
	            	
	            for(int i= 0;i<listaCarduri.size();i++) 
	            	{
	            	writer.write("\n"+listaCarduri.get(i).getNume()+"/"+listaCarduri.get(i).getPrenume()+"/"+listaCarduri.get(i).getTipCard()+"/"+listaCarduri.get(i).getNrCard()+"/"+listaCarduri.get(i).getDataExpirare()+"/"+listaCarduri.get(i).getCVC()+"/"+listaCarduri.get(i).getSuma());
	            	}
	            writer.close();
	            	}
		 catch (IOException e) {
	            e.printStackTrace();
	        }
		
	}
	
	public static void actualizareZboruri() {
		
		File numeFisier2 = new File("src//EBileteAvion//Zboruri.txt");
		ArrayList<Zbor> lista = VariabileGlobale.getListaCompletaZboruri();
		LocalDate dataCurenta = LocalDate.now();
		LocalDate dataZbor;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		int contor=0;
		for(int i = 0;i < lista.size();i++)
		{
			dataZbor=LocalDate.parse(lista.get(i).getDataPlecare(), formatter);
			if(validareData(lista.get(i).getDataPlecare()) == true && (lista.get(i).getNrLocuri()[0] > 0 || lista.get(i).getNrLocuri()[1] > 0 ||lista.get(i).getNrLocuri()[2] > 0))
			{
				lista.get(i).setZborDeschis(true);
			}
			else
			{
				
				lista.get(i).setZborDeschis(false);
			}
		}
		 try {
	            FileWriter fileWriter = new FileWriter(numeFisier2);

	            BufferedWriter writer = new BufferedWriter(fileWriter);
	            writer.write("OrasPlecare/OrasDestinatie/DataPlecare/DataIntoarcere/Piloti/Insotitori/Escale/NrLocCls1/NrLocCls2/NrLocCls3/PretBilet/Status(Diponibil?)");
	            	
	            for(int i= 0;i<lista.size();i++) 
	            	{
	            	writer.write(
	            	"\n"+lista.get(i).getOrasPlecare()+"/"+lista.get(i).getOrasDestinatie()+"/"
	            	+lista.get(i).getDataPlecare()+"/"+lista.get(i).getDataIntoarcere()+"/"+lista.get(i).getPiloti()+"/"+lista.get(i).getInsotitori()+"/"+
	            	lista.get(i).getEscale()+"/"+lista.get(i).getNrLocuri()[0]+"/"+lista.get(i).getNrLocuri()[1]+"/"+lista.get(i).getNrLocuri()[2]+"/"+lista.get(i).getPret()+"/"+
	            	lista.get(i).getZborDeschis()+"/"+lista.get(i).getCod());
	            	}
	            
	            writer.close();
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 VariabileGlobale.setListaCompletaZboruri(lista);
		 VariabileGlobale.setListaZboruriDisponibile(InformatiiZboruri.getListaZboruriDisponibile());
	}
	
	private static boolean validareData(String dataPlecare) {
		
		DateTimeFormatter format=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate dataZbor=LocalDate.parse(dataPlecare,format);
		LocalDate dataCurenta=LocalDate.now();
		short zileRamase=(short) ChronoUnit.DAYS.between(dataCurenta, dataZbor);
		if(zileRamase > 60 || zileRamase<0)
		{
			return false;
		}
		return true;
	}
	
	static void actualizareRezervari() {
		
		ArrayList<Rezervare> lista = VariabileGlobale.getRezervari();
		File Rezervari = new File("src//EBileteAvion//Rezervari.txt");
		
		try {
            FileWriter fileWriter = new FileWriter(Rezervari);

            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write("Nume/Prenume/OrasPlecare/OrasDestinatie/DataPlecare/DataIntoarcere/PretBilet/CodRezervare/TipBilet/Email/MetodaPlata");
            
            for(int i= 0;i< lista.size();i++) 
            	{
            	writer.write(
            	"\n"+lista.get(i).getNume()+"/"+lista.get(i).getPrenume()+"/"+lista.get(i).getOrasPlecare()+"/"+lista.get(i).getOrasDestinatie()+"/"
            	+lista.get(i).getDataPlecare()+"/"+lista.get(i).getDataIntoarcere()+"/"+lista.get(i).getPret()+"/"+lista.get(i).getCodRezervare()+"/"+
            	lista.get(i).getTipBilet()+"/"+lista.get(i).getEmail()+"/"+lista.get(i).getMetodaPlata());
            	}
            writer.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
			
	}
	
		static int verificaRezervare(int cod) {
			
			ArrayList<Rezervare> lista = VariabileGlobale.getRezervari();
			int rezultat = -1;
			
			for(int i = 0;i < lista.size();i++) {
				if(lista.get(i).getCodRezervare() == cod)
				{
					return i;
				}
			}
			return rezultat;		
		}
		
		public static void modificaZboruri(String orasPlecare,String orasDestinatie,String dataPlecare,String dataIntoarcere,int tipLoc,String actiune) 
		{
			
			var listaZboruri=VariabileGlobale.getListaCompletaZboruri();
			
			for(int i = 0;i < listaZboruri.size();i++)
			{
				if(listaZboruri.get(i).getOrasPlecare().equalsIgnoreCase(orasPlecare) && 
						listaZboruri.get(i).getOrasDestinatie().equalsIgnoreCase(orasDestinatie) && 
						listaZboruri.get(i).getDataPlecare().equalsIgnoreCase(dataPlecare) && 
						listaZboruri.get(i).getDataIntoarcere().equalsIgnoreCase(dataIntoarcere)  ) 
				{
					if(actiune == "sterge")
					{
						switch(tipLoc)
						{
							case 1:listaZboruri.get(i).setNrLocuri(++listaZboruri.get(i).getNrLocuri()[0],listaZboruri.get(i).getNrLocuri()[1] ,listaZboruri.get(i).getNrLocuri()[2]);
							break;
							case 2:listaZboruri.get(i).setNrLocuri(listaZboruri.get(i).getNrLocuri()[0],++listaZboruri.get(i).getNrLocuri()[1] ,listaZboruri.get(i).getNrLocuri()[2]);
							break;
							case 3:listaZboruri.get(i).setNrLocuri(listaZboruri.get(i).getNrLocuri()[0]++,listaZboruri.get(i).getNrLocuri()[1] ,++listaZboruri.get(i).getNrLocuri()[2]);
							break;
						}
					}
					else if(actiune == "rezerva")
					{
						switch(tipLoc)
						{
							case 1:listaZboruri.get(i).setNrLocuri(--listaZboruri.get(i).getNrLocuri()[0],listaZboruri.get(i).getNrLocuri()[1] ,listaZboruri.get(i).getNrLocuri()[2]);;
							break;
							case 2:listaZboruri.get(i).setNrLocuri(listaZboruri.get(i).getNrLocuri()[0],--listaZboruri.get(i).getNrLocuri()[1] ,listaZboruri.get(i).getNrLocuri()[2]);
							break;
							case 3:listaZboruri.get(i).setNrLocuri(listaZboruri.get(i).getNrLocuri()[0],listaZboruri.get(i).getNrLocuri()[1] ,--listaZboruri.get(i).getNrLocuri()[2]);
							break;
						}
					}
					}
				}
			VariabileGlobale.setListaCompletaZboruri(listaZboruri);
			
		}
}