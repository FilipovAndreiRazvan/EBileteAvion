package EBileteAvion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
 public class Interfata6  implements ActionListener{
	    // Tabel lista zboruri gasite
	 int i=0; 
		JLabel label1=new JLabel("Zboruri Disponibile");
		JLabel label2=new JLabel("Introduceti numarul zborului:");
		JLabel label3=new JLabel("Introduceti orasul de destinatie:");
		JFrame frame1=new JFrame();
		JTextField  textField1=new JTextField(10);
		JTextField  textField2=new JTextField(10);
		JTextField  textField3=new JTextField(10);
		JTextField  textField4=new JTextField(10);
		public int nrZbor;
		File f1=new File("E:\\proiecte-eclips\\proiect poo\\src\\proiect\\pasageri.txt");
		public Interfata6() {
			
		}
		public  Interfata6(String orasPlecare, String orasDestinatie,String dataPlecare,String dataIntoarcere) {
			Tabel C=new Tabel();
			JButton button=new JButton("Selecteaza");
			JPanel panel1=new JPanel();
			JPanel panel2=new JPanel();
			
			 button.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			 button.setSize(50, 50);
			 button.addActionListener(this);
			 
			JTable table1=C.cauta(orasPlecare, orasDestinatie,dataPlecare,dataIntoarcere);
			JScrollPane scrollPane1=new JScrollPane(table1);
			
		
			panel1.add(label2,BorderLayout.WEST);
			 panel1.add(textField1,BorderLayout.CENTER);
			 panel1.add(button,BorderLayout.EAST);
			 panel2.add(label1,BorderLayout.CENTER);
			 panel1.setBorder(BorderFactory.createEmptyBorder(10,0,10,10));
			   scrollPane1.setVisible(true);
			scrollPane1.setBorder(BorderFactory.createEmptyBorder(50,10,10,10));
		    frame1.add(scrollPane1,BorderLayout.CENTER);
		    frame1.add(panel1,BorderLayout.SOUTH);
		    frame1.add(panel2,BorderLayout.NORTH);
			frame1.setTitle("Zboruri Disponibile");
			frame1.setSize(150,150);
			frame1.pack();
			frame1.setLayout(null);
			frame1.setVisible(true);
		}
		public void actionPerformed(ActionEvent e) {
			i++;
	    	String indexZbor=textField1.getText();
	      	nrZbor=Integer.parseInt(indexZbor);
	    	textField1.setText(null);

			
	    	new Interfata3();
			frame1.setVisible(false);

	        
		}
}