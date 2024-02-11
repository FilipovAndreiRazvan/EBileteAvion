package EBileteAvion;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

public class CreareZbor {

	ArrayList<Utilizator> utilizatori = VariabileGlobale.getUtilizatori();
		
	JFrame frame = new JFrame();
	Zbor modifica = new Zbor();
	
	JLabel label1 = new JLabel("Oras de plecare");
	JLabel label2 = new JLabel("Oras destinatie");
	JLabel label3 = new JLabel("Data plecare");
	JLabel label4 = new JLabel("Data intoarcere");
	JLabel label5 = new JLabel("Pilot 1");
	JLabel label6 = new JLabel("Pilot 2");
	JLabel label7 = new JLabel("Insotitor 1");
	JLabel label8 = new JLabel("Insotitor 2");
	JLabel label9 = new JLabel("Escale");
	JLabel label10 = new JLabel("Nr locuri clasa I");
	JLabel label11 = new JLabel("Nr locuri clasa II");
	JLabel label12 = new JLabel("Nr locuri clasa III");
	JLabel label13 = new JLabel("Pret bilet");

	JTextField  textField3 = new JTextField(10);
	JTextField  textField4 = new JTextField(10);
	JTextField  textField9 = new JTextField(10);
	JTextField  textField10 = new JTextField(10);
	JTextField  textField11 = new JTextField(10);
	JTextField  textField12 = new JTextField(10);
	JTextField  textField13 = new JTextField(10);
	
	String[] orase = {"-alege-","Amsterdam", "Atena", "Berlin", "Bern", "Bratislava", "Bruxelles", "București", "Budapesta", "Chișinău", "Copenhaga", "Dublin", "Helsinki", "Lisabona", "Ljubljana", "Londra", "Madrid", "Oslo", "Paris", "Praga", "Reykjavik", "Riga", "Roma", "Sofia", "Stockholm", "Tallinn", "Varșovia", "Viena", "Vilnius"};
	String[] piloti = {"-alege-","Adrian", "Alexandru", "Andrei", "Bogdan", "Cristian", "Cosmin", "Daniel", "Eduard", "Florin", "Gabriel", "Ion", "Mihai", "Răzvan", "Sorin", "Ștefan"};
	String[] insotitori = {"-alege-","Alina", "Ana", "Alexandra", "Andreea", "Cristina", "Daniela", "Diana", "Elena", "Gabriela", "Ioana", "Laura", "Maria", "Mihaela", "Roxana", "Simona"};	
	
	JButton button = new JButton("Creaza");
	
	JDialog calendarDialog = new JDialog(frame, "Calendar", true);
    JCalendar calendar = new JCalendar();
   
