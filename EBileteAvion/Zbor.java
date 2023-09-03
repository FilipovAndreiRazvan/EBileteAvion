package EBileteAvion;


import java.io.*;
import java.util.*;
public class Zbor { // citire fisier rezervari
  ArrayList<Zbor> Zbor2=new ArrayList<Zbor>();
  InformatiiZboruri A=new InformatiiZboruri();
  public String orasePlecare,oraseDestinatie, piloti, insotitori,escale;
  public int cod, numarLocuri,nr=0;
  public String dataPleacare, dataIntoarcere;
  public boolean zborDeschis,locuriDisponibile;
 
  public int cumparaBilet(int a) // crearea rezervarii
     {  
	  File f2=new File("src\\EBileteAvion\\Rezervari.txt");

		ArrayList<Zbor> Zboruri=A.getListaZboruri();
		Zbor M=new Zbor();
		a--;
		
		modifyFile("src\\EBileteAvion\\escale&nrLocuri.txt",String.valueOf(Zboruri.get(a).numarLocuri) ,String.valueOf(--Zboruri.get(a).numarLocuri));
		
	  M.orasePlecare=Zboruri.get(a).orasePlecare;
	  M.oraseDestinatie=Zboruri.get(a).oraseDestinatie;
	  M.dataPleacare=Zboruri.get(a).dataPleacare;
	  M.dataIntoarcere=Zboruri.get(a).dataIntoarcere;
	  Random random=new Random();
	  M.cod=random.nextInt(100);  // generare cod rezervare random
	  Zbor2.add(M);
	  System.out.println("orasPlecare - Zbor: "+Zbor2.get(0).orasePlecare);
		  try {
			  System.out.println("orasPlecare - Zbor: "+Zbor2.get(0).orasePlecare);
			BufferedWriter scriere=new BufferedWriter(new FileWriter(f2,true));  // scriere rezervarii in fisier
    		scriere.write(Zbor2.get(0).orasePlecare+","+Zbor2.get(0).oraseDestinatie+","+Zbor2.get(0).dataPleacare+","
			+Zbor2.get(0).dataIntoarcere+","+Zbor2.get(0).cod+",");
	
			} catch (IOException e) {
			System.out.println("Eroare scriere zbor.");
			e.printStackTrace();
			}
		  
		  return M.cod;
     }
  

  
  void modifyFile(String filePath, String oldString, String newString)
  {
	  
	  
      File fileToBeModified = new File(filePath);
       
      String oldContent = "";
       
      BufferedReader reader = null;
       
      FileWriter writer = null;
       
      try
      {
          reader = new BufferedReader(new FileReader(fileToBeModified));
         
          String line = reader.readLine();
           
          while (line != null) 
          {
              oldContent = oldContent + line + System.lineSeparator();
               
              line = reader.readLine();
          }
           
          //inlocuirea oldString cu newString în oldContent
           
          String newContent = oldContent.replaceAll(oldString, newString);
           
          //Rescrierea fișierului text de intrare cu newContent
           
          writer = new FileWriter(fileToBeModified);
           
          writer.write(newContent);
      }
      catch (IOException e)
      {
          e.printStackTrace();
      }
      finally
      {
          try
          {
               
              reader.close();
               
              writer.close();
          } 
          catch (IOException e) 
          {
              e.printStackTrace();
          }
      }
  
}}
