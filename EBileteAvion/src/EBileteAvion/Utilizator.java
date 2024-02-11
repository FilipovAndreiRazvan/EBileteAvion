package EBileteAvion;

public class Utilizator {
	
	private  String nume,parola,email;
	private float suma;
	
	public void setNume(String varNume)
	{
		nume = varNume;
	}
	public String getNume()
	{
		return nume;
	}
	
	public void setParola(String varParola)
	{
		parola = varParola;
	}
	public String getParola()
	{
		return parola;
	}
	
	public void setEmail(String varEmail)
	{
		email = varEmail;
	}
	public String getEmail()
	{
		return email;
	}
	
	public void setSuma(float varSuma)
	{
		suma = varSuma;
	}
	public float getSuma()
	{
		return suma;
	}
}
