package EBileteAvion;
import java.util.Random;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.*;
public class Interfata4 implements ActionListener{

		JLabel label1 = new JLabel("Introduceti datele:");
		JLabel label2 = new JLabel("Nume:");
		JLabel label3 = new JLabel("Prenume:");
		JLabel label4 = new JLabel("Tip card:");
		JLabel label5 = new JLabel("Numar card:");
		JLabel label6 = new JLabel("Data expirare:");
		JLabel label7 = new JLabel("CVC:");
		
		JTextField  textField1 = new JTextField(10);
		JTextField  textField2 = new JTextField(10);
		String[] optiuni = {"MasterCard", "Visa"}; 
	    JComboBox<String> tipCard = new JComboBox<>(optiuni);
		JTextField  textField4 = new JTextField(10);
		JTextField  textField5 = new JTextField(10);
		JTextField  textField6 = new JTextField(10);
		
		
		JFrame frame = new JFrame();
		
		
		public  Interfata4() {
				
			JPanel panel1 = new JPanel();
			JPanel panel2 = new JPanel();
			GridLayout experimentLayout = new GridLayout(0,1);
			
			panel1.setLayout(experimentLayout);
			panel1.add(label1);
			
			panel1.add(label2);
			panel1.add(textField1);
			
			panel1.add(label3);
			panel1.add(textField2);
			
			panel1.add(label4);
			panel1.add(tipCard);
			
			panel1.add(label5);
			panel1.add(textField4);
			
			panel1.add(label6);
			panel1.add(textField5);
			
			panel1.add(label7);
			panel1.add(textField6);
			
			
			panel1.setSize(new Dimension(200,600));
			panel1.setBorder(BorderFactory.createEmptyBorder(25,50,50,50));
			label1.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));
			
			JButton button = new JButton("Trimite");
			button.addActionListener(this);
			button.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			button.setSize(50, 50);
			
			panel2.add(button);
			panel2.setBorder(BorderFactory.createEmptyBorder(0,50,25,50));
			
			frame.add(panel1,BorderLayout.CENTER);
			frame.add(label1,BorderLayout.NORTH);
			frame.add(panel2,BorderLayout.SOUTH);
			frame.setTitle("Card");
			frame.setSize(1400,400);
			frame.pack();
			frame.setLayout(null);
			frame.setVisible(true);
			
			frame.addWindowListener((WindowListener) new WindowAdapter() {
		        public void windowClosing(WindowEvent e) {
		        	Main.initializare();
		        }});
		
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String nume = textField1.getText();
			String prenume = textField2.getText();
			String Card = (String) tipCard.getSelectedItem();
			String nrCard = textField4.getText();
			String dataExpirare = textField5.getText();
			
			int cvc = Integer.parseInt(textField6.getText());
			
			if(validare(nume,prenume,Card,nrCard,dataExpirare,cvc) == true) 
			{
				
				Random random = new Random();
				int codRezervare =  random.nextInt(100,1000);
				VariabileGlobale.getRezervare(true).setCodRezervare(codRezervare);
				
				ArrayList<Rezervare> listaRezervari = VariabileGlobale.getRezervari();
				Rezervare rezervareNoua = VariabileGlobale.getRezervare(true);
				Rezervare rezervareExistenta = VariabileGlobale.getRezervare(false);
				
				if(VariabileGlobale.getActiune() == "modifica")
				{
					JOptionPane.showMessageDialog(null, "Rezervarea a fost modificata! Cod rezervare: "+ codRezervare);
					Main.modificaZboruri(rezervareNoua.getOrasPlecare(), rezervareNoua.getOrasDestinatie(), rezervareNoua.getDataPlecare(), rezervareNoua.getDataIntoarcere(), rezervareNoua.getTipBilet(), "rezerva");
					Main.modificaZboruri(rezervareExistenta.getOrasPlecare(), rezervareExistenta.getOrasDestinatie(), rezervareExistenta.getDataPlecare(), rezervareExistenta.getDataIntoarcere(), rezervareExistenta.getTipBilet(), "sterge");
					Email.trimitere(rezervareExistenta.getEmail(),rezervareNoua.getOrasPlecare(),rezervareNoua.getOrasDestinatie(),rezervareNoua.getDataPlecare(),rezervareNoua.getDataIntoarcere(),rezervareNoua.getTipBilet(),codRezervare,rezervareNoua.getPret());
					for(int i = 0;i < listaRezervari.size();i++) 
					{
						if(listaRezervari.get(i).getCodRezervare() == rezervareExistenta.getCodRezervare())
						{
							listaRezervari.remove(i);
							VariabileGlobale.setRezervari(listaRezervari);
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Rezervarea a fost creata! Cod rezervare: "+ codRezervare);
					Main.modificaZboruri(rezervareNoua.getOrasPlecare(), rezervareNoua.getOrasDestinatie(), rezervareNoua.getDataPlecare(), rezervareNoua.getDataIntoarcere(), rezervareNoua.getTipBilet(), "rezerva");
				}
				
				Main.actualizareRezervari();
				new SalvareDate(rezervareNoua,VariabileGlobale.getListaCompletaZboruri(),VariabileGlobale.getUtilizatori(),VariabileGlobale.getCarduri());
				frame.setVisible(false);
				Main.initializare();
				
				if(VariabileGlobale.getActiune() == "modifica")
				{
					Email.trimitere(rezervareExistenta.getEmail(),rezervareNoua.getOrasPlecare(),rezervareNoua.getOrasDestinatie(),rezervareNoua.getDataPlecare(),rezervareNoua.getDataIntoarcere(),rezervareNoua.getTipBilet(),codRezervare,rezervareNoua.getPret());
				}
				else
				{
					Email.trimitere(rezervareNoua.getEmail(),rezervareNoua.getOrasPlecare(),rezervareNoua.getOrasDestinatie(),rezervareNoua.getDataPlecare(),rezervareNoua.getDataIntoarcere(),rezervareNoua.getTipBilet(),codRezervare,rezervareNoua.getPret());
				}
			}
		}
		
		public boolean validare(String nume,String prenume,String tipCard, String nrCard, String dataExpirare,int cvc) 
		{
			ArrayList<Card> carduri=VariabileGlobale.getCarduri();
			boolean valid=false;
			float diferentaPret;
			for(int i = 0;i < carduri.size();i++)
			{
				if(carduri.get(i).getNume().equals(nume) && carduri.get(i).getPrenume().equals(prenume) 
						&& carduri.get(i).getTipCard().equals(tipCard) && 
						carduri.get(i).getNrCard().equals(nrCard) && carduri.get(i).getDataExpirare().equals(dataExpirare) 
						&& carduri.get(i).getCVC()==cvc && getDateTime(carduri.get(i).getDataExpirare()) == false)
				{
					valid = true;
					
					diferentaPret = Math.abs(VariabileGlobale.getRezervare(true).getPret()-VariabileGlobale.getRezervare(false).getPret());
						
						if(VariabileGlobale.getActiune() == "rezerva" )
						{
							if( VariabileGlobale.getRezervare(true).getPret() > carduri.get(i).getSuma())
							{
								JOptionPane.showMessageDialog(null, "Fonduri insuficiente!");
								return false;
							}
							else
							{
								carduri.get(i).setSuma(carduri.get(i).getSuma()-VariabileGlobale.getRezervare(true).getPret());
							}
						}
						else
						{
							if(VariabileGlobale.getActiune() == "modifica")
							{
								if(VariabileGlobale.getPretBiletNou() == "mai ieftin")
								{
									carduri.get(i).setSuma(carduri.get(i).getSuma()+diferentaPret);
								}
								else
								{
									if(diferentaPret > carduri.get(i).getSuma())
									{
										JOptionPane.showMessageDialog(null, "Fonduri insuficiente!");
										return false;
									}
									else
									{
										carduri.get(i).setSuma(carduri.get(i).getSuma()-diferentaPret);
									}
								}
							}
						}
						
						VariabileGlobale.setCarduri(carduri);
						String metodaPlata = VariabileGlobale.getRezervare(true).getMetodaPlata()+"-"+i;
						VariabileGlobale.getRezervare(true).setMetodaPlata(metodaPlata);
						return true;
				}
			}
			
			if(valid == false)
			{
				JOptionPane.showMessageDialog(null, "Datele nu sunt corecte!");
			}
			return false;
		}
		
		private boolean getDateTime(String dataUtil) {
	        String[] data = new String[3];
	        data = dataUtil.split("-");
	        
	        LocalDateTime dataCurenta = LocalDateTime.now();

	        DateTimeFormatter modelData = DateTimeFormatter.ofPattern("MM-yy");

	        String dataFormatata = dataCurenta.format(modelData);
	        String[] sirDataCurenta = dataFormatata.split("-");
	        char[] lunaDataCurenta = sirDataCurenta[0].toCharArray();
	        char[] lunaDataUtil = data[0].toCharArray();
	        int luna,lunaUtil,an,anUtil;
	        if(lunaDataCurenta[0] == '0') 
	        {
	        	luna = Integer.parseInt(Character.toString(lunaDataCurenta[1]));
	        }
	        else
	        {
	        	luna = Integer.parseInt(sirDataCurenta[0]);
	        }
	        if(lunaDataUtil[0] == '0')
	        {
	        	lunaUtil = Integer.parseInt(Character.toString(lunaDataUtil[1]));
	        }
	        else
	        {
	        	lunaUtil = Integer.parseInt(data[0]);
	        }
	        anUtil = Integer.parseInt(data[1]);
	        an = Integer.parseInt(sirDataCurenta[1]);
	        
	        if(anUtil - an > 0) 
	    	   return false;
	         else if(anUtil == an && lunaUtil - luna > 0)
	        		 return false;
	        
	       return true;
		}
		
}