	public CreareZbor() {
        
	LocalDate dataCurenta = LocalDate.now();	
	ZoneId zoneId = ZoneId.systemDefault();
    Date data = Date.from(dataCurenta.atStartOfDay(zoneId).toInstant());
    calendar.setMinSelectableDate(data);
    
	JComboBox<String> orasPlecare = new JComboBox<>(orase);
	JComboBox<String> orasDestinatie= new JComboBox<>(orase);
	JComboBox<String> pilot1 = new JComboBox<>(piloti);
	JComboBox<String> pilot2 = new JComboBox<>(piloti);
	JComboBox<String> insotitor1 = new JComboBox<>(insotitori);
	JComboBox<String> insotitor2 = new JComboBox<>(insotitori);
	
	itemDiferit(orasPlecare,orasDestinatie,orase);
	itemDiferit(pilot1,pilot2,piloti);
	itemDiferit(insotitor1,insotitor2,insotitori);
	
	

	label1.setBounds(50, 10, 100, 30);
	orasPlecare.setBounds(200, 10, 100, 30);
	
	frame.add(label1);
	frame.add(orasPlecare);
	
	label2.setBounds(50, 40, 100, 30);
	orasDestinatie.setBounds(200, 40, 100, 30);
	
	frame.add(label2);
	frame.add(orasDestinatie);
	
	label3.setBounds(50, 70, 100, 30);
	textField3.setBounds(200, 70, 100, 30);
	
	frame.add(label3);
	frame.add(textField3);
	
	label4.setBounds(50, 100, 100, 30);
	textField4.setBounds(200, 100, 100, 30);
	
	frame.add(label4);
	frame.add(textField4);
	
	label5.setBounds(50, 130, 100, 30);
	pilot1.setBounds(200, 130, 100, 30);
	
	frame.add(label5);
	frame.add(pilot1);
	
	label6.setBounds(50, 160, 100, 30);
	pilot2.setBounds(200, 160, 100, 30);
	
	frame.add(label6);
	frame.add(pilot2);
	
	label7.setBounds(50, 190, 100, 30);
	insotitor1.setBounds(200, 190, 100, 30);
	
	frame.add(label7);
	frame.add(insotitor1);
	
	label8.setBounds(50, 220, 100, 30);
	insotitor2.setBounds(200, 220, 100, 30);
	
	frame.add(label8);
	frame.add(insotitor2);
	
	label9.setBounds(50, 250, 100, 30);
	textField9.setBounds(200, 250, 100, 30);
	
	frame.add(label9);
	frame.add(textField9);
	
	label10.setBounds(50, 280, 100, 30);
	textField10.setBounds(200, 280, 100, 30);
	
	frame.add(label10);
	frame.add(textField10);
	
	label11.setBounds(50, 310, 100, 30);
	textField11.setBounds(200, 310, 100, 30);
	
	frame.add(label11);
	frame.add(textField11);
	
	label12.setBounds(50, 340, 100, 30);
	textField12.setBounds(200, 340, 100, 30);
	
	frame.add(label12);
	frame.add(textField12);
	
	label13.setBounds(50, 370, 100, 30);
	textField13.setBounds(200, 370, 100, 30);
	
	frame.add(label13);
	frame.add(textField13);
	
    frame.add(new JLabel("Select Date: "));
    frame.add(calendar);
	
	button.setBounds(120, 420, 100, 30);
	
	afisareCalendar(textField3);

	afisareCalendar(textField4);
	
	frame.add(button);
	frame.setSize(350,500);
	frame.add(label1);
	frame.setLayout(null);
	frame.setTitle("CreareZbor");
	frame.setVisible(true);

	
	
	button.addActionListener(new ActionListener() {		
		
		public void actionPerformed(ActionEvent arg0) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			 Date dataPlecare = null;
			try {
				dataPlecare = dateFormat.parse(textField3.getText());
			} catch (ParseException e) {
				
			}
			 Date dataIntoarcere = null;
			try {
				dataIntoarcere = dateFormat.parse(textField4.getText());
			} catch (ParseException e) {
				
			}
			if(orasPlecare.getSelectedItem().toString().equals("-alege-") || orasDestinatie.getSelectedItem().toString().equals("-alege-") ||  
					pilot1.getSelectedItem().toString().equals("-alege-") || pilot2.getSelectedItem().toString().equals("-alege-") || 
					insotitor1.getSelectedItem().toString().equals("-alege-") || insotitor2.getSelectedItem().toString().equals("-alege-")||
					textField3.getText().isEmpty() == true || textField4.getText().isEmpty() == true|| textField9.getText().isEmpty() == true ||
					textField10.getText().isEmpty() == true ||  textField11.getText().isEmpty() == true || textField12.getText().isEmpty() == true ||
					textField13.getText().isEmpty() == true)
			{
				JOptionPane.showMessageDialog(null, "Toate campurile sunt obligatorii!"); 
			}
			else if(dataPlecare.compareTo(dataIntoarcere) > 0)
			{
				JOptionPane.showMessageDialog(null, "Data de plecare trebuie sa fie inaintea datei de intoarcere"); 
			}
			else
			{
				String numeFisier = "src\\EBileteAvion\\Zboruri.txt";
				Zbor zborCreat = new Zbor();
				zborCreat.setOrasPlecare((String) orasPlecare.getSelectedItem());
				zborCreat.setOrasDestinatie((String) orasDestinatie.getSelectedItem());
				zborCreat.setDataPlecare(textField3.getText());
				zborCreat.setDataIntoarcere(textField4.getText());
				zborCreat.setPiloti(pilot1.getSelectedItem()+" "+pilot2.getSelectedItem());
				zborCreat.setInsotitori(insotitor1.getSelectedItem()+" "+insotitor2.getSelectedItem());
				zborCreat.setEscale(textField9.getText());
				zborCreat.setNrLocuri(Integer.parseInt(textField10.getText()),Integer.parseInt(textField11.getText()),Integer.parseInt(textField12.getText()));
				zborCreat.setPret(Float.parseFloat(textField13.getText()));
				zborCreat.setZborDeschis(true);
				
				    
			      try (BufferedWriter scriere = new BufferedWriter(new FileWriter(numeFisier,true)))
					 {
			    	  scriere.write("\n"+orasPlecare.getSelectedItem()+","+orasDestinatie.getSelectedItem()+","+textField3.getText()+","+textField4.getText()+","+pilot1.getSelectedItem()+" "+pilot2.getSelectedItem()+","+insotitor1.getSelectedItem()+
			    	  " "+insotitor2.getSelectedItem()+","+textField9.getText()+","+textField10.getText()+","+textField11.getText()+","+textField12.getText()+","+textField13.getText()+","+"true"
			    			  );
					 scriere.close();
					 }
			      catch (IOException e1) 
			      	{ 
					  System.out.println( "Eroare scriere rezervare!");
			      	}
			      frame.setVisible(false);
			      VariabileGlobale.getListaCompletaZboruri().add(zborCreat);
			      JOptionPane.showMessageDialog(null, "Zborul a fost creat!"); 
			}
		}
		});
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
               // TODO Auto-generated method stub
           	focusHandled = false;
           }	        });
	}
	
	public void itemDiferit(JComboBox<String> lista1,JComboBox<String> lista2,String[] lista3)
	{
		lista1.addItemListener((ItemListener) new ItemListener() {
	        public void itemStateChanged(ItemEvent e) {
	            if (e.getStateChange() == ItemEvent.SELECTED) {
	                String orasSelectat = (String) lista1.getSelectedItem();
	                DefaultComboBoxModel<String> newModel = new DefaultComboBoxModel<>();
	                for (String item : lista3) {
	                    if (!item.equals(orasSelectat)) {
	                        newModel.addElement(item);
	                    }
	                }
	                lista2.setModel(newModel);
	            }
	        }
	    });
	}
	
}
