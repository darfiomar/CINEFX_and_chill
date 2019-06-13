package controllers;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Subscribemailer {
	private String to;
	private String subject = "CINEFX : ";
	private String body;
	final String username = ""; // gmail sender here
	final String password = ""; // gmail password here
	Properties props;
	Session session;
	private String htmlheader = "<!DOCTYPE html><html><head><title>CINEFX</title></head><body>";
	private String htmlfooter = "</body></html>";
	public Subscribemailer(String to, String subject, String htmlcontent) {
		//super();
		this.to = to;
		this.subject += subject;
		this.body = htmlheader+htmlcontent+htmlfooter;
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
	
	public void sendemail() {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("noreply.cinefx@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.to));
			message.setSubject(this.subject);
			message.setContent(body, "text/html");
			Transport.send(message);
			System.out.println("Subscription mail sent.");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}