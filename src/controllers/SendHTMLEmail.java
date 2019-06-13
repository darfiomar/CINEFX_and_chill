package controllers;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendHTMLEmail {
	private String to;
	private String subject;
	private String htmlstring;
	private String filename;
	final String username = ""; // gmail sender here
	final String password = ""; // gmail password here
	Properties props;
	Session session;

	public SendHTMLEmail(String to, String subject, String htmlstring, String filename) {
		//super();
		this.to = to;
		this.subject = subject;
		this.htmlstring = htmlstring;
		this.filename = filename;
		props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	}
	
	public void sendticket() {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("noreply.cinefx@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.to));
			message.setSubject(this.subject);
			
			// Create the message part
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         messageBodyPart.setText("Ci-joint, vous trouverez le ticket du film que vous venez d'acheter.");
	         messageBodyPart.setContent(htmlstring, "text/html");
	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         String file = "C:\\CINEFXTickets\\"+filename+".html";
	         DataSource source = new FileDataSource(file);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename+".html");
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         message.setContent(multipart);
			Transport.send(message);

			System.out.println("Ticket sent by EMAIL.");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}