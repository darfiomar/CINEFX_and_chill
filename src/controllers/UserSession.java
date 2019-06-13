package controllers;

public final class UserSession {

	private static UserSession instance;
	private int userID;
	private String userMail;
	private String userNom;
	private String userPrenom;
	private boolean rememberme;

	private UserSession(int id,String email,String nom,String prenom, boolean rememberme) {
		this.userID = id;
		this.userMail = email;
		this.userNom = nom;
		this.userPrenom = prenom;
		this.rememberme = rememberme;
	}

	public static UserSession setInstance(int id,String email,String nom,String prenom, boolean rememberme) {
		if (instance == null) {
			instance = new UserSession(id,email,nom,prenom,rememberme);
		}
		return instance;
	}

	public static UserSession getInstance() {
		return instance;
	}

	public String getUserMail() {
		return userMail;
	}

	public String getUserNom() {
		return userNom;
	}

	public String getUserPrenom() {
		return userPrenom;
	}

	public int getUserID() {
		return userID;
	}

	public boolean isRememberme() {
		return rememberme;
	}

	public static void cleanUserSession() {
		instance = null;
	}
}