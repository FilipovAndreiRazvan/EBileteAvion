package EBileteAvion;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

import com.toedter.calendar.JCalendar;

 public class Interfata1 implements ActionListener{
	 
		JLabel label1 = new JLabel("Zboruri Disponibile");
		JLabel label2 = new JLabel("Orasul de plecare:");
		JLabel label3 = new JLabel("Orasul de destinatie:");
		JLabel label4 = new JLabel("Data de plecare:");
		JLabel label5 = new JLabel("Data de intoarcere:");
		



		JComboBox orasPlecare = new JComboBox<>();
		JComboBox orasDestinatie = new JComboBox<>();

		
		JTextField  textField1 = new JTextField(10);
		JTextField  textField2 = new JTextField(10);
		JTextField  textField3 = new JTextField(10);
		JTextField  textField4 = new JTextField(10);
		JFrame frame=new JFrame();
		
		JDialog calendarDialog = new JDialog(frame, "Calendar", true);
	    JCalendar calendar = new JCalendar();
	    	
		public  Interfata1(){
			
			LocalDate dataCurenta = LocalDate.now();	
			ZoneId zoneId = ZoneId.systemDefault();
			Date data = Date.from(dataCurenta.atStartOfDay(zoneId).toInstant());
			calendar.setMinSelectableDate(data);
			    
			
			for (Zbor var : VariabileGlobale.getListaZboruriDisponibile()) {
			    orasPlecare.addItem(var.getOrasPlecare());
			}

			for (Zbor var : VariabileGlobale.getListaZboruriDisponibile()) {
			    orasDestinatie.addItem(var.getOrasDestinatie());
			}
			JButton button = new JButton("Cauta");
			JPanel panel1 = new JPanel();
			JPanel panel2 = new JPanel();

			button.setSize(50, 50);
			button.addActionListener(this);
			
			JTable table = new JTable();
			
			
			table=Tabel.adaugaTabel();
			JScrollPane scrollPanel = new JScrollPane(table);
			
			panel1.add(label2);
			panel1.add(orasPlecare);
			panel1.add(label3);
			panel1.add(orasDestinatie);
			panel1.add(label4);
			panel1.add(textField3);
			panel1.add(label5);
			panel1.add(textField4);
			panel1.add(button);
			panel1.setLayout(new FlowLayout());
			panel1.setBorder(BorderFactory.createEmptyBorder(10,0,10,10));
			
			panel2.add(label1,BorderLayout.CENTER);
			
			scrollPanel.setVisible(true);
			scrollPanel.setBorder(BorderFactory.createEmptyBorder(50,10,10,10));
			
			frame.add(scrollPanel,BorderLayout.CENTER);
			frame.add(panel1,BorderLayout.SOUTH);
			frame.add(panel2,BorderLayout.NORTH);
			frame.setTitle("Rezerva-EBileteAvion");
			frame.setSize(150,150);
			frame.pack();
			frame.setLayout(null);
			frame.setVisible(true);
			
			afisareCalendar(textField3);
			afisareCalendar(textField4);
			
			frame.addWindowListener((WindowListener) new WindowAdapter() {
		        public void windowClosing(WindowEvent e) {
		        	Main.initializare();
		        }});
		}
		
		public void afisareCalendar(JTextField field)
		{
			field.addFocusListener((FocusListener) new FocusListener() {
           	 private boolean focusHandled = false;
           	 public void focusGained(FocusEvent e) {
           		 if (!focusHandled) {
           	            calendarDialog.add(calendar);

           	            // Setări pentru dialog
           	            calendarDialog.setSize(300, 300);
           	            calendarDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

           	            // Faceți dialogul vizibil
           	            calendarDialog.setVisible(true);
           	            
           	            Date dataAleasa = calendar.getCalendar().getTime();
           	            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
           	            
           	            
           	            field.setText(format.format(dataAleasa).toString());
           	            calendarDialog.dispose();
           	            focusHandled = true;
           	        }
                }
               @Override
               public void focusLost(FocusEvent e) {
               		focusHandled = false;
               }	        });
		}
		public void actionPerformed(ActionEvent e) {
			String orasPlecare1 = (String) orasPlecare.getSelectedItem();
			String orasDestinatie1 = (String) orasDestinatie.getSelectedItem();
			String dataPlecare = textField3.getText();
			String dataIntoarcere = textField4.getText();
			
			textField1.setText(null);
			textField2.setText(null);
			textField3.setText(null);
			textField4.setText(null);
			
			if(dataPlecare.isEmpty() || dataIntoarcere.isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Toate campurile sunt obligatorii!");
			}
			else if(cautaZbor(orasPlecare1, orasDestinatie1, dataPlecare, dataIntoarcere)==true)
			{
				new Interfata2(orasPlecare1, orasDestinatie1, dataPlecare, dataIntoarcere);
				frame.setVisible(false);
			}
			else 
			{
				JOptionPane.showMessageDialog(null, "Nu este disponibil niciun zbor cu datele:\n"
					+ "Oras de plecare:"+orasPlecare1+
					"\nOras de destinatie:"+orasDestinatie1+
					"\nData de plecare:"+dataPlecare+
					"\nData de intoarcere:"+dataIntoarcere);
				
				frame.setVisible(false);
				Main.initializare();
			}
		
		}
		public  boolean cautaZbor(String orasPlecare,String orasDestinatie,String dataPlecare,String dataIntoarcere) {
			
			ArrayList<Zbor> listaZboruri = InformatiiZboruri.getListaZboruriDisponibile();
			boolean zborGasit = false;
			
			for(int j = 0;j < listaZboruri.size();j++){
				if(
						listaZboruri.get(j).getOrasPlecare().equalsIgnoreCase(orasPlecare) && 
						listaZboruri.get(j).getOrasDestinatie().equalsIgnoreCase(orasDestinatie) && 
						listaZboruri.get(j).getDataPlecare().equalsIgnoreCase(dataPlecare) && 
						listaZboruri.get(j).getDataIntoarcere().equalsIgnoreCase(dataIntoarcere)
					)
				{
					zborGasit = true;
				}	
		}
			return zborGasit;
		}
}
