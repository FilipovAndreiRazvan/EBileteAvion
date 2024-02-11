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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ModificaRezervare {
	
	static JTextField  textField = new JTextField(10);
	static int pozitie;
	
	public static void initializare(int index) {
		pozitie = index;
		
		VariabileGlobale.setRezervare(VariabileGlobale.getRezervari().get(index),"false");
		
		JFrame frame = new JFrame();
			
		JButton button = new JButton("Cauta");
		
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
	
		JTable table = Tabel.selecteaza(index);
		JScrollPane scrollPanel = new JScrollPane(table);
		JLabel label = new JLabel("Introduceti noua destinatie");
			
		panel1.add(label);
		panel1.add(textField);
		panel1.add(button);
		panel1.setLayout(new FlowLayout());
		panel1.setBorder(BorderFactory.createEmptyBorder(10,0,10,10));
		
		scrollPanel.setVisible(true);
		scrollPanel.setBorder(BorderFactory.createEmptyBorder(50,10,10,10));
		
		frame.add(scrollPanel,BorderLayout.CENTER);
		frame.add(panel1,BorderLayout.SOUTH);
		frame.add(panel2,BorderLayout.NORTH);
		frame.setTitle("Modifica Rezervare-EBileteAvion");
		frame.setSize(150,150);
		frame.pack();
		frame.setLayout(null);
		frame.setVisible(true);
		

		frame.addWindowListener((WindowListener) new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	        	Main.initializare();
	        }});
		
		button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			String destinatieNoua=textField.getText();
			boolean zborGasit=false;
			
			for (Zbor zbor :VariabileGlobale.getListaZboruriDisponibile() ) {
				if (zbor.getOrasDestinatie().equals(destinatieNoua) && zbor.getOrasPlecare().equals(VariabileGlobale.getRezervari().get(index).getOrasPlecare())) {
					zborGasit = true;
					break; 
				}
			}
			if(zborGasit == false)
			{
				JOptionPane.showMessageDialog(null, "Nu am gasit niciun zbor!");
			}
			else
			{
				Main.actualizareZboruri();
				frame.setVisible(false);
				new Interfata2(VariabileGlobale.getRezervari().get(pozitie).getOrasPlecare(),destinatieNoua,"","");
			}
		}
	
		});
	}
}
