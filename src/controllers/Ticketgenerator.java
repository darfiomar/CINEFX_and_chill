package controllers;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class Ticketgenerator {
	private String to;
	private String subject= "CINEFX TICKET : ";
	private String htmledittime;
	private String htmlprice;
	private String htmlbarcode;
	private String htmlsalle;
	private String htmlid;
	private String htmlmovietitle;
	private String htmlhoraire;
	private String htmldate;
	File htmlTemplateFile;
	SendHTMLEmail mail;
	public Ticketgenerator(String destinataire,String htmledittime, String htmlprice, String htmlbarcode,
			String htmlsalle, String htmlid, String htmlmovietitle, String htmlhoraire, String htmldate) {
		//super();
		this.to = destinataire;
		this.htmledittime = htmledittime;
		this.htmlprice = htmlprice;
		this.htmlbarcode = htmlbarcode;
		this.htmlsalle = htmlsalle;
		this.htmlid = htmlid;
		this.htmlmovietitle = htmlmovietitle;
		this.htmlhoraire = htmlhoraire;
		this.htmldate = htmldate;
		this.htmlTemplateFile = new File(getClass().getResource("../resources/view/TicketTemplate.html").toString().trim().substring(6));
	}

	public void genrate() throws IOException {
		try {
		String ticketfilename;
		String htmlString = FileUtils.readFileToString(htmlTemplateFile,"utf-8");
		htmlString = htmlString.replace("htmledittime", this.htmledittime);
		htmlString = htmlString.replace("htmlprice", this.htmlprice);
		htmlString = htmlString.replace("htmlbarcode", this.htmlbarcode);
		htmlString = htmlString.replace("htmlsalle", this.htmlsalle);
		htmlString = htmlString.replace("htmlid", this.htmlid);
		htmlString = htmlString.replace("htmlmovietitle", this.htmlmovietitle);
		htmlString = htmlString.replace("htmlhoraire", this.htmlhoraire);
		htmlString = htmlString.replace("htmldate", this.htmldate);
		ticketfilename = "C:\\CINEFXTickets\\"+this.htmlid+".html";
		File newHtmlFile = new File(ticketfilename);
		FileUtils.writeStringToFile(newHtmlFile, htmlString,"utf-8");
		this.subject += this.htmlmovietitle;
		String body = "Reception de votre ticket : Ci-joint, vous trouverez le ticket du film que vous venez d'acheter.<br>"
				+ "Détails de votre ticket :<br>"
				+ " > Ticket : "+this.htmlid+"<br>"
				+ " > Titre du film : <strong>"+this.htmlmovietitle+"</strong><br>"
				+ " > Date de la séance : <strong>"+this.htmldate+"</strong><br>"
				+ " > Horaire de la séance : <strong>"+this.htmlhoraire+"</strong><br>"
				+ " > Salle de la séance : <strong>"+this.htmlsalle+"</strong><br>"
				+ " > Montant payé : <strong>"+this.htmlprice+"DH</strong><br>"
				+ " > Code-barre  : "+this.htmlbarcode+"<br>"
				+ " > Edité le : "+this.htmledittime+"<br>"
				+ "CINEFX vous souhaite une visualisation agréable!";
		mail = new SendHTMLEmail(this.to,this.subject,body,this.htmlid);
		mail.sendticket();
		}catch(IOException e) {System.out.println(e);}
	}
	
	
}
