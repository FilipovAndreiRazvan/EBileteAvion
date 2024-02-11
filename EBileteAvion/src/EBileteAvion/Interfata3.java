package EBileteAvion;

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
import java.util.ArrayList;

import javax.swing.*;
public class Interfata3  {
	
	JTextField  textField1=new JTextField(10);
	JFrame frame=new JFrame();

   public Interfata3(int nrZbor) {
	   
	    int numarZbor = nrZbor;
	    
	   
	   JLabel label1 = new JLabel("Bilete Disponibile");
	   
	   JButton cardBtn = new JButton("Card Bancar");
	   JButton abonamentBtn = new JButton("Abonament");
	   
	   JPanel panel1 = new JPanel();
	   JPanel panel2 = new JPanel();
	   JPanel panel3 = new JPanel();
	   
	   panel2.add(label1,BorderLayout.CENTER);
	   frame.add(panel2,BorderLayout.NORTH);
	   
	   panel1.setBorder(BorderFactory.createEmptyBorder(25,50,25,50)); 
	   
	   panel3.add(textField1);
	   panel3.add(cardBtn);
	   panel3.add(abonamentBtn);
	   panel3.setBorder(BorderFactory.createEmptyBorder(25,50,25,50));
	   panel3.setLayout(new GridLayout(0,1));
	   panel1.setLayout(new GridLayout(0,1));
		 
	   
	   ArrayList<Zbor> listaZboruri = InformatiiZboruri.getListaZboruriDisponibile();
	   
	   if(listaZboruri.get(nrZbor).getNrLocuri()[0] > 0)
	   {
		   JLabel label2 = new JLabel("1. Clasa1: "+calculBilet(1,nrZbor)+" RON");
		   label2.setBorder(BorderFactory.createEmptyBorder(25,50,25,50));
		   panel1.add(label2);
	   }
	   if(listaZboruri.get(nrZbor).getNrLocuri()[1]>0)
	   {
		   JLabel label3 = new JLabel("2. Clasa2: "+calculBilet(2,nrZbor)+" RON");
		   label3.setBorder(BorderFactory.createEmptyBorder(25,50,25,50));
		   panel1.add(label3);
	   }
	   if(listaZboruri.get(nrZbor).getNrLocuri()[2]>0)
	   {
		   JLabel label4 = new JLabel("3. Clasa3: "+calculBilet(3,nrZbor)+" RON");
		   label4.setBorder(BorderFactory.createEmptyBorder(25,50,25,50));
		   panel1.add(label4);
	   }
	  
	   frame.add(panel1,BorderLayout.CENTER);
	   frame.add(panel3,BorderLayout.SOUTH);
	   frame.setTitle("Bilete-EBileteAvion");
			
	   textField1.setSize(100,10);
	   frame.pack();
	   frame.setLayout(null);
	   frame.setVisible(true);
			
	   frame.addWindowListener((WindowListener) new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	        	Main.initializare();
	        }});

	   cardBtn.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent arg0) {
			   VariabileGlobale.getRezervare(true).setMetodaPlata("card");
			   
			   String optiune = textField1.getText();
			   float pret,diferentaPret;
			   int nr;
			   nr = Integer.parseInt(optiune);
			   pret = calculBilet(nr,getNrZbor());  
			   int raspuns;
			   if(VariabileGlobale.getActiune() == "rezerva") 
			   { 
				   raspuns = JOptionPane.showOptionDialog(null,"Pretul biletului este de "+pret+" RON\n"+ " Confirmati?","Selecteaza o optiune!",
						   JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
			   }
			   else
			   {
				   diferentaPret = calculBilet(nr,getNrZbor())-VariabileGlobale.getRezervare(false).getPret();
				   if(diferentaPret>0) 
				   {
					   raspuns = JOptionPane.showOptionDialog(null,"Biletul este mai scump cu "+Math.abs(diferentaPret)+" RON\n"+ " Confirmati?","Selecteaza o optiune!",
							   JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
					   VariabileGlobale.setPretBiletNou("mai scump");
				   }
				   else
				   {
					   raspuns = JOptionPane.showOptionDialog(null,"Biletul este mai ieftin cu "+Math.abs(diferentaPret)+" RON\n"+ " Confirmati?","Selecteaza o optiune!",
							   JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
					   VariabileGlobale.setPretBiletNou("mai ieftin");
				   }
				    
			   }
			   if(raspuns == JOptionPane.YES_OPTION)
				{
					
					if(VariabileGlobale.getActiune() == "rezerva")
					{
						VariabileGlobale.getRezervare(true).setPret(pret);
					}
					else
					{
						VariabileGlobale.getRezervare(true).setPret(pret);
					}
					VariabileGlobale.getRezervare(true).setTipBilet(nr);
					frame.setVisible(false);
					
					new Interfata4();
				}
				else
				{
					int indexZbor=getNrZbor();
					frame.setVisible(false);
					
					new Interfata3(indexZbor);
				}
		
     
		   }
		   public int getNrZbor() {
			   return numarZbor;
		   }

	});
	
	abonamentBtn.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent arg0) {
			VariabileGlobale.getRezervare(true).setMetodaPlata("abonament");
		String optiune1 = textField1.getText();
		float pret,diferentaPret;
		int nr,raspuns;
		nr = Integer.parseInt(optiune1);
		pret = calculBilet(nr,getNrZbor());
		
		if(VariabileGlobale.getActiune() == "rezerva") 
		   {
			   pret = calculBilet(nr,getNrZbor());   
			   raspuns = JOptionPane.showOptionDialog(null,"Pretul biletului este de "+pret+" RON\n"+ " Confirmati?","Selecteaza o optiune!",
					   JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
		   }
		   else
		   {
			   diferentaPret = calculBilet(nr,getNrZbor())-VariabileGlobale.getRezervare(false).getPret();
			   if(diferentaPret > 0) 
			   {
				   raspuns = JOptionPane.showOptionDialog(null,"Biletul este mai scump cu "+Math.abs(diferentaPret)+" RON\n"+ " Confirmati?","Selecteaza o optiune!",
						   JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
				   VariabileGlobale.setPretBiletNou("mai scump");
			   }
			   else
			   {
				   raspuns = JOptionPane.showOptionDialog(null,"Biletul este mai ieftin cu "+Math.abs(diferentaPret)+" RON\n"+ " Confirmati?","Selecteaza o optiune!",
						   JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
				   VariabileGlobale.setPretBiletNou("mai ieftin");
			   }		   }
			if(raspuns == JOptionPane.YES_OPTION)
			{
				
				if(VariabileGlobale.getActiune() == "rezerva")
				{
					VariabileGlobale.getRezervare(true).setPret(pret);
				}
				else
				{
					VariabileGlobale.getRezervare(true).setPret(pret);
				}
				VariabileGlobale.getRezervare(true).setTipBilet(nr);
				frame.setVisible(false);
				
				new Interfata5();
			}
			else
			{
				int indexZbor=getNrZbor();
				frame.setVisible(false);
				
				new Interfata3(indexZbor);
			}
	   }

		   public int getNrZbor() {
			   return numarZbor;
		   }
		   
		});
	
   }
   
   public float calculBilet(int nrClasa,int nrZbor) {
	    
	   ArrayList<Zbor> listaZboruri = InformatiiZboruri.getListaZboruriDisponibile();
	   float pretBilet;
	    
	    switch (nrClasa) {
	        case 3:
	            pretBilet = listaZboruri.get(nrZbor).getPret();
	            break;
	        case 2:
	            pretBilet = listaZboruri.get(nrZbor).getPret() * 1.5f;
	            break;
	        case 1:
	            pretBilet = listaZboruri.get(nrZbor).getPret() * 2;
	            break;
	        default:
	            pretBilet = 0;
	            break;
	    }
	    
	    return pretBilet;
	}
}
