package EBileteAvion;

import javax.swing.table.*;
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
import javax.swing.table.DefaultTableModel;
 public class Interfata2  implements ActionListener{

		JLabel label1 = new JLabel("Zboruri Disponibile");
		JLabel label2 = new JLabel("Introduceti codul zborului:");
		
		JFrame frame = new JFrame();
		JTextField  nrZborTxt=new JTextField(10);
		
		Zbor zbor = new Zbor();
		public int nrZbor;
		
		
		
		public  Interfata2(String orasPlecare, String orasDestinatie,String dataPlecare,String dataIntoarcere) {
			
			JButton button = new JButton("Selecteaza");
			JPanel panel1 = new JPanel();
			JPanel panel2 = new JPanel();
			button.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			button.setSize(50, 50);
			button.addActionListener(this);
			 
			JTable tabel = cauta(orasPlecare, orasDestinatie,dataPlecare,dataIntoarcere);
			JScrollPane scrollPane1 = new JScrollPane(tabel);
			
		
			panel1.add(label2,BorderLayout.WEST);
			panel1.add(nrZborTxt,BorderLayout.CENTER);
			panel1.add(button,BorderLayout.EAST);
			panel1.setBorder(BorderFactory.createEmptyBorder(10,0,10,10));
			
			panel2.add(label1,BorderLayout.CENTER);
			
			scrollPane1.setVisible(true);
			scrollPane1.setBorder(BorderFactory.createEmptyBorder(50,10,10,10));
		    
			frame.add(scrollPane1,BorderLayout.CENTER);
			frame.add(panel1,BorderLayout.SOUTH);
			frame.add(panel2,BorderLayout.NORTH);
			frame.setTitle("Zboruri Disponibile-EBileteAvion");
			frame.setSize(150,150);
			frame.pack();
			frame.setLayout(null);
			frame.setVisible(true);
			
			VariabileGlobale.getRezervare(true).setOrasPlecare(orasPlecare);
			VariabileGlobale.getRezervare(true).setOrasDestinatie(orasDestinatie);
			
			if(VariabileGlobale.getActiune() == "rezerva")
			{
				VariabileGlobale.getRezervare(true).setDataPlecare(dataPlecare);
				VariabileGlobale.getRezervare(true).setDataIntoarcere(dataIntoarcere);
			}
			
			frame.addWindowListener((WindowListener) new WindowAdapter() {
		        public void windowClosing(WindowEvent e) {
		        	Main.initializare();
		        }});
		}
		
		public void actionPerformed(ActionEvent e) {
			int codZbor = Integer.parseInt(nrZborTxt.getText());
			int index = 0,zborGasit = 0;
			for(int i = 0;i < VariabileGlobale.getListaZboruriDisponibile().size();i++)
			{
				if(VariabileGlobale.getListaZboruriDisponibile().get(i).getCod() == codZbor)
				{
					index = i;
					zborGasit = 1;
				}
			}
			if(zborGasit == 0)
			{
				JOptionPane.showMessageDialog(null,"Nu am gasit niciun zbor cu codul de rezervare "+codZbor);
			}
			else
			{
				if(VariabileGlobale.getActiune() == "rezerva") 
				{
					new Interfata3(index);		    	
				}
				else
				{
					VariabileGlobale.getRezervare(true).setNume(VariabileGlobale.getRezervare(false).getNume());
					VariabileGlobale.getRezervare(true).setPrenume(VariabileGlobale.getRezervare(false).getPrenume());
					VariabileGlobale.getRezervare(true).setDataPlecare(VariabileGlobale.getListaZboruriDisponibile().get(index).getDataPlecare());
					VariabileGlobale.getRezervare(true).setDataIntoarcere(VariabileGlobale.getListaZboruriDisponibile().get(index).getDataIntoarcere());
					new Interfata3(index);
					frame.setVisible(false);
				}
			}
			
		}
		
		
		public static int nr;
		public static   JTable cauta(String orasPlecare,String orasDestinatie,String dataPlecare,String dataIntoarcere){

		Object[][] h = {};
		JTable tabel = new JTable(new DefaultTableModel(h,new Object[]{
				"Nr Zbor","OrasPlecare","OrasDestinatie","DataPlecare","DataIntoarcere","Piloti","Insotitori","Escale","Cod Zbor"
		}));
		
		DefaultTableModel model = (DefaultTableModel) tabel.getModel();
		ArrayList<Zbor> listaZboruri = InformatiiZboruri.getListaZboruriDisponibile();
		
		var data = new Object[9];
		int index = 0 ;
		for(int j = 0;j < listaZboruri.size();j++){
			if(dataPlecare != "" && dataIntoarcere != "") {
			if(listaZboruri.get(j).getOrasPlecare().equalsIgnoreCase(orasPlecare) && listaZboruri.get(j).getOrasDestinatie().equalsIgnoreCase(orasDestinatie) 
					&& listaZboruri.get(j).getDataPlecare().equalsIgnoreCase(dataPlecare) && 
					listaZboruri.get(j).getDataIntoarcere().equalsIgnoreCase(dataIntoarcere)) 
				{
					index++;
					data[0] = index;
					data[1] = listaZboruri.get(j).getOrasPlecare();	
					data[2] = listaZboruri.get(j).getOrasDestinatie();	
					data[3] = listaZboruri.get(j).getDataPlecare();
					data[4] = listaZboruri.get(j).getDataIntoarcere();
					data[5] = listaZboruri.get(j).getPiloti();
					data[6] = listaZboruri.get(j).getInsotitori();
					data[7] = listaZboruri.get(j).getEscale();
					data[8] = listaZboruri.get(j).getCod();
					model.addRow(data);
				}
			}
			else if(listaZboruri.get(j).getOrasPlecare().equalsIgnoreCase(orasPlecare) && listaZboruri.get(j).getOrasDestinatie().equalsIgnoreCase(orasDestinatie))
			{
				index++;
				data[0] = index;
				data[1] = listaZboruri.get(j).getOrasPlecare();	
				data[2] = listaZboruri.get(j).getOrasDestinatie();	
				data[3] = listaZboruri.get(j).getDataPlecare();
				data[4] = listaZboruri.get(j).getDataIntoarcere();
				data[5] = listaZboruri.get(j).getPiloti();
				data[6] = listaZboruri.get(j).getInsotitori();
				data[7] = listaZboruri.get(j).getEscale();
				data[8] = listaZboruri.get(j).getCod();
				model.addRow(data);
			}
		}
	

			tabel.setPreferredScrollableViewportSize(new Dimension(800,300));
			tabel.setFillsViewportHeight(false);
		
			return tabel;
		}		
}