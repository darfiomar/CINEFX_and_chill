package application;

import java.util.*;

public class Langues {

	Collection<Films> lesfilmsl;
	private int IDLANGUE;
	

	private String Langue;
	private String Iso6391;

	public Langues(int iDLANGUE, String langue, String iso6391) {
		super();
		IDLANGUE = iDLANGUE;
		Langue = langue;
		Iso6391 = iso6391;
	}
	public Langues() {}
	public int getIDLANGUE() {
		return this.IDLANGUE;
	}

	public void setIDLANGUE(int value) {
		this.IDLANGUE = value;
	}

	public String getLangue() {
		return this.Langue;
	}

	public void setLangue(String value) {
		this.Langue = value;
	}

	public String getIso6391() {
		return this.Iso6391;
	}

	public void setIso6391(String value) {
		this.Iso6391 = value;
	}
}
