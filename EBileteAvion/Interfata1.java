package EBileteAvion;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

 public class Interfata1 implements ActionListener{
	    //afisare zboruri disponibile
		JLabel label1=new JLabel("Zboruri Disponibile");
		JLabel label2=new JLabel("Orasul de plecare:");
		JLabel label3=new JLabel("Orasul de destinatie:");
		JLabel label4=new JLabel("Data de plecare:");
		JLabel label5=new JLabel("Data de intoarcere:");
		JTextField  textField1=new JTextField(10);
		JTextField  textField2=new JTextField(10);
		JTextField  textField3=new JTextField(10);
		JTextField  textField4=new JTextField(10);
		JFrame frame1=new JFrame();
		File fisier=new File("src//EBileteAvion//Rezervari.txt");
		
		public  Interfata1(){
			JButton button=new JButton("Cauta");
			JPanel panel1=new JPanel();
			JPanel panel2=new JPanel();
	
			
			 button.setSize(50, 50);
			 button.addActionListener(this);
			 Tabel A=new Tabel();
			JTable table=new JTable();
			table=A.adaugaTabel();
			JScrollPane scrollPane1=new JScrollPane(table);
			
			
			 panel1.add(label2);
			 panel1.add(textField1);
			 panel1.add(label3);
			 panel1.add(textField2);
			 panel1.add(label4);
			 panel1.add(textField3);
			 panel1.add(label5);
			 panel1.add(textField4);
			 panel1.add(button);
			 panel1.setLayout(new FlowLayout());
			 panel2.add(label1,BorderLayout.CENTER);
			 panel1.setBorder(BorderFactory.createEmptyBorder(10,0,10,10));
			 scrollPane1.setVisible(true);
			scrollPane1.setBorder(BorderFactory.createEmptyBorder(50,10,10,10));
		    frame1.add(scrollPane1,BorderLayout.CENTER);
		    frame1.add(panel1,BorderLayout.SOUTH);
		    frame1.add(panel2,BorderLayout.NORTH);
			frame1.setTitle("I");
			frame1.setSize(150,150);
			frame1.pack();
			frame1.setLayout(null);
			frame1.setVisible(true);
		
		}
		public void actionPerformed(ActionEvent e) {
			String orasPlecare=textField1.getText();
			String orasDestinatie=textField2.getText();
			String dataPlecare=textField3.getText();
			String dataIntoarcere=textField4.getText();
			textField1.setText(null);
			textField2.setText(null);
			textField3.setText(null);
			textField4.setText(null);
			
			if(cautaZbor(orasPlecare, orasDestinatie, dataPlecare, dataIntoarcere)==true)
			{
				try (BufferedWriter scriere=new BufferedWriter(new FileWriter(fisier,true))) {  // scriere rezervarii in fisier
					scriere.write(orasPlecare+","+orasDestinatie+","+dataPlecare+","+dataIntoarcere+",");
					System.out.println("Am scris in fiser!Interfata1");
					}
				catch (IOException e1) {
					System.out.println("Eroare interfata3");
					System.out.println("Nu am scris in fisier");
					}
				new Interfata2(orasPlecare, orasDestinatie, dataPlecare, dataIntoarcere);
			}
			else JOptionPane.showMessageDialog(null, "Nu este disponibil niciun zbor cu datele:\n"
					+ "Oras de plecare:"+orasPlecare+
					"\nOras de destinatie:"+orasDestinatie+
					"\nData de plecare:"+dataPlecare+
					"\nData de intoarcere:"+dataIntoarcere);
			frame1.setVisible(false);
		
		}
		public  boolean cautaZbor(String orasPlecare,String orasDestinatie,String dataPlecare,String dataIntoarcere) {
			
			InformatiiZboruri A=new InformatiiZboruri();
			
			boolean zborGasit=false;
			
			ArrayList<Zbor> listaZboruri=A.getListaZboruri();
			
			for(int j=0;j<listaZboruri.size();j++){
				if(listaZboruri.get(j).orasePlecare.equalsIgnoreCase(orasPlecare) && listaZboruri.get(j).oraseDestinatie.equalsIgnoreCase(orasDestinatie) 
						&& listaZboruri.get(j).dataPleacare.equalsIgnoreCase(dataPlecare) && listaZboruri.get(j).dataIntoarcere.equalsIgnoreCase(dataIntoarcere)) {
				zborGasit=true;
				}	
		}
			return zborGasit;
		}
}
