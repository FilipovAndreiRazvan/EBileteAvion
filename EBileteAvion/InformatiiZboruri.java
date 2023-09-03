package EBileteAvion;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class InformatiiZboruri {

	 File fisier1=new File("src\\EBileteAvion\\OrasePlecare.txt");
	 File fisier2=new File("src\\EBileteAvion\\OraseDestinatie.txt");
	 File fisier3=new File("src\\EBileteAvion\\DatePlecare.txt");
	 File fisier4=new File("src\\EBileteAvion\\DateIntoarcere.txt");
	 File fisier5=new File("src\\EBileteAvion\\Personal.txt");
	 File fisier6=new File("src\\EBileteAvion\\escale&nrLocuri.txt");
	 ArrayList<Zbor> Zbor=new ArrayList<Zbor>();

public  ArrayList<Zbor> getListaZboruri(){  //Creare lista zboruri
	
	
	
	 try (Scanner f1 = new Scanner(fisier1);
			Scanner f2 = new Scanner(fisier2);
			Scanner f3 = new Scanner(fisier3);
			Scanner f4 = new Scanner(fisier4);
			Scanner f5 = new Scanner(fisier5);
			Scanner f6 = new Scanner(fisier6)) {
		   while(f1.hasNextLine() && f2.hasNextLine() && f3.hasNextLine() && f4.hasNextLine() && f5.hasNextLine()&& f6.hasNextLine()) {
			   String sir1=f1.nextLine();
			   String sir2=f2.nextLine();
			   String sir3=f3.nextLine();
			   String sir4=f4.nextLine();
			   String sir5 = f5.nextLine();
			   String sir6 = f6.nextLine();
			   String[] separator = new String[3];
			   String[] separator1 = new String[3];

			   separator= sir5.split(" .. ");
			Zbor  cursa=new Zbor();
			cursa.orasePlecare=sir1;
			cursa.oraseDestinatie=sir2;
				cursa.dataPleacare =sir3;
				cursa.dataIntoarcere =sir4;
				cursa.piloti=separator[0];
				cursa.insotitori=separator[1];
				separator1= sir6.split(" .. ");
				cursa.escale=separator1[0];
				
				cursa.numarLocuri=1000;
				cursa.zborDeschis=true;
			Zbor.add(cursa);

		   }
		  
	   }
	 catch(IOException a) {
		   System.out.println(a);
	 } catch (NumberFormatException e) {
		// TODO Auto-generated catch block
	}
	
	 return Zbor;
}
}