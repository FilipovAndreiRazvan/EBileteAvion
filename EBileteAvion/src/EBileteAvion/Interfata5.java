package EBileteAvion;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;

public  class Interfata5  {
	
	Utilizator utilizator=new Utilizator();
	
	JLabel label1 = new JLabel("Introduceti datele de logare:");
	JLabel label2 = new JLabel("UserName:");
	JLabel label3 = new JLabel("Parola:");
	
	JTextField  textField1 = new JTextField(10);
	JPasswordField  textField2 = new JPasswordField(10);
	JCheckBox checkBox1 = new JCheckBox("Afisati parola");
	
	JFrame frame = new JFrame();
	
	Zbor modifica = new Zbor();
	
	

	public Interfata5() {
	
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	
	
	JButton button = new JButton("Logare");
	
	panel1.setBorder(BorderFactory.createEmptyBorder(25,50,50,50));
	panel1.setLayout(new GridLayout(0,1));
	panel1.add(label2);
	panel1.add(textField1);
	panel1.add(label3);
	panel1.add(textField2);
	panel1.add(checkBox1);
	
	panel2.setBorder(BorderFactory.createEmptyBorder(0,50,50,50));
	panel2.add(button);
	
	panel3.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));
	panel3.add(label1,BorderLayout.CENTER);
	
	frame.add(panel1,BorderLayout.CENTER);
	frame.add(panel2,BorderLayout.SOUTH);
	frame.add(panel3,BorderLayout.NORTH);
	
	frame.setTitle("Abonament-EBileteAvion");
	frame.setSize(150,150);
	frame.pack();
	frame.setLayout(null);
	frame.setVisible(true);

	frame.addWindowListener((WindowListener) new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
        	Main.initializare();
        }});
	
	button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
		String nume = textField1.getText();
    	String parola = String.valueOf(textField2.getPassword());

				if( validare(nume,parola) == true)
					{
					Random random = new Random();
					int codRezervare =  random.nextInt(100,1000);
					VariabileGlobale.getRezervare(true).setCodRezervare(codRezervare);
					Rezervare rezervareNoua = VariabileGlobale.getRezervare(true);
					Rezervare rezervareExistenta = VariabileGlobale.getRezervare(false);
					ArrayList<Rezervare> listaRezervari = VariabileGlobale.getRezervari();
					if(VariabileGlobale.getActiune() == "modifica")
					{
						Main.modificaZboruri(rezervareNoua.getOrasPlecare(), rezervareNoua.getOrasDestinatie(), rezervareNoua.getDataPlecare(), rezervareNoua.getDataIntoarcere(), rezervareNoua.getTipBilet(), "rezerva");
						Main.modificaZboruri(rezervareExistenta.getOrasPlecare(), rezervareExistenta.getOrasDestinatie(), rezervareExistenta.getDataPlecare(), rezervareExistenta.getDataIntoarcere(), rezervareExistenta.getTipBilet(), "sterge");
						
						for(int i = 0;i < listaRezervari.size();i++) 
						{
							if(listaRezervari.get(i).getCodRezervare() == rezervareExistenta.getCodRezervare())
							{
								listaRezervari.remove(i);
								VariabileGlobale.setRezervari(listaRezervari);
							}
						}
						JOptionPane.showMessageDialog(null, "Rezervarea a fost modificata! Cod rezervare: "+ codRezervare); 
						new SalvareDate(rezervareNoua,VariabileGlobale.getListaCompletaZboruri(),VariabileGlobale.getUtilizatori(),VariabileGlobale.getCarduri());
						Email.trimitere(rezervareExistenta.getEmail(),rezervareNoua.getOrasPlecare(),rezervareNoua.getOrasDestinatie(),rezervareExistenta.getDataPlecare(),rezervareExistenta.getDataIntoarcere(),rezervareNoua.getTipBilet(),codRezervare,rezervareNoua.getPret());			
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Rezervarea a fost creata! Cod rezervare: "+ codRezervare); 
						Main.modificaZboruri(rezervareNoua.getOrasPlecare(), rezervareNoua.getOrasDestinatie(), rezervareNoua.getDataPlecare(), rezervareNoua.getDataIntoarcere(), rezervareNoua.getTipBilet(), "rezerva");
						Email.trimitere(rezervareNoua.getEmail(),rezervareNoua.getOrasPlecare(),rezervareNoua.getOrasDestinatie(),rezervareNoua.getDataPlecare(),rezervareNoua.getDataIntoarcere(),rezervareNoua.getTipBilet(),codRezervare,rezervareNoua.getPret());
					}				
					Main.actualizareRezervari();
					new SalvareDate(rezervareNoua,VariabileGlobale.getListaCompletaZboruri(),VariabileGlobale.getUtilizatori(),VariabileGlobale.getCarduri());					
					frame.setVisible(false);
					Main.initializare();
					}
				else 
				{
					frame.setVisible(true);
				}
	
		}	
	});
	
	 checkBox1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             char currentEchoChar = textField2.getEchoChar();

             if (currentEchoChar == 0) {
             	textField2.setEchoChar('\u2022');
             } else {
             	textField2.setEchoChar((char) 0);
             }
         }
     });
	}
	
	
	
	public boolean validare(String nume,String parola) {
		ArrayList<Utilizator> utilizatori = VariabileGlobale.getUtilizatori();
		boolean valid = false;
		float diferentaPret;
			for(int i = 0;i < utilizatori.size();i++)
			{
				if(nume.equals(utilizatori.get(i).getNume()) && parola.equals(utilizatori.get(i).getParola()))
					{
						valid = true;
						Rezervare rezervare = VariabileGlobale.getRezervare(true);
						
						diferentaPret = Math.abs(VariabileGlobale.getRezervare(true).getPret()-VariabileGlobale.getRezervare(true).getPret());
						if(VariabileGlobale.getActiune() == "rezerva" )
						{
							if( rezervare.getPret() > utilizatori.get(i).getSuma())
							{
								JOptionPane.showMessageDialog(null, "Fonduri insuficiente!");
								return false;
							}
							else
							{
								float suma = utilizatori.get(i).getSuma() - rezervare.getPret();
								utilizatori.get(i).setSuma(suma);						
							}
						}
						else
						{
							if(VariabileGlobale.getActiune() == "modifica")
							{
								if(VariabileGlobale.getPretBiletNou() == "mai ieftin")
								{
									float suma = utilizatori.get(i).getSuma()+diferentaPret;
									utilizatori.get(i).setSuma(suma) ;
								}
								else
								{
									if(diferentaPret > utilizatori.get(i).getSuma())
									{
										JOptionPane.showMessageDialog(null, "Fonduri insuficiente!");
										return false;
									}
									else
									{
										float suma = utilizatori.get(i).getSuma()-diferentaPret;;
										utilizatori.get(i).setSuma(suma);
									}
								}
							}
						}
						String metodaPlata = VariabileGlobale.getRezervare(true).getMetodaPlata()+"-"+i;
						VariabileGlobale.getRezervare(true).setMetodaPlata(metodaPlata);
					}
			}
			if(valid == false)
			{
					JOptionPane.showMessageDialog(null, "Datele nu sunt corecte!");
					return false;
			}
		return true;
	}

}