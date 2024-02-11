package EBileteAvion;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AdminLogin {
	
		JLabel label1 = new JLabel("Introduceti datele de logare:");
		JLabel label2 = new JLabel("UserName:");
		JLabel label3 = new JLabel("Parola:");
		
		JCheckBox checkBox1 = new JCheckBox("Afisati parola");   
		
		JTextField  textField1 = new JTextField(10);
		JPasswordField textField2 = new  JPasswordField(10);
		
		JFrame frame = new JFrame();

		public AdminLogin() {
			
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
		frame.setTitle("Login");
		frame.setSize(150,150);
		frame.pack();
		frame.setLayout(null);
		frame.setVisible(true);

		
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
					
				String password = String.valueOf(textField2.getPassword());
					if(textField1.getText().equals("admin") && password.equals("4321"))
					{
						VariabileGlobale.setAdmin(true);
						frame.setVisible(false);
						 Main.initializare();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Datele sunt incorecte!");
						textField1.setText("");
						textField2.setText("");
					}
			}
			});
		
	    checkBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform actions when the checkbox is selected or deselected
                char currentEchoChar = textField2.getEchoChar();

                if (currentEchoChar == 0) {
                	textField2.setEchoChar('\u2022');
                } else {
                	textField2.setEchoChar((char) 0);
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
