package application;

import java.util.*;

public class Salles {

	Collection<Seances> lesseancess;
	private int IDSALLE;
	private String Salle;
	private int Nplaces;

	public Salles(int iDSALLE, String salle, int nplaces) {
		super();
		IDSALLE = iDSALLE;
		Salle = salle;
		Nplaces = nplaces;
	}

	public Salles() {
		// TODO Auto-generated constructor stub
	}

	public int getIDSALLE() {
		return this.IDSALLE;
	}

	public void setIDSALLE(int value) {
		this.IDSALLE = value;
	}

	public String getSalle() {
		return this.Salle;
	}

	public void setSalle(String value) {
		this.Salle = value;
	}

	public int getNplaces() {
		return this.Nplaces;
	}

	public void setNplaces(int value) {
		this.Nplaces = value;
	}
}
