package EBileteAvion;

public class Card {
	private String nume,prenume,tipCard,nrCard,dataExpirare;
	private float suma;
	private int CVC;
	
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
	
	public void setTipCard(String varTipCard)
	{
		tipCard = varTipCard;
	}
	public String getTipCard()
	{
		return tipCard;
	}
	
	public void setNrCard(String varNrCard)
	{
		nrCard = varNrCard;
	}
	public String getNrCard()
	{
		return nrCard;
	}
	
	public void setDataExpirare(String varDataExpirare)
	{
		dataExpirare = varDataExpirare;
	}
	public String getDataExpirare()
	{
		return dataExpirare;
	}
	
	public void setSuma(float varSuma)
	{
		suma = varSuma;
	}
	public float getSuma()
	{
		return suma;
	}
	
	public void setCVC(int varCVC)
	{
		CVC = varCVC;
	}
	public int getCVC()
	{
		return CVC;
	}
}
