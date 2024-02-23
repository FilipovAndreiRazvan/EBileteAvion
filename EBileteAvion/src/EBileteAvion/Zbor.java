package EBileteAvion;
public class Zbor {
  private  String orasPlecare,orasDestinatie,dataPlecare, dataIntoarcere;
  private String piloti, insotitori,escale;
  private int cod;
  private int[] nrLocuri = new int[3];
  private float pret;
  private boolean zborDeschis;
  	
  	public void setOrasPlecare(String varOrasPlecare)
	{
		orasPlecare = varOrasPlecare;
	}
	public String getOrasPlecare()
	{
		return orasPlecare;
	}
	
	public void setOrasDestinatie(String varOrasDestinatie)
	{
		orasDestinatie = varOrasDestinatie;
	}
	public String getOrasDestinatie()
	{
		return orasDestinatie;
	}
	
	public void  setDataPlecare(String varDataPlecare)
	{
		dataPlecare = varDataPlecare;
	}
	public String getDataPlecare()
	{
		return dataPlecare;
	}
	
	public void  setDataIntoarcere(String varDataIntoarcere)
	{
		dataIntoarcere = varDataIntoarcere;
	}
	public String getDataIntoarcere()
	{
		return dataIntoarcere;
	}
  
	public void setPiloti(String varPiloti)
	{
		piloti = varPiloti;
	}
	public String getPiloti()
	{
		return piloti;
	}
	
	public void setInsotitori(String varInsotitori)
	{
		insotitori = varInsotitori;
	}
	public String getInsotitori()
	{
		return insotitori;
	}
	
	public void setEscale(String varEscale)
	{
		escale = varEscale;
	}
	public String getEscale()
	{
		return escale;
	}
	
	public void setCod(int varCod)
	{
		cod = varCod;
	}
	public int getCod()
	{
		return cod;
	}
	
	public void setNrLocuri(int cls1,int cls2,int cls3)
	{
		nrLocuri[0] = cls1;
		nrLocuri[1] = cls2;
		nrLocuri[2] = cls3;
	}
	public int[] getNrLocuri()
	{
		return nrLocuri;
	}
	
	public void setPret(float varPret)
	{
		pret = varPret;
	}
	public float getPret()
	{
		return pret;
	}
	
	public void setZborDeschis(boolean varZborDeschis)
	{
		zborDeschis = varZborDeschis;
	}
	public boolean getZborDeschis()
	{
		return zborDeschis;
	}
}
