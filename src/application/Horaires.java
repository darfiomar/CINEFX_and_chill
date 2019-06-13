package application;

import java.util.*;

public class Horaires {

	Collection<Seances> lesseancesh;
	private int IDHORAIRE;
	private String Horaire;

	public Horaires(int iDHORAIRE, String horaire) {
		super();
		IDHORAIRE = iDHORAIRE;
		Horaire = horaire;
	}

	public Horaires() {
		// TODO Auto-generated constructor stub
	}

	public int getIDHORAIRE() {
		return this.IDHORAIRE;
	}

	public void setIDHORAIRE(int value) {
		this.IDHORAIRE = value;
	}

	public String getHoraire() {
		return this.Horaire;
	}

	public void setHoraire(String value) {
		this.Horaire = value;
	}
}
