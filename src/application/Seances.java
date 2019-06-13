package application;

import java.util.*;

public class Seances {

	Films lefilm;
	Collection<Tickets> lesticketss;
	Administrateurs ladmins;
	Salles lasalle;
	Dates ladate;
	Horaires lhoraire;
	private int IDSEANCE;
	private double PrixSeance;
	private int NReservation;

	

	public Seances(int iDSEANCE, Films lefilm, Administrateurs ladmins, Salles lasalle, Dates ladate, Horaires lhoraire, double prixSeance, int nReservation) {
		//super();
		this.lefilm = lefilm;
		this.ladmins = ladmins;
		this.lasalle = lasalle;
		this.ladate = ladate;
		this.lhoraire = lhoraire;
		IDSEANCE = iDSEANCE;
		PrixSeance = prixSeance;
		NReservation = nReservation;
	}

	public Seances() {
		// TODO Auto-generated constructor stub
	}

	public int getIDSEANCE() {
		return this.IDSEANCE;
	}

	public void setIDSEANCE(int value) {
		this.IDSEANCE = value;
	}

	public double getPrixSeance() {
		return this.PrixSeance;
	}

	public void setPrixSeance(double value) {
		this.PrixSeance = value;
	}

	public int getNReservation() {
		return this.NReservation;
	}

	public void setNReservation(int value) {
		this.NReservation = value;
	}

	public Films getLefilm() {
		return lefilm;
	}

	public void setLefilm(Films lefilm) {
		this.lefilm = lefilm;
	}

	public Administrateurs getLadmins() {
		return ladmins;
	}

	public void setLadmins(Administrateurs ladmins) {
		this.ladmins = ladmins;
	}

	public Salles getLasalle() {
		return lasalle;
	}

	public void setLasalle(Salles lasalle) {
		this.lasalle = lasalle;
	}

	public Dates getLadate() {
		return ladate;
	}

	public void setLadate(Dates ladate) {
		this.ladate = ladate;
	}

	public Horaires getLhoraire() {
		return lhoraire;
	}

	public void setLhoraire(Horaires lhoraire) {
		this.lhoraire = lhoraire;
	}
	
}
