package application;

import java.util.*;

public class Pays {

	Collection<Films> lesfilmsp;
	private int IDPAYS;
	private String Pays;
	private String CodePays;

	public Pays(int iDPAYS, String pays, String codePays) {
		super();
		IDPAYS = iDPAYS;
		Pays = pays;
		CodePays = codePays;
	}

	public Pays() {}

	public int getIDPAYS() {
		return this.IDPAYS;
	}

	public void setIDPAYS(int value) {
		this.IDPAYS = value;
	}

	public String getPays() {
		return this.Pays;
	}

	public void setPays(String value) {
		this.Pays = value;
	}

	public String getCodePays() {
		return this.CodePays;
	}

	public void setCodePays(String value) {
		this.CodePays = value;
	}
}
