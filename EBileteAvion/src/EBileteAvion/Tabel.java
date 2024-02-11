package EBileteAvion;


import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;


public class Tabel extends JFrame {

	static JTable table;
	JTable table1;
	
	
	public static  JTable adaugaTabel(){
		
		String[] columsNames = {
			"Nr","OrasPlecare","OrasDestinatie"
	};
		
	Object[][] h = {};
	
	table=new JTable(h,columsNames);
	JTable table = new JTable(new DefaultTableModel(h,new Object[]{
			"Nr Zbor","OrasPlecare","OrasDestinatie","DataPlecare","DataIntoarcere","Piloti","Insotitori","Escale"
	}));
	
	DefaultTableModel model = (DefaultTableModel) table.getModel();
	ArrayList<Zbor> listaZboruri = VariabileGlobale.getListaZboruriDisponibile();
	
	Object[] data = new Object[10];

	for(int j = 0;j < listaZboruri.size();j++){
		if(listaZboruri.get(j).getZborDeschis() == true) {
		data[0] = j+1;
	    data[1] = listaZboruri.get(j).getOrasPlecare();	
	    data[2] = listaZboruri.get(j).getOrasDestinatie();	
	    data[3] = listaZboruri.get(j).getDataPlecare();
	    data[4] = listaZboruri.get(j).getDataIntoarcere();
	    data[5] = listaZboruri.get(j).getPiloti();
	    data[6] = listaZboruri.get(j).getInsotitori();
	    if(listaZboruri.get(j).getEscale().equals(" -"))
	    	data[7] = "Nu exista escale.";
	    else
	    	data[7] = listaZboruri.get(j).getEscale();

	model.addRow(data);
		}
	}

	table.setEnabled(false);
	table.setPreferredScrollableViewportSize(new Dimension(800,400));
	table.setFillsViewportHeight(false);
	
	return table;
}
	
	public static JTable selecteaza(int nr) {
		
		String[] columsNames = {
			"Nr","OrasPlecare","OrasDestinatie"
		};
		
		Object[][] h = {};
	
		table=new JTable(h,columsNames);
		JTable table = new JTable(new DefaultTableModel(h,new Object[]{
			"Nr Zbor","OrasPlecare","OrasDestinatie","DataPlecare","DataIntoarcere"
		}));
	
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		ArrayList<Rezervare> listaZboruri = VariabileGlobale.getRezervari();
	
		Object[] data = new Object[5];
		nr++;
		data[0] = nr;
		nr--;
		data[1] = listaZboruri.get(nr).getOrasPlecare();	
		data[2] = listaZboruri.get(nr).getOrasDestinatie();	
		data[3] = listaZboruri.get(nr).getDataPlecare();
		data[4] = listaZboruri.get(nr).getDataIntoarcere();
    
		model.addRow(data);
    
		return table;
	}
}