package EBileteAvion;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	
	public static void trimitere(String adresaEmail,String orasPlecare, String orasDestinatie, String dataPlecare, String dataIntoarcere, int tipBilet, int codRezervare ,float pret) {
	String senderEmail = "ebileteavion@gmail.com";
    String senderPassword = "cztc rbts ilss fifv";

    // Recipient's email address
    String recipientEmail = adresaEmail;

    // Set the properties for the email server
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.ssl.protocols", "TLSv1.2");

    props.put("mail.smtp.port", "587");


    // Create a Session object
    Session session = Session.getInstance(props, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(senderEmail, senderPassword);
        }
    });

    try {
        // Create a MimeMessage object
        Message message = new MimeMessage(session);

        // Set the sender and recipient addresses
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        String actiune = VariabileGlobale.getActiune();

        // Set the email subject and content
        if(actiune == "rezerva")
        {
        message.setSubject("Rezervare creata");
        message.setText(
        		"Rezervarea cu codul "+codRezervare+" a fost creată cu succes!\n"
        		+ "Mai jos găsiți detaliile rezervării.\n"+"Oraș de plecare:"+orasPlecare+
        		"\nOraș de destinație:"+orasDestinatie+"\nDată de plecare:"+dataPlecare+
        		"\nDată de întoarcere:"+dataIntoarcere+"\nTip bilet:Clasa"+tipBilet+"\nPreț bilet:"+pret+" RON\n"
        		+"Vă multumim ca ați apelat la serviciile noastre!"
        				);
        }
        else if(actiune == "modifica")
        {
        	message.setSubject("Rezervare modificată!");
            message.setText(
            		"Rezervarea a fost modificată cu succes!\n"
            		+ "Noul cod de rezervare este "+codRezervare+"\nMai jos gasiti noile detalii ale rezervării.\n"+"Oraș de plecare:"+orasPlecare+
            		"\nOraș de destinație:"+orasDestinatie+"\nDată de plecare:"+dataPlecare+
            		"\nDată de întoarcere:"+dataIntoarcere+"\nTip bilet:Clasa"+tipBilet+"\nPreț bilet:"+pret+" RON\n"
            		+"Vă multumim ca ați apelat la serviciile noastre!"
            				);
        }
        else if(actiune == "anuleaza" )
        {
        	message.setSubject("Rezervare anulată!");
            message.setText(
            		"Rezervarea cu codul "+codRezervare+" a fost anulată cu succes!\n"
            		+ "Mai jos găsiți detaliile rezervării.\n"+"Oraș de plecare:"+orasPlecare
            		+ "\nOraș de destinație:"+orasDestinatie+"\nDată de plecare:"+dataPlecare
            		+ "\nDată de întoarcere:"+dataIntoarcere+"\nTip bilet:Clasa"+tipBilet+"\nPreț bilet:"+pret+" RON\n"
            		+ "Vă multumim ca ați apelat la serviciile noastre!");
        }

        // Send the email
        Transport.send(message);

    } catch (MessagingException e) {
        e.printStackTrace();
    }
}
}
