package EBileteAvion;


import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
public class Rezervare extends Zbor {

	private  String nume,prenume,Email,metodaPlata;
	private int codRezervare,tipBilet;
	
	public void setNume(String varNume)
	{
		nume = varNume;
	}
	public String getNume()
	{
		return nume;
	}
		
	public void setPrenume(String varPrenume)
	{
		prenume = varPrenume;
	}
	public String getPrenume()
	{
		return prenume;
	}
	
	public void setOrasPlecare(String varOrasPlecare)
	{
		super.setOrasPlecare(varOrasPlecare);
	}
	public String getOrasPlecare()
	{
		return super.getOrasPlecare();
	}
	
	public void setOrasDestinatie(String varOrasDestinatie)
	{
		super.setOrasDestinatie(varOrasDestinatie);
	}
	public String getOrasDestinatie()
	{
		return super.getOrasDestinatie();
	}
	
	public void  setDataPlecare(String varDataPlecare)
	{
		super.setDataPlecare(varDataPlecare);
	}
	public String getDataPlecare()
	{
		return super.getDataPlecare();
	}
	
	public void  setDataIntoarcere(String varDataIntoarcere)
	{
		super.setDataIntoarcere(varDataIntoarcere);
	}
	public String getDataIntoarcere()
	{
		return super.getDataIntoarcere();
	}
	
	public void setEmail(String varEmail)
	{
		  	String pattern = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
	        Pattern regex = Pattern.compile(pattern);

	        Matcher matcher = regex.matcher(varEmail);

	        if(matcher.matches() == true)
	        {
	        	Email = varEmail;
	        }
	        else
	        {
	        	JOptionPane.showMessageDialog(null, "Adresa de email invalida!");
	        }
	}
	public String getEmail()
	{
		return Email;
	}
	
	public void setMetodaPlata(String varMetodaPlata)
	{
		metodaPlata = varMetodaPlata;
	}
	public String getMetodaPlata()
	{
		return metodaPlata;
	}
	
	public void setPret(float varPret)
	{
		super.setPret(varPret);
	}
	public float getPret()
	{
		return super.getPret();
	}
	
	public void setCodRezervare(int varCodRezervare)
	{
		codRezervare = varCodRezervare;
	}
	public int getCodRezervare()
	{
		return codRezervare;
	}
	
	public void setTipBilet(int varTipBilet)
	{
		tipBilet = varTipBilet;
	}
	public int getTipBilet()
	{
		return tipBilet;
	}
}