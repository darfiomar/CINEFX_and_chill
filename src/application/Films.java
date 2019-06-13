package application;

import java.util.*;

public class Films {

	Pays lepays;
	Langues lalangue;
	Set<Seances> lesseancesf = new HashSet<Seances>();
	Administrateurs ladminf;
	private int IDFILM;
	private String NomFilm;
	private String SynopsisFilm;
	private String TrailerFilm;
	private String ImageFilm;
	private String ActeursFilm;
	private String RealisateurFilm;
	private int DureeFilm;
	private String GenresFilm;
	private double ImdbFilm;
	private int ReleaseFilm;
	private boolean projectable;

	public Films(int iDFILM, String nomFilm, String synopsisFilm, String trailerFilm, String imageFilm,
			String acteursFilm, String realisateurFilm, int dureeFilm, String genresFilm, double imdbFilm,
			int releaseFilm, boolean projectable, Pays pays, Langues langue, Administrateurs admin) {
		super();
		IDFILM = iDFILM;
		NomFilm = nomFilm;
		SynopsisFilm = synopsisFilm;
		TrailerFilm = trailerFilm;
		ImageFilm = imageFilm;
		ActeursFilm = acteursFilm;
		RealisateurFilm = realisateurFilm;
		DureeFilm = dureeFilm;
		GenresFilm = genresFilm;
		ImdbFilm = imdbFilm;
		ReleaseFilm = releaseFilm;
		this.projectable = projectable;
		this.lepays = pays;
		this.lalangue = langue;
		this.ladminf = admin;
	}

	public Films() {
	}

	public int getIDFILM() {
		return this.IDFILM;
	}

	public void setIDFILM(int value) {
		this.IDFILM = value;
	}

	public String getNomFilm() {
		return this.NomFilm;
	}

	public void setNomFilm(String value) {
		this.NomFilm = value;
	}

	public String getSynopsisFilm() {
		return this.SynopsisFilm;
	}

	public void setSynopsisFilm(String value) {
		this.SynopsisFilm = value;
	}

	public String getTrailerFilm() {
		return this.TrailerFilm;
	}

	public void setTrailerFilm(String value) {
		this.TrailerFilm = value;
	}

	public String getImageFilm() {
		return this.ImageFilm;
	}

	public void setImageFilm(String value) {
		this.ImageFilm = value;
	}

	public String getActeursFilm() {
		return this.ActeursFilm;
	}

	public void setActeursFilm(String value) {
		this.ActeursFilm = value;
	}

	public String getRealisateurFilm() {
		return this.RealisateurFilm;
	}

	public void setRealisateurFilm(String value) {
		this.RealisateurFilm = value;
	}

	public int getDureeFilm() {
		return this.DureeFilm;
	}

	public void setDureeFilm(int value) {
		this.DureeFilm = value;
	}

	public String getGenresFilm() {
		return this.GenresFilm;
	}

	public void setGenresFilm(String value) {
		this.GenresFilm = value;
	}

	public double getImdbFilm() {
		return this.ImdbFilm;
	}

	public void setImdbFilm(double value) {
		this.ImdbFilm = value;
	}

	public int getReleaseFilm() {
		return this.ReleaseFilm;
	}

	public void setReleaseFilm(int value) {
		this.ReleaseFilm = value;
	}

	public boolean isProjectable() {
		return this.projectable;
	}

	public void setProjectable(boolean value) {
		this.projectable = value;
	}

	public Pays getLepays() {
		return lepays;
	}

	public void setLepays(Pays lepays) {
		this.lepays = lepays;
	}

	public Langues getLalangue() {
		return lalangue;
	}

	public void setLalangue(Langues lalangue) {
		this.lalangue = lalangue;
	}

	public Administrateurs getLadminf() {
		return ladminf;
	}

	public void setLadminf(Administrateurs ladminf) {
		this.ladminf = ladminf;
	}

	public Set<Seances> getLesseancesf() {
		return lesseancesf;
	}

	public void setLesseancesf(Set<Seances> lesseances) {
		this.lesseancesf = new HashSet<Seances>(lesseances);
	}

	public void addSeance(Seances seance) {
		if (!lesseancesf.contains(seance)) lesseancesf.add(seance);
	}

	public void removeSeance(Seances seance) {
		this.lesseancesf.remove(seance);
	}

}
