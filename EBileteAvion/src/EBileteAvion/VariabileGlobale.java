package EBileteAvion;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class VariabileGlobale {
	
	private static  String actiune;
	private static Rezervare rezervareNoua = new Rezervare();
	private static Rezervare rezervareExistenta = new Rezervare();
	private static ArrayList<Rezervare> rezervari = InformatiiZboruri.getRezervari();
	private static ArrayList<Zbor> listaCompletaZboruri = InformatiiZboruri.getListaCompletaZboruri();
	private static ArrayList<Zbor> listaZboruriDisponibile = InformatiiZboruri.getListaZboruriDisponibile();
	private static ArrayList<Utilizator> utilizatori = InformatiiZboruri.getUtilizatori();
	private static ArrayList<Card> carduri = InformatiiZboruri.getCarduri();
	private static boolean admin = false;
	private static String pretBiletNou;
	
	public static void setActiune(String varActiune)
	{
		actiune = varActiune;
	}
	public static String getActiune()
	{
		return actiune;
	}
	
	public static Rezervare getRezervare(boolean nou)
	{
		if(nou == true)
		{
			return rezervareNoua;
		}
		else
		{
			return rezervareExistenta;
		}
	}
	
	public static void setRezervare(Rezervare rezervare,String nou)
	{
		if(nou == "nou")
		{
			rezervareNoua = rezervare;
		}
		else
		{
			rezervareExistenta = rezervare;
		}
	}
	
	public static  void setRezervari(ArrayList<Rezervare> varRezervari)
	{
		rezervari = varRezervari;
	}
	public static ArrayList<Rezervare> getRezervari()
	{
		return rezervari;
	}
	
	public static void setListaCompletaZboruri(ArrayList<Zbor> varZboruri)
	{
		listaCompletaZboruri = varZboruri;
	}
	public static ArrayList<Zbor> getListaCompletaZboruri()
	{
		return listaCompletaZboruri;
	}
	
	public static void setListaZboruriDisponibile(ArrayList<Zbor> varZboruri)
	{
		listaZboruriDisponibile = varZboruri;
	}
	public static ArrayList<Zbor> getListaZboruriDisponibile()
	{
		return listaZboruriDisponibile;
	}
	
	public static void setUtilizatori(ArrayList<Utilizator> varUtilizatori)
	{
		utilizatori = varUtilizatori;
	}
	public static ArrayList<Utilizator> getUtilizatori()
	{
		return utilizatori;
	}
	
	public static void setCarduri(ArrayList<Card> varCarduri)
	{
		carduri = varCarduri;
	}
	public static ArrayList<Card> getCarduri()
	{
		return carduri;
	}
	
	public static void setAdmin(boolean varAdmin)
	{
		admin = varAdmin;
	}
	public static boolean getAdmin()
	{
		return admin;
	}
	
	public static void setPretBiletNou(String varPretBiletNou)
	{
		pretBiletNou = varPretBiletNou;
	}
	public static String getPretBiletNou()
	{
		return pretBiletNou;
	}
}