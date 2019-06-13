package application;

public class Tickets {

	Seances laseance;
	Clients leclient;
	Cartesbancaires lacarte;
	private int IDTICKET;
	private String tempsTicket;
	private int CodeBarreTicket;
	private double PrixTicket;

	public Tickets(Seances laseance, Clients leclient, Cartesbancaires lacarte,  double prixTicket) {
		super();
		this.laseance = laseance;
		this.leclient = leclient;
		this.lacarte = lacarte;
		PrixTicket = prixTicket;
	}
	
	public Tickets(Seances laseance, Clients leclient, Cartesbancaires lacarte, int iDTICKET, String temps, int codeBarreTicket, double prixTicket) {
		super();
		this.laseance = laseance;
		this.leclient = leclient;
		this.lacarte = lacarte;
		IDTICKET = iDTICKET;
		tempsTicket = temps;
		CodeBarreTicket = codeBarreTicket;
		PrixTicket = prixTicket;
	}

	public Tickets() {
		// TODO Auto-generated constructor stub
	}

	public int getIDTICKET() {
		return this.IDTICKET;
	}

	public void setIDTICKET(int value) {
		this.IDTICKET = value;
	}

	public String getTempsTicket() {
		return tempsTicket;
	}

	public void setTempsTicket(String tempsTicket) {
		this.tempsTicket = tempsTicket;
	}

	public int getCodeBarreTicket() {
		return this.CodeBarreTicket;
	}

	public void setCodeBarreTicket(int value) {
		this.CodeBarreTicket = value;
	}

	public double getPrixTicket() {
		return this.PrixTicket;
	}

	public void setPrixTicket(double value) {
		this.PrixTicket = value;
	}
	public Seances getLaseance() {
		return laseance;
	}

	public void setLaseance(Seances laseance) {
		this.laseance = laseance;
	}

	public Clients getLeclient() {
		return leclient;
	}

	public void setLeclient(Clients leclient) {
		this.leclient = leclient;
	}

	public Cartesbancaires getLacarte() {
		return lacarte;
	}

	public void setLacarte(Cartesbancaires lacarte) {
		this.lacarte = lacarte;
	}
}
