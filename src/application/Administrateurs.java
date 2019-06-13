package application;

import java.util.*;

public class Administrateurs {

	Collection<Seances> lesseancesa;
	Collection<Films> lesfilmsa;
	private int IDADMIN;
	

	private String NomAdmin;
	private String PrenomAdmin;
	private String EmailAdmin;
	private String PasswordAdmin;
	private String PhoneAdmin;
	public Administrateurs(int iDADMIN, String nomAdmin, String prenomAdmin, String emailAdmin, String passwordAdmin,
			String phoneAdmin) {
		super();
		IDADMIN = iDADMIN;
		NomAdmin = nomAdmin;
		PrenomAdmin = prenomAdmin;
		EmailAdmin = emailAdmin;
		PasswordAdmin = passwordAdmin;
		PhoneAdmin = phoneAdmin;
	}
	public Administrateurs() {}
	public int getIDADMIN() {
		return this.IDADMIN;
	}

	public void setIDADMIN(int value) {
		this.IDADMIN = value;
	}

	public String getNomAdmin() {
		return this.NomAdmin;
	}

	public void setNomAdmin(String value) {
		this.NomAdmin = value;
	}

	public String getPrenomAdmin() {
		return this.PrenomAdmin;
	}

	public void setPrenomAdmin(String value) {
		this.PrenomAdmin = value;
	}

	public String getEmailAdmin() {
		return this.EmailAdmin;
	}

	public void setEmailAdmin(String value) {
		this.EmailAdmin = value;
	}

	public String getPasswordAdmin() {
		return this.PasswordAdmin;
	}

	public void setPasswordAdmin(String value) {
		this.PasswordAdmin = value;
	}

	public String getPhoneAdmin() {
		return this.PhoneAdmin;
	}

	public void setPhoneAdmin(String value) {
		this.PhoneAdmin = value;
	}
}
