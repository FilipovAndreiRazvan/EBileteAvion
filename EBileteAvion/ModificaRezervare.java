package EBileteAvion;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;



public class ModificaRezervare extends JFrame {
	
	private JFrame frame;
	private JLabel titlu;
	private JTable tabel;
	private int  index;
	JTable table;
	public String orasDestinatie;
	 JLabel oras = new JLabel();
	 Rezervari rezervari1 = new Rezervari();
	ArrayList<Zbor> zbor = rezervari1.getRezervari();
	JTextField orasNou = new JTextField();
	JLabel mesajModificare = new JLabel();
	JLabel informatii = new JLabel();
	boolean ok;
	
	
	public ModificaRezervare(){
		
	}
	
	public void  content(int index)  {
		

		frame=new JFrame("Modifica Rezervare");
		frame.setSize(800,800);
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
	    titlu = new JLabel();
	    titlu.setText(" Modificari Rezervare");
	    titlu.setFont(new Font("Time New Roman", Font.PLAIN, 22));
	    panel2.add(titlu,BorderLayout.CENTER);
	    titlu.setBorder(BorderFactory.createLineBorder(Color.black));
	    frame.add(panel2, BorderLayout.NORTH);
	    titlu.setVisible(true);
	     
	    
	    informatii.setText("Informatii Rezervare");
	    informatii.setFont(new Font("Time New Roman", Font.PLAIN, 18));
	    informatii.setBounds(50, 100, 300, 50);
	    //frame.add(informatii);
	    //informatii.setVisible(true);
	   
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setLayout(null);

		
		 JButton modifica = new JButton ("Modifica Rezervarea");
		
		
		
		 oras.setText("Oras destinatie nou: ");
		 orasNou.setVisible(false);
		 oras.setVisible(false);
		
		 JButton confirma = new JButton ("Confirma");
		 confirma.setBounds(300, 210, 100, 20);
		 frame.add(confirma);
		 confirma.setVisible(false);
		 
		 frame.add(mesajModificare);
		 mesajModificare.setBounds(100, 230, 250, 39);
		 
		 Tabel A=new Tabel();
		 JTable table=new JTable();
			table=cauta(index);
			panel1.setLayout(new GridLayout(1,2));
			panel1.add(table,BorderLayout.CENTER);
			panel3.add(modifica, BorderLayout.SOUTH);
			frame.add(panel1, BorderLayout.CENTER);
			frame.add(panel3, BorderLayout.SOUTH);
			JScrollPane scrollPane1=new JScrollPane(table);
			frame.add(scrollPane1);
			 modifica.setVisible(true);
			scrollPane1.setVisible(true);
			scrollPane1.setBorder(BorderFactory.createEmptyBorder(50,10,10,10));
		    frame.add(scrollPane1,BorderLayout.CENTER);
		   
		    panel3.setLayout(new GridLayout(0,5));
		    panel3.add(oras);
		    panel3.add(orasNou);
		    panel3.add(mesajModificare);
		    panel3.add(confirma);	
	
	modifica.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			orasNou.setVisible(true);
			 oras.setVisible(true);
			 confirma.setVisible(true);
			 modifica.setVisible(false);
			 
		}
	});
	
	
	confirma.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			getOrasDestinatie();
		}
	});
	
	}
	public void getOrasDestinatie(){ //De completat
		System.out.println("Apel getOrasDest");
		String orasNou2 = orasNou.getText();
		for(int j=0;j<zbor.size();j++) {
			System.out.println(zbor.get(j).oraseDestinatie);
		if(orasNou2.equals(zbor.get(j).oraseDestinatie)  ) {
		System.out.println(zbor.get(j).oraseDestinatie);
		ok=true;
		}
		else {
			System.out.println("Eroare");
			ok=false;
		}
		}
		mesajModificare.setVisible(true);
		mesajConfirma();
	}
	
	public void mesajConfirma() {
		if(ok==true) {
			mesajModificare.setText("Succes! ");
			mesajModificare.setForeground(Color.green);
			new Interfata6();
		}
			else {
				mesajModificare.setText("Eroare! Orasul nu este disponibil! ");
				mesajModificare.setForeground(Color.red);
			}
			 
		}

	
	public  JTable cauta(int numar){
		
		int j = numar;
		System.out.println("j" +j + "   index: "+numar);
		boolean gasit=false;
		String[] columsNames= {
			"Nr","OrasPlecare","OrasDestinatie"
	};
	Object[][] h= {};
	setLayout(new FlowLayout());
	table=new JTable(h,columsNames);
	JTable table = new JTable(new DefaultTableModel(h,new Object[]{
			"Nr Zbor","OrasPlecare","OrasDestinatie","DataPlecare","DataIntoarcere"
	}));
	DefaultTableModel model = (DefaultTableModel) table.getModel();
	Rezervari A=new Rezervari();
	ArrayList<Zbor> cautareZbor=A.getRezervari();
	Object[] data=new Object[10];


		data[0]=numar+1;
	    data[1]=cautareZbor.get(numar).orasePlecare;	
	    data[2]=cautareZbor.get(numar).oraseDestinatie;	
	    data[3]=cautareZbor.get(numar).dataPleacare;
	    data[4]=cautareZbor.get(numar).dataIntoarcere;
	    data[5]=cautareZbor.get(numar).piloti;
	    data[6]=cautareZbor.get(numar).insotitori;
	    data[7]=cautareZbor.get(numar).escale;
	    model.addRow(data);


	table.setPreferredScrollableViewportSize(new Dimension(800,300));
	table.setFillsViewportHeight(false);
	if(gasit==true);
	return table;
}
	

}