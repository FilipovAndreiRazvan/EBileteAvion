package EBileteAvion;


public class Bilet {
	private Clasa1 clasa1 = new Clasa1();
	private Clasa2 clasa2 = new Clasa2();
	private Clasa3 clasa3 = new Clasa3();
	private String tipBilet;
	private Integer cod;
	public boolean validare ;
	private String metoda;
	
	
	public Bilet(String clasa, Integer cod) {
		if(clasa.toString()!=null)
			tipBilet = clasa.toString();
			this.cod=cod;
	}
	
	public Bilet() {
		
	}
	
	public void afisareTipBilet() {
		System.out.println("Tipbilet: "+tipBilet+" cod: "+cod+" VALID: " + validare);
	}
	
	public void setValidare(boolean valid) {
		this.validare = valid;
		//trimite valiare
		if(validare == true)
			if(tipBilet.getClass().toString()!=null) {
			plata(metoda);
		}
	}
	
	public  void plata(String metoda) {
		this.metoda=metoda;
		// trimit metoda spre card
	}
	
	public int clasa1(){
		return clasa1.getPret();
	}
	
	public int clasa2(){
		return clasa2.getPret();
	}
	
	public int clasa3(){
		return clasa3.getPret();
	}
	
}
