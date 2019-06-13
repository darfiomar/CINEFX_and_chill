package application;

import java.util.*;

public class Cartesbancaires {

	Collection<Tickets> lesticketsc;
	private int IDCARTE;
	private String NumeroCarte;
	private String DECarte;
	private int CryptoCarte;
	private double SoldeCarte;

	public Cartesbancaires(int iDCARTE, String numeroCarte, String dECarte, int cryptoCarte, double soldeCarte) {
		super();
		IDCARTE = iDCARTE;
		NumeroCarte = numeroCarte;
		DECarte = dECarte;
		CryptoCarte = cryptoCarte;
		SoldeCarte = soldeCarte;
	}

	public Cartesbancaires() {
		// TODO Auto-generated constructor stub
	}

	public int getIDCARTE() {
		return this.IDCARTE;
	}

	public void setIDCARTE(int value) {
		this.IDCARTE = value;
	}

	public String getNumeroCarte() {
		return this.NumeroCarte;
	}

	public void setNumeroCarte(String value) {
		this.NumeroCarte = value;
	}

	public String getDECarte() {
		return this.DECarte;
	}

	public void setDECarte(String value) {
		this.DECarte = value;
	}

	public int getCryptoCarte() {
		return this.CryptoCarte;
	}

	public void setCryptoCarte(int value) {
		this.CryptoCarte = value;
	}

	public double getSoldeCarte() {
		return this.SoldeCarte;
	}

	public void setSoldeCarte(double value) {
		this.SoldeCarte = value;
	}
	public boolean payer(double prix) {
		if(this.SoldeCarte > prix) return true;
		else return false;
	}
}
