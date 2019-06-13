package application;

import java.util.*;

public class Clients {

	Collection<Tickets> lesticketscl;
	private int IDCLIENT;
	private int ForfaitAbonne;
	private double fraisabonnements;
	private String EmailClient;
	private String FinAbonnement;
	private String NomClient;
	private String PrenomClient;
	private String AdresseAbonne;
	private String PhoneAbonne;
	private int NAchats;
	private boolean FlagAbonne;
	
	public Clients(int iDCLIENT, int forfaitAbonne, String emailClient, String finAbonnement, String nomClient,
			String prenomClient, String adresseAbonne, String phoneAbonne, int nAchats, boolean flagAbonne, double frais) {
		super();
		IDCLIENT = iDCLIENT;
		fraisabonnements = frais;
		ForfaitAbonne = forfaitAbonne;
		EmailClient = emailClient;
		FinAbonnement = finAbonnement;
		NomClient = nomClient;
		PrenomClient = prenomClient;
		AdresseAbonne = adresseAbonne;
		PhoneAbonne = phoneAbonne;
		NAchats = nAchats;
		FlagAbonne = flagAbonne;
	}

	public Clients() {
		// TODO Auto-generated constructor stub
	}

	public Clients(String email, String nom, String prenom) {
		// TODO Auto-generated constructor stub
		EmailClient = email;
		NomClient = nom;
		PrenomClient = prenom;
	}

	public int getIDCLIENT() {
		return this.IDCLIENT;
	}

	public void setIDCLIENT(int value) {
		this.IDCLIENT = value;
	}

	public int getForfaitAbonne() {
		return this.ForfaitAbonne;
	}

	public void setForfaitAbonne(int value) {
		this.ForfaitAbonne = value;
	}

	public String getEmailClient() {
		return this.EmailClient;
	}

	public void setEmailClient(String value) {
		this.EmailClient = value;
	}

	public String getFinAbonnement() {
		return this.FinAbonnement;
	}

	public void setFinAbonnement(String value) {
		this.FinAbonnement = value;
	}

	public String getNomClient() {
		return this.NomClient;
	}

	public void setNomClient(String value) {
		this.NomClient = value;
	}

	public String getPrenomClient() {
		return this.PrenomClient;
	}

	public void setPrenomClient(String value) {
		this.PrenomClient = value;
	}

	public String getAdresseAbonne() {
		return this.AdresseAbonne;
	}

	public void setAdresseAbonne(String value) {
		this.AdresseAbonne = value;
	}

	public String getPhoneAbonne() {
		return this.PhoneAbonne;
	}

	public void setPhoneAbonne(String value) {
		this.PhoneAbonne = value;
	}

	public int getNAchats() {
		return this.NAchats;
	}

	public void setNAchats(int value) {
		this.NAchats = value;
	}

	public boolean isFlagAbonne() {
		return this.FlagAbonne;
	}

	public void setFlagAbonne(boolean value) {
		this.FlagAbonne = value;
	}

	public double getFraisabonnements() {
		return fraisabonnements;
	}

	public void setFraisabonnements(double fraisabonnements) {
		this.fraisabonnements = fraisabonnements;
	}
	
}
