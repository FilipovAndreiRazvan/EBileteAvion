package EBileteAvion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

public class DetaliiUtilizator {

		JLabel titlu = new JLabel("Introduceti urmatoaarele informatii!");
		JLabel Nume = new JLabel("Nume:");
		JLabel Prenume = new JLabel("Prenume:");
		JLabel Email = new JLabel("Email:");
		 
		
		JTextField  nume = new JTextField(10);
		JTextField  prenume = new JTextField(10);
		JTextField  email = new JTextField(10);
		
		JFrame frame = new JFrame();
		

		public DetaliiUtilizator() {
			
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		
		
		JButton button = new JButton("Trimite");
		
		panel1.setBorder(BorderFactory.createEmptyBorder(25,50,50,50));
		panel1.setLayout(new GridLayout(0,1));
		panel1.add(Nume);
		panel1.add(nume);
		panel1.add(Prenume);
		panel1.add(prenume);
		panel1.add(Email);
		panel1.add(email);
		
		panel2.setBorder(BorderFactory.createEmptyBorder(0,50,50,50));
		panel2.add(button);
		
		panel3.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));
		panel3.add(titlu,BorderLayout.CENTER);
		
		frame.add(panel1,BorderLayout.CENTER);
		frame.add(panel2,BorderLayout.SOUTH);
		frame.add(panel3,BorderLayout.NORTH);
		frame.setTitle("DetaliiPasager");
		frame.setSize(150,150);
		frame.pack();
		frame.setLayout(null);
		frame.setVisible(true);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

					

						VariabileGlobale.getRezervare(true).setNume(nume.getText());
						VariabileGlobale.getRezervare(true).setPrenume(prenume.getText());
						VariabileGlobale.getRezervare(true).setEmail(email.getText());
						
						if(VariabileGlobale.getRezervare(true).getEmail() != null)
						{
							frame.setVisible(false);
							new Interfata1();
						}
						

			}
			});
	           
		
		  frame.addWindowListener((WindowListener) new WindowAdapter() {
	            @Override
	            public void windowClosing(WindowEvent e) {
	            	Main.initializare();
	            }});
		}
		
		
}
