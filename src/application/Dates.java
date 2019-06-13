package application;

import java.util.*;

public class Dates {

	Collection<Seances> lesseancesd;
	private int IDDATES;
	private String Dates;

	public Dates(int iDDATES, String dates) {
		super();
		IDDATES = iDDATES;
		Dates = dates;
	}

	public Dates() {
		// TODO Auto-generated constructor stub
	}

	public int getIDDATES() {
		return this.IDDATES;
	}

	public void setIDDATES(int value) {
		this.IDDATES = value;
	}

	public String getDates() {
		return this.Dates;
	}

	public void setDates(String value) {
		this.Dates = value;
	}
}
